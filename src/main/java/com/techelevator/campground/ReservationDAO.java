package com.techelevator.campground;

import java.sql.Date;
import java.util.List;

import com.techelevator.model.Reservation;
import com.techelevator.model.Site;

public interface ReservationDAO {

	List<Site> getAvailableSites(Long campgroundId, Date fromDate, Date toDate);
	public void saveReservation(Long siteId, String name, Date fromDate, Date toDate);
}
