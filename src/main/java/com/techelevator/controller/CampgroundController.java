package com.techelevator.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.techelevator.campground.CampgroundDAO;
import com.techelevator.campground.ParkDAO;
import com.techelevator.campground.ReservationDAO;
import com.techelevator.model.Campground;
import com.techelevator.model.Park;
import com.techelevator.model.Reservation;
import com.techelevator.model.Site;

@Controller
@SessionAttributes("reservation")
@Transactional
public class CampgroundController {

	private ParkDAO parkDAO;
	private CampgroundDAO campgroundDAO;
	private ReservationDAO reservationDAO;
	
	@Autowired //when constructor is autowired Spring searches for a class with @Component at the top 
	public CampgroundController(ParkDAO parkDAO, CampgroundDAO campgroundDAO, ReservationDAO reservationDAO) {
		this.parkDAO = parkDAO;
		this.campgroundDAO= campgroundDAO;
		this.reservationDAO = reservationDAO;
	}
	
	@RequestMapping({"/", "/nationalParkList"})
	public String displayParkList(ModelMap model) {
		model.put("parks", parkDAO.getAllParks());
		return "nationalParkList";
	}
	
	@RequestMapping({"/campgroundList"})
	public String displayCampgroundList(ModelMap model, @RequestParam Long parkId) {
		model.put("park", parkDAO.readParkById(parkId));
		model.put("campgrounds", campgroundDAO.getAllCampgrounds(parkId));
		return "campgroundList";
	}
	
	@RequestMapping({"/reservationPage"})
	public String displayReservationPage(ModelMap model, @RequestParam Long campgroundId) {
		model.put("campground", campgroundDAO.getCampgroundById(campgroundId));
		return "reservationPage";
	}
	
	@RequestMapping(path={"/checkAvailability"}, method=RequestMethod.GET)
	public String checkCampsiteAvailability(@RequestParam("fromDate")
											@DateTimeFormat(pattern= "yyyy-MM-dd") LocalDate fromDate,
											@RequestParam("toDate")
											@DateTimeFormat(pattern= "yyyy-MM-dd") LocalDate toDate,
											@RequestParam Long parkId, @RequestParam Long campgroundId,
											ModelMap model) {
		
		Reservation reservation = getReservation(model);
		reservation.setFromDate(fromDate);
		reservation.setToDate(toDate);
		Date checkInDate = java.sql.Date.valueOf(fromDate);
		Date checkOutDate = java.sql.Date.valueOf(toDate);
		
		model.put("campground", campgroundDAO.getCampgroundById(campgroundId));
		model.put("park", parkDAO.readParkById(parkId));
		model.put("availableSites", reservationDAO.getAvailableSites(campgroundId, checkInDate, checkOutDate));
		
		return "checkAvailability";
	}
	
	@RequestMapping(path={"/reserveView"}, method=RequestMethod.GET)
	public String getReserveView(@RequestParam Long siteId, 
								 @RequestParam Long campgroundId, ModelMap model){
		Reservation reservation = getReservation(model);
		reservation.setSiteId(siteId);
		
		return "reserveView";
	}
	@RequestMapping(path={"/confirmView"}, method=RequestMethod.POST)			
	public String processReservationView(@RequestParam String fName,
								 @RequestParam String lName, ModelMap model) {
		Reservation reservation = getReservation(model);
		reservation.setName(fName+ " " + lName);
		Date checkInDate = java.sql.Date.valueOf(reservation.getFromDate());
		Date checkOutDate = java.sql.Date.valueOf(reservation.getToDate());
		reservationDAO.saveReservation(reservation.getSiteId(), reservation.getName(), checkInDate, checkOutDate);
		model.put("site", campgroundDAO.getSiteById(reservation.getSiteId()) );
		return "redirect:/confirmView"; 
	}
	@RequestMapping(path={"/confirmView"}, method=RequestMethod.GET)
	public String getConfirmView(ModelMap model){
		Reservation reservation = getReservation(model);
		Site campsite = campgroundDAO.getSiteById(reservation.getSiteId());
		Campground campground = campgroundDAO.getCampgroundById(campsite.getCampgroundId());
		Park park = parkDAO.readParkById(campground.getParkId());
		model.put("site", campsite);
		model.put("park", park);
		model.put("campground", campground);
		return "confirmView";
		
	}
	
	private Reservation getReservation(ModelMap model) {
		Reservation reservation = (Reservation)model.get("reservation");
		if(reservation == null) {
			reservation = new Reservation();
			model.put("reservation", reservation);
		}
		return reservation;
		

	}
	
	
	
}
