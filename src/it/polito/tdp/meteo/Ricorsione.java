package it.polito.tdp.meteo;

import java.util.ArrayList;
import java.util.List;


import it.polito.tdp.meteo.bean.Rilevamento;
import it.polito.tdp.meteo.db.MeteoDAO;

public class Ricorsione {
	private List<Rilevamento> soluzione=new ArrayList<>();;
	private MeteoDAO dao=new MeteoDAO();
	private List<ArrayList<Rilevamento>> rilevamenti;
	//NB il vincolo allCities è già incluso nei vincoli morethan3 e lessthan6
	
	public List<Rilevamento> getBestRoute(int mese)
	{
		
		List<Rilevamento> parziale=new ArrayList<>();
		rilevamenti=dao.get15(mese);
		recursive(parziale, 0);
		
		return soluzione;
		
	}
	
	public void azzera()
	{
		soluzione.clear();
		minSum=Integer.MAX_VALUE;
	}
	
	int minSum=Integer.MAX_VALUE;
	private void recursive(List<Rilevamento> parziale, int livello)
	{
		
	if(livello==15)
	{
		
		int sommaU=0;
		
		for(Rilevamento r: parziale)
		sommaU+=r.getUmidita();
		
		int count=changes(parziale);
		int somma=sommaU+count*100;
		
		if (somma<minSum)
		{
			minSum=somma;
			soluzione=new ArrayList<>(parziale);
		}
		
		
		return;
	}
	
		for(Rilevamento r:rilevamenti.get(livello))
		{
		if(moreThan3(parziale, r) && lessThan6(parziale, r)) {
		parziale.add(r);	
		recursive(parziale, livello+1);
		parziale.remove(r);	
		}
		}
		
	}
	

private boolean lessThan6(List<Rilevamento> parziale, Rilevamento ril) {
	
	int count=0;
	 
	 for(Rilevamento r: parziale)
	 {
		if(r.getLocalita().equals(ril.getLocalita()))
			count++;
	 }
	 
	 if(count>=6)
		 return false;
	 else
		 return true;

	}

private int changes(List<Rilevamento> parziale) {
	String tmpCity=parziale.get(0).getLocalita();
	int count=0;	
	for(Rilevamento r:parziale)
	{
		if(!tmpCity.equals(r.getLocalita()))
		{
			tmpCity=r.getLocalita();
			count++;
		}
	}
	
		return count;
	}


private boolean moreThan3(List<Rilevamento> parziale, Rilevamento ril)
  {
	if(parziale.size()==0)  // primo giorno
		return true ;
	
	if(parziale.size()==1 || parziale.size()==2) // secondo o terzo giorno: non posso cambiare
		return parziale.get(parziale.size()-1).getLocalita().equals(ril.getLocalita()) ;
	
	if(parziale.get(parziale.size()-1).getLocalita().equals(ril.getLocalita())) // giorni successivi, posso SEMPRE rimanere
		return true ;
	
	// sto cambiando citta
	if(parziale.get(parziale.size()-1).getLocalita().equals(parziale.get(parziale.size()-2).getLocalita()) &&
			parziale.get(parziale.size()-2).getLocalita().equals(parziale.get(parziale.size()-3).getLocalita()) )
	{return true ;}
	
	return false;
  }


public int getCost()
{
	return minSum;
}
	
	
	

}
