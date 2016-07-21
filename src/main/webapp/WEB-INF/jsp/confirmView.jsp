<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<c:import url="/WEB-INF/jsp/header.jsp">
    <c:param name="pageTitle" value="National Parks - Make a Reservation" />
</c:import>

<h2>Thank you, ${reservation.name}, Your reservation for: ${park.name} at campsite: ${site.siteNumber} Successful!</h2>

<p>
	Handicap Accessible:	${site.accessibility}
</p>
<p>
	Utilities Available:	${site.utility}
</p>
<p>
	Maximum Occupancy:		${site.maxOccupancy}
</p>


















<c:import url	="/WEB-INF/jsp/footer.jsp" />