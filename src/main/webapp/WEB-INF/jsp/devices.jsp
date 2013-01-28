<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:global>
    <jsp:attribute name="title">
        Devices
    </jsp:attribute>

    <jsp:body>
        <table>
        <c:forEach items="${rows}" var="row">
            <tr>
                <td>
                ${row.id}
                </td>
                <td>
                    <a href="device_edit/${row.id}.htm">
                        ${row.name}
                    </a>
                <td>
                    ${row.score}
                </td>
                <td>
                    ${row.customer.name}
                </td>
            </tr>
        </c:forEach>
        </table>
    </jsp:body>
</t:global>