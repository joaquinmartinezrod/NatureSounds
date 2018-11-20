package com.joaquinmtz.naturesounds;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;

import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.SimpleCircleButton;
import com.nightonke.boommenu.BoomButtons.TextInsideCircleButton;
import com.nightonke.boommenu.BoomMenuButton;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.joaquinmtz.naturesounds.R.string.campfire;

public class Player extends AppCompatActivity implements OnBMClickListener {



    String[] stringIdiomas = {"es","de","en"};


    @BindView(R.id.imback)
    ImageButton imback;

    @BindView(R.id.imbplaypause)
    ImageButton imbplaypause;

    @BindView(R.id.imbnext)
    ImageButton imbnext;

    @BindView(R.id.imageview)
    ImageView imageView;

    @BindView(R.id.imText)
    TextView imText;

    MediaPlayer player;

    @BindView(R.id.bmb)
    BoomMenuButton bmb;

    int x = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        ButterKnife.bind(this);

        for (int i = 0; i < bmb.getPiecePlaceEnum().pieceNumber(); i++) {
            TextInsideCircleButton.Builder builder = new TextInsideCircleButton.Builder()
                    .listener(new OnBMClickListener() {
                        @Override
                        public void onBoomButtonClick(int index) {
                            // When the boom-button corresponding this builder is clicked.
                            Toast.makeText(Player.this, "Language " + stringIdiomas[index], Toast.LENGTH_SHORT).show();
                            Log.v("OnClick","CLICK EN: BOOOM "+stringIdiomas[index]);
                            updateViews(stringIdiomas[index]);
                        }
                    });
            builder.normalText(stringIdiomas[i]);
            bmb.addBuilder(builder);
            x++;
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        if(player!=null)
            player.release();
    }

    int[] images = {R.mipmap.fire,R.mipmap.jungle,R.mipmap.waves,R.mipmap.waterfall,R.mipmap.rain,R.mipmap.river};
    int[] audios = {R.raw.fire,R.raw.jungle,R.raw.ocean,R.raw.waterfall,R.raw.rain,R.raw.river};

    //String[] titles = {"Fogata","Jungla","Mar","Cascada","Lluvia","Rio"};
    //La linea siguiente es para usar como array de cadenas de diferentes strings

    int[] titles = {R.string.campfire,R.string.jungle,R.string.ocean,R.string.waterfall,R.string.rain,R.string.river};

    int pos = images.length-1;

    @OnClick({R.id.imbnext,R.id.imbplaypause,R.id.imback})
    public void ClickButton(View v){
        switch (v.getId()){
            case R.id.imback:
                pos--;
                pos = (pos<0) ? images.length-1 : pos;
                if(player!=null)
                    player.release();

                player = MediaPlayer.create(this,audios[pos]);
                imageView.setImageResource(images[pos]);
                imText.setText(titles[pos]);
                imbplaypause.setImageResource(R.mipmap.playicon);
                Log.v("OnClick","CLICK EN: BACK");
                break;

            case R.id.imbplaypause:
                if(player!=null) {
                    if (player.isPlaying()) {
                        imbplaypause.setImageResource(R.mipmap.playicon);
                        player.pause();
                    } else {
                        imbplaypause.setImageResource(R.mipmap.pauseicon);
                        player.start();
                    }
                }
                else{
                    player = MediaPlayer.create(this,audios[pos]);
                    imbplaypause.setImageResource(R.mipmap.pauseicon);
                    player.start();
                }
                Log.v("OnClick","CLICK EN: PLAY/PAUSE");
                break;

            case R.id.imbnext:
                pos++;
                pos = (pos==images.length) ? 0 : pos;
                if(player!=null)
                    player.release();

                player = MediaPlayer.create(this,audios[pos]);
                imageView.setImageResource(images[pos]);
                imText.setText(titles[pos]);
                imbplaypause.setImageResource(R.mipmap.playicon);
                Log.v("OnClick","CLICK EN: NEXT");
                break;

            case R.id.bmb:
                break;
        }
    }

    public void onBoomButtonClick(int index){
        Locale locale = new Locale(stringIdiomas[index]);

        Configuration configuration = new Configuration();
        configuration.locale = locale;
        getResources().updateConfiguration(configuration,getResources().getDisplayMetrics());

        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    public void updateViews(String language){
        Context context = LocaleHelper.setLocale(this,language);
        Resources resources = context.getResources();
        imText.setText(titles[pos]);
    }
}
