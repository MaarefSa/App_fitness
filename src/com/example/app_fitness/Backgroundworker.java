package com.example.app_fitness;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;

public class Backgroundworker extends AsyncTask<String,String,String>{
	
	String id=MainActivity.myId;
	Context context;
    AlertDialog alertDialog;
    Backgroundworker(Context ctx){
        context=ctx;
        }

	@Override
	protected String doInBackground(String... params) {
		// TODO Auto-generated method stub
		 String type =params[0];
	        String registreurl=id+"/logAdmin/insert.php";
	        String updateurl=id+"/logAdmin/update.php";
	        String deleteurl=id+"/logAdmin/delete.php";
	        String insert_pasion_url=id+"/passion/insert.php";
	        String insert_regim_url=id+"/coach/insertR.php";
	        String delete_exrecice=id+"/coach/deleteEx.php";
	        String insert_exercice=id+"/coach/upload.php";
	        if(type.equals("coachAjout")){
	            try {
	                String nom=params[1];
	                String prenom=params[2];
	                String categorie=params[3];
	                
	                String user=params[4];
	                String pwd=params[5];
	                URL url= new URL(registreurl);
	                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
	                httpURLConnection.setRequestMethod("POST");
	                httpURLConnection.setDoInput(true);
	                httpURLConnection.setDoOutput(true);
	                OutputStream OutputStream=httpURLConnection.getOutputStream();
	                BufferedWriter BufferedWriter = new BufferedWriter(new OutputStreamWriter(OutputStream,"utf-8"));
	                String post_data= URLEncoder.encode("name","UTF-8")+"="+URLEncoder.encode(nom,"UTF-8")+"&"
	                		+URLEncoder.encode("surname","UTF-8")+"="+URLEncoder.encode(prenom,"UTF-8")+"&"
	                		+URLEncoder.encode("categorie","UTF-8")+"="+URLEncoder.encode(categorie,"UTF-8")+"&"
	                		+URLEncoder.encode("user","UTF-8")+"="+URLEncoder.encode(user,"UTF-8")+"&"
	                        +URLEncoder.encode("passe","UTF-8")+"="+URLEncoder.encode(pwd,"UTF-8");
	                BufferedWriter.write(post_data);
	                BufferedWriter.flush();
	                BufferedWriter.close();
	                OutputStream.close();
	                InputStream inputStream = httpURLConnection.getInputStream();
	                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
	                String result="";
	                String ligne="";
	                while((ligne =bufferedReader.readLine())!=null){
	                    result+=ligne;
	                }

	            } catch (MalformedURLException e) {
	                e.printStackTrace();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }else if(type.equals("coachUpdate")){
	        	 try {
		                String nom=params[1];
		                String prenom=params[2];
		                String categorie=params[3];
		                String user=params[4];
		                String pwd=params[5];
		                String id=params[6];
		                URL url= new URL(updateurl);
		                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
		                httpURLConnection.setRequestMethod("POST");
		                httpURLConnection.setDoInput(true);
		                httpURLConnection.setDoOutput(true);
		                OutputStream OutputStream=httpURLConnection.getOutputStream();
		                BufferedWriter BufferedWriter = new BufferedWriter(new OutputStreamWriter(OutputStream,"utf-8"));
		                String post_data= URLEncoder.encode("name","UTF-8")+"="+URLEncoder.encode(nom,"UTF-8")+"&"
		                		+URLEncoder.encode("surname","UTF-8")+"="+URLEncoder.encode(prenom,"UTF-8")+"&"
		                		+URLEncoder.encode("categorie","UTF-8")+"="+URLEncoder.encode(categorie,"UTF-8")+"&"
		                		+URLEncoder.encode("user","UTF-8")+"="+URLEncoder.encode(user,"UTF-8")+"&"
		                		+URLEncoder.encode("passe","UTF-8")+"="+URLEncoder.encode(pwd,"UTF-8")+"&"
		                        +URLEncoder.encode("id","UTF-8")+"="+URLEncoder.encode(id,"UTF-8");
		                BufferedWriter.write(post_data);
		                BufferedWriter.flush();
		                BufferedWriter.close();
		                OutputStream.close();
		                InputStream inputStream = httpURLConnection.getInputStream();
		                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
		                String result="";
		                String ligne="";
		                while((ligne =bufferedReader.readLine())!=null){
		                    result+=ligne;
		                }

		            } catch (MalformedURLException e) {
		                e.printStackTrace();
		            } catch (IOException e) {
		                e.printStackTrace();
		            }
	        	
	        }else if(type.equals("coachDelete")){
	        	 try {
		                
		                String id=params[1];
		                URL url= new URL(deleteurl);
		                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
		                httpURLConnection.setRequestMethod("POST");
		                httpURLConnection.setDoInput(true);
		                httpURLConnection.setDoOutput(true);
		                OutputStream OutputStream=httpURLConnection.getOutputStream();
		                BufferedWriter BufferedWriter = new BufferedWriter(new OutputStreamWriter(OutputStream,"utf-8"));
		                String post_data= URLEncoder.encode("id","UTF-8")+"="+URLEncoder.encode(id,"UTF-8");
		                        
		                BufferedWriter.write(post_data);
		                BufferedWriter.flush();
		                BufferedWriter.close();
		                OutputStream.close();
		                InputStream inputStream = httpURLConnection.getInputStream();
		                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
		                String result="";
		                String ligne="";
		                while((ligne =bufferedReader.readLine())!=null){
		                    result+=ligne;
		                }

		            } catch (MalformedURLException e) {
		                e.printStackTrace();
		            } catch (IOException e) {
		                e.printStackTrace();
		            }
	        
	        }if(type.equals("passion")){
	            try {
	                String nom=params[1];
	                String prenom=params[2];
	                String age=params[3];
	                String abonnement=params[4];
	                String user=params[5];
	                String pwd=params[6];
	                URL url= new URL(insert_pasion_url);
	                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
	                httpURLConnection.setRequestMethod("POST");
	                httpURLConnection.setDoInput(true);
	                httpURLConnection.setDoOutput(true);
	                OutputStream OutputStream=httpURLConnection.getOutputStream();
	                BufferedWriter BufferedWriter = new BufferedWriter(new OutputStreamWriter(OutputStream,"utf-8"));
	                String post_data= URLEncoder.encode("name","UTF-8")+"="+URLEncoder.encode(nom,"UTF-8")+"&"
	                		+URLEncoder.encode("surname","UTF-8")+"="+URLEncoder.encode(prenom,"UTF-8")+"&"
	                		+URLEncoder.encode("age","UTF-8")+"="+URLEncoder.encode(age,"UTF-8")+"&"
	                		+URLEncoder.encode("abonnement","UTF-8")+"="+URLEncoder.encode(abonnement,"UTF-8")+"&"
	                		+URLEncoder.encode("user","UTF-8")+"="+URLEncoder.encode(user,"UTF-8")+"&"
	                        +URLEncoder.encode("passe","UTF-8")+"="+URLEncoder.encode(pwd,"UTF-8");
	                BufferedWriter.write(post_data);
	                BufferedWriter.flush();
	                BufferedWriter.close();
	                OutputStream.close();
	                InputStream inputStream = httpURLConnection.getInputStream();
	                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
	                String result="";
	                String ligne="";
	                while((ligne =bufferedReader.readLine())!=null){
	                    result+=ligne;
	                }

	            } catch (MalformedURLException e) {
	                e.printStackTrace();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }else if(type.equals("regime")){
	        	 try {
		                String categ=params[1];
		                String jour=params[2];
		                String desc=params[3];
		                
		                URL url= new URL(insert_regim_url);
		                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
		                httpURLConnection.setRequestMethod("POST");
		                httpURLConnection.setDoInput(true);
		                httpURLConnection.setDoOutput(true);
		                OutputStream OutputStream=httpURLConnection.getOutputStream();
		                BufferedWriter BufferedWriter = new BufferedWriter(new OutputStreamWriter(OutputStream,"utf-8"));
		                String post_data= URLEncoder.encode("categorie","UTF-8")+"="+URLEncoder.encode(categ,"UTF-8")+"&"
		                		+URLEncoder.encode("jour","UTF-8")+"="+URLEncoder.encode(jour,"UTF-8")+"&"
		                        +URLEncoder.encode("description","UTF-8")+"="+URLEncoder.encode(desc,"UTF-8");
		                BufferedWriter.write(post_data);
		                BufferedWriter.flush();
		                BufferedWriter.close();
		                OutputStream.close();
		                InputStream inputStream = httpURLConnection.getInputStream();
		                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
		                String result="";
		                String ligne="";
		                while((ligne =bufferedReader.readLine())!=null){
		                    result+=ligne;
		                }

		            } catch (MalformedURLException e) {
		                e.printStackTrace();
		            } catch (IOException e) {
		                e.printStackTrace();
		            }
	        }else if(type.equals("exerciceDelete")){
	        	 try {
		                
		                String id=params[1];
		                URL url= new URL(delete_exrecice);
		                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
		                httpURLConnection.setRequestMethod("POST");
		                httpURLConnection.setDoInput(true);
		                httpURLConnection.setDoOutput(true);
		                OutputStream OutputStream=httpURLConnection.getOutputStream();
		                BufferedWriter BufferedWriter = new BufferedWriter(new OutputStreamWriter(OutputStream,"utf-8"));
		                String post_data= URLEncoder.encode("id","UTF-8")+"="+URLEncoder.encode(id,"UTF-8");
		                        
		                BufferedWriter.write(post_data);
		                BufferedWriter.flush();
		                BufferedWriter.close();
		                OutputStream.close();
		                InputStream inputStream = httpURLConnection.getInputStream();
		                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
		                String result="";
		                String ligne="";
		                while((ligne =bufferedReader.readLine())!=null){
		                    result+=ligne;
		                }

		            } catch (MalformedURLException e) {
		                e.printStackTrace();
		            } catch (IOException e) {
		                e.printStackTrace();
		            }
	        }else if(type.equals("ajoutExercice")){
	            try {
	                
	                String categorie=params[1];
	                String jour=params[2];
	                String titre=params[3];
	                String description=params[4];
	                String image=params[5];
	        
	                URL url= new URL(insert_exercice);
	                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
	                httpURLConnection.setRequestMethod("POST");
	                httpURLConnection.setDoInput(true);
	                httpURLConnection.setDoOutput(true);
	                OutputStream OutputStream=httpURLConnection.getOutputStream();
	                BufferedWriter BufferedWriter = new BufferedWriter(new OutputStreamWriter(OutputStream,"utf-8"));
	                String post_data= URLEncoder.encode("categorie","UTF-8")+"="+URLEncoder.encode(categorie,"UTF-8")+"&"
	                		+URLEncoder.encode("jour","UTF-8")+"="+URLEncoder.encode(jour,"UTF-8")+"&"
	                		+URLEncoder.encode("titre","UTF-8")+"="+URLEncoder.encode(titre,"UTF-8")+"&"
	                		+URLEncoder.encode("description","UTF-8")+"="+URLEncoder.encode(description,"UTF-8")+"&"
	                		
	                        +URLEncoder.encode("image","UTF-8")+"="+URLEncoder.encode(image,"UTF-8");
	                BufferedWriter.write(post_data);
	                BufferedWriter.flush();
	                BufferedWriter.close();
	                OutputStream.close();
	                InputStream inputStream = httpURLConnection.getInputStream();
	                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
	                String result="";
	                String ligne="";
	                while((ligne =bufferedReader.readLine())!=null){
	                    result+=ligne;
	                }

	            } catch (MalformedURLException e) {
	                e.printStackTrace();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
		return null;
	}

}
