package com.github.matcalc;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

public class KeypadAdapter extends BaseAdapter {
	private Context mContext;

	public KeypadAdapter(Context c) {
		mContext = c;
	}

	public int getCount() {
		return mButtons.length;
	}

	public Object getItem(int position) {
		return mButtons[position];
	}

	public long getItemId(int position) {
		return 0;
	}
	
	private KeypadButtons[] mButtons = { KeypadButtons.ADD, KeypadButtons.SUBTRACT, 
			KeypadButtons.MULTIPLY, KeypadButtons.DIVIDE };

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		  Button btn;
		  if (convertView == null) { // if it's not recycled, initialize some attributes
		    btn = new Button(mContext);
		    KeypadButtons keypadButton = mButtons[position];
								
		    // Set CalculatorButton enumeration as tag of the button so that we
		    // will use this information from our main view to identify what to do
		    btn.setTag(keypadButton);
		  } 
		  else {
		    btn = (Button) convertView;
		  }

		  btn.setText(mButtons[position].getText());
		  return btn;
		}

}
