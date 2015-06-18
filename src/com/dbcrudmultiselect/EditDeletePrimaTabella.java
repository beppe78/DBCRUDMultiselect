package com.dbcrudmultiselect;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.HashMap;

import com.dbcrudmultiselect.DB.DBController;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class EditDeletePrimaTabella extends Activity{
	EditText nomeEdit, cognomeEdit, telEdit;
	String id;	
	Intent objIntent;
	DBController controller = new DBController(this);

	 @Override
	    public void onCreate(Bundle savedInstanceState) {
		 	super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_edit_delete);
			
			nomeEdit = (EditText) findViewById(R.id.nome_edit_delete);
			cognomeEdit = (EditText) findViewById(R.id.cognome_edit_delete);
			telEdit = (EditText) findViewById(R.id.tel_edit_delete);
			
			objIntent = getIntent();
			id = objIntent.getStringExtra("table_id");			
				
			HashMap<String, String> contattiList = controller.getInfoTableStudent(id);
							
			if(contattiList.size()!=0) {
				nomeEdit.setText(contattiList.get("nome"));
				cognomeEdit.setText(contattiList.get("cognome"));
				telEdit.setText(contattiList.get("tel"));
			}
			
	    }
	public void ModificaPrimaTabella(final View view) {
		AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Messaggio"); //Set Alert dialog title here
        alert.setMessage("Sei sicuro di Modificare?"); //Message here

        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
        	public void onClick(DialogInterface dialog, int whichButton) {
        		if (TextUtils.isEmpty(nomeEdit.getText().toString()) && TextUtils.isEmpty(cognomeEdit.getText().toString()) 
        				&& TextUtils.isEmpty(telEdit.getText().toString())) {
            		Toast.makeText(getApplicationContext(), "Fill All Texts", Toast.LENGTH_LONG).show();
            	}else {
		        	HashMap<String, String> queryValues =  new  HashMap<String, String>();
		    		nomeEdit = (EditText) findViewById(R.id.nome_edit_delete);
		    		cognomeEdit = (EditText) findViewById(R.id.cognome_edit_delete);
		    		telEdit = (EditText) findViewById(R.id.tel_edit_delete);
		    		
		    		objIntent = getIntent();
		    		id = objIntent.getStringExtra("table_id");
		    		queryValues.put("id_student", id);
		    		queryValues.put("nome", nomeEdit.getText().toString());
		    		queryValues.put("cognome", cognomeEdit.getText().toString());
		    		queryValues.put("tel", telEdit.getText().toString());
		    		controller.EditPrimaTabella(queryValues);
		    		this.callHomeActivity(view);
		    		Toast.makeText(getApplicationContext(), "Dati Modificati!", Toast.LENGTH_LONG).show();
            	}
        	}

        	private void callHomeActivity(View view) {
				// TODO Auto-generated method stub
				Intent objIntent = new Intent(getApplicationContext(), MainActivity.class);
				startActivity(objIntent);
			}
        });

        alert.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
        	public void onClick(DialogInterface dialog, int whichButton) {
	            // Canceled.
	            dialog.cancel();
        	}
        });
        AlertDialog alertDialog = alert.create();
        alertDialog.show();
		
		
	}
	
	public void EliminaPrimaTabella(final View view) {
		AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Message"); //Set Alert dialog title here
        alert.setMessage("Are you sure you Remove them?"); //Message here

        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int whichButton) {
        	objIntent = getIntent();
    		id = objIntent.getStringExtra("table_id");
    		controller.EliminaPrimaTabella(id);
    		this.callHomeActivity(view);
    		Toast.makeText(getApplicationContext(), "Dati Eliminati!", Toast.LENGTH_LONG).show();
        }

		private void callHomeActivity(View view) {
				// TODO Auto-generated method stub
				Intent objIntent = new Intent(getApplicationContext(), MainActivity.class);
				startActivity(objIntent);
			}
        });

        alert.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
          public void onClick(DialogInterface dialog, int whichButton) {
            // Canceled.
              dialog.cancel();
          }
        });
        AlertDialog alertDialog = alert.create();
        alertDialog.show();
		
		
	}
	public void IndietroModificaPrimaTabella(View view) {
		Intent objIntent = new Intent(getApplicationContext(), MainActivity.class);
		startActivity(objIntent);
	}
	public void callHomeActivity(View view) {
		Intent objIntent = new Intent(getApplicationContext(), MainActivity.class);
		startActivity(objIntent);
	}
	
	
}
