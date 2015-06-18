package com.dbcrudmultiselect.DB;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import android.util.Log;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBController  extends SQLiteOpenHelper {
	private static final String LOGCAT = null;

	// CREO DATABASE
	public DBController(Context applicationcontext) {
        super(applicationcontext, "database.db", null, 1);
        Log.d(LOGCAT,"Created");
    }
	//CREO LE TRE TABELLE
	@Override
	public void onCreate(SQLiteDatabase database) {
		String query, query2;
		query = "CREATE TABLE table_student (id_student INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT NOT NULL, " +
				"cognome TEXT NOT NULL, tel TEXT NOT NULL)";					
		
		query2 = "CREATE TABLE table_select ( id_select INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT NOT NULL, " +
				"cognome TEXT NOT NULL, tel TEXT NOT NULL, eta TEXT NOT NULL)";
        database.execSQL(query);
        database.execSQL(query2);
        Log.d(LOGCAT,"table_student Created");
        Log.d(LOGCAT,"table_select Created");
	}
	//IN CASO LA TABELLA ESISTE NNO CREO NULLA
	@Override
	public void onUpgrade(SQLiteDatabase db, int version_old, int current_version) {
		String query, query2;

		query = "DROP TABLE IF EXISTS table_student";
		query2 = "DROP TABLE IF EXISTS table_select";
		db.execSQL(query);
		db.execSQL(query2);
        onCreate(db);
	}
	
	//ADD DELLA PRIMA TABELLA
	public void AddPrimaTabella(HashMap<String, String> queryValues) {
		SQLiteDatabase database = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("nome", queryValues.get("nome"));
		values.put("cognome", queryValues.get("cognome"));
		values.put("tel", queryValues.get("tel"));		
		database.insert("table_student", null, values);
		database.close();
	}
	//ADD DELLA SECONDA TABELLA
	public void AddSecondaTabella(HashMap<String, String> queryValues) {
		SQLiteDatabase database = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("nome", queryValues.get("nome"));
		values.put("cognome", queryValues.get("cognome"));
		values.put("tel", queryValues.get("tel"));
		values.put("eta", queryValues.get("eta"));
		database.insert("table_select", null, values);
		database.close();
	}
	
	//EDIT PRIMA TABELLA
	public int EditPrimaTabella(HashMap<String, String> queryValues) {
		SQLiteDatabase database = this.getWritableDatabase();	 
	    ContentValues values = new ContentValues();
	    values.put("nome", queryValues.get("nome"));
	    values.put("cognome", queryValues.get("cognome"));
	    values.put("tel", queryValues.get("tel"));
	    return database.update("table_student", values, "id_student" + " = ?", new String[] { queryValues.get("id_student") });
	}
	//EDIT SECONDA TABELLA
	public int EditSecondaTabella(HashMap<String, String> queryValues) {
		SQLiteDatabase database = this.getWritableDatabase();	 
		ContentValues values = new ContentValues();
		values.put("nome", queryValues.get("nome"));
		values.put("cognome", queryValues.get("cognome"));
		values.put("tel", queryValues.get("tel"));
		values.put("eta", queryValues.get("eta"));
		return database.update("table_select", values, "id_select" + " = ?", new String[] { queryValues.get("id_select") });
	}
	
	//DELETE PRIMA TABELLA
	public void EliminaPrimaTabella(String id) {
		Log.d(LOGCAT,"delete");
		SQLiteDatabase database = this.getWritableDatabase();	 
		String deleteQuery = "DELETE FROM  table_student where id_student='"+ id +"'";
		Log.d("query",deleteQuery);		
		database.execSQL(deleteQuery);
	}
	//DELETE SECONDA TABELLA
	public void EliminaSecondaTabella(String id) {
		Log.d(LOGCAT,"delete");
		SQLiteDatabase database = this.getWritableDatabase();	 
		String deleteQuery = "DELETE FROM  table_select where id_select='"+ id +"'";
		Log.d("query",deleteQuery);		
		database.execSQL(deleteQuery);
	}
	
	//PER AVERE INFO SUL NOME CLICCATO
	public HashMap<String, String> getInfoTableStudent(String id) {
		HashMap<String, String> wordList = new HashMap<String, String>();
		SQLiteDatabase database = this.getReadableDatabase();
		String selectQuery = "SELECT * FROM table_student where id_student='"+id+"'"+"ORDER BY nome ASC";
		Cursor cursor = database.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {
	        do {					
	        	wordList.put("nome", cursor.getString(1));
	        	wordList.put("cognome", cursor.getString(2));
	        	wordList.put("tel", cursor.getString(3));
	        } while (cursor.moveToNext());
	    }				    
	return wordList;
	}
	
	//PER AVERE INFO SUL NOME CLICCATO
	public HashMap<String, String> getInfoTable2(String id) {
		HashMap<String, String> wordList = new HashMap<String, String>();
		SQLiteDatabase database = this.getReadableDatabase();
		String selectQuery = "SELECT * FROM table_select where id_select='"+id+"'"+"ORDER BY nome ASC";
		Cursor cursor = database.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {
			do {					
				wordList.put("nome", cursor.getString(1));
		        wordList.put("cognome", cursor.getString(2));
		        wordList.put("tel", cursor.getString(3));
		        wordList.put("eta", cursor.getString(4));
		        } while (cursor.moveToNext());
		    }				    
		return wordList;
		}
	
	//SHOW ALL TABLE_STUDENT IN ORDINE ALFABETICO NELLA LIST
	public ArrayList<HashMap<String, String>> getAllTableStudent() {
		ArrayList<HashMap<String, String>> wordList;
		wordList = new ArrayList<HashMap<String, String>>();
		String selectQuery = "SELECT * FROM table_student ORDER BY nome ASC ";
	    SQLiteDatabase database = this.getWritableDatabase();
	    Cursor cursor = database.rawQuery(selectQuery, null);
	    if (cursor.moveToFirst()) {
	        do {
	        	HashMap<String, String> map = new HashMap<String, String>();
	        	map.put("id_student", cursor.getString(0));
	        	map.put("nome", cursor.getString(1));
	        	map.put("cogonme", cursor.getString(2));
	        	map.put("tel", cursor.getString(3));
                wordList.add(map);
	        } while (cursor.moveToNext());
	    }	 	    
	    return wordList;
	}
	
	//SHOW ALL TABLE_SELECT IN ORDINE ALFABETICO NELLA LIST
	public ArrayList<HashMap<String, String>> getAllTableSelect() {
		ArrayList<HashMap<String, String>> wordList;
		wordList = new ArrayList<HashMap<String, String>>();
		String selectQuery = "SELECT * FROM table_select ORDER BY nome ASC ";
		SQLiteDatabase database = this.getWritableDatabase();
		Cursor cursor = database.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {
			do {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("id_select", cursor.getString(0));
		        map.put("nome", cursor.getString(1));
		        map.put("cogonme", cursor.getString(2));
		        map.put("tel", cursor.getString(3));
		        map.put("eta", cursor.getString(4));
	            wordList.add(map);
			} while (cursor.moveToNext());
		}	 	    
		return wordList;
	}

	//SHOW LIST TABLE STUDENT PER ADD TABLE SELECT
	public List<Contatti> getAllLabelsPrimaTabella() {
		List<Contatti> contactList = new ArrayList<Contatti>();
		// Select All Query
		String selectQuery = "SELECT  * FROM table_student ORDER BY nome ASC";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Contatti contact = new Contatti();
				contact.setID(Integer.parseInt(cursor.getString(0)));
				contact.setNome(cursor.getString(1));
				contact.setCognome(cursor.getString(2));
				contact.setTel(cursor.getString(3));
				//contact.setEta(cursor.getString(4));
				// Adding contact to list
				contactList.add(contact);
			} while (cursor.moveToNext());
				
		}
		// return contact list
		return contactList;
	}
}