package com.techelevator.campground;

import static org.junit.Assert.*;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import com.techelevator.model.Campground;
import com.techelevator.model.Site;

public class JDBCReservationDAOIntegrationTest {
	
	private static SingleConnectionDataSource dataSource;
	private JDBCcampgroundDAO campgroundDao;
	private JDBCReservationDAO reservationDao;
	private static final String CAMPGROUND_NAME = "Smallville";
	private static final String SITE_NAME = "SUPERMAN";
	private Campground testCampground = new Campground();
	private Site testSite;
	private Date fromDate = java.sql.Date.valueOf("2016-09-18");
	private Date toDate = java.sql.Date.valueOf("2016-09-27");
	
	
	@BeforeClass
	public static void setupDataSource() {
		dataSource = new SingleConnectionDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/campground");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");
		dataSource.setAutoCommit(false);
	}
	
	@AfterClass
	public static void closeDataSource() throws SQLException {
		dataSource.destroy();
	}
	
	@Before
	public void setup() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sqlInsertCampground = "INSERT INTO campground (campground_id, park_id, name, open_from_mm, open_to_mm, daily_fee) " + 
									 "VALUES (500, 3, ?, '01', '12', 35.00)";
		jdbcTemplate.update(sqlInsertCampground, CAMPGROUND_NAME);
		String sqlInsertSite = "INSERT INTO site (site_id, campground_id, site_number, max_occupancy, accessible, max_rv_length, utilities) " + 
				 					 "VALUES (1000, 500, 1, 8, TRUE, 10, TRUE)";
		jdbcTemplate.update(sqlInsertSite);
		String sqlInsertSite2 = "INSERT INTO site (site_id, campground_id, site_number, max_occupancy, accessible, max_rv_length, utilities) " + 
				 			    "VALUES (1001, 500, 2, 6, FALSE, 12, TRUE)";
		jdbcTemplate.update(sqlInsertSite2);
		testCampground.setCampgroundId(500L);
		testCampground.setParkId(1L);
		testCampground.setName(CAMPGROUND_NAME);
		testCampground.setOpenFromMm("01");
		testCampground.setOpenToMm("12");
		testCampground.setDailyFee(35.00);
		campgroundDao = new JDBCcampgroundDAO(dataSource);
		reservationDao = new JDBCReservationDAO(dataSource);
	}
	
	@After
	public void rollback() throws SQLException {
		dataSource.getConnection().rollback();
	}

	@Test
	public void add_reservation_and_return_availabile_reservations() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sqlInsertReservation = "INSERT INTO reservation (site_id, name, from_date, to_date) " + 
									 "VALUES (1000, 'Anthony Valentino', '2016-09-19', '2016-09-25')";
		jdbcTemplate.update(sqlInsertReservation);
		
		List<Site> availableSites = reservationDao.getAvailableSites(testCampground.getCampgroundId(), fromDate, toDate);
		assertNotNull(availableSites);
		assertEquals(1, availableSites.size());
	}
	
	@Test
	public void return_availabile_reservations_and_match_size() {
		List<Site> availableSites = reservationDao.getAvailableSites(testCampground.getCampgroundId(), fromDate, toDate);
		assertNotNull(availableSites);
		assertEquals(2, availableSites.size());
	}
	
	

}
