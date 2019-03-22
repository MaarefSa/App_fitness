package com.example.app_fitness;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class IMC extends Activity implements View.OnClickListener{
	Button calcul, exercice,regime,back; 
	EditText taille,poids;
	TextView imc;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_imc);
		
		
		calcul=(Button) findViewById(R.id.calcul);
		calcul.setOnClickListener(this);
		exercice=(Button) findViewById(R.id.exercice);
		exercice.setOnClickListener(this);
		regime=(Button) findViewById(R.id.regime);
		regime.setOnClickListener(this);
		back=(Button) findViewById(R.id.back);
		back.setOnClickListener(this);
		taille=(EditText)findViewById(R.id.edit);
		poids=(EditText)findViewById(R.id.edit1);
		imc=(TextView)findViewById(R.id.res);
		
		/*calcul.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String t=taille.getText().toString();
				String p=poids.getText().toString();
				
				
				
				
				if(t.equals("") || p.equals("")){
					Toast.makeText(IMC.this,"Sisir votre taille et votre poid",Toast.LENGTH_SHORT).show();
        			 
        		}
				else {
					Double taill=Double.parseDouble(t);
					Double poid=Double.parseDouble(p);
				
				//IMC = Poids / Taille2
				Double result = poid/puiss(taill);
				
				String results=result.toString();
				imc.setText(results);


				//intent
				//taille normal 18.5 à 25
				if(result < 18.5){
					Intent intent=new Intent(IMC.this,Direction1.class);
					startActivityForResult(intent,1);
				}
				else if(result >=18.5 && result<=25){
					Intent intent=new Intent(IMC.this,Direction2.class);
					startActivityForResult(intent,2);
					
				}
				if(result > 25){
					Intent intent=new Intent(IMC.this,Direction3.class);
					startActivityForResult(intent,3);
				}
				
				}
				
				
			}
		});*/
		
		
		
		
		
	}
	Double puiss(Double x){
		 double res;
			res=x*x;
			return res;
			
		}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.imc, menu);
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
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		if(v.getId()==R.id.calcul){
			String t=taille.getText().toString();
			String p=poids.getText().toString();
			
			
			
			
			if(t.equals("") || p.equals("")){
				Toast.makeText(IMC.this,"Sisir votre taille et votre poid",Toast.LENGTH_SHORT).show();
    			 
    		}
			else {
				Double taill=Double.parseDouble(t);
				Double poid=Double.parseDouble(p);
			
			//IMC = Poids / Taille2
			Double result = poid/puiss(taill);
			
			String results=result.toString();
			imc.setText(results);
			}


		}
		if(v.getId()==R.id.exercice){
			
			String contenuIMC=imc.getText().toString();
			Double result=Double.parseDouble(contenuIMC);
			
			if(result < 18.5){
				Intent intent=new Intent(IMC.this,Exercice.class);
				startActivityForResult(intent,1);
			}
			else if(result >=18.5 && result<=25){
				Intent intent=new Intent(IMC.this,Exercicem.class);
				startActivityForResult(intent,2);
				
			}
			if(result > 25){
				Intent intent=new Intent(IMC.this,Exercicet.class);
				startActivityForResult(intent,3);
			}
	        
			
		}
		if(v.getId()==R.id.regime){
			String contenuIMC=imc.getText().toString();
			Double result=Double.parseDouble(contenuIMC);
			
			if(result < 18.5){
				Intent intent=new Intent(IMC.this,Regime.class);
				startActivityForResult(intent,1);
			}
			else if(result >=18.5 && result<=25){
				Intent intent=new Intent(IMC.this,Regimm.class);
				startActivityForResult(intent,2);
				
			}
			if(result > 25){
				Intent intent=new Intent(IMC.this,Regimt.class);
				startActivityForResult(intent,3);
			}
	        
			
		}
		if(v.getId()==R.id.back){
			Intent intent=new Intent(IMC.this,MainActivity.class);
			startActivityForResult(intent,4);
			
		}
	}
}
