package com.gruppe23.oblig2app;

import android.os.Bundle;
import android.os.Vibrator;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	public String tag = "logg";
	public int notificationID = 1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	protected void onResume() {
		super.onResume();
		switchActivity();
		startVibrating();
		startCamera();
		startToast();
	}
	
	protected void switchActivity()	{
		Button switchActivity = (Button) findViewById(R.id.button_other_activity);
		switchActivity.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.d(tag,"Knappen er trykket");
				startActivity(new Intent("android.intent.action.otherActivity"));
				
			}
		});
	}
	
	protected void startVibrating()	{
		Button startVibrating = (Button) findViewById(R.id.button_vibrate);
		startVibrating.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Vibrator vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
				vib.vibrate(500);
				
			}
		});	
	}
	protected void startCamera()	{
		Button startCamera = (Button) findViewById(R.id.button_camera);
		startCamera.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent("android.media.action.IMAGE_CAPTURE"));
				
			}
		});
	}
	protected void startToast()	{
		Button startToast = (Button) findViewById(R.id.button_toast);
		startToast.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Context context = getApplicationContext();
				CharSequence text = "Jeg begynner å bli sulten :(";
				int duration = Toast.LENGTH_SHORT;

				Toast toast = Toast.makeText(context, text, duration);
				toast.show();
				displayNotification();
				
				
			}
		});
	}
	
	protected void displayNotification()	{
		Intent i = new Intent(this, MainActivity.class);
        i.putExtra("notificationID", notificationID);

        PendingIntent pendingIntent =
            PendingIntent.getActivity(this, 0, i, 0);

        NotificationManager nm = (NotificationManager)
            getSystemService(NOTIFICATION_SERVICE); 

        Notification notif = new Notification(
            R.drawable.ic_launcher, 
            "Du har fått en notification!",
            System.currentTimeMillis());

        CharSequence from = "Viktig medling";
        CharSequence message = "Dette er en veldig viktig melding!";
        
        notif.setLatestEventInfo(this, from, message, pendingIntent);

        //---100ms delay, vibrate for 250ms, pause for 100 ms and
        // then vibrate for 500ms---
        //notif.vibrate = new long[] { 100, 250, 100, 500};
        nm.notify(notificationID, notif);        
	}


		
}
