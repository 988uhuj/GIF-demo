package com.example.gif_test;

import java.io.IOException;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.apache.http.Header;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;
import android.app.Activity;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.BinaryHttpResponseHandler;

@EActivity(R.layout.activity_main)
public class MainActivity extends Activity {

	private final String TAG = "MainActivity";
	
	@ViewById(R.id.gifImageView)
	GifImageView gifImageView;

	@AfterInject
	void load(){
		String url = "http://img3.3lian.com/2006/013/08/20051105162836928.gif";
//		String url = "http://cdn.v2ex.com/avatar/83dd/3f9f/7329_large.png";
		Log.d(TAG, "load");
		String[] allowedTypes = new String[] { "image/gif", "image/png" };	// need
		AsyncHttpClient client = new AsyncHttpClient();
		client.get(url, new BinaryHttpResponseHandler(allowedTypes){

			@Override
			public void onSuccess(byte[] binaryData) {
				Log.d(TAG, "byte[] length = " + binaryData.length);
				
				try {
					GifDrawable gifFromBytes = new GifDrawable( binaryData );
					Log.d(TAG, "load successfully");
					gifImageView.setImageDrawable(gifFromBytes);
				} catch (IOException e) {
					Log.d(TAG, e.getMessage());
					e.printStackTrace();
				}
			}

			@Override
			public void onFailure(int statusCode, Header[] headers,
					byte[] binaryData, Throwable error) {
				Log.d(TAG, "error = " + error.getMessage());
			}
			
		});
	}

}
