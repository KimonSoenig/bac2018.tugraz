package bac.koenig.findme;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListAdapter;

public class AlertHandler {

    private ExpandableListAdapter adapter;

    private Context context;
    private View settingsAlertView;
    private AlertDialog settingAlert;



    AlertHandler(Context context) {
        this.context = context;
        //this.shakeName = AnimationUtils.loadAnimation(context, R.anim.shake);
    }


    void showSettingsAlert() {
        AlertDialog.Builder settingDialogBuilder = new AlertDialog.Builder(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        settingsAlertView = inflater.inflate(R.layout.alert_settings, null);
        settingDialogBuilder.setView(settingsAlertView);

        settingAlert = settingDialogBuilder.create();
        settingAlert.show();

        Button btn_ok = settingsAlertView.findViewById(R.id.btn_OK);
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                settingAlert.dismiss();
            }
        });

        Button btn_cancel = settingsAlertView.findViewById(R.id.btn_cancel);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                settingAlert.dismiss();
            }
        });
    }

}