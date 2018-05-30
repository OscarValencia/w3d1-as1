package com.valencia.oscar.w3d1_as1;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.os.PowerManager;
import android.util.Log;

public class MusicService extends Service {
    private static final String TAG = MusicService.class.getSimpleName()+"_TAG";
    MediaPlayer mediaPlayer;


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        Log.d(TAG,"onCreate");
        createMediaPlayerIfNeeded();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG,"onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG,"onDestroy");
        mediaPlayer.stop();
    }

    private void createMediaPlayerIfNeeded(){
        if(null==mediaPlayer){
            mediaPlayer = MediaPlayer.create(this, R.raw.braincandy);
            mediaPlayer.setLooping(false);
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    Log.d(TAG,"onCompletion");
                    stopSelf();
                }
            });
            mediaPlayer.start();
        }else{
            mediaPlayer.reset();
        }
    }



}
