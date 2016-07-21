<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<c:import url="/WEB-INF/jsp/header.jsp">
    <c:param name="pageTitle" value="National Parks - Make a Reservation" />
</c:import>



<form action="confirmView" method="POST">
	<p>
		First name:<input type="text" name="fName">
	</p>
	<p>
		Last name:<input type="text" name="lName">
	</p>
	<input type="submit" value="Submit" style="margin-left: 150px;"/>


</form>


























<c:import url	="/WEB-INF/jsp/footer.jsp" />

