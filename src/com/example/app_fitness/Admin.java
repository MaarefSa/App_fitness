package com.example.app_fitness;


import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.LocalActivityManager;
import android.content.Intent;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

public class Admin extends Activity implements View.OnClickListener {
	
	Button Gclient,Bsupprimer,Bmodifier,Bajouter;
	EditText editnom,editpren,editcateg,edituser,editpass,editid;
	TextView tnom,tpren,tage,tabn,tuser,tpwd;
	TabHost tabHost;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_admin);
		
		TabHost host = (TabHost)findViewById(R.id.tabHost);
		 LocalActivityManager mLocalActivityManager = new LocalActivityManager(this, false);		
		   mLocalActivityManager.dispatchCreate(savedInstanceState); // state will be bundle your activity state which you get in onCreate
		   host.setup(mLocalActivityManager);
		
	        Intent intent;
        
        
      //Tab 1
        TabHost.TabSpec spec = host.newTabSpec("profile");
        spec.setContent(R.id.lay1);
        spec.setIndicator("Profile");
        
        host.addTab(spec);
        
      //Tab 2
        spec = host.newTabSpec("Passion");
        spec.setContent(R.id.lay2);
        spec.setIndicator("Gestion passion");
        intent = new Intent(Admin.this, Passion.class);
        spec.setContent(intent);
        host.addTab(spec);
        
        //Tab 3
        spec = host.newTabSpec("Coach");
        spec.setContent(R.id.lay3);
        spec.setIndicator("Gestion coach");
        host.addTab(spec);
        
        
		
        /* *************** list des Bouttons ************* */
	
       
		Bajouter=(Button)findViewById(R.id.Bajouter);
		Bajouter.setOnClickListener(this);
		Bmodifier=(Button)findViewById(R.id.Bmodifier);
		Bmodifier.setOnClickListener(this);
		Bsupprimer=(Button)findViewById(R.id.Bsupprimer);
		Bsupprimer.setOnClickListener(this);
		
		/* ************ liste des EditTexts d'un coach*************** */
		
		editnom=(EditText)findViewById(R.id.editNom);
		editpren=(EditText)findViewById(R.id.editPren);
		editcateg=(EditText)findViewById(R.id.editCateg);
		edituser=(EditText)findViewById(R.id.editUser);
		editpass=(EditText)findViewById(R.id.editPass);
		editid=(EditText)findViewById(R.id.editId);
		/* ************ liste des TextViews d'un passion*************** */
		 host.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
	            @Override
	            public void onTabChanged(String tabId) {
	                // display the name of the tab whenever a tab is changed
	                Toast.makeText(getApplicationContext(), tabId, Toast.LENGTH_SHORT).show();
	            }
	        });
		
		
		
	
	}
	
	

	public void onClick(View v) {
		if(v.getId()==R.id.Bajouter)
		{
			String nom = editnom.getText().toString();
			String prenom = editpren.getText().toString();
			String categorie = editcateg.getText().toString();
			String login = edituser.getText().toString();
			String password = editpass.getText().toString();
			if(nom.equals("")|| prenom.equals("")||categorie.equals("")||login.equals("")||password.equals(""))
			{
				Toast.makeText(this, "remplir les cases", Toast.LENGTH_LONG).show();
			}else
			{
				 Toast.makeText(Admin.this,"onclick ajout fonctionne", Toast.LENGTH_LONG).show();
	                
	                String type="coachAjout";
	                Backgroundworker Backgroundworker = new Backgroundworker(Admin.this);
	                Backgroundworker.execute(type,nom,prenom,categorie,login,password);
			/*SyncData orderData = new SyncData();
			orderData.execute("");*/
			
			}
			
			
			
			
			
		}
		if(v.getId()==R.id.Bmodifier)
		{
			String nom = editnom.getText().toString();
			String prenom = editpren.getText().toString();
			String categorie = editcateg.getText().toString();
			String login = edituser.getText().toString();
			String password = editpass.getText().toString();
			String id =editid.getText().toString();
			if(nom.equals("")|| prenom.equals("")||categorie.equals("")||login.equals("")||password.equals(""))
			{
				Toast.makeText(this, "remplir les cases", Toast.LENGTH_LONG).show();
			}else
			{
			Toast.makeText(Admin.this,"onclick modif fonctionne", Toast.LENGTH_LONG).show();
            
            String type="coachUpdate";
            Backgroundworker Backgroundworker = new Backgroundworker(Admin.this);
            Backgroundworker.execute(type,nom,prenom,categorie,login,password,id);
			}
		}
		if(v.getId()==R.id.Bsupprimer)
		{
			
			String id =editid.getText().toString();
			
			
			Toast.makeText(Admin.this,"onclick supprime fonctionne", Toast.LENGTH_LONG).show();
            
            String type="coachDelete";
            Backgroundworker Backgroundworker = new Backgroundworker(Admin.this);
            Backgroundworker.execute(type,id);
			
			
		}

		
	}
	
	
	/*private class SyncData extends AsyncTask<String, String, String> {
        String msg = " ";
        String nom = editnom.getText().toString();
		String prenom = editpren.getText().toString();
		String categorie = editcateg.getText().toString();
		String login = edituser.getText().toString();
		String password = editpass.getText().toString();

		String url = "jdbc:mysql://192.168.100.42/base_gym";
		 String user = "root";
		 String passw = "root";

        @Override
        protected void onPreExecute() //Starts the progress dailog
        {
        	
        	Toast.makeText(Admin.this,"***************************",Toast.LENGTH_LONG).show();
        	//ProgressDialog progres;
        	//progres = ProgressDialog.show(Admin.this, "Chargement", "ajout d'un coach ",
				//	true);
        	
        }
        
        @Override
        protected String doInBackground(String... args)  // Connect to the database, write query and add items to array list
        {
        	
        	try {
        		
                Class.forName("com.mysql.jdbc.Driver");
            	Toast.makeText(Admin.this,"In try",Toast.LENGTH_LONG).show();
                Connection conn = DriverManager.getConnection(url,user,passw); //Connection Object
                if (conn == null) {
                	
                	msg="cnx null";
                } else {
                    // Change below query according to your own database.
                    String query = "INSERT INTO coach(nom, prenom, categorie,utilisateur,passe) VALUES ('" + nom
        					+ "','" + prenom + "','" + categorie + "','" + login + "','" + password + "')";
                    Statement stmt = conn.createStatement();
                    stmt.executeUpdate(query);
                    msg="insert avec succé";
                   
                }

            	Toast.makeText(Admin.this,"Con == "+msg,Toast.LENGTH_LONG).show();
                conn.close();
            } 
        	catch (Exception e) {
            	msg="cnx wrong";
                e.printStackTrace();
                
               
            }
            return null;
        }
	}
	*/
	/*public void testDB()
	{
    		
			 String nom = editnom.getText().toString();
				String prenom = editpren.getText().toString();
				String categorie = editcateg.getText().toString();
				String login = edituser.getText().toString();
				String password = editpass.getText().toString();
			
				 String url = "jdbc:mysql://192.168.100.42:3306/base_gym";
				 String user = "root";
				 String passw = "root";
				 Connection conn=null;
				 Statement stmt=null;
				Toast.makeText(Admin.this,"in testDB",Toast.LENGTH_LONG).show();

		try {
			
			StrictMode.ThreadPolicy policy = 
					new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
			
			Class.forName("com.mysql.jdbc.Driver");
			Toast.makeText(Admin.this,"jdbc fonctionne",Toast.LENGTH_LONG).show();
			 
			 conn = DriverManager.getConnection(url,user,passw); // Connection Object
			 stmt = conn.createStatement();
			// Change below query according to your own database.
			String query = "INSERT INTO `coach`(`nom`, `prenom`, `categorie`,`utilisateur`,`passe`) VALUES ('" + nom
					+ "','" + prenom + "','" + categorie + "','" + login + "','" + password + "')";
			
			 stmt.executeUpdate(query);
		
		} catch (SQLException e) {
			e.printStackTrace();

		} catch (ClassNotFoundException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		finally{
			try {
				conn.close();
				stmt.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
	}*/
	/*void Display(String title,String message)
	{
		AlertDialog.Builder builder =new AlertDialog.Builder(this);
		builder.setTitle(title);
		builder.setMessage(message);
		builder.setCancelable(true);
		builder.show();
	}
*/
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.admin, menu);
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
