package com.techelevator.campground;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.model.Campground;
import com.techelevator.model.Site;
@Component //spring uses this annotation to indicate to spring that this is 
		   //the class needing to be used for the Autowired annotation
public class JDBCcampgroundDAO implements CampgroundDAO{

	private JdbcTemplate jdbcTemplate;
	
	@Autowired//as soon as you go to a page the DAO is accessible
	public JDBCcampgroundDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		
	}
	
	@Override
	public List<Campground> getAllCampgrounds(Long parkId) {
		List<Campground> allCampgrounds = new ArrayList<>();
		String sqlForAllCampgrounds = "SELECT campground.campground_id, campground.park_id, campground.name, campground.open_from_mm, campground.open_to_mm, campground.daily_fee, COUNT(site.site_id) AS number_of_sites " + 
									  "FROM campground JOIN site ON campground.campground_id = site.campground_id " +
									  "WHERE park_id = ? " +
									  "GROUP BY campground.campground_id";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlForAllCampgrounds, parkId);
		while (results.next() ) {
			Campground campground = new Campground();
			campground.setCampgroundId(results.getLong("campground_id"));
			campground.setParkId(results.getLong("park_id"));
			campground.setName(results.getString("name"));
			campground.setOpenFromMm(results.getString("open_from_mm"));
			campground.setOpenToMm(results.getString("open_to_mm"));
			campground.setDailyFee(results.getDouble("daily_fee"));
			campground.setNumberOfCampsites(results.getInt("number_of_sites"));
			allCampgrounds.add(campground);
		}
		return allCampgrounds;
	}
	
	@Override
	public Campground getCampgroundById(Long campgroundId) {
		Campground campground = null;
		String sqlRequestForPark = "SELECT * FROM campground WHERE campground_id = ?";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlRequestForPark, campgroundId);  //second parameter ('id') defines the ? in sqlRequestForPark
		while(result.next()) {
			campground = new Campground();
			campground.setCampgroundId(result.getLong("campground_id"));
			campground.setParkId(result.getLong("park_id"));
			campground.setName(result.getString("name"));
			campground.setOpenFromMm(result.getString("open_from_mm"));
			campground.setOpenToMm(result.getString("open_to_mm"));
			campground.setDailyFee(result.getDouble("daily_fee"));
		}
		return campground;
	}

	@Override
	public List<Site> getAllSites(Long campgroundId) {
		List<Site> allSites = new ArrayList<>();
		String sqlForAllSites = "SELECT * FROM site WHERE campground_id =?";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlForAllSites, campgroundId);
		while (result.next()) {
			Site site = new Site();
			site.setSiteId(result.getLong("site_id"));
			site.setCampgroundId(result.getLong("campground_id"));
			site.setSiteNumber(result.getInt("site_number"));
			site.setMaxOccupancy(result.getInt("max_occupancy"));
			site.setAccessible(result.getBoolean("accessible"));
			site.setMaxRvLength(result.getInt("max_rv_length"));
			site.setUtilities(result.getBoolean("utilities"));
			allSites.add(site);
		}
		return allSites;
	}

	@Override 
	public Site getSiteById(Long siteId) {
		String sqlRequestForSite = "SELECT * FROM site WHERE site_id = ?";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlRequestForSite, siteId);
		Site campsite = new Site();
		while (result.next()) {
			campsite.setSiteId(result.getLong("site_id"));
			campsite.setCampgroundId(result.getLong("campground_id"));
			campsite.setSiteNumber(result.getInt("site_number"));
			campsite.setMaxOccupancy(result.getInt("max_occupancy"));
			campsite.setAccessible(result.getBoolean("accessible"));
			campsite.setMaxRvLength(result.getInt("max_rv_length"));
			campsite.setUtilities(result.getBoolean("utilities"));
		}
		return campsite;
	}
}


