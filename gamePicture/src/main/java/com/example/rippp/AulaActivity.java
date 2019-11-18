package com.example.rippp;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


/**
 * Created by GraceToa on 22/3/16.
 */
public class AulaActivity extends Activity implements View.OnClickListener {

    SoundPool soundPool;
    private int idlaser;
    boolean loaded = false;
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
        aula.setBackgroundResource(R.drawable.animation_aula);
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


    private void initializeSoundPool(){
        //inicializamos la clase SoundPool indicamos 3 parametros ,máximo de reproducciones simultáneas
        //tipo de stream de audio,y la calidad de reproducción
        soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC,0);
        //cada una de las pistas se cargan mediante el método load()
        idlaser = soundPool.load(getApplicationContext(),R.raw.intro_wd,0);

        AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        float curVolumen = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        float maxVolumen = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        float leftVolumen = curVolumen / maxVolumen;//volumen para canal izquierdo
        float rightVolumen = curVolumen / maxVolumen;//volumen para canal derecho
        int priority = 1;//(0=la prioridad mas baja)
        int no_loop = 2;// el numero de repeticiones(-1=siempre,0=solo una vez, 1=repetir una vez)
        float normal_playback_rate = 1f;//velocidad de reproducción 1,0=frecuencia original
        //2,0 doble de rápido, 0,5 velocidad media

        /*El metodo play() permite reproducir una pista*/
        soundPool.play(idlaser, leftVolumen, rightVolumen, priority, no_loop,normal_playback_rate);

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
    //finaliza la Aula Actividad y regresa a la principal
    @Override
    public void onClick(View v) {
        finish();
    }

    /*================= SONS ===============*/

    private void playMediaPlayer(){
        sonido = MediaPlayer.create(AulaActivity.this,R.raw.good_night);
        sonido.start();
    }
}//end class

