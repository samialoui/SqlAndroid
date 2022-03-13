package com.example.crudandroidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText id = findViewById(R.id.editTextId);
        EditText nom = findViewById(R.id.editTextName);
        EditText adress = findViewById(R.id.editTextAdress);
        Button btAdd = (Button) findViewById(R.id.btnAdd);
        TextView result = findViewById(R.id.textViewResult);

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Connection connection = connectionclass();
                try {
                    if(connection != null){
                        String sqlInsert = "Insert into User_Tab values ('"+nom.getText().toString()+"','"+adress.getText().toString()+"')";
                        Statement st = connection.createStatement();
                        ResultSet res = st.executeQuery(sqlInsert);
                    }
                    else {
                        result.setText("Connection is null");
                    }

                }
                catch (Exception exception){
                    Log.e("Error",exception.getMessage());
                }
            }
        });


    }

@SuppressLint("NewApi")
    public Connection connectionclass(){
        Connection con = null;
        String ip="127.0.0.1",port="1433",databasename="CrudAndroid",username="",password="";
    StrictMode.ThreadPolicy tp = new StrictMode.ThreadPolicy.Builder().permitAll().build();
    StrictMode.setThreadPolicy(tp);

    try {
        Class.forName("net.sourceforge.jtds.jdbc.Driver");
        String connectionUrl = "jdbc:jtds:sqlserver://"+ip+":"+port+";databasename="+databasename+";User="+username+";password="+password+";";
        con = DriverManager.getConnection(connectionUrl);

    }
    catch (Exception exception){
        Log.e("error",exception.getMessage());
    }
    return con;

}
}