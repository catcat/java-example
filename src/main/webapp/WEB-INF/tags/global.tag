<%@tag description="Global layout" pageEncoding="UTF-8"%>
<%@attribute name="title" fragment="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN"
"http://www.w3.org/TR/html4/strict.dtd">
<HTML>
<HEAD>
    <TITLE><jsp:invoke fragment="title"/></TITLE>
    <link rel="stylesheet" href="static/css/reset.css" type="text/css"/>
    <link rel="stylesheet" href="static/css/style.css" type="text/css"/>
</HEAD>
<BODY>

<div id="header">
    Header
</div>

<jsp:doBody/>

</BODY>
</HTML>