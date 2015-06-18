package com.dbcrudmultiselect;

import java.util.ArrayList;
import java.util.HashMap;

import com.dbcrudmultiselect.DB.Contatti;
import com.dbcrudmultiselect.DB.DBController;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ListView;
import android.widget.Toast;


public class AddTable2 extends Activity {
	MyCustomAdapter dataAdapter2 = null;
	
	Intent intent;
	DBController controller = new DBController(this);
	EditText edit_text_eta;
	ListView listView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add2);
		
		checkButtonClick();
		
		listView = (ListView) findViewById(R.id.list2);
		
		ArrayList<Contatti> countryList = (ArrayList<Contatti>) controller.getAllLabelsPrimaTabella();
		dataAdapter2 = new MyCustomAdapter(this,R.layout.activity_row2, countryList);	
		listView.setAdapter(dataAdapter2);
		
		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			    // When clicked, show a toast with the TextView text
			    Contatti country = (Contatti) parent.getItemAtPosition(position);
			    Toast.makeText(getApplicationContext(),"Clicked on Row: " +country.getCheck() , Toast.LENGTH_LONG).show();
			}
		});
		dataAdapter2.notifyDataSetChanged();
	}
			
	private class MyCustomAdapter extends ArrayAdapter<Contatti> {
		 
		  private ArrayList<Contatti> countryList;
		 
		  public MyCustomAdapter(Context context, int textViewResourceId, ArrayList<Contatti> countryList) {
			  super(context, textViewResourceId, countryList);
			  this.countryList = new ArrayList<Contatti>();
			  this.countryList.addAll(countryList);
		  }
		 
		  private class ViewHolder {
		   TextView nome, cognome, tel, eta;
		   CheckBox check;
		  }
		 
		  @Override
		  public View getView(int position, View convertView, ViewGroup parent) {
		 
		   ViewHolder holder = null;
		   Log.v("ConvertView", String.valueOf(position));
		 
		   if (convertView == null) {
		   LayoutInflater vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		   convertView = vi.inflate(R.layout.activity_row2, null);
		 
		   holder = new ViewHolder();
		   holder.nome = (TextView) convertView.findViewById(R.id.nomeText2);		   
		   holder.cognome = (TextView) convertView.findViewById(R.id.cognomeText2);
		   holder.tel = (TextView) convertView.findViewById(R.id.telText2);	
		   holder.eta = (TextView) convertView.findViewById(R.id.etaText2);
		   holder.check = (CheckBox) convertView.findViewById(R.id.checkBox1);
		   convertView.setTag(holder);
		 
		   holder.check.setOnClickListener( new View.OnClickListener() {  
		     public void onClick(View v) {  
		      CheckBox cb = (CheckBox) v ;  
		      Contatti country = (Contatti) cb.getTag();  
		      country.setSelected(cb.isChecked());
		     }  
		    });
		   } 
		   else {
		    holder = (ViewHolder) convertView.getTag();
		   }
		 
		   Contatti country = countryList.get(position);
		   //holder.nome.setText(" (" +  country.getCheck() + ")");
		   holder.check.setText(country.getNome());
		   holder.check.setChecked(country.isSelected());
		   holder.check.setTag(country);
		 
		   return convertView;
		 
		  }
		 
	}
	
	private void checkButtonClick() {
		edit_text_eta = (EditText)findViewById(R.id.edit_text_eta);	 
		Button myButton = (Button) findViewById(R.id.btn_crea_playlist);
		myButton.setOnClickListener(new OnClickListener() {	 
		   @Override
		   public void onClick(View v) {			 
			   ArrayList<Contatti> countryList = dataAdapter2.countryList;
			   for(int i=0;i<countryList.size();i++){
				   Contatti country = countryList.get(i);
				   if(country.isSelected()){		    	 
					   //String valList = (String) spinner.getSelectedItem();
					   String valList = edit_text_eta.getText().toString();
					   HashMap<String, String> queryValues =  new  HashMap<String, String>();
					   queryValues.put("nome", country.getNome());
					   queryValues.put("cognome", country.getCognome());
					   queryValues.put("tel", country.getTel());
					   queryValues.put("eta", valList);
					   controller.AddSecondaTabella(queryValues);	    	 
				   }

			   }	 
			   Toast.makeText(getApplicationContext(), "Dati inseriti!", Toast.LENGTH_LONG).show();	 
		   }
		});		 
	}
	
	public void indietroAddTable2(View view) {
		Intent objIntent = new Intent(getApplicationContext(), MainActivity.class);
		objIntent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
		startActivity(objIntent);
		//overridePendingTransition(R.anim.in_destra, R.anim.out_destra);

	}
	
	
}