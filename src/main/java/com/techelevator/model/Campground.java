package com.techelevator.model;

import java.util.HashMap;
import java.util.Map;

public class Campground {
	
	private Long campgroundId;
	private Long parkId;
	private String name;
	private String openFromMm;
	private String openToMm;
	private Double dailyFee;
	private Integer numberOfCampsites;


	private static Map<String, String> months = new HashMap<>();
	
	static {
		months.put("01", "January");
		months.put("02", "February");
		months.put("03", "March");
		months.put("04", "April");
		months.put("05", "May");
		months.put("06", "June");
		months.put("07", "July");
		months.put("08", "August");
		months.put("09", "September");
		months.put("10", "October");
		months.put("11", "November");
		months.put("12", "December");
	}
	
	public Integer getNumberOfCampsites() {
		return numberOfCampsites;
	}
	public void setNumberOfCampsites(Integer numberOfCampsites) {
		this.numberOfCampsites = numberOfCampsites;
	}
	
	public Long getCampgroundId() {
		return campgroundId;
	}
	public void setCampgroundId(Long campgroundId) {
		this.campgroundId = campgroundId;
	}
	public Long getParkId() {
		return parkId;
	}
	public void setParkId(Long parkId) {
		this.parkId = parkId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOpenFromMm() {
		return months.get(openFromMm);
	}
	public void setOpenFromMm(String openFromMm) {
		this.openFromMm = openFromMm;
	}
	public String getOpenToMm() {
		return months.get(openToMm);
	}
	public void setOpenToMm(String openToMm) {
		this.openToMm = openToMm;
	}
	public Double getDailyFee() {
		return dailyFee;
	}
	
	public void setDailyFee(Double dailyFee) {
		this.dailyFee = dailyFee;
	}
	
	public String getDailyFeeCost() {
		return String.format( "%.2f", dailyFee );
	}

}
