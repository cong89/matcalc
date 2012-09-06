package com.github.matcalc;

public enum KeypadButtons {
	ADD("A+B", KeypadTypes.SIMPLE_OPERATION),
	SUBTRACT("A-B", KeypadTypes.SIMPLE_OPERATION),
	MULTIPLY("AB", KeypadTypes.SIMPLE_OPERATION),
	SOLVE("A*X=B", KeypadTypes.COMPLEX_OPERATION),
	DET("|A|", KeypadTypes.SIMPLE_OPERATION),
	INV("A^-1", KeypadTypes.COMPLEX_OPERATION),
	POW2("A^2", KeypadTypes.COMPLEX_OPERATION),
	POWN("A^n", KeypadTypes.COMPLEX_OPERATION),
	TRANSPOSE("A'", KeypadTypes.SIMPLE_OPERATION),
	RANK("rk(A)", KeypadTypes.SIMPLE_OPERATION),
	TRACE("tr(A)", KeypadTypes.SIMPLE_OPERATION),
	EIGVALUE("eig(A)", KeypadTypes.COMPLEX_OPERATION),
	EIGVECTOR("eigv(A)", KeypadTypes.COMPLEX_OPERATION),
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
