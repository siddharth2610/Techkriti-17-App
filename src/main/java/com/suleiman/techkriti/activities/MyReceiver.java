package com.suleiman.techkriti.activities;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.suleiman.techkriti.MapsActivity1;
import com.suleiman.techkriti.R;

import java.util.Random;

/**
 * Created by HP on 1/26/2016.
 */
public class MyReceiver extends BroadcastReceiver
{Intent resultIntent;
    startPage2 st=new startPage2();

    @Override
    public void onReceive(Context context, Intent intent)
    {String content=intent.getExtras().getString("Content");
        String Title=intent.getExtras().getString("Title");

        String type=intent.getExtras().getString("Type");
        String addData=intent.getExtras().getString("AddData");
        Log.d("OhYes", Title);


       NotificationCompat.Builder mBuilder= (NotificationCompat.Builder) new NotificationCompat.Builder(context).setSmallIcon(R.drawable.tech2).setContentTitle(Title).setContentText(content);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        if(type.compareTo("link")==0)
        { resultIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(addData));
          //  stackBuilder.addParentStack(LaunchScreenActivity.class);

        }
        else if(type.compareTo("location")==0)
        {Log.d("hg",addData);
            resultIntent = new Intent(context, MapsActivity1.class);
            resultIntent.putExtra("Location",addData);
            resultIntent.putExtra("Snippet", content);
           // stackBuilder.addParentStack(MapsActivity1.class);

        }
        else
        {
         resultIntent = new Intent(context, LaunchScreenActivity.class);}
        int k=new Random().nextInt();
        PendingIntent resultPendingIntent =
                PendingIntent.getActivity(
                        context,
                        k,
                        resultIntent,
                        PendingIntent.FLAG_CANCEL_CURRENT|PendingIntent.FLAG_ONE_SHOT
                );

      /*  stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);*/
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mBuilder.build().flags= Notification.FLAG_INSISTENT;
mBuilder.setAutoCancel(true);
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        mBuilder.setSound(alarmSound);
        mBuilder.setLights(Color.BLUE, 500, 500);
        long[] pattern = {500,500,500,500,500,500,500,500,500};
        mBuilder.setVibrate(pattern);

        mNotificationManager.notify(k, mBuilder.build());
    }

}
