	package com.example.app_fitness;

import java.io.InputStream;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomListView extends ArrayAdapter<String>{
		private String[] titre;
	    private String[] desc;
	    private String[] imagepath;
	    private Activity context;
	    Bitmap bitmap;
	    
	    public CustomListView(Activity context,String[] titre,String[] desc,String[] imagepath) {
	        super(context, R.layout.layout_exercice,titre);
	        this.context=context;
	        this.titre=titre;
	        this.desc=desc;
	        this.imagepath=imagepath;
	    }
	    
	   

		@NonNull
	    @Override

	    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
	        View r=convertView;
	        ViewHolder viewHolder=null;
	        if(r==null){
	            LayoutInflater layoutInflater=context.getLayoutInflater();
	            r=layoutInflater.inflate(R.layout.layout_exercice,null,true);
	            viewHolder=new ViewHolder(r);
	            r.setTag(viewHolder);
	        }
	        else {
	            viewHolder=(ViewHolder)r.getTag();

	        }

	        viewHolder.tvw1.setText(titre[position]);
	        viewHolder.tvw2.setText(desc[position]);
	        new GetImageFromURL(viewHolder.ivw).execute(imagepath[position]);

	        return r;
	    }
	    class ViewHolder{

	        TextView tvw1;
	        TextView tvw2;
	        ImageView ivw;

	        ViewHolder(View v){
	            tvw1=(TextView)v.findViewById(R.id.tvprofilename);
	            tvw2=(TextView)v.findViewById(R.id.tvemail);
	            ivw=(ImageView)v.findViewById(R.id.idimageView);
	        }

	    }
	    public class GetImageFromURL extends AsyncTask<String,Void,Bitmap>
	    {

	        ImageView imgView;
	        public GetImageFromURL(ImageView imgv)
	        {
	            this.imgView=imgv;
	        }
	        @Override
	        protected Bitmap doInBackground(String... url) {
	            String urldisplay=url[0];
	            bitmap=null;

	            try{

	                InputStream ist=new java.net.URL(urldisplay).openStream();
	                bitmap= BitmapFactory.decodeStream(ist);
	            }
	            catch (Exception ex)
	            {
	                ex.printStackTrace();
	            }

	            return bitmap;
	        }

	        @Override
	        protected void onPostExecute(Bitmap bitmap){

	            super.onPostExecute(bitmap);
	            imgView.setImageBitmap(bitmap);
	        }
	    }
	   
	    
	
	

}
