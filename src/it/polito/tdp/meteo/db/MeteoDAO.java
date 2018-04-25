package it.polito.tdp.meteo.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.meteo.bean.Rilevamento;

public class MeteoDAO {

	
	
	public List<Rilevamento> getAVG(int mese)
	{
	String sql="select avg(umidita), localita from situazione where month(data)= ? group by localita";
	List<Rilevamento> rilevamenti = new ArrayList<Rilevamento>();
	try {
		Connection conn = DBConnect.getInstance().getConnection();
		PreparedStatement st = conn.prepareStatement(sql);
		st.setInt(1, mese);

		ResultSet rs = st.executeQuery();

		while (rs.next()) {
			Rilevamento r = new Rilevamento(rs.getString("Localita"), mese,  rs.getFloat("avg(umidita)"));
			rilevamenti.add(r);
		}

		conn.close();
		return rilevamenti;

	} catch (SQLException e) {

		e.printStackTrace();
		throw new RuntimeException(e);
	}
	
	}
	
	public List<ArrayList<Rilevamento>> get15(int mese)
	{
	String sql="select * from situazione where month(data)=? and (day(data) between 1 and 15) order by data"	;
	List<ArrayList<Rilevamento>> rilevamenti = new ArrayList<>();
	try {
		Connection conn = DBConnect.getInstance().getConnection();
		PreparedStatement st = conn.prepareStatement(sql);
		st.setInt(1, mese);

		ResultSet rs = st.executeQuery();
        int count=1;
		while (rs.next()) {
			Rilevamento r = new Rilevamento(rs.getString("Localita"), rs.getDate("data"),  rs.getFloat("umidita"));
			if(r!= null && count==1)
			{
				ArrayList<Rilevamento> lista=new ArrayList<>();
				lista.add(r);
				rilevamenti.add(lista);
				count++;
			}
			else if(count==3)
			{
			rilevamenti.get(rilevamenti.size()-1).add(r);
			count=1;
			}
			else
			{
				rilevamenti.get(rilevamenti.size()-1).add(r);
				count++;
			}
				
		}

		conn.close();
		return rilevamenti;

	} catch (SQLException e) {

		e.printStackTrace();
		throw new RuntimeException(e);
	}
	
		
		
		
		
	}
	
	
	

}
