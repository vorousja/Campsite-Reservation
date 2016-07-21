package com.techelevator.campground;

import static org.junit.Assert.*;

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

public class JDBCcampgroundDAOIntegrationTest {
	
	private static SingleConnectionDataSource dataSource;
	private JDBCcampgroundDAO dao;
	private static final String CAMPGROUND_NAME = "Smallville";
	private static final String SITE_NAME = "SUPERMAN";
	private Campground testCampground = new Campground();
	private Site testSite;

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
									 "VALUES (500, 1, ?, '01', '12', 35.00)";
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
		dao = new JDBCcampgroundDAO(dataSource);
	}
	
	@After
	public void rollback() throws SQLException {
		dataSource.getConnection().rollback();
	}
	
	@Test
	public void get_campgrounds_by_parkId_and_match_size() {
		List<Campground> campgrounds = dao.getAllCampgrounds(1L);
		assertNotNull(campgrounds);
		assertEquals(4, campgrounds.size());
	}
	
	@Test
	public void get_campground_by_campgroundId_and_match_campground() {
		Campground campground = dao.getCampgroundById(500L);
		assertCampgroundsAreEqual(testCampground, campground);
	}
	
	@Test
	public void get_sites_for_campground_and_match_size() {
		List<Site> sites = dao.getAllSites(500L);
		assertNotNull(sites);
		assertEquals(2, sites.size());
	}
	
	private void assertCampgroundsAreEqual(Campground expected, Campground actual) {
		assertEquals(expected.getCampgroundId(), actual.getCampgroundId());
		assertEquals(expected.getParkId(), actual.getParkId());
		assertEquals(expected.getName(), actual.getName());
		assertEquals(expected.getOpenFromMm(), actual.getOpenFromMm());
		assertEquals(expected.getOpenToMm(), actual.getOpenToMm());
		assertEquals(expected.getDailyFee(), actual.getDailyFee());
		assertEquals(expected.getDailyFeeCost(), actual.getDailyFeeCost());
	}
	
	

}
