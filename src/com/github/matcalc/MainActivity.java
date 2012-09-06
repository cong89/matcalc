package com.github.matcalc;

import Jama.Matrix;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
//import android.widget.Toast;
import com.android.matcalc.R;

public class MainActivity extends Activity {

	public static final int MATRIX_RESULT = 0;
	public static final int DOUBLE_RESULT = 1;
	public static final int INT_RESULT = 2;

	public static final int WRONG_DIMENSION = 0;
	public static final int PARSING_FAILED = 1;
	public static final int NO_INVERSE = 2;
	public static final int NOT_SQUARE = 3;

	GridView mKeypadGrid;
	KeypadAdapter mKeypadAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Get reference to the keypad button GridView
		mKeypadGrid = (GridView) findViewById(R.id.gridView);

		// Create Keypad Adapter
		mKeypadAdapter = new KeypadAdapter(this);

		// Set adapter of the keypad grid
		mKeypadGrid.setAdapter(mKeypadAdapter);

		// Set button click listener of the keypad adapter
		mKeypadAdapter.setOnButtonClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Button btn = (Button) v;
				// Get the KeypadButton value which is used to identify the
				// keypad button from the Button's tag
				KeypadButtons keypadButton = (KeypadButtons) btn.getTag();

				// Process keypad button
				ProcessKeypadInput(keypadButton);
			}
		});

		mKeypadGrid.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {

			}
		});

	}

	private Matrix getMatrix(int id) {

		EditText matrix = (EditText) findViewById(id);
		String strMatrix = matrix.getText().toString();
		double[][] dblMatrix = MatrixParser.parse(strMatrix);
		if (dblMatrix == null) {
			return null;
		} else {
			Matrix X = new Matrix(dblMatrix);
			return X;
		}
	}

	private boolean isSquare(Matrix A) {
		return (A.getColumnDimension() == A.getRowDimension());
	}

	private void ProcessKeypadInput(KeypadButtons keypadButton) {

		boolean valid = false;
		Matrix A;
		Matrix b;

		Matrix mtxAns = null;
		double dblAns = 0.0;
		int intAns = -1;

		double[][] dblTmpAns;

		int row;
		int col;

		int ansType = -1;
		int errType = -1;
		String ans = "No result";

		// switch between different button pressed
		switch (keypadButton) {

		case ADD:
			ansType = MATRIX_RESULT;
			A = getMatrix(R.id.matrixA);
			b = getMatrix(R.id.matrixB);

			if (A != null && b != null) {
				if (A.getColumnDimension() == b.getColumnDimension()
						&& A.getRowDimension() == b.getRowDimension()) {
					mtxAns = A.plus(b);
					valid = true;
				} else {
					errType = WRONG_DIMENSION;
				}
			} else {
				errType = PARSING_FAILED;
			}
			break;

		case SUBTRACT:
			ansType = MATRIX_RESULT;
			A = getMatrix(R.id.matrixA);
			b = getMatrix(R.id.matrixB);

			if (A != null && b != null) {
				if (A.getColumnDimension() == b.getColumnDimension()
						&& A.getRowDimension() == b.getRowDimension()) {
					mtxAns = A.minus(b);
					valid = true;
				} else {
					errType = WRONG_DIMENSION;
				}
			} else {
				errType = PARSING_FAILED;
			}
			break;

		case MULTIPLY:
			ansType = MATRIX_RESULT;
			A = getMatrix(R.id.matrixA);
			b = getMatrix(R.id.matrixB);

			if (A != null && b != null) {
				if (A.getColumnDimension() == b.getRowDimension()) {
					mtxAns = A.times(b);
					valid = true;
				} else {
					errType = WRONG_DIMENSION;
				}
			} else {
				errType = PARSING_FAILED;
			}
			break;

		case DIVIDE:
			ansType = MATRIX_RESULT;
			A = getMatrix(R.id.matrixA);
			b = getMatrix(R.id.matrixB);
			if (A != null && b != null) {
				if (A.getColumnDimension() == b.getRowDimension()) {
					mtxAns = b.solve(A);
					valid = true;
				} else {
					errType = WRONG_DIMENSION;
				}
			} else {
				errType = PARSING_FAILED;
			}
			break;

		case DET:
			ansType = DOUBLE_RESULT;
			A = getMatrix(R.id.matrixA);
			if (A != null) {
				if (isSquare(A)) {
					dblAns = A.det();
					valid = true;
				} else {
					errType = NOT_SQUARE;
				}
			} else {
				errType = PARSING_FAILED;
			}
			break;

		case INV:
			ansType = MATRIX_RESULT;
			A = getMatrix(R.id.matrixA);
			if (A != null) { // check for null, then square, then det!=0
				if (isSquare(A)) {
					if (!MatrixParser.isZero(A.det())) {
						mtxAns = A.inverse();
						valid = true;
					} else {
						errType = NO_INVERSE;
					}
				} else {
					errType = NOT_SQUARE;
				}
			} else {
				errType = PARSING_FAILED;
			}
			break;

		case POW2:
			ansType = MATRIX_RESULT;
			A = getMatrix(R.id.matrixA);
			if (A != null) { // check for null, then square
				if (isSquare(A)) {
					mtxAns = A.times(A);
					valid = true;
				}
			} else {
				errType = PARSING_FAILED;
			}
			break;

		case TRANSPOSE:
			ansType = MATRIX_RESULT;
			A = getMatrix(R.id.matrixA);
			if (A != null) {
				mtxAns = A.transpose();
				valid = true;
			} else {
				errType = PARSING_FAILED;
			}
			break;

		case RANK:
			ansType = INT_RESULT;
			A = getMatrix(R.id.matrixA);
			if (A != null) {
				intAns = A.rank();
				valid = true;
			} else {
				errType = PARSING_FAILED;
			}
			break;

		case TRACE:
			ansType = DOUBLE_RESULT;
			A = getMatrix(R.id.matrixA);
			if (A != null) {
				if (isSquare(A)) {
					dblAns = A.trace();
					valid = true;
				} else {
					errType = NOT_SQUARE;
				}
			} else {
				errType = PARSING_FAILED;
			}
			break;

		case NORM1:
			ansType = DOUBLE_RESULT;
			A = getMatrix(R.id.matrixA);
			if (A != null){
				dblAns = A.norm1();
				valid = true;
			} else {
				errType = PARSING_FAILED;
			}
			break;

		case NORM2:
			ansType = DOUBLE_RESULT;
			A = getMatrix(R.id.matrixA);
			if (A != null){
				dblAns = A.norm2();
				valid = true;
			} else {
				errType = PARSING_FAILED;
			}
			break;

		case NORMINF:
			ansType = DOUBLE_RESULT;
			A = getMatrix(R.id.matrixA);
			if (A != null){
				dblAns = A.normInf();
				valid = true;
			} else {
				errType = PARSING_FAILED;
			}
			break;
		default:
			System.out.println("default error: " + keypadButton.toString());
			break;
		}

		if (valid) {
			if (ansType == MATRIX_RESULT) {
				dblTmpAns = mtxAns.getArrayCopy();
				row = mtxAns.getRowDimension();
				col = mtxAns.getColumnDimension();
				ans = MatrixParser.dblToStr(dblTmpAns, row, col);
			} else if (ansType == DOUBLE_RESULT) {
				ans = String.format("%.4f", dblAns);
			} else if (ansType == INT_RESULT) {
				ans = String.format("%d", intAns);
			}

			// Toast to display which button is pressed
//			Toast toast = Toast.makeText(MainActivity.this, keypadButton
//					.getText().toString() + " " + keypadButton.toString(),
//					Toast.LENGTH_SHORT);

			// Dialog to show the results and additional options
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("Output:")
					.setMessage(ans)
					.setCancelable(false)
					.setPositiveButton("Why?",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int id) {
									MainActivity.this.finish();
								}
							})
					.setNeutralButton("Copy",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									dialog.cancel();

								}
							})
					.setNegativeButton("Ok",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int id) {
									dialog.cancel();
								}
							});
			AlertDialog alert = builder.create();

			// Showing dialog and toast
//			toast.show();
			alert.show();
		} else {

			String errMsg;
			if (errType == WRONG_DIMENSION) {
				errMsg = "Incorrect dimension";
			} else if (errType == PARSING_FAILED) {
				errMsg = "Invalid matrix";
			} else if (errType == NO_INVERSE) {
				errMsg = "No inverse (|A| = 0)";
			} else if (errType == NOT_SQUARE) {
				errMsg = "Not square matrix";
			} else {
				errMsg = "Unknown error";
			}

			// Dialog to say invalid operation
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("Sorry!")
					.setMessage(errMsg)
					.setCancelable(false)
					.setNegativeButton("Ok",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int id) {
									dialog.cancel();
								}
							});
			AlertDialog alert = builder.create();

			alert.show();

			// Toast to display an invalid operation
//			Toast.makeText(MainActivity.this, "invalid operation",
//					Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
}
