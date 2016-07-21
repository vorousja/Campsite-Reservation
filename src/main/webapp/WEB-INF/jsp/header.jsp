<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html style="background-color:#AADC95;">

<head>
<meta charset="UTF-8">
<c:url value="imagehover.css-master/css/imagehover.css" var="cssURL" />
<link rel="stylesheet" type="text/css" href="${cssURL}">
<title>National Park Campground Reservation Site</title>
</head>

<body>

<c:url value="/" var="homePageURL" />

 
    <c:url value="/img/logo.png" var="logoURL" />
    <img src="${logoURL}" id="logo" style=" max-width: 100%;
    										height: auto;
   										    width: auto\9;
   										    background-color: green;"/>



<div id="content">
    
   <nav id="nav-3">
	  <a class="link-3" href="${homePageURL}">Home</a>
   </nav>
	 
    		
 
    
    <h1 style="font-size:50px;"  class="title"><c:out value="${param.pageTitle }" /></h1>