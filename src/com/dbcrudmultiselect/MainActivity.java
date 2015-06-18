package com.dbcrudmultiselect;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
    }
    public void openAggiungiPrimaTabella(View view) {
		Intent objIntent = new Intent(getApplicationContext(), AddDB.class);
		startActivity(objIntent);	
	}
    public void openAggiungiSecondaTabella(View view) {
		Intent objIntent = new Intent(getApplicationContext(), AddTable2.class);
		startActivity(objIntent);	
	}
    public void openListPrimaTabella(View view) {
		Intent objIntent = new Intent(getApplicationContext(), ListaPrimaTabella.class);
		startActivity(objIntent);	
	}
    public void openListSecondaTabella(View view) {
		Intent objIntent = new Intent(getApplicationContext(), ListaSecondaTabella.class);
		startActivity(objIntent);	
	}
    
}
