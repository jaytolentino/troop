package com.linkedladies.troop.helpers;

import android.content.Context;
import android.media.MediaPlayer;

import com.linkedladies.troop.R;

public class SoundManager {

    private Context context;

    public SoundManager(Context context) {
        this.context = context;
    }

    public void playLove() {
        final MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.love_ping);
        mediaPlayer.start();
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mediaPlayer.release();
            }
        });
    }
}
