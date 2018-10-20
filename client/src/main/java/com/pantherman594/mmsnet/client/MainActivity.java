package com.pantherman594.mmsnet.client;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    final String[] VPN_Status = {"VPN: OFF","VPN: ON"};

    ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#4b77ef"));
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // VPN on/off changes according to switch.
        final TextView status = (TextView) findViewById(R.id.VPN_Status);
        final Switch VPN_Switch = (Switch) findViewById(R.id.VPN_Switch);

        //ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.vpn_layout);

        // Checks for if the switch is on/off, then change the status text.
        VPN_Switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(numAuthenticate(((EditText) findViewById(R.id.editText2)).getText().toString())) {
                    if (!buttonView.isChecked()) {
                        status.setText(VPN_Status[0]);
                        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#7e7e7e")));
                        EditText editText = (EditText) findViewById(R.id.editText2);
                        editText.setEnabled(true);
                    }
                }
                else {
                    VPN_Switch.setChecked(false);
                }
                if (buttonView.isChecked()) {
                    status.setText(VPN_Status[1]);
                    getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#4b77ef")));
                    EditText editText = (EditText) findViewById(R.id.editText2);
                    editText.setEnabled(false);
                }
            }
        });
    }

    public void sendMessage(View view) {
        EditText editText = (EditText) findViewById(R.id.editText2);
        String phoneNumber = editText.getText().toString();

        if (numAuthenticate(phoneNumber)) {
            ((Switch) findViewById(R.id.VPN_Switch)).setChecked(true);
        }

        System.out.println(phoneNumber);
    }

    public boolean numAuthenticate(String phoneNumber) {
        int duration = Toast.LENGTH_SHORT;
        if(phoneNumber == null || phoneNumber.length() != 10) {
            Context context = getApplicationContext();
            Toast toast = Toast.makeText(context, "Please submit a 10-digit phone number", duration);
            toast.show();
            return false;
        }
        return true;
    }
}
