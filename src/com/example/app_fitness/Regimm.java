package com.example.app_fitness;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

public class Regimm extends Activity {

	String id=MainActivity.myId;
	
	String urladdress=id+"/coach/readRm.php";
    String[] jour;
    String[] desc;
    ListView listViewm;
    BufferedInputStream is;
    String line=null;
    String result=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_regimm);
		
		 listViewm=(ListView)findViewById(R.id.lviewm);

	        StrictMode.setThreadPolicy((new StrictMode.ThreadPolicy.Builder().permitNetwork().build()));
	        collectData();
	        CustomListViewR customListView=new CustomListViewR(this,jour,desc);
	        listViewm.setAdapter(customListView);
	        
		
		
	}

	private void collectData()
 {
//Connection
     try{

         URL url=new URL(urladdress);
         HttpURLConnection con=(HttpURLConnection)url.openConnection();
         con.setRequestMethod("POST");
         is=new BufferedInputStream(con.getInputStream());

     }
     catch (Exception ex)
     {
         ex.printStackTrace();
     }
//content
     try{
         BufferedReader br=new BufferedReader(new InputStreamReader(is));
         StringBuilder sb=new StringBuilder();
         while ((line=br.readLine())!=null){
             sb.append(line+"\n");
         }
         is.close();
         result=sb.toString();

     }
     catch (Exception ex)
     {
         ex.printStackTrace();

     }

//JSON
     try{
         JSONArray ja=new JSONArray(result);
         JSONObject jo=null;
         jour=new String[ja.length()];
         desc=new String[ja.length()];
         

         for(int i=0;i<=ja.length();i++){
             jo=ja.getJSONObject(i);
             jour[i]=jo.getString("jour");
             desc[i]=jo.getString("description");
             
         }
     }
     catch (Exception ex)
     {

         ex.printStackTrace();
     }


 }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.regimm, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
