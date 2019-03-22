package com.example.app_fitness;



import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomListViewR extends ArrayAdapter<String>{
		private String[] jour;
	    private String[] desc;
	   
	    private Activity context;
	   
	    
	    public CustomListViewR(Activity context,String[] jour,String[] desc) {
	        super(context, R.layout.layout_regime,jour);
	        this.context=context;
	        this.jour=jour;
	        this.desc=desc;
	        
	    }
	    
	    @NonNull
	    @Override

	    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
	        View r=convertView;
	        ViewHolder viewHolder=null;
	        if(r==null){
	            LayoutInflater layoutInflater=context.getLayoutInflater();
	            r=layoutInflater.inflate(R.layout.layout_regime,null,true);
	            viewHolder=new ViewHolder(r);
	            r.setTag(viewHolder);
	        }
	        else {
	            viewHolder=(ViewHolder)r.getTag();

	        }

	        viewHolder.tvw1.setText(jour[position]);
	        viewHolder.tvw2.setText(desc[position]);
	      

	        return r;
	    }
	    class ViewHolder{

	        TextView tvw1;
	        TextView tvw2;
	       

	        ViewHolder(View v){
	            tvw1=(TextView)v.findViewById(R.id.tvprofilename);
	            tvw2=(TextView)v.findViewById(R.id.tvemail);
	           
	        }

	    }
	    
	
	

}
