package com.example.emalp.database;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Emalp on 30/10/2017.
 */
// THIS CLASS IS THE MAIN MEMO DISPLAY CLASS, YOU VIEW THE MEMOS HERE
public class loginUser extends AppCompatActivity {

    TextView memoview;
    Button home;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_login);

        memoview = (TextView)findViewById(R.id.memotext);

        Bundle data = getIntent().getExtras();
        String memo = data.getString("Memo");

        memoview.append("\n"+memo);



    }
}
