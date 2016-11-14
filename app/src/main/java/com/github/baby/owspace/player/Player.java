package com.github.baby.owspace.player;

import android.media.MediaPlayer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr.Yangxiufeng
 * DATE 2016/11/14
 * owspace
 */

public class Player implements IPlayback,MediaPlayer.OnCompletionListener{

    private static volatile Player sInstance;
    private MediaPlayer mPlayer;
    private List<Callback> mCallbacks = new ArrayList<>(2);
    private boolean isPaused;

    private Player() {
        mPlayer = new MediaPlayer();
        mPlayer.setOnCompletionListener(this);
    }

    public static Player getInstance() {
        if (sInstance == null) {
            synchronized (Player.class) {
                if (sInstance == null) {
                    sInstance = new Player();
                }
            }
        }
        return sInstance;
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        notifyComplete(null);
    }

    @Override
    public boolean play() {
        if (isPaused){
            mPlayer.start();
            notifyPlayStatusChanged(true);
            return true;
        }
        return false;
    }

    @Override
    public boolean play(String song) {
        try {
            mPlayer.reset();
            mPlayer.setDataSource(song);
            mPlayer.prepare();
            mPlayer.start();
            notifyPlayStatusChanged(true);
            return true;
        } catch (IOException e) {
            notifyPlayStatusChanged(false);
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean pause() {
        if (mPlayer.isPlaying()) {
            mPlayer.pause();
            isPaused = true;
            notifyPlayStatusChanged(false);
            return true;
        }
        return false;
    }

    @Override
    public boolean isPlaying() {
        return mPlayer.isPlaying();
    }

    @Override
    public int getProgress() {
        return mPlayer.getCurrentPosition();
    }

    @Override
    public boolean seekTo(int progress) {
        return false;
    }

    @Override
    public void registerCallback(Callback callback) {
        mCallbacks.add(callback);
    }

    @Override
    public void unregisterCallback(Callback callback) {
        mCallbacks.remove(callback);
    }

    @Override
    public void removeCallbacks() {
        mCallbacks.clear();
    }

    @Override
    public void releasePlayer() {
        mPlayer.reset();
        mPlayer.release();
        mPlayer = null;
        sInstance = null;
    }

    private void notifyPlayStatusChanged(boolean isPlaying) {
        for (Callback callback : mCallbacks) {
            callback.onPlayStatusChanged(isPlaying);
        }
    }
    private void notifyComplete(String song) {
        for (Callback callback : mCallbacks) {
            callback.onComplete(song);
        }
    }
}
