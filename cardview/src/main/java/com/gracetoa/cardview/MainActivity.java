package com.gracetoa.cardview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView texViewTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        texViewTitle = (TextView) findViewById(R.id.textViewTitle);
        texViewTitle.setText("Hello from CardView¡");

    }
}
