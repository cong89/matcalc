package com.android.matcalc;

public class MatrixParser {

	// parse string into 2D double
	public static double[][] parse(String strX) {
		double[][] dblX = null;
		int i;
		int j;
		strX = strX.replace(" ", "");
		strX = strX.replace("\n\n", "\n");

		String[] strM = strX.split("\n");
		String[] strN = strM[0].split(",");
		String[] strTmp;

		dblX = new double[strM.length][strN.length];

		for (i = 0; i < strM.length; i++) {
			strTmp = strM[i].split(",");
			for (j = 0; j < strN.length; j++) {
				try {
					// dblX[i][j] = Double.valueOf(strTmp[j]).doubleValue();
					dblX[i][j] = Double.parseDouble(strTmp[j]);
				} catch (Exception e) {
					System.out.println("Parsing failed.");
					dblX = null;
					break;
				}
			}
			if (dblX == null) break;
		}
		return dblX;
	}

	// Convert double to string
	public static String dblToStr(double[][] x, int row, int col) {
		int i = 0;
		int j = 0;

		String strAns = "";

		for (i = 0; i < row; i++) {
			for (j = 0; j < col; j++) {
				strAns = strAns + String.format("%.4f", x[i][j]) + ", ";
			}
			strAns = strAns + "\n";
		}
		return strAns;

	}

}