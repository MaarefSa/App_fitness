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
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

public class Exercicet extends Activity {
	
	String id=MainActivity.myId;
	
		String urladdress=id+"/coach/readEt.php";
		String urladdressm=id+"/coach/readEtm.php";
	    String[] titre;
	    String[] desc;
	    String[] imagepath;
	    ListView listViewt,listViewtm;
	    BufferedInputStream is;
	    String line=null;
	    String result=null;
	TabHost tabHost;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_exercicet);
		TabHost host = (TabHost)findViewById(R.id.tabHost);
		LocalActivityManager mLocalActivityManager = new LocalActivityManager(this, false);
		   mLocalActivityManager.dispatchCreate(savedInstanceState); // state will be bundle your activity state which you get in onCreate
		   host.setup(mLocalActivityManager);
	        Intent intent;
        
        
      //Tab 1
        TabHost.TabSpec spec = host.newTabSpec("Lundi");
        spec.setContent(R.id.lay1);
        spec.setIndicator("Lu");
        intent = new Intent(Exercicet.this, Lundi.class);
        intent.putExtra("numbers", "surpoidLu");
        
        spec.setContent(intent);
        host.addTab(spec);
        
      //Tab 2
        spec = host.newTabSpec("Mardi");
        spec.setContent(R.id.lay2);
        spec.setIndicator("Ma");
        intent = new Intent(Exercicet.this, Mardi.class);
        intent.putExtra("numbers", "surpoidMa");
        
        spec.setContent(intent);
        host.addTab(spec);
        
        //Tab 3
        spec = host.newTabSpec("Mercredi");
        spec.setContent(R.id.lay3);
        spec.setIndicator("Me");
        intent = new Intent(Exercicet.this, Mercredi.class);
        intent.putExtra("numbers", "surpoidMe");
        
        spec.setContent(intent);
        host.addTab(spec);
      //Tab 4
        spec = host.newTabSpec("jeudi");
        spec.setContent(R.id.lay4);
        spec.setIndicator("Je");
        intent = new Intent(Exercicet.this, Jeudi.class);
        intent.putExtra("numbers", "surpoidJe");
        
        spec.setContent(intent);
        host.addTab(spec);
      //Tab 5
        spec = host.newTabSpec("vendredi");
        spec.setContent(R.id.lay5);
        spec.setIndicator("Ve");
        intent = new Intent(Exercicet.this, Vendredi.class);
        intent.putExtra("numbers", "surpoidVe");
        
        spec.setContent(intent);
        host.addTab(spec);
      //Tab 6
        spec = host.newTabSpec("samedi");
        spec.setContent(R.id.lay6);
        spec.setIndicator("Sa");
        intent = new Intent(Exercicet.this, Samedi.class);
        intent.putExtra("numbers", "surpoidSa");
        
        spec.setContent(intent);
        host.addTab(spec);
      //Tab 7
        spec = host.newTabSpec("dimanche");
        spec.setContent(R.id.lay7);
        spec.setIndicator("Di");
        intent = new Intent(Exercicet.this, Dimanche.class);
        intent.putExtra("numbers", "surpoidDi");
        
        spec.setContent(intent);
        host.addTab(spec);
        
        /* ************************************************************************** */

        host.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                // display the name of the tab whenever a tab is changed
                Toast.makeText(getApplicationContext(), tabId, Toast.LENGTH_SHORT).show();
            }
        });
       /* listViewt=(ListView)findViewById(R.id.lviewt);
        listViewtm=(ListView)findViewById(R.id.lviewtm);
        
       
        StrictMode.setThreadPolicy((new StrictMode.ThreadPolicy.Builder().permitNetwork().build()));
        collectData(urladdress);
        CustomListView customListView=new CustomListView(this,titre,desc,imagepath);
        listViewt.setAdapter(customListView);
        */
	}
       
        
	private void collectData(String urll)
    {
//Connection
        try{

            URL url=new URL(urll);
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
		getMenuInflater().inflate(R.menu.exercicet, menu);
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
