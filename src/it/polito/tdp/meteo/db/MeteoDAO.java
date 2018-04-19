package it.polito.tdp.meteo.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.meteo.bean.Rilevamento;

public class MeteoDAO {

	/*public List<Rilevamento> getAllRilevamenti() {

		final String sql = "SELECT Localita, Data, Umidita FROM situazione ORDER BY data ASC";

		List<Rilevamento> rilevamenti = new ArrayList<Rilevamento>();

		try {
			Connection conn = DBConnect.getInstance().getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				Rilevamento r = new Rilevamento(rs.getString("Localita"), rs.getDate("Data"), rs.getInt("Umidita"));
				rilevamenti.add(r);
			}

			conn.close();
			return rilevamenti;

		} catch (SQLException e) {

			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}*/

	/*public List<Rilevamento> getAllRilevamentiLocalitaMese(int mese, String localita) {

		return null;
	}

	public Double getAvgRilevamentiLocalitaMese(int mese, String localita) {

		return 0.0;
	}*/
	
	
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
	
	public List<Rilevamento> get15(int mese)
	{
	String sql="select * from situazione where month(data)=? and (day(data) between 1 and 8)"	;
	List<Rilevamento> rilevamenti = new ArrayList<Rilevamento>();
	try {
		Connection conn = DBConnect.getInstance().getConnection();
		PreparedStatement st = conn.prepareStatement(sql);
		st.setInt(1, mese);

		ResultSet rs = st.executeQuery();

		while (rs.next()) {
			Rilevamento r = new Rilevamento(rs.getString("Localita"), rs.getDate("data"),  rs.getFloat("umidita"));
			rilevamenti.add(r);
		}

		conn.close();
		return rilevamenti;

	} catch (SQLException e) {

		e.printStackTrace();
		throw new RuntimeException(e);
	}
	
		
		
		
		
	}
	
	
	

}
