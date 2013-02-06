<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<t:global>
    <jsp:attribute name="title">
        Simple Submit form
    </jsp:attribute>

    <jsp:body>
        <h1>Simple Submit form </h1>

        <div style="">Message: ${msg}</div>
        <form method="POST">
            <input type="text" name="msg"/>
            <input type="submit"/>
        </form>

    </jsp:body>
</t:global>