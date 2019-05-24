package com.example.ex3;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText etuid,etpwd;
    Button blogin,bcancel;
    SQLiteDatabase db;
    Cursor c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        etuid=(EditText)findViewById(R.id.editTextUID);
        etpwd=(EditText)findViewById(R.id.editTextPassword);
        blogin=(Button)findViewById(R.id.loginbutton);
        bcancel=(Button)findViewById(R.id.cancelbutton);


        try{
            db= openOrCreateDatabase("logindb", Context.MODE_PRIVATE, null);
            db.execSQL("CREATE TABLE IF NOT EXISTS logintable(username VARCHAR,password VARCHAR);");
            c=db.rawQuery("SELECT * FROM logintable", null);
            //db.execSQL("delete from logintable");

        }catch(Exception e){
            Toast.makeText(getBaseContext(), "Error:"+e.getMessage().toString(), Toast.LENGTH_LONG).show();
        }

        if(!c.moveToFirst())
        {

            db.close();
            Intent i= new Intent(getBaseContext(),NewUser.class);
            startActivity(i);


        }else
        {
            try
            {

            }catch (Exception e)
            {
                Toast.makeText(getBaseContext(), "Error:"+e.getMessage().toString(), Toast.LENGTH_LONG).show();
            }
        }

        blogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                if( etuid.getText().toString().equals("saravanan") && etpwd.getText().toString().equals("klu"))
                {
                    Intent i= new Intent(getBaseContext(),HomePage.class);
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(getBaseContext(),"Invalid User Name or Password! Try Again!" ,Toast.LENGTH_LONG).show();
                }

            }
        });

        bcancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {   finishActivity(0);
                System.exit(0);

            }
        });


    }
}
