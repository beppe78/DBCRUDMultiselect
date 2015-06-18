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

public class EditDeleteSecondaTabella extends Activity{
	EditText nomeEdit2, cognomeEdit2, telEdit2, etaEdit2;
	String id;	
	Intent objIntent;
	DBController controller = new DBController(this);

	 @Override
	    public void onCreate(Bundle savedInstanceState) {
		 	super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_edit_delete2);
			
			nomeEdit2 = (EditText) findViewById(R.id.nome_edit_delete2);
			cognomeEdit2 = (EditText) findViewById(R.id.cognome_edit_delete2);
			telEdit2 = (EditText) findViewById(R.id.tel_edit_delete2);
			etaEdit2 = (EditText) findViewById(R.id.eta_edit_delete2);
			
			objIntent = getIntent();
			id = objIntent.getStringExtra("table_id2");			
				
			HashMap<String, String> contattiList = controller.getInfoTable2(id);
							
			if(contattiList.size()!=0) {
				nomeEdit2.setText(contattiList.get("nome"));
				cognomeEdit2.setText(contattiList.get("cognome"));
				telEdit2.setText(contattiList.get("tel"));
				etaEdit2.setText(contattiList.get("eta"));
			}
			
	    }
	public void ModificaSecondaTabella(final View view) {
		AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Messaggio"); //Set Alert dialog title here
        alert.setMessage("Sei sicuro di Modificare?"); //Message here

        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
        	public void onClick(DialogInterface dialog, int whichButton) {
        		if (TextUtils.isEmpty(nomeEdit2.getText().toString()) && TextUtils.isEmpty(cognomeEdit2.getText().toString()) 
        				&& TextUtils.isEmpty(telEdit2.getText().toString())) {
            		Toast.makeText(getApplicationContext(), "Fill All Texts", Toast.LENGTH_LONG).show();
            	}else {
		        	HashMap<String, String> queryValues =  new  HashMap<String, String>();
		    		nomeEdit2 = (EditText) findViewById(R.id.nome_edit_delete2);
		    		cognomeEdit2 = (EditText) findViewById(R.id.cognome_edit_delete2);
		    		telEdit2 = (EditText) findViewById(R.id.tel_edit_delete2);
		    		etaEdit2 = (EditText) findViewById(R.id.eta_edit_delete2);
		    		
		    		objIntent = getIntent();
		    		id = objIntent.getStringExtra("table_id2");
		    		queryValues.put("id_select", id);
		    		queryValues.put("nome", nomeEdit2.getText().toString());
		    		queryValues.put("cognome", cognomeEdit2.getText().toString());
		    		queryValues.put("tel", telEdit2.getText().toString());
		    		queryValues.put("eta", etaEdit2.getText().toString());
		    		controller.EditSecondaTabella(queryValues);
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
	
	public void EliminaSecondaTabella(final View view) {
		AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Message"); //Set Alert dialog title here
        alert.setMessage("Are you sure you Remove them?"); //Message here

        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int whichButton) {
        	objIntent = getIntent();
    		id = objIntent.getStringExtra("table_id2");
    		controller.EliminaSecondaTabella(id);
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
	public void IndietroModificaSecondaTabella(View view) {
		Intent objIntent = new Intent(getApplicationContext(), MainActivity.class);
		startActivity(objIntent);
	}
	public void callHomeActivity(View view) {
		Intent objIntent = new Intent(getApplicationContext(), MainActivity.class);
		startActivity(objIntent);
	}
	
	
}
