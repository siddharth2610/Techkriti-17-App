package com.suleiman.techkriti.activities;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.suleiman.techkriti.R;

/**
 * Created by HP on 1/26/2016.
 */
public class Utils {
    public static NotificationManager mManager;

    @SuppressWarnings("static-access")
    public static void generateNotification(Context context){

        mManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
        Intent intent1 = new Intent(context,LaunchScreenActivity.class);
        Notification notification = new Notification(R.drawable.techriti,"This is a test message!", System.currentTimeMillis());
        intent1.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP| Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingNotificationIntent = PendingIntent.getActivity(context,0, intent1, PendingIntent.FLAG_UPDATE_CURRENT);
        notification.flags |= Notification.FLAG_AUTO_CANCEL;

       // notification.setLatestEventInfo(context, "AlarmManagerDemo", "This is a test message!", pendingNotificationIntent);
        mManager.notify(0, notification);
    }
}
