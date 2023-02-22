package com.example.fixneat.Utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.widget.Toast;


public class SignalUser {

    @SuppressLint("StaticFieldLeak")
    private static SignalUser signalUser = null;

    private final Context context;
    private static Vibrator vibrator;


    private SignalUser(Context context) {
        this.context = context;
    }

    public static void initInstance(Context context) {
        if (signalUser == null) {
            signalUser = new SignalUser(context);
            vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        }
    }


    public static SignalUser getInstance() {
        return signalUser;
    }

    public void toast(String toastMsg) {
        Toast.makeText(context, toastMsg, Toast.LENGTH_SHORT).show();
    }

    public void vibrate(int vibrateTime) {
        vibrator.vibrate(VibrationEffect.createOneShot(vibrateTime,
                VibrationEffect.DEFAULT_AMPLITUDE));
    }

}
