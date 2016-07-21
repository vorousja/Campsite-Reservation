<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<c:import url="/WEB-INF/jsp/header.jsp">
    <c:param name="pageTitle" value="National Parks - Campground List" />
</c:import>
	
	<h2 id="parkTitle">${park.name} - ${park.location}</h2>
	<img src="img/${park.name}.jpg" id="parkListImage"/>
	<h3>Campground List</h3>
	
	<table id="campgroundInfo">
		<tr>
			<th>Campground Name</th>
			<th>Opens</th>
			<th>Closes</th>
			<th>Daily Fee</th>
			<th>Number of Sites</th>
		</tr>
		<c:forEach items="${campgrounds}" var="campground" >
			<c:url var="campgroundHref" value="/reservationPage">
				<c:param name="campgroundId">${campground.campgroundId}</c:param>
				<c:param name="parkId">${param.parkId}</c:param>
			</c:url>
			<tr>
				<td>${campground.name}</td>
				<td>${campground.openFromMm}</td>
				<td>${campground.openToMm}</td>
				<td>$${campground.dailyFeeCost}</td>
				<td>${campground.numberOfCampsites}</td>
				<td id="findAvailability">
					<a href="${campgroundHref}">Find Availability</a>
				</td>
			</tr>
		</c:forEach>
	</table>
	

<c:import url	="/WEB-INF/jsp/footer.jsp" />