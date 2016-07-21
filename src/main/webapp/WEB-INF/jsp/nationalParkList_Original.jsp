<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<c:import url="/WEB-INF/jsp/header.jsp">
    <c:param name="pageTitle" value="National Parks" />
</c:import>
	
	<c:forEach items="${parks}" var="park">
	  	<div class="parks"> 
			<img src="img/${park.name}.jpg" id="parkListImage" class="imghvr-zoom-out:hover"/>
			<c:url var="parkHref" value="/campgroundList">
				<c:param name="parkId">${park.parkId}</c:param>
			</c:url>
		</div>		
		<div>
			<h3 class="parkLink"><a href="${parkHref}"><c:out value="${park.name}" /> - <c:out value="${park.location}" /></a></h3>
			<p>Number of visitors: ${park.visitors}</p>
			<p>Park Area: ${park.area} km</p>
			<p>Established Date: ${park.establishDate}</p>
			<p>${park.description}</p>
		</div><br>

	</c:forEach>
	



	


<c:import url	="/WEB-INF/jsp/footer.jsp" />