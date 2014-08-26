package org.shujito.addressbook;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * An activity with a button that pops an alert dialog, nothing special.
 * @author shujito
 *
 */
public class SimpleButtonDialogActivity extends ActionBarActivity implements OnClickListener
{
	private Button mBtnHello = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_simple_button_dialog);
		this.mBtnHello = (Button) this.findViewById(R.id.btn_hello);
		this.mBtnHello.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v)
	{
		new AlertDialog.Builder(this)
			.setTitle("Hi there")
			.setPositiveButton(android.R.string.ok, null)
			.show();
	}
}
