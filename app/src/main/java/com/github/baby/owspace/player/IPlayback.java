package com.github.baby.owspace.player;

import android.support.annotation.Nullable;

/**
 * Created by Mr.Yangxiufeng
 * DATE 2016/11/14
 * owspace
 */

public interface IPlayback {

    boolean play();

    boolean play(String song);

    boolean pause();

    boolean isPlaying();

    int getProgress();

    boolean seekTo(int progress);

    void registerCallback(Callback callback);

    void unregisterCallback(Callback callback);

    void removeCallbacks();

    void releasePlayer();

    interface Callback {

        void onComplete(@Nullable String next);

        void onPlayStatusChanged(boolean isPlaying);
    }
}
