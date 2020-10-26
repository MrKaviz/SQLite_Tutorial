package com.example.dbhandlingtute;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dbhandlingtute.Database.DBHandler;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText editUser,editPass;
    private TextView textShow;
    private Button select,add,delete,update,signIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editUser = findViewById(R.id.userName);
        editPass = findViewById(R.id.password);

        textShow = findViewById(R.id.showText);

        select = findViewById(R.id.buttonSelect);
        add = findViewById(R.id.buttonAdd);
        delete = findViewById(R.id.buttonDelete);
        update = findViewById(R.id.buttonSign);
    }

    public void addData(View view){
        DBHandler dbHandler = new DBHandler(this);
        dbHandler.addInfo(editUser.getText().toString(), editPass.getText().toString());

        List unames = dbHandler.readAllInfo("user");
        textShow.setText("User Names are "+unames.toString());

    }

    public void viewAll(View view){
        DBHandler dbHandler = new DBHandler(this);

        List unames = dbHandler.readAllInfo("user");
        //Toast.makeText(this,unames.toString(),Toast.LENGTH_SHORT).show();

        textShow.setText("User Names are "+unames.toString());
    }

    public void deleteData(View view){
        DBHandler dbHandler = new DBHandler(this);
        List unames = dbHandler.readAllInfo("user");
        dbHandler.deleteInfo(editUser.getText().toString());
        textShow.setText("User Names are "+unames.toString());
    }

    public void updateData(View view){
        DBHandler dbHandler = new DBHandler(this);
        dbHandler.updateInfo(editUser.getText().toString(),editPass.getText().toString());
        List pass = dbHandler.readAllInfo("password");
        textShow.setText("Passwords are "+pass.toString());
    }

    public void signIn(View view){
        DBHandler dbHandler = new DBHandler(this);
        List unames = dbHandler.readAllInfo("user");
        List pass = dbHandler.readAllInfo("password");

        String user = editUser.getText().toString();
        String passw = editPass.getText().toString();

        if(unames.indexOf(user)>=0){
            if(pass.get(unames.indexOf(user)).equals(passw)){
                Toast.makeText(this,"Login Successful",Toast.LENGTH_SHORT).show();
            } else{
                Toast.makeText(this,"Login Failed.",Toast.LENGTH_SHORT).show();
            }
        }
    }
}