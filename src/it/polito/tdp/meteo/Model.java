package it.polito.tdp.meteo;

import java.util.List;

import it.polito.tdp.meteo.bean.Rilevamento;
import it.polito.tdp.meteo.bean.SimpleCity;
import it.polito.tdp.meteo.db.MeteoDAO;

public class Model {

	private final static int COST = 100;
	private final static int NUMERO_GIORNI_CITTA_CONSECUTIVI_MIN = 3;
	private final static int NUMERO_GIORNI_CITTA_MAX = 6;
	private final static int NUMERO_GIORNI_TOTALI = 15;
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
		List<Rilevamento> lista= rec.getBestRoute(mese);
		StringBuilder sb=new StringBuilder();
		for(Rilevamento r:lista)
		{
			sb.append(r.getLocalita()+"  "+r.getData()+"\n");
		}
		sb.append("Total cost: "+ rec.getCost());
		

		return sb.toString();
	}

	/*private Double punteggioSoluzione(List<SimpleCity> soluzioneCandidata) {

		double score = 0.0;
		return score;
	}

	private boolean controllaParziale(List<SimpleCity> parziale) {

		return true;
	}*/

}
