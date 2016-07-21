<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<c:import url="/WEB-INF/jsp/header.jsp">
    <c:param name="pageTitle" value="National Parks - Reservation Page" />
</c:import>

<h2>${campground.name}</h2>
	<div id="dateForm">
		<form action="checkAvailability" method=GET>
			<label for="fromDate">Start Date: </label>
			<input type="date" name="fromDate" id="fromDate" /><br>
			
			<label for="toDate">End Date: </label>
			<input type="date" name="toDate" id="toDate" /><br>
			
			<input type="hidden" name="parkId" value="${param.parkId}" />
			<input type="hidden" name="campgroundId" value="${param.campgroundId}" />
			
			<input type="submit" value="submit" />
		</form>
	</div>
	

<c:import url	="/WEB-INF/jsp/footer.jsp" />



