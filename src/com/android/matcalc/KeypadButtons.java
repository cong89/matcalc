package com.android.matcalc;

import com.cong89.matcalc.R;

public enum KeypadButtons {
	ADD(R.string.add_sub_url, R.string.add),
	SUBTRACT(R.string.add_sub_url, R.string.subtract),
	MULTIPLY(R.string.multiply_url, R.string.multiply),
	SOLVE(R.string.solve_url, R.string.solve),
	DET(R.string.det_url, R.string.det),
	INV(R.string.inv_url, R.string.inv),
	POW2(R.string.multiply_url, R.string.pow2),
	POW3(R.string.multiply_url, R.string.pow3),
	TRANSPOSE(R.string.transpose_url, R.string.trans),
	RANK(R.string.rank_url, R.string.rank),
	TRACE(R.string.trace_url, R.string.trace),
	EIGVALUE(R.string.eigen_url, R.string.eigValue),
	EIGVECTOR(R.string.eigen_url, R.string.eigVector),
	NORM1(R.string.norm_url, R.string.norm1),
	NORM2(R.string.norm_url, R.string.norm2),
	NORMINF(R.string.norm_url, R.string.normInf),
	LUL(R.string.lu_url, R.string.lul),
	LUU(R.string.lu_url, R.string.luu),
	SWAP(R.string.google_url, R.string.swap),
	CLEARA(R.string.google_url, R.string.clear),
	DUMMY(R.string.google_url, R.string.dummy);

	int mUrlId;
	int mTxtId;
	
	KeypadButtons(int urlId, int txtId){
		mUrlId = urlId;
		mTxtId = txtId;
	}
	
	public int getUrlId(){
		return mUrlId;
	}
	
	public int getTxtId(){
		return mTxtId;
	}
	
}


