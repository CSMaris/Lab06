package it.polito.tdp.meteo;

import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.meteo.bean.Citta;
import it.polito.tdp.meteo.bean.Rilevamento;
import it.polito.tdp.meteo.db.MeteoDAO;

public class Ricorsione {
	private List<Rilevamento> soluzione;
	private MeteoDAO dao=new MeteoDAO();
	
	
	public List<Rilevamento> getBestRoute(int mese)
	{
		soluzione=new ArrayList<>();
		List<Rilevamento> parziale=new ArrayList<>();
		List<Rilevamento> rilevamenti=dao.get15(mese);
		recursive(parziale, mese, 0, rilevamenti);
		
		return soluzione;
		
	}
	
	
	
	
	int minSum=1000;
	private void recursive(List<Rilevamento> parziale, int mese, int livello, List<Rilevamento> ril15)
	{
		
	if(livello==8)
	{
		if(allCities(parziale)  && allDates(parziale, ril15) && moreThan3(parziale)) {
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
		}
		
		return;
	}
	
	
	for(Rilevamento r: ril15)
	{
		parziale.add(r);
		if(lessThan6(parziale))
		{	
			List<Rilevamento> lista=new ArrayList<>(ril15);
			lista.remove(r);
		recursive(parziale, mese, livello+1, lista);
		}
		parziale.remove(r);			
	}	
	}
	
	public int getCost()
	{
		return minSum;
	}
	
	
private boolean allDates(List<Rilevamento> parziale, List<Rilevamento> ril15) {
	boolean flag;
	for(Rilevamento r1:ril15)
	{
		flag=false;
		for(Rilevamento r2:parziale)
		{
			if(r1.getData().equals(r2.getData()))
			{
				flag=true;
				break;
			}	
		}
		if(!flag)
			return false;
		
	}
		
		return true;
	}


private boolean lessThan6(List<Rilevamento> parziale) {
	
		
	 String city1="Genova";
	 String city2="Milano";
	 String city3="Torino";
	 int count1=0;
	 int count2=0;
	 int count3=0;
	 
	 for(Rilevamento r: parziale)
	 {
		if(r.getLocalita().equals(city1))
			count1++;
			
		
		if(r.getLocalita().equals(city2))
			count2++;
		
		if(r.getLocalita().equals(city3))
			count3++;
		 
	 }
	 
	 if(count1>6 || count2>6 || count3>6)
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


private boolean allCities(List<Rilevamento> parziale)
{
 String city1="Genova";
 String city2="Milano";
 String city3="Torino";
 boolean flag1=false;
 boolean flag2=false;
 boolean flag3=false;
 
 for(Rilevamento r: parziale)
 {
	if(r.getLocalita().equals(city1))
		flag1=true;
	
	if(r.getLocalita().equals(city2))
		flag2=true;
	
	if(r.getLocalita().equals(city3))
		flag3=true;
	 
 }
 
 if(flag1 && flag2 && flag3)
	 return true;
 else
	 return false;
}

private boolean moreThan3(List<Rilevamento> parziale)
  {
	String tmpCity=parziale.get(0).getLocalita();
	int count=0;
for(Rilevamento r:parziale)
{
if(tmpCity.equals(r.getLocalita()))
count++;
else
  {
	
	if(count<3)
		{return false;}
	
	count=1;
	tmpCity=r.getLocalita();
  }
	
  }


return true;	
 }
	
	
	

}
