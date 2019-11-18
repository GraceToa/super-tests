package com.gracetoa.fragments.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.gracetoa.fragments.Fragments.DataFragment;
import com.gracetoa.fragments.Fragments.DetailFragment;
import com.gracetoa.fragments.R;

import androidx.fragment.app.FragmentActivity;

public class MainActivity extends FragmentActivity implements DataFragment.DataListener {

    private  boolean isMultiPanel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setMultiPanel();
    }

    @Override
    public void sendData(String text) {

        if (isMultiPanel){
            //llamar metodo renderizarText (DetailsFragments) send text
            Toast.makeText(getApplicationContext(),text,Toast.LENGTH_LONG).show();
            DetailFragment detailFragment = (DetailFragment) getSupportFragmentManager().findFragmentById(R.id.detailFragment);
            detailFragment.renderText(text);
        }else {
            Intent intent = new Intent(this, DetailsActivity.class);
            intent.putExtra("text",text);
            startActivity(intent);
        }





    }

    //es para comprobar si el tamaño del layout, en este ejemplo
    //cuando es movil y (land) se verifica que solo tiene en su layout 1 solo fragment
    //entonces se envia a la otra activity
    //cuando es tablet el layout (xlarge y xlarge-land) comprueba estos layouts
    //como tienen 2 fragments es multipanel entonces se despliega la primera opcion del if
    private void setMultiPanel(){
        isMultiPanel = (getSupportFragmentManager().findFragmentById(R.id.detailFragment) != null);
    }
}
