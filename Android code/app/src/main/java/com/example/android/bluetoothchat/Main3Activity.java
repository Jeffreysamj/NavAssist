package com.example.android.bluetoothchat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.common.activities.SampleActivityBase;

public class Main3Activity extends SampleActivityBase {

    Button save,view;
    EditText et;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        save = (Button) findViewById(R.id.save);
        view= (Button) findViewById(R.id.view);
        et= (EditText) findViewById(R.id.editText);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str=et.getText().toString();
                DBHelper dbh=new DBHelper(Main3Activity.this);
                if(dbh.addData(str)){
                    et.setText("");
                    Toast.makeText(Main3Activity.this, "Inserted Successful", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(Main3Activity.this, "Insertion Failed", Toast.LENGTH_SHORT).show();
                }
                /*Intent intent = new Intent();
                intent.putExtra("key", str);
                setResult(RESULT_OK, intent);
                finish();*/
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in =new Intent(Main3Activity.this,ViewActivity.class);
                startActivity(in);
            }
        });

    }
}
