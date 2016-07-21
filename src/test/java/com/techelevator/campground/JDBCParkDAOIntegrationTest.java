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

import com.techelevator.model.Park;

public class JDBCParkDAOIntegrationTest {
	
	private static SingleConnectionDataSource dataSource;
	private JDBCParkDAO dao;

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
//		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		dao = new JDBCParkDAO(dataSource);
	}
	
	@After
	public void rollback() throws SQLException {
		dataSource.getConnection().rollback();
	}
	
	@Test
	public void pull_parks_and_test_size() {
		List<Park> allParks = dao.getAllParks();
		assertNotNull(allParks);
		assertEquals(3, allParks.size());
	}
	
	@Test
	public void get_park_by_id_and_match_park() {
		Park testPark = new Park();
		testPark.setName("Acadia");
		Park actualPark = dao.readParkById(1L);
		assertEquals(testPark.getName(), actualPark.getName());
	}
	
	

}
