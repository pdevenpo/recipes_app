package edu.osu.recipe_app;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import edu.osu.recipe_app.ui.Timer.TimerActivity;

public class AlertReceiver extends BroadcastReceiver {

    private int notificationId = 0;

    @Override
    public void onReceive(Context context, Intent intent){
        // Potentially use this when the Intent/Extra bug is fixed
        String timerLength = "";

        Bundle extras = intent.getExtras();
        if(extras != null){
            timerLength =  extras.getString("TimerLength");
            extras.clear();
        }

        // Intent to open upon clicking notification
        Intent alarmIntent = new Intent(context, TimerActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, alarmIntent, 0);

        // Build the timer notification
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, "1")
                // Notification icon
                .setSmallIcon(R.drawable.ic_timer_notification)
                .setContentTitle("Timer Complete")
                .setContentText("Timer complete")
                // Priority = MAX
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                // Set the intent that will fire when the user taps the notification
                .setContentIntent(pendingIntent);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(notificationId, mBuilder.build());
    }
}
