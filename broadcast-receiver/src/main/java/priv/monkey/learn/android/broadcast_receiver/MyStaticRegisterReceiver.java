package priv.monkey.learn.android.broadcast_receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.widget.Toast;

public class MyStaticRegisterReceiver extends BroadcastReceiver {

    /**
     * 运行在主线程，10s不响应ANR
     * @param context
     * @param intent
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Log.e("Monkey.Learn", "broadcastRecevier");
        Toast.makeText(context,"静态注册的广播的onReceiver",Toast.LENGTH_LONG).show();
//        Snackbar.make(context, "it is Snackbar", Snackbar.LENGTH_SHORT).show();
    }
}
