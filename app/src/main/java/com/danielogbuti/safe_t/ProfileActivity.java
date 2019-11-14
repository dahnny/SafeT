package com.danielogbuti.safe_t;

import android.os.Bundle;

import com.danielogbuti.safe_t.Common.Common;
import com.danielogbuti.safe_t.models.Profile;
import com.danielogbuti.safe_t.models.SignIn;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.danielogbuti.safe_t.Home.getToken;

public class ProfileActivity extends AppCompatActivity {

    TextView uniqueCodeText;
    FirebaseDatabase database;
    DatabaseReference reference;
    DatabaseReference courses;
    TextView bloodgroupText;
    TextView matricText;
    TextView firstNameText;
    ImageView imageView;
    private Profile activity;
    private String id;
    private boolean value;
    private boolean login;
    private List<String> tokens;
    private static int count = 0;
    AppCompatButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tokens = new ArrayList<>();
        uniqueCodeText = (TextView)findViewById(R.id.uniqueCodeText);
        bloodgroupText = (TextView)findViewById(R.id.bloodGroupText);
        matricText = (TextView)findViewById(R.id.matricText);
        firstNameText = (TextView)findViewById(R.id.nameText);
        imageView = (ImageView)findViewById(R.id.profileImage);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Profile");
        if(getIntent() !=null){
            id = getIntent().getStringExtra("id");
        }

//        for (int i = 0;i < 10;i++){
//            String token = getToken(6);
//            tokens.add(token);
//        }
//
//        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
//        for (String token :tokens){
//            rootRef.child("tokens").child(token).setValue("True");
//        }

        addToProfile();

        if (Common.CourseTaken != null) {
            courses = database.getReference(Common.CourseTaken);

        }

        fab = (AppCompatButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uniqueCodeText.setVisibility(View.VISIBLE);

                generateCode();


                fixCourses();
                if (isSignedIn()){
                    Toast.makeText(ProfileActivity.this, "Sign In successful", Toast.LENGTH_SHORT).show();

                }

            }
        });

    }

    //This method updates the database
    private void updateCode(List<String> tokens) {
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference tokenRef = rootRef.child("tokens");


        tokenRef.child(tokens.get(0)).removeValue();

    }

    private void generateCode() {
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference tokenRef = rootRef.child("tokens");

        tokenRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (count == 0) {
                    List<String> tokens = new ArrayList<>();
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        String token = ds.getKey();

                        tokens.add(token);
                        count += 1;

                    }
                    String string = tokens.get(0);
                    Log.i("fish", string + "token");
                    uniqueCodeText.setText(string);
                    uniqueCodeText.setVisibility(View.VISIBLE);
                    updateCode(tokens);
                    fab.setVisibility(View.GONE);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private boolean isSignedIn(){
        courses.child(activity.getMatriculationNumber())
                .addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                SignIn login = dataSnapshot.getValue(SignIn.class);
                if (login.getSignedIn()!=null) {
                    if (login.getSignedIn().equals("True")){
                        value = true;
                    }else {
                        value = false;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return value;
    }

    private void fixCourses() {
        String matriculation = activity.getMatriculationNumber();
        DatabaseReference updation = courses.child(matriculation);
        Map<String, Object> updates = new HashMap<>();
        updates.put("SignedIn","True");

        updation.updateChildren(updates);

    }

    private void addToProfile() {
        reference.child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                activity = dataSnapshot.getValue(Profile.class);

                Picasso.with(getBaseContext()).load(activity.getImage())
                        .into(imageView);
                matricText.setText(activity.getMatriculationNumber());
                firstNameText.setText(activity.getFirstName());
                bloodgroupText.setText(activity.getBloodGroup());


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
