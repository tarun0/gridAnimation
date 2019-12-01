package com.tarun.gridapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

public class MainActivity extends AppCompatActivity {

    public static boolean hasMovedFromMainActivity = false;
    CustomGridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initAdapter(false);
    }

    private void initAdapter(boolean force) {

        if (gridView == null || force) {
            gridView = findViewById(R.id.grid);
        }

        gridView.setColumnCount(Constants.columnCount);
        gridView.setAlignmentMode(GridLayout.ALIGN_BOUNDS);
        LayoutInflater inflater = LayoutInflater.from(this);
        for (int i = 0; i < 20; i++) {
            final CardView card = (CardView) inflater.inflate(R.layout.grid_item, null);
            TextView tv = card.findViewById(R.id.tvItem);
            tv.setText("Item " + i);
            card.setTag(i);

            card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    gridView.removeViewAt(gridView.getIndex((int) card.getTag()));
                }
            });

            GridLayout.LayoutParams param = new GridLayout.LayoutParams();
            param.setGravity(Gravity.CENTER_HORIZONTAL);
            param.height = GridLayout.LayoutParams.WRAP_CONTENT;

            DisplayMetrics displayMetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int screenWidth = displayMetrics.widthPixels;

            param.width = (screenWidth - (Constants.spacing * (Constants.columnCount + 1))) / Constants.columnCount;
            param.topMargin = 5;
            param.leftMargin = Constants.spacing;
            card.setLayoutParams(param);

            gridView.addView(card, i);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (hasMovedFromMainActivity) {
            initAdapter(true);
        }
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
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
