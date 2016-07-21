<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<c:import url="/WEB-INF/jsp/header.jsp">
    <c:param name="pageTitle" value="National Parks - Campground List" />
</c:import>
	
	<h2 id="parkTitle">${park.name} - ${park.location}</h2>
	<img src="img/${park.name}.jpg" id="parkListImage"/>
	<h3>Available Sites List for Campground ${campground.name}</h3>
		
		
		
	
	<div style="height:300px;width:800px;border:1px solid; #ccc; font: 16px/26px Georgia, Garamond, Serif;overflow:auto; background-color:#317115; color:#aadc95">
		<table id="campgroundInfo">
			<tr>
				<th>Site #</th>
				<th>Max Occupancy</th>
				<th>Max RV Length</th>
				<th>Handicap-Accessible</th>
				<th>Utilities</th>
			</tr>
			<c:forEach items="${availableSites}" var="site" >
				<c:url var="reserveHref" value="/reserveView">
					<c:param name="campgroundId">${campground.campgroundId}</c:param>
					<c:param name="siteId">${site.siteId}</c:param>
				</c:url>
		
				<tr>
					<td>${site.siteNumber}</td>
					<td>${site.maxOccupancy}</td>
					<td>${site.maxRvLength}</td>
					<td>${site.accessibility}</td>
					<td>${site.utility}</td>
					<td><a href="${reserveHref}" >   <button>Reserve Me Now!</button>   </a></td>
				</tr>
			</c:forEach>
		</table>
	</div>	



<c:import url	="/WEB-INF/jsp/footer.jsp" />