package com.example.app_fitness;




import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import java.sql.Statement;

import org.json.JSONArray;
import org.json.JSONObject;




import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import android.widget.Toast;


public class MainActivity extends Activity {
	
	 static String myId="http://192.168.1.102";
	//192.168.100.43
	String urladdress=myId+"/main/verifA.php";
	String urladdressc=myId+"/main/verifC.php";
	String urladdressp=myId+"/main/verifP.php";
    String[] login;
    String[] password;
     
    BufferedInputStream is;
    String line=null;
    String result=null;
	
	
	Button conn;
	Button inscrire;
	EditText log,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        conn =(Button)findViewById(R.id.connection);
        inscrire =(Button)findViewById(R.id.inscrire);
        
        log=(EditText) findViewById(R.id.edit);
        pass=(EditText) findViewById(R.id.edit1);
        
      
        
        conn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				StrictMode.setThreadPolicy((new StrictMode.ThreadPolicy.Builder().permitNetwork().build()));
		        collectData();
		        collectDataC();
		        collectDataP();		
				}
				
				

			  
					});
        
        inscrire.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent intent=new Intent(MainActivity.this,Inscrire.class);
				startActivityForResult(intent,3);
				
			}
		});
       

    }
    
    
    
    private void collectData()
    {
    	String lo = log.getText().toString();
		String pas = pass.getText().toString();
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
            login=new String[ja.length()];
            password=new String[ja.length()];
            

            for(int i=0;i<=ja.length();i++){
                jo=ja.getJSONObject(i);
                login[i]=jo.getString("username");
                password[i]=jo.getString("password");
                if(lo.equals(login[i])&&pas.equals(password[i])){
                	Intent intent=new Intent(MainActivity.this,Admin.class);
    				startActivityForResult(intent,1);
                }
            }
            
        }
        catch (Exception ex)
        {

            ex.printStackTrace();
        }


    }

    /* **************************verification coach********************************** */
    
    private void collectDataC()
    {
    	String lo = log.getText().toString();
		String pas = pass.getText().toString();
//Connection
        try{

            URL url=new URL(urladdressc);
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
            login=new String[ja.length()];
            password=new String[ja.length()];
            

            for(int i=0;i<=ja.length();i++){
                jo=ja.getJSONObject(i);
                login[i]=jo.getString("username");
                password[i]=jo.getString("password");
                if(lo.equals(login[i])&&pas.equals(password[i])){
                	Intent intent=new Intent(MainActivity.this,Coach.class);
    				startActivityForResult(intent,4);
                }
            }
            
        }
        catch (Exception ex)
        {

            ex.printStackTrace();
        }


    }
/* **************************verification passion********************************** */
    
    private void collectDataP()
    {
    	String lo = log.getText().toString();
		String pas = pass.getText().toString();
//Connection
        try{

            URL url=new URL(urladdressp);
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
            login=new String[ja.length()];
            password=new String[ja.length()];
            

            for(int i=0;i<=ja.length();i++){
                jo=ja.getJSONObject(i);
                login[i]=jo.getString("username");
                password[i]=jo.getString("password");
                if(lo.equals(login[i])&&pas.equals(password[i])){
                	Intent intent=new Intent(MainActivity.this,IMC.class);
    				startActivityForResult(intent,4);
                }
            }
            
        }
        catch (Exception ex)
        {

            ex.printStackTrace();
        }


    }
    
    
   public void onActivityResult(int requestCode, int resultCode, Intent data){
    	
    	if (requestCode == 3) {
		      if (resultCode == RESULT_OK) {
		    	  
		      	Toast.makeText(this, "Inscription réussite pour cette client : " + data.getStringExtra("clé"), Toast.LENGTH_SHORT).show();
		      }
		      if (resultCode == RESULT_CANCELED) {
		    	  
			      	Toast.makeText(this, "Inscription annulée ", Toast.LENGTH_SHORT).show();
			      }
		  }
    	
    }
    
    
    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
