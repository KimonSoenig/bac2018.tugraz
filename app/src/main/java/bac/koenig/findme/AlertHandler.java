package bac.koenig.findme;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ExpandableListAdapter;
import android.widget.Switch;

public class AlertHandler {

    private ExpandableListAdapter adapter;

    private Context context;
    private View settingsAlertView;
    private AlertDialog settingAlert;

    private SharedPreferences settings;
    private SharedPreferences settingscache;
    private SharedPreferences.Editor settingseditor;
    private SharedPreferences.Editor cacheeditor;


    AlertHandler(Context context) {
        this.context = context;
        this.settings= context.getSharedPreferences("settings", context.MODE_PRIVATE);
        this.settingscache = context.getSharedPreferences("settingscache", context.MODE_PRIVATE);
        this.settingseditor = settings.edit();
        this.cacheeditor = settingscache.edit();
    }


    void showSettingsAlert() {

        AlertDialog.Builder settingDialogBuilder = new AlertDialog.Builder(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        settingsAlertView = inflater.inflate(R.layout.alert_settings, null);
        settingDialogBuilder.setView(settingsAlertView);
        initListener();
        settingAlert = settingDialogBuilder.create();
        settingAlert.show();

        Button btn_ok = settingsAlertView.findViewById(R.id.btn_OK);
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                settingAlert.dismiss();
                //If you press OK the changes of the Switches will be written in the file
                //If  you backpress (see below) it will not be written

                settings.edit().putBoolean("wheelchairmode", settingscache.getBoolean("wheelchairmode", false)).commit();
                settings.edit().putBoolean("big_display", settingscache.getBoolean("big_display", false)).commit();
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

    private void initListener() {
        //Method to check switch-state
        //If Switch is switched it will be loaded from settings.xml
        //if not the isChecked state will be saved with true or false ->goto showSettingsAlert->settingsAlertView.OnClickListener

        Switch wheelchairmode = (Switch) settingsAlertView.findViewById(R.id.wheelchair_mode);
        Switch big_display = (Switch) settingsAlertView.findViewById(R.id.big_display_mode);
        wheelchairmode.setChecked(settings.getBoolean("wheelchairmode", false));
        big_display.setChecked(settings.getBoolean("big_display", false));
        wheelchairmode.setChecked(settingscache.getBoolean("wheelchairmode", false));
        big_display.setChecked(settingscache.getBoolean("big_display", false));

        wheelchairmode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                cacheeditor.putBoolean("wheelchairmode", isChecked).commit();
            }
        });
        big_display.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                cacheeditor.putBoolean("big_display", isChecked).commit();
            }
        });
    }
}