<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<c:url value="site.css" var="cssURL" />
<link rel="stylesheet" type="text/css" href="${cssURL}">
<title>National Park Campground Reservation Site</title>
</head>

<body>

<c:url value="/" var="homePageURL" />
<p>
<a href="${homePageURL}"> 
    <c:url value="/img/logo.png" var="logoURL" />
    <img src="${logoURL}" id="logo" />
</a>
</p>

<div id="content">
    
    <nav>
    		<ul class="navigation">
    			<li class="links"><a href="${homePageURL}">Home</a></li>
    		</ul>
    </nav>
    
    <h1 class="title"><c:out value="${param.pageTitle }" /></h1>