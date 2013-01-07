<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:global>
    <jsp:attribute name="title">
        Error
    </jsp:attribute>

    <jsp:body>
        <div class="article">
            ${message}
        </div>
    </jsp:body>
</t:global>