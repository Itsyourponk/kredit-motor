package com.example.pr_kredit_irfansm_ti26_;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TabHost;

public class MainActivity extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Resources res = getResources();
        TabHost tabHost = getTabHost();
        TabHost.TabSpec spec;
        Intent intent;

        // Tab 1: Home
        intent = new Intent().setClass(this, HomeActivity.class);
        spec = tabHost.newTabSpec("Home")
                      .setIndicator("Home", res.getDrawable(R.drawable.ic_tab_home))
                      .setContent(intent);
        tabHost.addTab(spec);

        // Tab 2: Transaksi
        intent = new Intent().setClass(this, TransaksiActivity.class);
        spec = tabHost.newTabSpec("Transaksi")
                      .setIndicator("Transaksi", res.getDrawable(R.drawable.ic_tab_tansaksi))
                      .setContent(intent);
        tabHost.addTab(spec);

        // Tab 3: About
        intent = new Intent().setClass(this, AboutActivity.class);
        spec = tabHost.newTabSpec("About")
                      .setIndicator("About", res.getDrawable(R.drawable.ic_tab_about))
                      .setContent(intent);
        tabHost.addTab(spec);

        // Set default tab yang aktif pertama kali (0 = Home)
        tabHost.setCurrentTab(0);
    }



@Override
public boolean onCreateOptionsMenu(Menu menu) {
// Inflate the menu; this adds items to the action bar if it is present.
getMenuInflater().inflate(R.menu.main, menu);
return true;
}

}

