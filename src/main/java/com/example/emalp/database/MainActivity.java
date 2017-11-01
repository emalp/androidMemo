package com.example.emalp.database;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DBHelper mydb;
    private Button login;
    private Button register;
    public TextView password;
    private Button delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mydb = new DBHelper(this);
        login = (Button)findViewById(R.id.loginBtn);
        register = (Button)findViewById(R.id.register);
        password = (TextView)findViewById(R.id.editText2);
      //  delete = (Button)findViewById(R.id.dltBtn);


        //String x = mydb.checkDatabase();
        //Toast.makeText(getApplicationContext(), "The name of db is : " + x, Toast.LENGTH_LONG).show();


        login.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v) {
                // TODO Auto-generated method stub

               // Toast.makeText(getApplicationContext(), "You clicked the login button! yay", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),loginUser.class);

                String spclpas = password.getText().toString();

                Cursor cursor = mydb.login(spclpas);

                String data= "";
                Bundle dataBundle= new Bundle();

                if(cursor.getCount() == 0){
                    Toast.makeText(getApplicationContext(), "No unique password found", Toast.LENGTH_SHORT).show();
                }else{
                    for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                        data += cursor.getString(cursor.getColumnIndex("memo"));
                    }
                    cursor.close();
                    dataBundle.putString("Memo", "\n" + data);

                   intent.putExtras(dataBundle);
                   startActivity(intent);
                }

            }
        });

        register.setOnClickListener(new View.OnClickListener(){
            public void onClick(View c){


                Intent intent = new Intent(getApplicationContext(),allMemo.class);

                startActivity(intent);
            }
        });

//        delete.setOnClickListener(new View.OnClickListener(){
//            String spclpas = password.getText().toString();
//            public void onClick(View c){
//
//                int x = mydb.deleteMemo(spclpas);
//                if(x<0){
//                    Toast.makeText(getApplicationContext(), "Could not delete that memo!", Toast.LENGTH_SHORT).show();
//                }
//                else{
//                    Toast.makeText(getApplicationContext(), "Memo deleted successfully!", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

    }
}
