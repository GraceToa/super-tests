package com.example.rippp;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by GraceToa on 23/3/16.
 */
public class OriolActivity extends Activity implements View.OnClickListener{

    MediaPlayer sonido;
    Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        ImageView aula = (ImageView)findViewById(R.id.imageViewAula);
        Button btnBack = (Button) findViewById(R.id.btnBack);

        btnBack.setOnClickListener(this);
        AnimationDrawable myAnimationDrawable
                = (AnimationDrawable)aula.getDrawable();

        //hacemos referencia al fichero drawable/animation que contiene el nombre de las imagenes
        aula.setBackgroundResource(R.drawable.animation_oriol);
        //
        myAnimationDrawable = (AnimationDrawable)aula.getBackground();

        final AnimationDrawable finalMyAnimationDrawable = myAnimationDrawable;
        aula.post(
                new Runnable() {

                    @Override
                    public void run() {
                        finalMyAnimationDrawable.start();
                    }
                });

        //El telefono vibrara por 3000 milisegundos
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(2000);

    }

    @Override
    public void onClick(View v) {
        finish();
    }
    /*================= SONS ===============*/

    private void playMediaPlayer(){
        sonido = MediaPlayer.create(OriolActivity.this, R.raw.zombiesound);
        sonido.start();
    }

    /*================ CICLO DE VIDA ========*/

    /**
     * Estado de la Activity Nos indica que la actividad esta a punto de ser mostrada al usuario
     * se cargara el audio nada mas empezar la actividad, asi se encuentre en segundo plano
     * por haber lanzado otra actividad esta volvera a cargarse cuando se haga back
     */
    protected void onStart(){
        playMediaPlayer();
        super.onStart();
    }

    /**
     * aqui la actividad sera lanzada a segundo plano, porque otra actividad sera
     * lanzada, se detendra animaciones, musica o datos que estaban en ejecución
     */
    @Override
    protected void onStop(){
        sonido.stop();
        super.onStop();

    }
}
