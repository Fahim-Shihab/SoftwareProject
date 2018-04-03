package org.firebaseproject;

import android.app.usage.UsageStats;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

public class AppUsageStat extends AppCompatActivity {

    private ArrayList<UsageStats> usagestats = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_usage_stat);

        if (UStats.getUsageStatsList(this).isEmpty()) {
            Intent intent = new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS);
            startActivity(intent);
        }

        UStats.printCurrentUsageStatus(AppUsageStat.this);
    }
}
