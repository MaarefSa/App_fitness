package com.example.app_fitness;





import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class Inscrire extends Activity {
	private Button ok,annuler;
	private EditText nom, prenom ,age, login , pwd;
	private Spinner spinner;
	private String abonnement;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inscrire);
		
		ok =(Button)findViewById(R.id.inscrire);
        annuler =(Button)findViewById(R.id.annuler);
        nom=(EditText)findViewById(R.id.edit);
        prenom=(EditText)findViewById(R.id.edit1);
        age=(EditText)findViewById(R.id.editA);
        login=(EditText)findViewById(R.id.edit2);
        pwd=(EditText)findViewById(R.id.edit3);
        
    	spinner = (Spinner) findViewById(R.id.abonnement);
		
		List<String> abonnements = new ArrayList<String>();
		abonnements.add("Mois");
		abonnements.add("Trimestre");
		abonnements.add("Semestre");
		abonnements.add("Ans");
		    
		ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, abonnements);
		spinner.setPrompt("Mois");
		spinner.setAdapter(dataAdapter1);
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {             
            @Override
            public void onItemSelected(AdapterView<?> adapter, View v,int position, long id) {
            	abonnement = adapter.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
        
        
        
        ok.setOnClickListener(new OnClickListener() {
			
        	
			@Override
			public void onClick(View v) {
				
				String n=nom.getText().toString();
	        	String p=prenom.getText().toString();
	        	String ag=age.getText().toString();
	        	String spin =spinner.getSelectedItem().toString();
	        	String l=login.getText().toString();
	        	String pass=pwd.getText().toString();
	        	
	        	
				if(n.equals("") || p.equals("") || ag.equals("") || l.equals("")|| pass.equals("")){
					Toast.makeText(Inscrire.this,"remplir les champs",Toast.LENGTH_SHORT).show();
        			 
        		}
				else{
					String type="passion";
		            Backgroundworker Backgroundworker = new Backgroundworker(Inscrire.this);
		            Backgroundworker.execute(type,n,p,ag,spin,l,pass);
					 Intent result = new Intent();
				        result.putExtra("clé", n+" "+p+"  "+ag+" "+abonnement+" "+l+" "+pass);
				setResult(RESULT_OK,result);
				finish();
				}
					
				
			
			}
		});
        
        annuler.setOnClickListener(new OnClickListener() {
			
        	@Override
			public void onClick(View v) {
				
				setResult(RESULT_CANCELED);
				finish();
				
				
			}
		});
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.inscrire, menu);
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
