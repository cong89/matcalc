package com.android.matcalc;

public enum KeypadButtons {
	ADD("A+B", R.string.add_sub_url),
	SUBTRACT("A-B", R.string.add_sub_url),
	MULTIPLY("AB", R.string.multiply_url),
	SOLVE("AX=B", R.string.solve_url),
	DET("|A|", R.string.det_url),
	INV("A^-1", R.string.inv_url),
	POW2("A^2", R.string.multiply_url),
	POW3("A^3", R.string.multiply_url),
	TRANSPOSE("A'", R.string.transpose_url),
	RANK("RK(A)", R.string.rank_url),
	TRACE("TR(A)", R.string.trace_url),
	EIGVALUE("D(A)", R.string.eigen_url),
	EIGVECTOR("V(A)", R.string.eigen_url),
	NORM1("||A||1", R.string.norm_url),
	NORM2("||A||2", R.string.norm_url),
	NORMINF("||A||Inf", R.string.norm_url),
	LUL("LU:L", R.string.lu_url),
	LUU("LU:U", R.string.lu_url),
	SWAP("swap", R.string.google_url),
	CLEARA("clr(A)", R.string.google_url),
	DUMMY("", R.string.google_url);

	CharSequence mText;
//	KeypadTypes mTypes;
	int mUrlId;
	
	KeypadButtons(CharSequence text, int urlId){
		mUrlId = urlId;
		mText = text;
//		mTypes = category;
	}
	
	public CharSequence getText(){
		return mText;
	}
	
	public int getUrlId(){
		return mUrlId;
	}
	
}
