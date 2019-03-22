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

public class Passion extends Activity {
	
	String id=MainActivity.myId;
	
	String urlPassion=id+"/logAdmin/read.php";
    String[] nom;
    String[] prenom;
    String[] age;
    String[] abonnement;
    String[] login;
    String[] password;
    
    ListView listView;
    BufferedInputStream is;
    String line=null;
    String result=null;
 
	@Override 
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_admin);
		
		
		listView=(ListView)findViewById(R.id.lviewP);
		
		StrictMode.setThreadPolicy((new StrictMode.ThreadPolicy.Builder().permitNetwork().build()));
		collectData();
        CustomListViewP customListView=new CustomListViewP(Passion.this,nom,prenom,age,abonnement,login,password);
        listView.setAdapter(customListView);
		
	        
		
		
	} 

	private void collectData()
    {
//Connection
        try{

            URL url=new URL(urlPassion);
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
            nom=new String[ja.length()];
            prenom=new String[ja.length()];
            age=new String[ja.length()];
            abonnement=new String[ja.length()];
            login=new String[ja.length()];
            password=new String[ja.length()];
            

            for(int i=0;i<=ja.length();i++){
                jo=ja.getJSONObject(i);
                nom[i]=jo.getString("nom");
                prenom[i]=jo.getString("prenom");
                age[i]=jo.getString("age");
                abonnement[i]=jo.getString("abonnement");
                login[i]=jo.getString("login");
                password[i]=jo.getString("password");
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
