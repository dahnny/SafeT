package com.danielogbuti.safe_t;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.danielogbuti.safe_t.Common.Common;

public class MainActivity extends AppCompatActivity {
    Button adminLogin;
    EditText courseTaken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adminLogin = (Button)findViewById(R.id.admin_login);
        courseTaken = (EditText)findViewById(R.id.courseText);

        //Common.CourseTaken = courseTaken.getText().toString();
        adminLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (courseTaken.getText().toString().isEmpty()){
                    courseTaken.setError("Field cannot be empty");
                }else {
                    Intent intent = new Intent(MainActivity.this, Home.class);
                    startActivity(intent);
                }
            }
        });
    }
}
