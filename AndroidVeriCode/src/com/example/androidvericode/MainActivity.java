package com.example.androidvericode;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
/**
 * 描述：验证码的实现
 */
public class MainActivity extends Activity {

	private ImageView mImageView_code;
	private EditText mEditText_code;
	
	private VerificationCode code;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mImageView_code = (ImageView)findViewById(R.id.image_code);
		mEditText_code = (EditText)findViewById(R.id.edit_confirm_code);
		
		mImageView_code.setImageBitmap(code.getInstance().createBitmap());
		mImageView_code.setOnClickListener(onClick);
		
	}
	
	private OnClickListener onClick = new OnClickListener(){
		
		@Override
		public void onClick(View v) {
			
			mImageView_code.setImageBitmap(code.getInstance().createBitmap());
			mEditText_code.setText(code.getInstance().getCode());
		}
	};

}
