package com.example.myincremental;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

//    private Timer timer;
    long startTime = System.nanoTime();
    long totalIncome = 0;
    int levelBoost = 0;
    PurchaseLevel lv1 = new PurchaseLevel("Sword", 1, 1);
    PurchaseLevel lv2 = new PurchaseLevel("Shield", 2, 2);

    TextView totalSumView;

    Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {

        @Override
        public void run() {
            long elapsed = (long) Math.ceil((System.nanoTime() - startTime) / 1000000);

            totalIncome += (long) Math.ceil(elapsed * levelBoost / 1000);
            totalSumView.setText(Long.toString(totalIncome));

            timerHandler.postDelayed(this, 1000);
            startTime = System.nanoTime();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerHandler.postDelayed(timerRunnable, 0);
        totalSumView = findViewById(R.id.totalSum);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onLv1Buy(View view) {
        lv1.increaseLevel();
        totalIncome -= lv1.Cost;
        totalSumView.setText(Long.toString(totalIncome));
        levelBoost = (int) (levelBoost + lv1.Boost);
    }

    public void onLv2Buy(View view) {
        lv2.increaseLevel();
        totalIncome -= lv2.Cost;
        totalSumView.setText(Long.toString(totalIncome));
        levelBoost = (int) (levelBoost + lv2.Boost);
    }
}
