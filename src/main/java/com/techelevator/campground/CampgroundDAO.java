package com.techelevator.campground;

import java.util.List;

import com.techelevator.model.Campground;
import com.techelevator.model.Site;

public interface CampgroundDAO {
	public List<Campground> getAllCampgrounds(Long parkId);
	public Campground getCampgroundById(Long campgroundId);
	public Site getSiteById(Long siteId);
	public List<Site> getAllSites(Long campgroundId);
	
}
