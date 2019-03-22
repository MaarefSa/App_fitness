package com.example.app_fitness;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.Toast;

public class Coach extends Activity {
	
	private static final int PICK_IMAGE = 100;
	private static final int STORAGE_PERMISSION_CODE = 123;
	private Button Bpick,BajoutR,Bajouter,Bsupprimer,Bmodifier;
	private EditText editCategR,editJour,editDescR,editid,editCategE,editjourE,editTitre,editDescE;
	private ImageView imgPick;
	Uri ImageURI;
	Bitmap bitmap;
	TabHost tabHost;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_coach);
		
		TabHost host = (TabHost)findViewById(R.id.tabHost);
        host.setup();
        
      //Tab 1
        TabHost.TabSpec spec = host.newTabSpec("Regime");
        spec.setContent(R.id.lay1);
        spec.setIndicator("Gestion regime");
        host.addTab(spec);
        
      //Tab 2
        spec = host.newTabSpec("Exercice");
        spec.setContent(R.id.lay2);
        spec.setIndicator("Gestion exercice");
        host.addTab(spec);
		
		Bpick=(Button)findViewById(R.id.Bpick);
		
		/* ********************************* */
		editCategR=(EditText)findViewById(R.id.editCategR);
		editJour=(EditText)findViewById(R.id.editJour);
		editDescR=(EditText)findViewById(R.id.editDescR);
		BajoutR=(Button)findViewById(R.id.BajoutR);
		/* ********************************* */
		editid=(EditText)findViewById(R.id.editid);
		editCategE=(EditText)findViewById(R.id.editCategE);
		editjourE=(EditText)findViewById(R.id.editJourE);
		editTitre=(EditText)findViewById(R.id.editTitre);
		editDescE=(EditText)findViewById(R.id.editDescE);
		imgPick=(ImageView)findViewById(R.id.imgPick);
		Bajouter=(Button)findViewById(R.id.Bajouter);
		Bsupprimer=(Button)findViewById(R.id.Bsupprimer);
		Bmodifier=(Button)findViewById(R.id.Bmodifier);
		//jout exercice 
		Bajouter.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(Coach.this,"onclick fonctionne bien " , Toast.LENGTH_LONG).show();
				String categorie =editCategE.getText().toString();
		    	String jour =editjourE.getText().toString();
		    	String titre =editTitre.getText().toString();
		    	String description =editDescE.getText().toString();
		    	String image=imgPick.toString();
		    	if(categorie.equals("") || jour.equals("") || titre.equals("") || description.equals("")|| image.equals("")){
					Toast.makeText(Coach.this,"remplir tous les champs",Toast.LENGTH_SHORT).show();
					 
				}
				else{
					Toast.makeText(Coach.this,"vous ete dans else", Toast.LENGTH_LONG).show();
					String type="ajoutExercice";
		            Backgroundworker Backgroundworker = new Backgroundworker(Coach.this);
		            Backgroundworker.execute(type,categorie,jour,titre,description,image);
					 
				}
				
			}
		});
		
		//Delete Exercice
		Bsupprimer.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String id =editid.getText().toString();
				
				
				Toast.makeText(Coach.this,"Supprime exercice fonctionne", Toast.LENGTH_LONG).show();
	            
	            String type="exerciceDelete";
	            Backgroundworker Backgroundworker = new Backgroundworker(Coach.this);
	            Backgroundworker.execute(type,id);
				
				
			}
		});
		
		
		
		/* ***********Ajouter regime********************** */
		BajoutR.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String categ = editCategR.getText().toString();
				String jour = editJour.getText().toString();
				String desc = editDescR.getText().toString();
				
				if(categ.equals("")|| jour.equals("")||desc.equals(""))
				{
					Toast.makeText(Coach.this, "remplir les cases", Toast.LENGTH_LONG).show();
				}else
				{
					 Toast.makeText(Coach.this,"onclick ajout fonctionne", Toast.LENGTH_LONG).show();
		                
		                String type="regime";
		                Backgroundworker Backgroundworker = new Backgroundworker(Coach.this);
		                Backgroundworker.execute(type,categ,jour,desc);
				}
				
			}
		});
		
		
		
		Bpick.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				openGallery();
			}

			
		});
		
		
	}
	
	

	private void openGallery(){
		Intent gallery = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.INTERNAL_CONTENT_URI);
		startActivityForResult(gallery,PICK_IMAGE);
	}

	@Override
	protected void onActivityResult(int requestCode,int resultCode,Intent data){
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode ==RESULT_OK && requestCode ==PICK_IMAGE){
			ImageURI =data.getData();
			imgPick.setImageURI(ImageURI);
		}
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.coach, menu);
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
