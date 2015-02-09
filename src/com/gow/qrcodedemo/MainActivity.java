package com.gow.qrcodedemo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.cng.show.ShowInfoActivity;
import com.google.zxing.CaptureActivity;
import com.gow.qrcodedemo.utils.QRCodeUtils;

public class MainActivity extends Activity implements OnClickListener {
	private final String TEST_DATA = "smiling test";
	static final private int GET_CODE = 0;
	private ImageView mImageViewQRCode;
	private TextView mTextViewScan;
	private WindowManager wm;
	private final String QRRESULT = "qrresult";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		initView();
//		setQRCodeImage();
	}

	private void initView() {
		mImageViewQRCode = (ImageView) findViewById(R.id.qrcode_img);
		mTextViewScan = (TextView) findViewById(R.id.scan_text);
		mTextViewScan.setOnClickListener(this);
	}

	private void setQRCodeImage() {
		wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
		int width = wm.getDefaultDisplay().getWidth();// 屏幕宽度
		Bitmap mBitmap = QRCodeUtils.createImage(TEST_DATA, (width * 2) / 3,
				(width * 2) / 3, BitmapFactory.decodeResource(getResources(),
						R.drawable.ic_launcher));
		mImageViewQRCode.setImageBitmap(mBitmap);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == Activity.RESULT_OK && requestCode == GET_CODE) {
			final String result = data.getExtras().getString("result");
//			mTextViewScan.setText(result);
			Intent intent = new Intent(MainActivity.this,
					ShowInfoActivity.class);
			intent.putExtra(QRRESULT, result);
			startActivity(intent);

			// 增加用浏览器打开的操作
			// new AlertDialog.Builder(this)
			// .setTitle("确认")
			// .setMessage("是否打开链接:" + result)
			// .setPositiveButton("是",
			// new DialogInterface.OnClickListener() {
			// @Override
			// public void onClick(DialogInterface dialog,
			// int which) {
			// Intent intent = new Intent();
			// intent.setAction("android.intent.action.VIEW");
			// Uri content_url = Uri.parse(result);
			// intent.setData(content_url);
			// startActivity(intent);
			// }
			//
			// }).setNegativeButton("否", null).show();
			//
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		startActivityForResult(new Intent(this, CaptureActivity.class),
				GET_CODE);
//		Intent intent = new Intent(MainActivity.this, ShowInfoActivity.class);
//		intent.putExtra(QRRESULT,
//				"date,2015/01/01 14:58:32;vehicle_id,京x;氮气,08;c02,1;h2,1");
//		startActivity(intent);
	}

}
