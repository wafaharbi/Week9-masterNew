package com.example.wafa.studentapp;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {
//private ImageView mProfileImage;

private TextView mName,musername,mEmail,mPassword,mPhone;
    private  DatabaseReference mUsersDatabase;

    private ProgressDialog mProgressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        String user_id=getIntent().getStringExtra("user_id");

        //getReference("Student").child(u.getUid());

        mUsersDatabase= FirebaseDatabase.getInstance().getReference().child("Student").child(user_id);

      //  mProfileImage=(ImageView)findViewById(R.id.imageView6);

        mName=(TextView)findViewById(R.id.textView12);
        musername=(TextView)findViewById(R.id.textView13);
        mEmail=(TextView)findViewById(R.id.textView14);
        mPassword=(TextView)findViewById(R.id.textView15);
        mPhone=(TextView)findViewById(R.id.textView16);

        mProgressDialog=new ProgressDialog(this);
        mProgressDialog.setTitle("loading  user data ");
        mProgressDialog.setMessage("Wait ");
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.show();


        mUsersDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String name =dataSnapshot.child("name").getValue().toString();
                String username = dataSnapshot.child("username").getValue().toString();
                String email = dataSnapshot.child("email").getValue().toString();
                String password = dataSnapshot.child("password").getValue().toString();
                String phone = dataSnapshot.child("phone").getValue().toString();
                String image = dataSnapshot.child("mage").getValue().toString();

                mName.setText(name);
                musername.setText(username);
                mEmail.setText(email);
                mPassword.setText(password);
                mPhone.setText(phone);

              //  Picasso.with(ProfileActivity.this).load(image).placeholder(R.drawable.cs2).into(mProfileImage);

                mProgressDialog.dismiss();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });












    }
}
