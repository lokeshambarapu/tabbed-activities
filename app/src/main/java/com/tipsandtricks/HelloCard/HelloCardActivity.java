package com.tipsandtricks.HelloCard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.BreakIterator;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;

public class HelloCardActivity extends Activity
{
	/**
	 * Called when the activity is first created.
	 */
	TextView etResponse;
	TextView etResponse1;
	TextView etResponse2;
	TextView etResponse3;
	TextView etResponse4;
	TextView etResponse5;
	TextView etResponse6;
	TextView etResponse7;


	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hello_card);

		etResponse = (TextView) findViewById(R.id.etResponse);
		etResponse1 = (TextView) findViewById(R.id.etResponse1);
		etResponse2 = (TextView) findViewById(R.id.etResponse2);
		etResponse3 = (TextView) findViewById(R.id.etResponse3);
		etResponse4 = (TextView) findViewById(R.id.etResponse4);
		etResponse5 = (TextView) findViewById(R.id.etResponse5);
		etResponse6 = (TextView) findViewById(R.id.etResponse4);
		etResponse7 = (TextView) findViewById(R.id.etResponse5);

		findViewById(R.id.etResponse).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				Intent intent = new Intent(HelloCardActivity.this, data.class);
				startActivity(intent);
			}
		});
		findViewById(R.id.etResponse1).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				Intent intent = new Intent(HelloCardActivity.this, HelloCardListActivity.class);
				startActivity(intent);
			}
		});


		// check if you are connected or not

		// call AsynTask to perform network operation on separate thread
		new HttpAsyncTask().execute("http://blog-intigrent.cloudapp.net/");
	}

	public static String GET(String url){
		InputStream inputStream = null;
		String result = "";
		try {

			// create HttpClient
			HttpClient httpclient = new DefaultHttpClient();

			// make GET request to the given URL
			HttpResponse httpResponse = httpclient.execute(new HttpGet(url));

			// receive response as inputStream
			inputStream = httpResponse.getEntity().getContent();

			// convert inputstream to string
			if(inputStream != null)
				result = convertInputStreamToString(inputStream);
			else
				result = "Did not work!";

		} catch (Exception e) {
			Log.d("InputStream", e.getLocalizedMessage());
		}

		return result;
	}

	private static String convertInputStreamToString(InputStream inputStream) throws IOException{
		BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
		String line = "";
		String result = "";
		while((line = bufferedReader.readLine()) != null)
			result += line;

		inputStream.close();
		return result;

	}

	private class HttpAsyncTask extends AsyncTask<String, Void, String> {
		@Override
		protected String doInBackground(String... urls) {

			return GET(urls[0]);
		}
		// onPostExecute displays the results of the AsyncTask.
		@Override
		protected void onPostExecute(String result) {
			Toast.makeText(getBaseContext(), "Received!", Toast.LENGTH_LONG).show();
			//etResponse.setText(result);
			try {
				JSONObject json = new JSONObject(result);

				String str = "";
				String str1 = "";
				String str2 = "";
				String str3 = "";
				String str4 = "";
				String str5 = "";
				String str6 = "";
				String str7 = "";




				JSONArray articles = json.getJSONArray("articles");

				str += articles.getJSONObject(0).getString("title");
				str += "\n";
				str += " author: "+articles.getJSONObject(0).getString("author");
				etResponse.setText(str);
				//etResponse.setText(json.toString(1));
				str1 += articles.getJSONObject(1).getString("title");
				str1 += "\n";
				str1 += articles.getJSONObject(1).getString("author");
				str1 += articles.getJSONObject(1).getString("published");
				etResponse1.setText(str1);

				str2 += articles.getJSONObject(2).getString("title");
				str2 += "\n";
				str2 += " author: "+articles.getJSONObject(2).getString("author");
				etResponse2.setText(str2);

				str3 += articles.getJSONObject(3).getString("title");
				str3 += "\n";
				str3 += " author: "+articles.getJSONObject(3).getString("author");
				etResponse3.setText(str3);

				str4 += articles.getJSONObject(4).getString("title");
				str4 += "\n";
				str4 += " author: "+articles.getJSONObject(4).getString("author");
				etResponse4.setText(str4);

				str5 += articles.getJSONObject(5).getString("title");
				str5 += "\n";
				str5 += " author: "+articles.getJSONObject(5).getString("author");
				etResponse5.setText(str5);

				str6 += articles.getJSONObject(6).getString("title");
				str6 += "\n";
				str6 += " author: "+articles.getJSONObject(6).getString("author");
				etResponse6.setText(str6);

				str7 += articles.getJSONObject(7).getString("title");
				str7 += "\n";
				str7 += " author: "+articles.getJSONObject(7).getString("author");
				etResponse7.setText(str7);


			}

			catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}



		}
	}
}