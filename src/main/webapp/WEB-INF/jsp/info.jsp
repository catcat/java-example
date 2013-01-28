<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:global>
    <jsp:attribute name="title">
        Info
    </jsp:attribute>

    <jsp:body>

        <div class="article">
            ${message}
        </div>

        <ul>
            <c:forEach items="${customers}" var="customer">
            <li>${customer.name}

                <c:forEach items="${customer.devices}" var="device">
                <ul>
                    <li>${device.name}</li>
                </ul>
                </c:forEach>

            </li>
            </c:forEach>
        </ul>
    </jsp:body>
</t:global>