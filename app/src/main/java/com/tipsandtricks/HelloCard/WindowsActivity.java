package com.tipsandtricks.HelloCard;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class WindowsActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView textview = new TextView(this);
        textview.setText("This is window tab");
        setContentView(textview);
    }
}
