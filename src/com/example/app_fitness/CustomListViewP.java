	package com.example.app_fitness;



import android.app.Activity;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.widget.TextView;

public class CustomListViewP extends ArrayAdapter<String>{
		private String[] nom;
	    private String[] prenom;
	    private String[] age;
	    private String[] abonnement;
	    private String[] login;
	    private String[] passwd;
	    
	    private Activity context;
	    
	    
	    public CustomListViewP(Activity context,String[] nom,String[] prenom,String[] age,String[] abonnement,String[] login,String[] passwd) {
	        super(context, R.layout.layout_passion,nom);
	        this.context=context;
	        this.nom=nom;
	        this.prenom=prenom;
	        this.age=age;
	        this.abonnement=abonnement;
	        this.login=login;
	        this.passwd=passwd;
	    }
	    
	   

		@NonNull
	    @Override

	    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
	        View r=convertView;
	        ViewHolder viewHolder=null;
	        if(r==null){
	            LayoutInflater layoutInflater=context.getLayoutInflater();
	            r=layoutInflater.inflate(R.layout.layout_passion,null,true);
	            viewHolder=new ViewHolder(r);
	            r.setTag(viewHolder);
	        }
	        else {
	            viewHolder=(ViewHolder)r.getTag();

	        }

	        viewHolder.tvw1.setText(nom[position]);
	        viewHolder.tvw2.setText(prenom[position]);
	        viewHolder.tvw3.setText(age[position]);
	        viewHolder.tvw4.setText(abonnement[position]);
	        viewHolder.tvw5.setText(login[position]);
	        viewHolder.tvw6.setText(passwd[position]);
	        

	        return r;
	    }
	    class ViewHolder{

	        TextView tvw1;
	        TextView tvw2;
	        TextView tvw3;
	        TextView tvw4;
	        TextView tvw5;
	        TextView tvw6;
	        

	        ViewHolder(View v){
	            tvw1=(TextView)v.findViewById(R.id.tvnom);
	            tvw2=(TextView)v.findViewById(R.id.tvprenom);
	            tvw3=(TextView)v.findViewById(R.id.tvage);
	            tvw4=(TextView)v.findViewById(R.id.tvabonnement);
	            tvw5=(TextView)v.findViewById(R.id.tvlogin);	            
	            tvw6=(TextView)v.findViewById(R.id.tvpwd);

	            
	        }

	    }
	    
	    
	   
	    
	
	

}
