package com.dbcrudmultiselect;


import java.util.HashMap;
import com.dbcrudmultiselect.DB.DBController;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class AddDB extends Activity{
	EditText nome, cognome, tel;
	DBController controller = new DBController(this);
	Intent intent;
	
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);        
	        setContentView(R.layout.activity_add);	       	        

	        
	        nome = (EditText) findViewById(R.id.txt_nome);
	        cognome = (EditText) findViewById(R.id.txt_cognome);
	        tel = (EditText) findViewById(R.id.txt_tel);

	}
	public void AddPrimaTabella(View view) {
		if (TextUtils.isEmpty(nome.getText().toString()) && TextUtils.isEmpty(cognome.getText().toString()) 
				&& TextUtils.isEmpty(tel.getText().toString())) {
    		Toast.makeText(getApplicationContext(), "Fill All Texts", Toast.LENGTH_LONG).show();
    	}else {
			HashMap<String, String> queryValues =  new  HashMap<String, String>();			
			queryValues.put("nome", nome.getText().toString());
			queryValues.put("cognome", cognome.getText().toString());
			queryValues.put("tel", tel.getText().toString());
			controller.AddPrimaTabella(queryValues);
			this.callHomeActivity(view);
			Toast.makeText(getApplicationContext(), "Dati Inseriti", Toast.LENGTH_LONG).show();
    	}
	}
	public void callHomeActivity(View view) {
		Intent objIntent = new Intent(getApplicationContext(), MainActivity.class);
		startActivity(objIntent);
	}	
    
}