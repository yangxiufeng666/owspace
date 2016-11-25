package com.github.baby.owspace.player;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.NotificationCompat;
import android.widget.RemoteViews;

import com.github.baby.owspace.R;
import com.github.baby.owspace.view.activity.MainActivity;
import com.orhanobut.logger.Logger;

/**
 * Created by Mr.Yangxiufeng
 * DATE 2016/11/14
 * owspace
 */

public class PlaybackService extends Service implements IPlayback,IPlayback.Callback{

    private static final String ACTION_PLAY_TOGGLE = "com.github.baby.owspace.ACTION.PLAY_TOGGLE";
    private static final String ACTION_PLAY_LAST = "com.github.baby.owspace.ACTION.PLAY_LAST";
    private static final String ACTION_PLAY_NEXT = "com.github.baby.owspace.ACTION.PLAY_NEXT";
    private static final String ACTION_STOP_SERVICE = "com.github.baby.owspace.ACTION.STOP_SERVICE";

    private static final int NOTIFICATION_ID = 1;

    private RemoteViews mContentViewBig, mContentViewSmall;

    private Player mPlayer;

    private final Binder mBinder = new LocalBinder();

    public class LocalBinder extends Binder {
        public PlaybackService getService() {
            return PlaybackService.this;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Logger.d("onBind");
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mPlayer = Player.getInstance();
        mPlayer.registerCallback(this);
        Logger.d("onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Logger.d("onStartCommand");
        if (intent != null) {
            String action = intent.getAction();
            if (ACTION_PLAY_TOGGLE.equals(action)) {
                if (isPlaying()) {
                    pause();
                } else {
                    play();
                }
            } else if (ACTION_STOP_SERVICE.equals(action)) {
                if (isPlaying()) {
                    pause();
                }
                stopForeground(true);
                unregisterCallback(this);
            }
        }
        return START_STICKY;
    }

    @Override
    public boolean stopService(Intent name) {
        Logger.d("stopService");
        stopForeground(true);
        unregisterCallback(this);
        return super.stopService(name);
    }

    @Override
    public void onDestroy() {
        Logger.d("onDestroy");
        releasePlayer();
        super.onDestroy();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Logger.d("onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public boolean play() {
        return mPlayer.play();
    }

    @Override
    public boolean play(String song) {
        return mPlayer.play(song);
    }

    @Override
    public boolean pause() {
        return mPlayer.pause();
    }

    @Override
    public boolean isPlaying() {
        return mPlayer.isPlaying();
    }
    public String getSong(){
        return mPlayer.getSong();
    }
    @Override
    public int getProgress() {
        return mPlayer.getProgress();
    }

    @Override
    public int getDuration() {
        return mPlayer.getDuration();
    }

    @Override
    public boolean seekTo(int progress) {
        return mPlayer.seekTo(progress);
    }

    @Override
    public void registerCallback(Callback callback) {
        mPlayer.registerCallback(callback);
    }

    @Override
    public void unregisterCallback(Callback callback) {
        mPlayer.unregisterCallback(callback);
    }

    @Override
    public void removeCallbacks() {
        mPlayer.removeCallbacks();
    }

    @Override
    public void onPosition(int position) {

    }

    @Override
    public void releasePlayer() {
        mPlayer.releasePlayer();
        super.onDestroy();
    }

    @Override
    public void onComplete(PlayState state) {
        showNotification(state);
    }

    @Override
    public void onPlayStatusChanged(PlayState status) {
        showNotification(status);
    }
    /**
            * Show a notification while this service is running.
    */
    private void showNotification(PlayState status) {
        // The PendingIntent to launch our activity if the user selects this notification
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), 0);

        // Set the info for the views that show in the notification panel.
        Notification notification = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)  // the status icon
                .setWhen(System.currentTimeMillis())  // the time stamp
                .setContentIntent(contentIntent)  // The intent to send when the entry is clicked
                .setCustomContentView(getSmallContentView())
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setOngoing(true)
                .build();

        // Send the notification.
        startForeground(NOTIFICATION_ID, notification);
    }

    private RemoteViews getSmallContentView() {
        if (mContentViewSmall == null) {
            mContentViewSmall = new RemoteViews(getPackageName(), R.layout.remote_view_music_player_small);
            setUpRemoteView(mContentViewSmall);
        }
        updateRemoteViews(mContentViewSmall);
        return mContentViewSmall;
    }

    private RemoteViews getBigContentView() {
        if (mContentViewBig == null) {
            mContentViewBig = new RemoteViews(getPackageName(), R.layout.remote_view_music_player);
            setUpRemoteView(mContentViewBig);
        }
        updateRemoteViews(mContentViewBig);
        return mContentViewBig;
    }

    private void setUpRemoteView(RemoteViews remoteView) {
        remoteView.setImageViewResource(R.id.image_view_close, R.drawable.ic_remote_view_close);
        remoteView.setImageViewResource(R.id.image_view_play_last, R.drawable.ic_remote_view_play_last);
        remoteView.setImageViewResource(R.id.image_view_play_next, R.drawable.ic_remote_view_play_next);

        remoteView.setOnClickPendingIntent(R.id.button_close, getPendingIntent(ACTION_STOP_SERVICE));
        remoteView.setOnClickPendingIntent(R.id.button_play_last, getPendingIntent(ACTION_PLAY_LAST));
        remoteView.setOnClickPendingIntent(R.id.button_play_next, getPendingIntent(ACTION_PLAY_NEXT));
        remoteView.setOnClickPendingIntent(R.id.button_play_toggle, getPendingIntent(ACTION_PLAY_TOGGLE));
    }

    private void updateRemoteViews(RemoteViews remoteView) {
        remoteView.setImageViewResource(R.id.image_view_play_toggle, isPlaying()
                ? R.drawable.ic_remote_view_pause : R.drawable.ic_remote_view_play);

    }

    // PendingIntent

    private PendingIntent getPendingIntent(String action) {
        return PendingIntent.getService(this, 0, new Intent(action), 0);
    }
}
