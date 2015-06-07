package com.linkedladies.troop.helpers;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;

import com.linkedladies.troop.R;

public class SoundManager {

    private Context context;

    public SoundManager(Context context) {
        this.context = context;
    }

    public void playLove() {
        final MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.love_ping);
        play(mediaPlayer);
    }

    public void playHelp() {
        // TODO (jay) get new music resource for help sound
        final MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.love_ping);
        play(mediaPlayer);
    }

    private void play(final MediaPlayer mediaPlayer) {
        mediaPlayer.start();
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mediaPlayer.release();
                Log.i(SoundManager.class.getSimpleName(), "Released media player resources.");
            }
        });
    }
}
