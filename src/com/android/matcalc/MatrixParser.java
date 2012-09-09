package com.android.matcalc;

import java.text.NumberFormat;

public class MatrixParser {

	/* parse string into 2D double
	 * if failed then will return null
	 * note: some operations require two matrices
	 */
	public static double[][] parse(String strX) {
		double[][] dblX;
		int i;
		int j;
		String[] strM;
		String[] strN;
		String[] strTmp;
		strX = strX.replace(" ", "");
		strX = strX.replace("\n\n", "\n");

		try {
			strM = strX.split("\n");
			strN = strM[0].split(",");
			dblX = new double[strM.length][strN.length];
		} catch (Exception e){
			System.out.println("Splitting failed.");
			return null;
		}

		for (i = 0; i < strM.length; i++) {
			strTmp = strM[i].split(",");
			for (j = 0; j < strN.length; j++) {
				try {
					// dblX[i][j] = Double.valueOf(strTmp[j]).doubleValue();
					dblX[i][j] = Double.parseDouble(strTmp[j]);
				} catch (Exception e) {
					System.out.println("Parsing failed.");
					return null;
				}
			}
		}
		return dblX;
	}

	/* Convert double to string */
	public static String dblToStr(double[][] x, int row, int col) {
		
		int i = 0;
		int j = 0;
		String strAns = "";
		
		/* Using NumberFormat to have more control over formatted strings */
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(4);
		nf.setMinimumFractionDigits(0);

		for (i = 0; i < row; i++) {
			for (j = 0; j < col; j++) {
				strAns = strAns + nf.format(x[i][j]) + ", ";
			}
			strAns = strAns + "\n";
		}
		return strAns;

	}
	
	/* Display complex number */
	public static String dispComplex(double re[], double im[]){
		String str = ""; 
		int i = 0;
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(4);
		nf.setMinimumFractionDigits(0);
		for (i = 0; i < re.length; i++){
			str = str + nf.format(re[i]) + " + " + nf.format(im[i]) + "i\n";
		}
		return str;
		
	}

}