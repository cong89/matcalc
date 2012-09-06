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
import android.widget.Toast;
import com.android.matcalc.R;

public class MainActivity extends Activity {

	public static final int DIMENSION_MISSMATCH = 0;
	public static final int PARSING_FAILED = 1;
	public static final int NO_INVERSE = 2;

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

	protected Matrix getMatrix(int id) {
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

	protected void ProcessKeypadInput(KeypadButtons keypadButton) {

		Matrix A;
		Matrix b;
		Matrix x = null;
		boolean valid = false;
		double[][] dblAns;
		int row;
		int col;
		String ans;

		// switch between different button pressed
		switch (keypadButton) {

		case ADD:
			A = getMatrix(R.id.matrixA);
			b = getMatrix(R.id.matrixB);

			if (A != null && b != null) {
				if (A.getColumnDimension() == b.getColumnDimension()
						&& A.getRowDimension() == b.getRowDimension()) {
					x = A.plus(b);
					valid = true;
				}
			}
			break;

		case SUBTRACT:
			A = getMatrix(R.id.matrixA);
			b = getMatrix(R.id.matrixB);

			if (A != null && b != null) {
				if (A.getColumnDimension() == b.getColumnDimension()
						&& A.getRowDimension() == b.getRowDimension()) {
					x = A.minus(b);
					valid = true;
				}
			}
			break;

		case MULTIPLY:
			A = getMatrix(R.id.matrixA);
			b = getMatrix(R.id.matrixB);

			if (A != null && b != null) {
				if (A.getColumnDimension() == b.getRowDimension()) {
					x = A.times(b);
					valid = true;
				}
			}
			break;

		case DIVIDE:
			A = getMatrix(R.id.matrixA);
			b = getMatrix(R.id.matrixB);
			if (A != null && b != null) {
				if (A.getColumnDimension() == b.getRowDimension()) {
					x = b.solve(A);
					valid = true;
				}
			}
			break;

		default:
			System.out.println("default error: " + keypadButton.toString());
			break;
		}

		if (valid) {
			dblAns = x.getArrayCopy();
			row = x.getRowDimension();
			col = x.getColumnDimension();
			ans = MatrixParser.dblToStr(dblAns, row, col);

			// Toast to display which button is pressed
			Toast toast = Toast.makeText(MainActivity.this, keypadButton
					.getText().toString() + " " + keypadButton.toString(),
					Toast.LENGTH_SHORT);

			// Dialog to show the results and additional options
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("Output:")
					.setMessage(ans)
					.setCancelable(false)
					.setPositiveButton("Yes",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int id) {
									MainActivity.this.finish();
								}
							})
					.setNeutralButton("Neutural",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									dialog.cancel();

								}
							})
					.setNegativeButton("No",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int id) {
									dialog.cancel();
								}
							});
			AlertDialog alert = builder.create();

			// Showing dialog and toast
			toast.show();
			alert.show();
		} else {

			// Dialog to say invalid operation
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("Sorry, invalid operation!")
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
			Toast.makeText(MainActivity.this, "invalid operation",
					Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
}
