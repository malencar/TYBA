package com.capstone.android.tyba;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class AllSet extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        //final String product = intent.getStringExtra("product");
        setContentView(R.layout.activity_all_set);
        /*AlertDialog alertDialog = new AlertDialog.Builder(AllSet.this).create();
        alertDialog.setTitle(getString(R.string.app_name));
        alertDialog.setMessage(getString(R.string.msgallset));
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                       InformUser(product);
                    }
                });
        alertDialog.show();*/
    }

    /*private void InformUser(String product){

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, MainActivity.CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Bread Time")
                .setContentText("Your Bread will be ready at "+product) //real time will come from Firebase
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(AllSet.this);
        int notificationId = 1;
        notificationManager.notify(notificationId, mBuilder.build());

    }
    */
}
