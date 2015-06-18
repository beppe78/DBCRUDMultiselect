package com.dbcrudmultiselect.DB;

public class Contatti {	
    //private variables
    int id;
    String nome;
    String cognome;
    String tel;
    String eta;
    String check;
    boolean selected = false;
    
    public Contatti(){
         
    }
    // constructor
    public Contatti(int id, String nome, String cognome, String tel, String eta, String check, boolean selected){
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.tel = tel;
        this.eta = eta;
        this.check = check;
        this.selected = selected;
    }

    // getting ID
    public int getID(){
        return this.id;
    }
     
    // setting id
    public void setID(int id){
        this.id = id;
    }
     
    public String getNome(){
        return this.nome;
    }
     
    public void setNome(String nome){
        this.nome = nome;
    }
     
    public String getCognome(){
        return this.cognome;
    }
     
    public void setCognome(String cognome){
        this.cognome = cognome;
    }
    
    public String getTel(){
        return this.tel;
    }
     
    public void setTel(String tel){
        this.tel = tel;
    }
    
    public String getEta(){
        return this.eta;
    }
     
    public void setEta(String eta){
        this.eta= eta;
    }

    
    
    public String getCheck() {
    	return check;
    }
    public void setCheck(String check) {
    	this.check = check;
    }
    public boolean isSelected() {
    	return selected;
    }
    public void setSelected(boolean selected) {
    	this.selected = selected;
    }

    
}