package com.capstone.android.tyba;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class SelectBreadActivity extends AppCompatActivity {
    private static final String cheeseBread =
            "https://firebasestorage.googleapis.com/v0/b/tyba-af349.appspot.com/o/cheese_bread.png?alt=media&token=223f07d4-d982-44ba-bb5b-676ab01e0000"; //cheese bread image
    private static final String frenchBread =
            "https://firebasestorage.googleapis.com/v0/b/tyba-af349.appspot.com/o/pao_frances.png?alt=media&token=45623807-b08d-4211-b370-b76b8825c486"; //french bread image
    private String TAG = "TYBATIME";
    static DataSnapshot dataSnapshot;
    public static String breadClicked = "frenchbread";

    //private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    //DataSnapshot dataSnapshot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_select_bread);
        final ImageView imFrenchBread = findViewById(R.id.im_frenchbread);


        ImageView imCheeseBread = findViewById(R.id.im_cheesebread);
        imCheeseBread.setScaleType(ImageView.ScaleType.FIT_XY);
        imFrenchBread.setScaleType(ImageView.ScaleType.FIT_XY);
        Button buttonAllSet = findViewById(R.id.btAllSet);

        //Dear Reviewer, in case cannot resolve GlideApp, Make Project (F9) 
        GlideApp.with(this).load(cheeseBread).into(imCheeseBread);
        GlideApp.with(this).load(frenchBread).into(imFrenchBread);


        imFrenchBread.setOnClickListener(new View.OnClickListener() {
            // Reads oven time from firebase for the selected bread, in order to inform user afterwards
            public void onClick(View v) {
                /*Intent allSetIntent = new Intent(SelectBreadActivity.this,AllSet.class);
                startActivity(allSetIntent);*/
                breadClicked = "frenchbread";

            }
        });




        imCheeseBread.setOnClickListener(new View.OnClickListener() {
            // Reads oven time from firebase for the selected bread, in order to inform user afterwards
            public void onClick(View v) {
                //Intent allSetIntent = new Intent(SelectBreadActivity.this,AllSet.class);
                //startActivity(allSetIntent);
                breadClicked = "cheesebread";
            }
        });

        buttonAllSet.setOnClickListener(new View.OnClickListener() {
            // Reads oven time from firebase for the selected bread, in order to informs user afterwards
            public void onClick(View v) {
                Intent allSetIntent = new Intent(SelectBreadActivity.this, AllSet.class);
                final String oventime = dataSnapshot.child(breadClicked).getValue(String.class);

                InformUser(oventime);
                AlertDialog alertDialog = new AlertDialog.Builder(SelectBreadActivity.this).create();
                alertDialog.setTitle(getString(R.string.app_name));
                alertDialog.setMessage(getString(R.string.msgallset));

                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                              dialog.dismiss();
                            }
                        });



                alertDialog.show();


            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        //attaching SingleValue event listener
        final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("products/");
        Log.d(TAG, "Database Reference of child " + mDatabase);
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                SelectBreadActivity.dataSnapshot = dataSnapshot;
                Log.d("MAURICIO", "BREAD = " + dataSnapshot.child("cheesebread").getKey());
                Log.d("MAURICIO", "TIME = " + dataSnapshot.child("cheesebread").getValue(String.class));

                Log.d("MAURICIO", "BREAD = " + dataSnapshot.child("frenchbread").getKey());
                Log.d("MAURICIO", "TIME = " + dataSnapshot.child("frenchbread").getValue(String.class));

                String frenchBreadTime = dataSnapshot.child("frenchbread").getValue(String.class);
                String cheeseBreadTime = dataSnapshot.child("cheesebread").getValue(String.class);

                Product product = new Product(cheeseBreadTime, frenchBreadTime);
                Log.d("MAURICIO", "French time  = " + product.frenchBreadTime);
                Log.d("MAURICIO", "Cheese time  = " + product.cheeseBreadTime);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void InformUser(String oventime){

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, MainActivity.CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Bread Time")
                .setContentText("Your Bread will be ready at "+oventime) //real time will come from Firebase
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(SelectBreadActivity.this);
        int notificationId = 1;
        notificationManager.notify(notificationId, mBuilder.build());

    }


}
