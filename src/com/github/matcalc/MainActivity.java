package com.github.matcalc;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;

public class MainActivity extends Activity {
	 GridView mKeypadGrid;
	 KeypadAdapter mKeypadAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // Get reference to the keypad button GridView
        mKeypadGrid = (GridView) findViewById(R.id.gridView);
        
        // Create Keypad Adapter
        mKeypadAdapter = new KeypadAdapter(this);
        
        // Set adapter of the keypad grid
        mKeypadGrid.setAdapter(mKeypadAdapter);
        
        mKeypadGrid.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
//                Toast.makeText(MainActivity.this, "" + position, Toast.LENGTH_LONG).show();
            }
        });

    }
  

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
