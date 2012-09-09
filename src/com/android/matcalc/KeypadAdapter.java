package com.android.matcalc;

import android.content.Context;
import android.graphics.PorterDuff;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

public class KeypadAdapter extends BaseAdapter {
	private Context mContext;

	// Declare button click listener variable
	private OnClickListener mOnButtonClick;

	// Method to set button click listener variable
	public void setOnButtonClickListener(OnClickListener listener) {
		mOnButtonClick = listener;
	}

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

	private KeypadButtons[] mButtons = { KeypadButtons.ADD,
			KeypadButtons.SUBTRACT, KeypadButtons.MULTIPLY,
			KeypadButtons.SOLVE, KeypadButtons.DET, KeypadButtons.INV,
			KeypadButtons.TRANSPOSE, KeypadButtons.POW2, KeypadButtons.NORM1,
			KeypadButtons.NORM2, KeypadButtons.NORMINF, KeypadButtons.POW3,
			KeypadButtons.RANK, KeypadButtons.TRACE, KeypadButtons.EIGVALUE,
			KeypadButtons.EIGVECTOR, KeypadButtons.LUL, KeypadButtons.LUU,
			KeypadButtons.SWAP , KeypadButtons.CLEARA};

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		Button btn;
		KeypadButtons keypadButton = mButtons[position];

		if (convertView == null) { // if it's not recycled, initialize some
									// attributes
			btn = new Button(mContext);
			
			if (keypadButton != KeypadButtons.DUMMY) {
				btn.setOnClickListener(mOnButtonClick);
			}

		} else {
			btn = (Button) convertView;
		}

		btn.setTag(keypadButton);
		btn.setText(mButtons[position].getTxtId());
		
		if (keypadButton == KeypadButtons.SWAP || keypadButton == KeypadButtons.CLEARA){
			btn.getBackground().setColorFilter(0xFFBBBBBB, PorterDuff.Mode.MULTIPLY);
		} else {
			btn.getBackground().setColorFilter(0xFFFFFFFF, PorterDuff.Mode.MULTIPLY);	
		}
	
		return btn;
	}

}
