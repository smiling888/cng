package com.cng.internet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import android.R.integer;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.gow.qrcodedemo.R;

//http://1.testxxxyy.sinaapp.com/?username=xx
public class Intenertdemo extends Activity {
	Button bt;
	TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.button_text);
		bt = (Button) findViewById(R.id.button1);
		tv = (TextView) findViewById(R.id.tv1);
		bt.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				switch (v.getId()) {
				case R.id.button1:

					Map<String, String> params = new HashMap<String, String>();
					params.put("username", "xxxyyyadsa");

					// tv.setText(getRequest(params));
					tv.setText("http://1.testxxxyy.sinaapp.com/?username=xx");
					break;
				}

			}
		});

	}

	public String mySubmit(String uString) throws IOException {
		URL url = new URL(uString);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		try {
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			InputStream input = connection.getInputStream();
			if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
				return null;
			}

			byte[] data = new byte[1024];
			int len = 0;
			try {
				while ((len = input.read(data)) != -1) {
					byteArrayOutputStream.write(data, 0, len);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			return new String(byteArrayOutputStream.toByteArray());
		} finally {
			connection.disconnect();
		}

	}

	/*
	 * Function : 发送Post请求到服务器 Param : params请求体内容，encode编码格式 Author : 博客园-依旧淡然
	 */
	public static String submitPostData(String url1) {

		byte[] data = getRequestData(null, null).toString().getBytes();//
		// 获得请求体

		try {
			URL url = new URL(url1);
			HttpURLConnection httpURLConnection = (HttpURLConnection) url
					.openConnection();
			httpURLConnection.setConnectTimeout(3000); // 设置连接超时时间
			httpURLConnection.setDoInput(true); // 打开输入流，以便从服务器获取数据
			httpURLConnection.setDoOutput(true); // 打开输出流，以便向服务器提交数据
			httpURLConnection.setRequestMethod("POST"); // 设置以Post方式提交数据
			httpURLConnection.setUseCaches(false); // 使用Post方式不能使用缓存
			// 设置请求体的类型是文本类型
			httpURLConnection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			// 设置请求体的长度
			httpURLConnection.setRequestProperty("Content-Length",
					String.valueOf(data.length));
			// 获得输出流，向服务器写入数据
			OutputStream outputStream = httpURLConnection.getOutputStream();
			outputStream.write(data);

			int response = httpURLConnection.getResponseCode(); // 获得服务器的响应码
			if (response == HttpURLConnection.HTTP_OK) {
				InputStream inptStream = httpURLConnection.getInputStream();
				return dealResponseResult(inptStream); // 处理服务器的响应结果
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}

	/*
	 * Function : 处理服务器的响应结果（将输入流转化成字符串） Param : inputStream服务器的响应输入流 Author :
	 * 博客园-依旧淡然
	 */
	public static String dealResponseResult(InputStream inputStream) {
		String resultData = null; // 存储处理结果
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		byte[] data = new byte[1024];
		int len = 0;
		try {
			while ((len = inputStream.read(data)) != -1) {
				byteArrayOutputStream.write(data, 0, len);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		resultData = new String(byteArrayOutputStream.toByteArray());
		return resultData;
	}

	/*
	 * Function : 封装请求体信息 Param : params请求体内容，encode编码格式 Author : 博客园-依旧淡然
	 */
	public static StringBuffer getRequestData(Map<String, String> params,
			String encode) {
		StringBuffer stringBuffer = new StringBuffer(); // 存储封装好的请求体信息
		try {
			for (Map.Entry<String, String> entry : params.entrySet()) {
				stringBuffer.append(entry.getKey()).append("=")
						.append(URLEncoder.encode(entry.getValue(), encode))
						.append("&");
			}
			stringBuffer.deleteCharAt(stringBuffer.length() - 1); // 删除最后的一个"&"
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stringBuffer;
	}

}
