package it.polito.tdp.meteo.bean;

import java.util.Date;

public class Rilevamento {

	private String localita;
	private Date data;
	private double umidita;
	private int mese;
	private boolean nuovaCity;

	public Rilevamento(String localita, Date data, double umidita) {
		super();
		this.localita = localita;
		this.data = data;
		this.umidita = umidita;
		this.mese=data.getMonth();
		
	}
	
	public Rilevamento(String localita,int mese,  double umidita)
	{
		super();
		this.localita = localita;
		this.mese=mese;
		this.umidita = umidita;
	}
	
	public Rilevamento(String localita, Date data, double umidita, boolean nuovaCity) {
		super();
		this.localita = localita;
		this.data = data;
		this.umidita = umidita;
		this.nuovaCity=nuovaCity;
		
	}
	
	public boolean getChange()
	{
		return nuovaCity;
	}
	

	public String getLocalita() {
		return localita;
	}

	public void setLocalita(String localita) {
		this.localita = localita;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public double getUmidita() {
		return umidita;
	}

	public void setUmidita(int umidita) {
		this.umidita = umidita;
	}

	// @Override
	// public String toString() {
	// return localita + " " + data + " " + umidita;
	// }

	@Override
	public String toString() {
		return String.valueOf(umidita);
	}

}
