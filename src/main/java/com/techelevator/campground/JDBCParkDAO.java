package com.techelevator.campground;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.model.Park;

@Component
public class JDBCParkDAO implements ParkDAO{

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JDBCParkDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<Park> getAllParks() {
		List<Park> allParks = new ArrayList<>();
		SqlRowSet results = jdbcTemplate.queryForRowSet("SELECT * FROM park");
		while(results.next()) {
			Park park = getPark(results);
			allParks.add(park);
		}
		return allParks;
	}


	@Override
	public Park readParkById(Long id) {
		Park park = null;
		String sqlRequestForPark = "SELECT * FROM park WHERE park_id = ?";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlRequestForPark, id);  //second parameter ('id') defines the ? in sqlRequestForPark
		while(result.next()) {
			park = getPark(result);
		}
		return park;
	}

	private Park getPark(SqlRowSet results) {
		Park park = new Park();
		park.setParkId(results.getLong("park_id"));
		park.setName(results.getString("name"));
		park.setLocation(results.getString("location"));
		Date date = results.getDate("establish_date");
		LocalDate establishDate = date.toLocalDate();
		park.setEstablishDate(establishDate);
		park.setArea(results.getInt("area"));
		park.setVisitors(results.getInt("visitors"));
		park.setDescription(results.getString("description"));
		return park;
	}
}
