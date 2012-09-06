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

	private void ProcessKeypadInput(KeypadButtons keypadButton) {

		boolean valid = false;
		boolean parsed = false;
		Matrix A;
		Matrix b;

		Matrix mtxAns = null;
		double dblAns = 0.0;
		int intAns = -1;

		double[][] dblTmpAns;

		int row;
		int col;

		int ansType = -1;

		String ans = "No result";
		String errMsg = "Unknown error";

		// switch between different button pressed
		switch (keypadButton) {

		case ADD:
			ansType = MATRIX_RESULT;
			A = getMatrix(R.id.matrixA);
			b = getMatrix(R.id.matrixB);

			if (A != null && b != null) {
				parsed = true;
				try {
					mtxAns = A.plus(b);
					valid = true;
				} catch (Exception e) {
					errMsg = e.getMessage();
				}
			}
			break;

		case SUBTRACT:
			ansType = MATRIX_RESULT;
			A = getMatrix(R.id.matrixA);
			b = getMatrix(R.id.matrixB);

			if (A != null && b != null) {
				parsed = true;
				try {
					mtxAns = A.minus(b);
					valid = true;
				} catch (Exception e) {
					errMsg = e.getMessage();
				}
			}
			break;

		case MULTIPLY:
			ansType = MATRIX_RESULT;
			A = getMatrix(R.id.matrixA);
			b = getMatrix(R.id.matrixB);

			if (A != null && b != null) {
				parsed = true;
				try {
					mtxAns = A.times(b);
					valid = true;
				} catch (Exception e){
					errMsg = e.getMessage();
				}
			}
			break;

		case SOLVE:
			ansType = MATRIX_RESULT;
			A = getMatrix(R.id.matrixA);
			b = getMatrix(R.id.matrixB);
			if (A != null && b != null) {
				parsed = true;
				try {
					mtxAns = A.solve(b);
					valid = true;
				} catch (Exception e) {
					errMsg = e.getMessage();
				}
			}
			break;

		/* SPECIAL CASE, manually implemented errMsg 
		 * because it wasn't giving the correct exception message*/ 
		case DET: 
			ansType = DOUBLE_RESULT;
			A = getMatrix(R.id.matrixA);
			if (A != null) {
				parsed = true;
				
				if (A.getColumnDimension() == A.getRowDimension()){
				try {
					dblAns = A.det();
					valid = true;
				} catch (Exception e) {
					errMsg = e.getMessage();
				}
				} else {
					errMsg = "Matrix must be square.";
				}
			}
			break;

		case INV:
			ansType = MATRIX_RESULT;
			A = getMatrix(R.id.matrixA);
			if (A != null) {
				parsed = true;
				try {
					mtxAns = A.inverse();
					valid = true;
				} catch (Exception e) {
					errMsg = e.getMessage();
				}
			}
			break;

		case POW2:
			ansType = MATRIX_RESULT;
			A = getMatrix(R.id.matrixA);
			if (A != null) {
				parsed = true;
				try {
					mtxAns = A.times(A);
					valid = true;
				} catch (Exception e) {
					errMsg = e.getMessage();
				}

			}
			break;

		case TRANSPOSE:
			ansType = MATRIX_RESULT;
			A = getMatrix(R.id.matrixA);
			if (A != null) {
				parsed = true;
				try {
					mtxAns = A.transpose();
					valid = true;
				} catch (Exception e) {
					errMsg = e.getMessage();
				}
			}
			break;

		case RANK:
			ansType = INT_RESULT;
			A = getMatrix(R.id.matrixA);
			if (A != null) {
				parsed = true;
				try {
					intAns = A.rank();
					valid = true;
				} catch (Exception e) {
					errMsg = e.getMessage();
				}
			}
			break;

		case TRACE:
			ansType = DOUBLE_RESULT;
			A = getMatrix(R.id.matrixA);
			if (A != null) {
				parsed = true;
				try {
					dblAns = A.trace();
					valid = true;
				} catch (Exception e) {
					errMsg = e.getMessage();
				}

			}
			break;

		case NORM1:
			ansType = DOUBLE_RESULT;
			A = getMatrix(R.id.matrixA);
			if (A != null) {
				parsed = true;
				try {
					dblAns = A.norm1();
					valid = true;
				} catch (Exception e) {
					errMsg = e.getMessage();
				}
			}
			break;

		case NORM2:
			ansType = DOUBLE_RESULT;
			A = getMatrix(R.id.matrixA);
			if (A != null) {
				parsed = true;
				try {
					dblAns = A.norm2();
					valid = true;
				} catch (Exception e) {
					errMsg = e.getMessage();
				}
			}
			break;

		case NORMINF:
			ansType = DOUBLE_RESULT;
			A = getMatrix(R.id.matrixA);
			if (A != null) {
				parsed = true;
				try {
					dblAns = A.normInf();
					valid = true;
				} catch (Exception e) {
					errMsg = e.getMessage();
				}
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
			// Toast toast = Toast.makeText(MainActivity.this, keypadButton
			// .getText().toString() + " " + keypadButton.toString(),
			// Toast.LENGTH_SHORT);

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
									/*
									 * http://stackoverflow.com/questions/9027629
									 * /
									 * android-clipboard-code-that-works-on-all-
									 * api-levels
									 */
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
			// toast.show();
			alert.show();
		} else {

			if (!parsed) {
				errMsg = "Invalid matrix.";
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
			// Toast.makeText(MainActivity.this, "invalid operation",
			// Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
}
