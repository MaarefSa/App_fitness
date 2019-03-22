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
import android.widget.Toast;

public class Dimanche extends Activity {
	
	String id=MainActivity.myId;
	
	String urladdressM=id+"/coach/ex_jour/minceDi.php";
	String urladdressN=id+"/coach/ex_jour/normaleDi.php";
	String urladdressS=id+"/coach/ex_jour/surpoidDi.php";
	String[] titre;
    String[] desc;
    String[] imagepath;
    BufferedInputStream is;
    String line=null;
    String result=null;
	
	ListView listView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_exercice);
		Bundle extras = getIntent().getExtras();
		String code = extras.getString("numbers");
		if(code.equals("minceDi"))
		{
		listView=(ListView)findViewById(R.id.lview7);
		
		StrictMode.setThreadPolicy((new StrictMode.ThreadPolicy.Builder().permitNetwork().build()));
		collectData(urladdressM);
        CustomListView customListView=new CustomListView(this,titre,desc,imagepath);
        
    	listView.setAdapter(customListView);
		}else if(code.equals("normaleDi")){
			listView=(ListView)findViewById(R.id.lview7);
			
			StrictMode.setThreadPolicy((new StrictMode.ThreadPolicy.Builder().permitNetwork().build()));
			collectData(urladdressN);
	        CustomListView customListView=new CustomListView(this,titre,desc,imagepath);
	        
	    	listView.setAdapter(customListView);
			
		}else if(code.equals("surpoidDi")){
			listView=(ListView)findViewById(R.id.lview7);
			
			StrictMode.setThreadPolicy((new StrictMode.ThreadPolicy.Builder().permitNetwork().build()));
			collectData(urladdressS);
	        CustomListView customListView=new CustomListView(this,titre,desc,imagepath);
	        
	    	listView.setAdapter(customListView);
			
		}
	}

	private void collectData(String add)
    {
//Connection
        try{

            URL url=new URL(add);
            HttpURLConnection con=(HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
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
            titre=new String[ja.length()];
            desc=new String[ja.length()];
            imagepath=new String[ja.length()];

            for(int i=0;i<=ja.length();i++){
                jo=ja.getJSONObject(i);
                titre[i]=jo.getString("titre");
                desc[i]=jo.getString("description");
                imagepath[i]=jo.getString("image");
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
		getMenuInflater().inflate(R.menu.lundi, menu);
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
