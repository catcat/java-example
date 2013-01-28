<%@tag description="Global layout" pageEncoding="UTF-8"%>
<%@attribute name="title" fragment="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN"
"http://www.w3.org/TR/html4/strict.dtd">
<HTML>
<HEAD>
    <TITLE><jsp:invoke fragment="title"/></TITLE>
    <link rel="stylesheet" href="/my/static/css/reset.css" type="text/css"/>
    <link rel="stylesheet" href="/my/static/css/style.css" type="text/css"/>
</HEAD>
<BODY>

<div id="global_container">
    <div id="header">
        <ul>
            <li><a href="devices.htm">devices</a></li>
            <li><a href="ya.htm">webpage</a></li>
        </ul>
    </div>

    <div id="body_container">
        <jsp:doBody/>
    </div">
</div>

</BODY>
</HTML>