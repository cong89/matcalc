package com.android.matcalc;

public enum KeypadButtons {
	ADD("A+B", KeypadTypes.SIMPLE_OPERATION, R.string.add_sub_url),
	SUBTRACT("A-B", KeypadTypes.SIMPLE_OPERATION, R.string.add_sub_url),
	MULTIPLY("AB", KeypadTypes.SIMPLE_OPERATION, R.string.multiply_url),
	SOLVE("AX=B", KeypadTypes.COMPLEX_OPERATION, R.string.solve_url),
	DET("|A|", KeypadTypes.SIMPLE_OPERATION, R.string.det_url),
	INV("A^-1", KeypadTypes.COMPLEX_OPERATION, R.string.inv_url),
	POW2("A^2", KeypadTypes.COMPLEX_OPERATION, R.string.multiply_url),
	POW3("A^3", KeypadTypes.COMPLEX_OPERATION, R.string.multiply_url),
	TRANSPOSE("A'", KeypadTypes.SIMPLE_OPERATION, R.string.transpose_url),
	RANK("RK(A)", KeypadTypes.SIMPLE_OPERATION, R.string.rank_url),
	TRACE("TR(A)", KeypadTypes.SIMPLE_OPERATION, R.string.trace_url),
	EIGVALUE("D(A)", KeypadTypes.COMPLEX_OPERATION, R.string.eigen_url),
	EIGVECTOR("V(A)", KeypadTypes.COMPLEX_OPERATION, R.string.eigen_url),
	NORM1("||A||1", KeypadTypes.SIMPLE_OPERATION, R.string.norm_url),
	NORM2("||A||2", KeypadTypes.SIMPLE_OPERATION, R.string.norm_url),
	NORMINF("||A||Inf", KeypadTypes.SIMPLE_OPERATION, R.string.norm_url),
	LUL("LU:L", KeypadTypes.SIMPLE_OPERATION, R.string.lu_url),
	LUU("LU:U", KeypadTypes.SIMPLE_OPERATION, R.string.lu_url),
	SWAP("swap", KeypadTypes.OTHER, R.string.google_url),
	CLEARA("clr(A)", KeypadTypes.OTHER, R.string.google_url),
	DUMMY("", KeypadTypes.DUMMY, R.string.google_url);

	CharSequence mText;
	KeypadTypes mTypes;
	int mUrlId;
	
	KeypadButtons(CharSequence text, KeypadTypes category, int urlId){
		mUrlId = urlId;
		mText = text;
		mTypes = category;
	}
	
	public CharSequence getText(){
		return mText;
	}
	
	public int getUrlId(){
		return mUrlId;
	}
	
}
