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

public class MainActivity extends Activity {
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

	protected void ProcessKeypadInput(KeypadButtons keypadButton) {

		EditText matrixA = (EditText) findViewById(R.id.matrixA);
		EditText matrixB = (EditText) findViewById(R.id.matrixB);

		String strMatrixA = matrixA.getText().toString();
		String strMatrixB = matrixB.getText().toString();

		double[][] dblMatrixA = MatrixParser.parse(strMatrixA);
		double[][] dblMatrixB = MatrixParser.parse(strMatrixB);

		Matrix A = new Matrix(dblMatrixA);
		Matrix b = new Matrix(dblMatrixB);
		Matrix x;

		double[][] dblAns;
		int row;
		int col;
		String ans;

		// switch between different button pressed
		switch (keypadButton) {
		case ADD:
			x = A.plus(b);
			break;

		case SUBTRACT:
			x = A.minus(b);
			break;

		case MULTIPLY:
			x = A.times(b);
			break;

		case DIVIDE:
			x = b.solve(A);
			break;

		default:
			System.out.println(keypadButton.getText().toString() + " "
					+ keypadButton.toString());
			x = null;
			break;
		}
		dblAns = x.getArrayCopy();
		row = x.getRowDimension();
		col = x.getColumnDimension();
		ans = MatrixParser.dblToStr(dblAns, row, col);

		// Toast to display which button is pressed
		Toast toast = Toast
				.makeText(MainActivity.this, keypadButton.getText().toString()
						+ " " + keypadButton.toString(), Toast.LENGTH_SHORT);

		// Dialog to show the results and additional options
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Output:")
				.setMessage(ans)
				.setCancelable(false)
				.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								MainActivity.this.finish();
							}
						})
				.setNegativeButton("No", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				});
		AlertDialog alert = builder.create();

		// Showing dialog and toast
		toast.show();
		alert.show();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
}
