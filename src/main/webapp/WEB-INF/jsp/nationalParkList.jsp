<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<c:import url="/WEB-INF/jsp/header.jsp">
    <c:param name="pageTitle" value="National Parks" />
</c:import>


<c:url value="imagehover.css-master/css/imagehover.css" var="cssURL" />
<link rel="stylesheet" type="text/css" href="${cssURL}">

<div style="margin-left: 15em;">
<c:forEach items="${parks}" var="park">
		    <a href="<c:url var="parkHref" value="/campgroundList">
					<c:param name="parkId">${park.parkId}</c:param>
				</c:url>"></a>
				 
	<div>
		<figure class="imghvr-zoom-out">
		    <img src="img/${park.name}.jpg" style=" 
		    									   width: 50em; 
		    									   height: 20em;
		    									   max-height: 400px;
		    									   max-width:500px;
		    									   display: block;
		    									   
		    									   ">
		    <figcaption>
		   
		        <h3 class="parkLink"><a href="${parkHref}"><c:out value="${park.name}" /> - <c:out value="${park.location}" /></a></h3>
				<p>Number of visitors: ${park.visitors}</p>
				<p>Park Area: ${park.area} km</p>
				<p>Established Date: ${park.establishDate}</p>
				<p>${park.description}</p>
		   
		    </figcaption>
		</figure>
	</div>	
</c:forEach>
</div>
	

	


<c:import url	="/WEB-INF/jsp/footer.jsp" />