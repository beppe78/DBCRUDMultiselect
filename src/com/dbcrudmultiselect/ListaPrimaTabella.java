package com.dbcrudmultiselect;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import com.dbcrudmultiselect.DB.DBController;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ListaPrimaTabella extends ListActivity {

	DBController controller = new DBController(this);	
	Intent intent;
	TextView idText, listText;	
	ListView list;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
		
		controller = new DBController(this);	
		
		ArrayList<HashMap<String, String>> contattiList =  controller.getAllTableStudent();
		Collections.sort(contattiList,new Comparator<HashMap<String,String>>(){
		    public int compare(HashMap<String,String> mapping1,HashMap<String,String> mapping2){
		        return mapping1.get("nome").compareTo(mapping2.get("nome"));
		    }
		});
		if(contattiList.size()!=0) {
		list = getListView();
		list.setOnItemClickListener(new OnItemClickListener() {
			  @Override 
			  public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
				  idText = (TextView) view.findViewById(R.id.idText);
				  listText = (TextView) view.findViewById(R.id.listText);
				  
				  String valId = idText.getText().toString();	
				  String valList = listText.getText().toString();	
				  Toast.makeText(getApplicationContext(), valList, Toast.LENGTH_LONG).show();
				  Intent  objIndent = new Intent(getApplicationContext(),EditDeletePrimaTabella.class);
				  objIndent.putExtra("table_id", valId); 
				  objIndent.putExtra("datalist", valList);
				  startActivity(objIndent);
			  }
		});
		ListAdapter adapter = new SimpleAdapter( ListaPrimaTabella.this,contattiList, R.layout.activity_row, new String[] { "id_student","nome"}, new int[] {R.id.idText, R.id.listText}); 
		setListAdapter(adapter);		
		}
	}
	public void indietroHome(View view) {
		Intent objIntent = new Intent(getApplicationContext(), MainActivity.class);
		startActivity(objIntent);	
	}

}