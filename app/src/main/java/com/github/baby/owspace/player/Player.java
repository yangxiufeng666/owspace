package com.github.baby.owspace.player;

import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;

import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

/**
 * Created by Mr.Yangxiufeng
 * DATE 2016/11/14
 * owspace
 */

public class Player implements IPlayback,MediaPlayer.OnCompletionListener,MediaPlayer.OnBufferingUpdateListener,MediaPlayer.OnPreparedListener,MediaPlayer.OnErrorListener{

    private static volatile Player sInstance;
    private MediaPlayer mPlayer;
    private List<Callback> mCallbacks = new ArrayList<>(2);
    private boolean isPaused;
    private String song;

    private Player() {
        mPlayer = new MediaPlayer();
        mPlayer.setOnCompletionListener(this);
        mPlayer.setOnPreparedListener(this);
        mPlayer.setOnBufferingUpdateListener(this);
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
    public boolean play() {
        if (isPaused){
            mPlayer.start();
            notifyPlayStatusChanged(PlayState.PLAYING);
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
            this.song = song;
            notifyPlayStatusChanged(PlayState.PLAYING);
            return true;
        } catch (IOException e) {
            notifyPlayStatusChanged(PlayState.ERROR);
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean pause() {
        if (mPlayer.isPlaying()) {
            mPlayer.pause();
            isPaused = true;
            notifyPlayStatusChanged(PlayState.PAUSE);
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
    public int getDuration() {
        return mPlayer.getDuration();
    }

    @Override
    public boolean seekTo(int progress) {
        try {
            mPlayer.seekTo(progress);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
        return true;
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
        song = null;
    }

    private void notifyPlayStatusChanged(PlayState status) {
        for (Callback callback : mCallbacks) {
            callback.onPlayStatusChanged(status);
        }
    }
    private void notifyComplete(PlayState state) {
        for (Callback callback : mCallbacks) {
            callback.onComplete(state);
        }
    }

    public String getSong() {
        return song;
    }

    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {
//        Logger.d("onBufferingUpdate");
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        Logger.d("onPrepared");
        mPlayer.start();
        notifyPlayStatusChanged(PlayState.PLAYING);
    }
    @Override
    public void onCompletion(MediaPlayer mp) {
        Logger.d("onCompletion");
        mPlayer.reset();
        notifyComplete(PlayState.COMPLETE);
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        notifyPlayStatusChanged(PlayState.ERROR);
        return false;
    }
}
