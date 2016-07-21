package com.techelevator.campground;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.model.Reservation;
import com.techelevator.model.Site;

@Component
public class JDBCReservationDAO implements ReservationDAO{

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JDBCReservationDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<Site> getAvailableSites(Long campgroundId, Date fromDate, Date toDate) {
		List<Site> allAvailableSites = new ArrayList<>();
		String sqlSearchAvailableSites = "SELECT * FROM site " +
									   "WHERE site.campground_id = ? AND site.site_id NOT IN " +
									   "(SELECT reservation.site_id FROM reservation WHERE ((?, ?) OVERLAPS (from_date, to_date)))";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSearchAvailableSites, campgroundId, fromDate, toDate);
		while (results.next()) {
			Site site = new Site();
			site.setSiteId(results.getLong("site_id"));
			site.setCampgroundId(results.getLong("campground_id"));
			site.setSiteNumber(results.getInt("site_number"));
			site.setMaxOccupancy(results.getInt("max_occupancy"));
			site.setAccessible(results.getBoolean("accessible"));
			site.setMaxRvLength(results.getInt("max_rv_length"));
			site.setUtilities(results.getBoolean("utilities"));
			allAvailableSites.add(site);
		}
		return allAvailableSites;
	}
	public void saveReservation(Long siteId, String name, Date fromDate, Date toDate) {
		String sqlInsertReservation = "INSERT INTO reservation(site_id, name, from_date, to_date)"
				+ 					   "VALUES(?, ?, ?, ?)";
	jdbcTemplate.update(sqlInsertReservation, siteId, name, fromDate, toDate);
	}

}
