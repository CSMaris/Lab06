package it.polito.tdp.meteo;

import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.meteo.bean.Rilevamento;
import it.polito.tdp.meteo.db.MeteoDAO;

public class Model {

	private MeteoDAO dao;
	private Ricorsione rec;

	public Model() {
		this.dao=new MeteoDAO();
		this.rec=new Ricorsione();

	}

	public String getUmiditaMedia(int mese) {
		
		List<Rilevamento> rilevamenti =dao.getAVG(mese);
		StringBuilder sb=new StringBuilder();
		
		for(Rilevamento r:rilevamenti)
		{
			sb.append(r.getLocalita()+": "+r.getUmidita()+"\n");
		}

		return sb.toString();
	}

	public String trovaSequenza(int mese) {
		List<Rilevamento> lista=new ArrayList<>(rec.getBestRoute(mese));
		StringBuilder sb=new StringBuilder();
		for(Rilevamento r:lista)
		{
			sb.append(r.getLocalita()+"  "+r.getData()+"\n");
		}
		sb.append("Total cost: "+ rec.getCost());
		

		return sb.toString();
	}
	
	public void azzeraSoluzione()
	{
		rec.azzera();
	}



}
