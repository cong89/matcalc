package com.github.matcalc;

public enum KeypadButtons {
	ADD("A+B", KeypadTypes.SIMPLE_OPERATION),
	SUBTRACT("A-B", KeypadTypes.SIMPLE_OPERATION),
	MULTIPLY("A*B", KeypadTypes.SIMPLE_OPERATION),
	DIVIDE("A/B", KeypadTypes.COMPLEX_OPERATION),
	DET("det(A)", KeypadTypes.SIMPLE_OPERATION),
	INV("inv(A)", KeypadTypes.COMPLEX_OPERATION),
	POW2("A^2", KeypadTypes.COMPLEX_OPERATION),
	POWN("A^n", KeypadTypes.COMPLEX_OPERATION),
	TRANSPOSE("A'", KeypadTypes.SIMPLE_OPERATION),
	NORM1("||A||1", KeypadTypes.SIMPLE_OPERATION),
	NORM2("||A||2", KeypadTypes.SIMPLE_OPERATION),
	NORMINF("||A||Inf", KeypadTypes.SIMPLE_OPERATION),
	DUMMY("", KeypadTypes.DUMMY);

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
