package com.example.app_fitness;

import java.util.ArrayList;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class EtudiantDatabaseHandler extends SQLiteOpenHelper{
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME ="DBEtudiants";
	private static final String TABLE_ETUDIANTS ="Etudiants";
	private static final String COLONNE_ID = "id";
	private static final String COLONNE_PRENOM = "prenom";
	private static final String COLONNE_NOM = "nom";
	
	
	private static final String COLONNE_LOGIN = "login";
	private static final String COLONNE_PASSWORD = "password";
	private static final String REQUETE_CREATION_BD = "create table "+ TABLE_ETUDIANTS + " (" 
	                                                  + COLONNE_ID+ " integer primary key autoincrement, " 
			                                          + COLONNE_PRENOM+ " TEXT not null, " 
	                                                  + COLONNE_NOM+ " TEXT not null,"
	                                                  
	                                                  + COLONNE_LOGIN+ " TEXT not null,"
	                                                  + COLONNE_PASSWORD+ " TEXT not null );";
	
	public EtudiantDatabaseHandler(Context context) {
		super(context, DATABASE_NAME ,null, DATABASE_VERSION);
		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(REQUETE_CREATION_BD);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE" +TABLE_ETUDIANTS + ";");
		onCreate(db);
		
	}
	public void insertEtudiant(Etudiant etu) {
		SQLiteDatabase maBD =this.getWritableDatabase();
		ContentValues valeurs = new ContentValues();
		valeurs.put(COLONNE_PRENOM,etu.getPrenom());
		valeurs.put(COLONNE_NOM,etu.getNom());
		
		valeurs.put(COLONNE_LOGIN,etu.getLogin());
		valeurs.put(COLONNE_PASSWORD,etu.getPassword());
		maBD.insert(TABLE_ETUDIANTS, null, valeurs);
		maBD.close();
		
	}
	public void updateEtudiant(int id, Etudiant etudiantToUpdate) {
		SQLiteDatabase maBD =this.getWritableDatabase();
		ContentValues valeurs = new ContentValues();
		valeurs.put(COLONNE_PRENOM,etudiantToUpdate.getPrenom());
		valeurs.put(COLONNE_NOM,etudiantToUpdate.getNom());
		
		
		valeurs.put(COLONNE_LOGIN,etudiantToUpdate.getLogin());
		valeurs.put(COLONNE_PASSWORD,etudiantToUpdate.getPassword());
		maBD.update(TABLE_ETUDIANTS, valeurs,COLONNE_ID + " = "+ id, null);
		maBD.close();
	}
	public void removeEtudiant(String nom) {
		SQLiteDatabase maBD =this.getWritableDatabase();
		maBD.delete(TABLE_ETUDIANTS, COLONNE_NOM+ " = ?", new String[] { nom});
		maBD.close();
	}
	public void removeEtudiant(int etudiantID) {
		SQLiteDatabase maBD =this.getWritableDatabase();
		maBD.delete(TABLE_ETUDIANTS, COLONNE_ID +" = "+ etudiantID , null);
		maBD.close();
	}
	public void removeAll() {
		SQLiteDatabase maBD =this.getWritableDatabase();
		maBD.delete(TABLE_ETUDIANTS,null , null);
		maBD.close();
	}
	public Etudiant getEtudiant(int id) {
		SQLiteDatabase maBD =this.getReadableDatabase();
		Cursor c =maBD.query(TABLE_ETUDIANTS,new String[] {COLONNE_ID, COLONNE_PRENOM,COLONNE_NOM,COLONNE_LOGIN,COLONNE_PASSWORD },COLONNE_ID + " =? " , new String[]{String.valueOf(id)},null, null, null);
		return cursorToEtudiant(c);
	}
	public Etudiant getEtudiantLogin(String login) {
		SQLiteDatabase maBD =this.getReadableDatabase();
		//Cursor c = maBD.rawQuery("Select * from Etudiants where login="+login/*+" and password="+password*/,null);
		Cursor c =maBD.query(TABLE_ETUDIANTS,new String[] {COLONNE_ID, COLONNE_PRENOM,COLONNE_NOM,COLONNE_LOGIN,COLONNE_PASSWORD },
							COLONNE_LOGIN + " =? " , new String[]{login},null, null, null);
		return cursorToEtudiant(c);
	}
	public Etudiant getEtudiantPassword(String password) {
		SQLiteDatabase maBD =this.getReadableDatabase();
		Cursor c =maBD.query(TABLE_ETUDIANTS,new String[] {COLONNE_ID, COLONNE_PRENOM,COLONNE_NOM,COLONNE_LOGIN,COLONNE_PASSWORD },
							COLONNE_PASSWORD + " =? " , new String[]{password},null, null, null);
		return cursorToEtudiant(c);
	}
	public ArrayList<Etudiant> getAllEtudiants() {
		SQLiteDatabase maBD =this.getReadableDatabase();
		Cursor c =maBD.query(TABLE_ETUDIANTS,new String[] {COLONNE_ID, COLONNE_PRENOM,COLONNE_NOM,COLONNE_LOGIN,COLONNE_PASSWORD},null ,null,null, null, null);
		return cursorToEtudiants(c);
	}
	private Etudiant cursorToEtudiant(Cursor c) {
		if (c==null || c.getCount() == 0){
			Log.i("Etudiant","NULLLLLLL");
			return null;
		}
		    
		c.moveToFirst();
		Etudiant retEtudiant = new Etudiant();
		retEtudiant.setId(c.getInt(c.getColumnIndex(COLONNE_ID)));
		retEtudiant.setPrenom(c.getString(c.getColumnIndex(COLONNE_PRENOM)));
	    retEtudiant.setNom(c.getString(c.getColumnIndex(COLONNE_NOM)));
	    
	    
	    retEtudiant.setLogin(c.getString(c.getColumnIndex(COLONNE_LOGIN)));
	    retEtudiant.setPassword(c.getString(c.getColumnIndex(COLONNE_PASSWORD)));
		c.close();
		return retEtudiant;
	}
	private ArrayList<Etudiant> cursorToEtudiants(Cursor c) {
		
		if (c==null || c.getCount() == 0)
		      return new ArrayList<Etudiant>(0);
		ArrayList<Etudiant> retEtudiants = new ArrayList<Etudiant>(c.getCount());
		c.moveToFirst();
		do {
		   Etudiant etudiant = new Etudiant();
		   etudiant.setId(c.getInt(c.getColumnIndex(COLONNE_ID)));
		   etudiant.setPrenom(c.getString(c.getColumnIndex(COLONNE_PRENOM)));
		   etudiant.setNom(c.getString(c.getColumnIndex(COLONNE_NOM)));
		  
		   
		   etudiant.setLogin(c.getString(c.getColumnIndex(COLONNE_LOGIN)));
		   etudiant.setPassword(c.getString(c.getColumnIndex(COLONNE_PASSWORD)));
		  retEtudiants.add(etudiant);
		} while (c.moveToNext());
		c.close();
		return retEtudiants;
		}
	


}
