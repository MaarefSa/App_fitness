package com.example.app_fitness;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.app.LocalActivityManager;
import android.app.TabActivity;
import android.content.Intent;

import android.os.Bundle;
import android.os.StrictMode;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

public class Exercice extends Activity {
	
	String id=MainActivity.myId;
	
	 String urladdress=id+"/coach/readE.php";
	 String urladdress2=id+"/coach/readExm.php";
	 
	    String[] titre;
	    String[] desc;
	    String[] imagepath;
	    ListView listView,listview2;
	    BufferedInputStream is;
	    String line=null;
	    String result=null;
	    LinearLayout lay1,lay2;
	
	TabHost tabhost;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_exercice);
		
		
		   TabHost host = (TabHost)findViewById(R.id.tabhost);
		   //here tabHost will be your Tabhost
		   LocalActivityManager mLocalActivityManager = new LocalActivityManager(this, false);
		   mLocalActivityManager.dispatchCreate(savedInstanceState); // state will be bundle your activity state which you get in onCreate
		   host.setup(mLocalActivityManager);
	        Intent intent;
	        
	        
	      //Tab 1
	        TabHost.TabSpec spec = host.newTabSpec("Lundi");
	        spec.setContent(R.id.lay1);
	        spec.setIndicator("Lu");
	        intent = new Intent(Exercice.this, Lundi.class);
	        intent.putExtra("numbers", "mincelu");
	        
	        spec.setContent(intent);
	        host.addTab(spec);
	        
	      //Tab 2
	        spec = host.newTabSpec("Mardi");
	        spec.setContent(R.id.lay2);
	        spec.setIndicator("Ma");
	        intent = new Intent(Exercice.this, Mardi.class);
	        intent.putExtra("numbers", "minceMa");
	        
	       spec.setContent(intent);
	        host.addTab(spec);
	        
	        //Tab 3
	        spec = host.newTabSpec("Mercredi");
	        spec.setContent(R.id.lay3);
	        spec.setIndicator("Me");
	        intent = new Intent(Exercice.this, Mercredi.class);
	        intent.putExtra("numbers", "minceMe");
	        
		       spec.setContent(intent);
	        host.addTab(spec);
	      //Tab 4
	        spec = host.newTabSpec("jeudi");
	        spec.setContent(R.id.lay4);
	        spec.setIndicator("je");
	        intent = new Intent(Exercice.this, Jeudi.class);
	        intent.putExtra("numbers", "minceJe");
		       spec.setContent(intent);
	        host.addTab(spec);
	      //Tab 5
	        spec = host.newTabSpec("vendredi");
	        spec.setContent(R.id.lay5);
	        spec.setIndicator("ve");
	        intent = new Intent(Exercice.this, Vendredi.class);
	        intent.putExtra("numbers", "minceVe");
		       spec.setContent(intent);
	        host.addTab(spec);
	      //Tab 6
	        spec = host.newTabSpec("samedi");
	        spec.setContent(R.id.lay6);
	        spec.setIndicator("sa");
	        intent = new Intent(Exercice.this, Samedi.class);
	        intent.putExtra("numbers", "minceSa");
		       spec.setContent(intent);
	        host.addTab(spec);
	      //Tab 7
	        spec = host.newTabSpec("dimanche");
	        spec.setContent(R.id.lay7);
	        spec.setIndicator("di");
	        intent = new Intent(Exercice.this, Dimanche.class);
	        intent.putExtra("numbers", "minceDi");
		    spec.setContent(intent);
	        host.addTab(spec);
	        /* **************************************************************************/
	        
	        
	        host.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
	            @Override
	            public void onTabChanged(String tabId) {
	                // display the name of the tab whenever a tab is changed
	                Toast.makeText(getApplicationContext(), tabId, Toast.LENGTH_SHORT).show();
	            }
	        });


	        /* listView=(ListView)findViewById(R.id.lview);
	        StrictMode.setThreadPolicy((new StrictMode.ThreadPolicy.Builder().permitNetwork().build()));
	        collectData(urladdress);
	        CustomListView customListView=new CustomListView(this,titre,desc,imagepath);
	    	listView.setAdapter(customListView);
	       // listview2=(ListView)findViewById(R.id.lview2);
	      
	        /*	 collectData(urladdress2);
	        	 listview2.setAdapter(customListView);
	        */
	      
	        
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
		getMenuInflater().inflate(R.menu.exercice, menu);
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
