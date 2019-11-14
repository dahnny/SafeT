package com.danielogbuti.safe_t;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import asia.kanopi.fingerscan.Status;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.danielogbuti.safe_t.Common.Common;
import com.danielogbuti.safe_t.models.Profile;
import com.danielogbuti.safe_t.models.ProfileDetails;
import com.danielogbuti.safe_t.models.SignIn;
import com.danielogbuti.safe_t.sourceafis.FingerprintMatcher;
import com.danielogbuti.safe_t.sourceafis.FingerprintTemplate;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Random;

public class Home extends AppCompatActivity {

    CardView signIn;
    CardView signOut;
    FirebaseDatabase database;
    DatabaseReference profile;
    DatabaseReference courses;
    List<ProfileDetails> detailsList;
    Profile data;
    ProfileDetails details;
    DatabaseReference reference;
    byte[] img;
    boolean value;
    private String id;
    FingerprintTemplate template;
    ProfileDetails match;
    Profile activity;
    private static final int REQUEST_CODE = 0;
    private static final Random random = new Random();
    private static final String CHARS = "abcdefghijkmnopqrstuvwxyzABCDEFGHJKLMNOPQRSTUVWXYZ234567890";
    private List<String> tokens;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        database = FirebaseDatabase.getInstance();
        profile = database.getReference("Profile");


        signIn = (CardView)findViewById(R.id.signInView);
        signOut = (CardView)findViewById(R.id.signOut);
        //tokens = new ArrayList<>();


        courses = database.getReference(Common.CourseTaken);
        firebaseData();


        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this,FingerPrintPage.class);
                startActivityForResult(intent,1);
            }
        });


        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, FingerPrintPage.class);
                startActivityForResult(intent,REQUEST_CODE);
            }
        });



    }

    private void firebaseData() {
        detailsList = new ArrayList<>();
        profile.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                    data = dsp.getValue(Profile.class);
                    Log.i("Tag", data.getFingerPrint());
                    byte[] img = Base64.getDecoder().decode(data.getFingerPrint());
                    FingerprintTemplate template = new FingerprintTemplate()
                            .dpi(500)
                            .create(img);
                    //updateTask(img);
                    details = new ProfileDetails(dsp.getKey(), template);
                    detailsList.add(details);
                    Log.i("Tag",detailsList.toString());
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

    public static String getToken(int length) {
        StringBuilder token = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            token.append(CHARS.charAt(random.nextInt(CHARS.length())));
        }
        return token.toString();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        int status;
        String errorMessage;

        switch (requestCode){
            case REQUEST_CODE:
                if (resultCode == RESULT_OK){
                    status = data.getIntExtra("status", Status.ERROR);

                    if (status == Status.SUCCESS){
                        img = data.getByteArrayExtra("img");

                        template = new FingerprintTemplate()
                                .dpi(500)
                                .create(img);

                        match = find(template,detailsList);


                        if (match != null){
                                Intent intent = new Intent(Home.this, ProfileActivity.class);
                                intent.putExtra("id", match.getId());
                                startActivity(intent);

                                Toast.makeText(this, "Already signed IN", Toast.LENGTH_SHORT).show();

                        }else {
                            Toast.makeText(this, "FingerPrint MisMatch", Toast.LENGTH_SHORT).show();
                        }

                    }else {
                        errorMessage = data.getStringExtra("errorMessage");
                        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
                    }
                }
            case 1:
                Toast.makeText(this, "SignedOut successfully", Toast.LENGTH_SHORT).show();
        }

    }

    private ProfileDetails find(FingerprintTemplate probe, List<ProfileDetails> candidates) {
        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Loading Profile");
        dialog.show();
        FingerprintMatcher matcher = new FingerprintMatcher()
                .index(probe);
        ProfileDetails match = null;
        double high = 0;
        for (ProfileDetails candidate : candidates) {
            //go through all the list and then match
            double score = matcher.match(candidate.getImageTemplate());
            if (score > high) {
                high = score;
                match = candidate;
            }
        }
        double threshold = 40;
        if (high>=threshold){
            dialog.dismiss();
            return match;
        }else {
            return null;
        }

    }
//    private void addToProfile(String id) {
//        profile.child(id).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                activity = dataSnapshot.getValue(Profile.class);
//                texter.setText(activity.getMatriculationNumber());
//
//                Log.i("fish",activity.getMatriculationNumber());
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//    }
}
