package com.example.emalp.database;

/**
 * Created by Emalp on 30/10/2017.
 */
// THIS CLASS IS THE REGISTER CLASS, HERE YOU REGISTER THE MEMOS
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

// THIS CLASS IS THE REGISTER CLASSS
public class allMemo extends AppCompatActivity {

    Button create;
    Button home;
    EditText uniquePswd;
    EditText memo;
    DBHelper mydb;

    public void onCreate(Bundle savedInstanceState){
        mydb = new DBHelper(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.allmemo);

        create = (Button)findViewById(R.id.create);
        uniquePswd = (EditText)findViewById(R.id.upswd);
        memo = (EditText)findViewById(R.id.memofield);
        home = (Button)findViewById(R.id.gohome);

        create.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){

                String spclpas = uniquePswd.getText().toString();
                String mem = memo.getText().toString();

                long created = mydb.register(spclpas, mem);
                if(created > 0){
                    Toast.makeText(getApplicationContext(), "The unique password and memo was registered!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Some error happened while registering!", Toast.LENGTH_SHORT).show();
                }


            }

        });

        home.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }

        });


    }
}
