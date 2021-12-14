package cn.hle.skipselfstartmanager;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.jit.autostart.util.MobileInfoUtils;

import xyz.kumaraswamy.autostart.Autostart;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void gotoSelfStartingManager(View view) throws Exception {
        Autostart autostart = new Autostart(this);

        Autostart.State state = autostart.getAutoStartState();

        if (state == Autostart.State.DISABLED) {
            // now we are sure that autostart is disabled
            // ask user to enable it manually in the settings app
        } else if (state == Autostart.State.ENABLED) {
            // now we are also sure that autostart is enabled
        }

        Toast.makeText(this,state+"",Toast.LENGTH_LONG).show();

        jumpStartInterface();
    }

    /**
     * Jump Start Interface
     * 提示是否跳转设置自启动界面
     */
    private void jumpStartInterface() {
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(R.string.app_user_auto_start);
            builder.setPositiveButton("立即设置",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            MobileInfoUtils.jumpStartInterface(MainActivity.this);
                        }
                    });
            builder.setNegativeButton("暂时不设置",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            builder.setCancelable(false);
            builder.create().show();
        } catch (Exception e) {
        }
    }



}
