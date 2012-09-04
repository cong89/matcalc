package com.github.matcalc;

public enum KeypadButtons {
	ADD("+", KeypadTypes.SIMPLE_OPERATION),
	SUBTRACT("-", KeypadTypes.SIMPLE_OPERATION),
	MULTIPLY("x", KeypadTypes.SIMPLE_OPERATION),
	DIVIDE("/", KeypadTypes.COMPLEX_OPERATION);
	
	CharSequence mText;
	KeypadTypes mTypes;
	
	KeypadButtons(CharSequence text, KeypadTypes category){
		mText = text;
		mTypes = category;
	}
	
	public CharSequence getText(){
		return mText;
	}

}
