package com.example.rippp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {

    AnimationDrawable animation;
    ImageView imageViewLook;
    MediaPlayer sonido;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnimationLook();
    }

    /**
     *Este evento se lanzara cada vez que el usuario toque la pantalla(candado)
     * Para evitar el bloqueo de la app en el hilo principal, trabajamos
     * con la clase AnsyncTask para ejecutar tareas en segundo plano
     * @param event objeto de la clase MotionEvent
     * @return int
     */
    public boolean onTouchEvent(MotionEvent event){
        if (event.getAction()== MotionEvent.ACTION_DOWN){
            LoadXmlTask task = new LoadXmlTask();
            task.execute();
        }
        return super.onTouchEvent(event);
    }

    /*===================== INTENT ================*/

    /**
     * Funciones que lanzaran los Intent para mostrar la pareja de imagenes
     */
    public void   getImageAula(){
        Intent intent = new Intent(MainActivity.this,AulaActivity.class);
        startActivity(intent);
    }
    public void   getImageJulio(){
        Intent intent = new Intent(MainActivity.this,JulioActivity.class);
        startActivity(intent);
    }
    public void   getImageMonica(){
        Intent intent = new Intent(MainActivity.this,MonicaActivity.class);
        startActivity(intent);
    }
    public void   getImageOriol(){
        Intent intent = new Intent(MainActivity.this,OriolActivity.class);
        startActivity(intent);
    }
    public void   getImageUri(){
        Intent intent = new Intent(MainActivity.this,UriActivity.class);
        startActivity(intent);
    }

        /*===================== FUNCION ================*/
    /**
     *
     */
    public void  AnimationLook ( ){
        //enlazamos vistas
        imageViewLook = (ImageView) findViewById(R.id.imageViewlook);
        int duration = 200;
        BitmapDrawable frame1 = (BitmapDrawable) getResources().getDrawable(R.drawable.lock);
        BitmapDrawable frame2 = (BitmapDrawable) getResources().getDrawable(R.drawable.unlock);
        animation = new AnimationDrawable();

        animation.addFrame(frame1, duration);
        animation.addFrame(frame2, duration);

        animation.setOneShot(false);
        imageViewLook.setBackgroundDrawable(animation);

    }
    /*===================== ANSYNCTASK ================*/
    // Tasca asincrona per carregar el fitxer XML en segon pla (background)
    private class LoadXmlTask extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... params) {
            auxSecondPlane();
            return null;
        }
    }

    public void auxSecondPlane(){
        //numero aleatorio para pausar el hilo donde se ejecuta la animación
        final int numero = (int) (Math.random() *1500) + 1000;
        //generamos un numero aleatorio en un rango >=0 y 5 para mostrar los intents
        final int numeImage = (int) (Math.random() * 5);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                animation.start();
                sonido.start();
            }
        });
        try {
            Thread.sleep(numero);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        animation.stop();
        sonido.pause();
        //lanzamos los intent segun el numero aleatorio
        switch (numeImage){
            case 0:getImageAula();
                break;
            case 1: getImageJulio();
                break;
            case 2: getImageMonica();
                break;
            case 3: getImageOriol();
                break;
            case 4: getImageUri();
                break;
            default:
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
        }
    }

    /*================= SONS ===============*/

    private void playMediaPlayer(){
        sonido = MediaPlayer.create(MainActivity.this,R.raw.intro_wd);
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
    protected void onPause(){
        sonido.pause();
        super.onPause();

    }

}//end clase
