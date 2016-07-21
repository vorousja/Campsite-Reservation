package com.techelevator.campground;

import java.util.List;

import com.techelevator.model.Park;

public interface ParkDAO {
	
	public List<Park> getAllParks();
	public Park readParkById(Long id);

}
