<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<t:global>
    <jsp:attribute name="title">
        <spring:message code="label.edit.device"/>
    </jsp:attribute>

    <jsp:body>
        <form:errors path="*"/>
        <h2><spring:message code="label.edit.device"/></h2>
        <form:form method="post" action="/my/device_edit_post.htm" modelAttribute="device">
            <table>
                <tr>
                    <td><form:label path="id">Id</form:label></td>
                    <td>
                        ${device.id}
                        <form:hidden path="id"/>
                    </td>
                </tr>
                <tr>
                    <td><form:label path="name">Name</form:label></td>
                    <td>
                        <form:input path="name" />
                        <form:errors path="name"/>
                    </td>
                </tr>
                <tr>
                    <td><form:label path="score">Score</form:label></td>
                    <td>
                        <form:input path="score" />
                        <form:errors path="score"/>
                    </td>
                </tr>
                <tr>
                    <td><form:label path="active">Active</form:label></td>
                    <td>
                        <form:checkbox path="active" />
                        <form:errors path="active"/>
                    </td>
                </tr>
                <tr>
                    <td><form:label path="active">Created</form:label></td>
                    <td>
                        <form:input path="created" />
                        <form:errors path="created"/>
                    </td>
                </tr>
                <tr>
                    <td>Customer</td>
                    <td>

                        <form:select path="customer">
                            <form:option value="NONE" label="Select One" />
                            <form:options items="${customers}" itemLabel="name" itemValue="id" />
                        </form:select>
                        <form:errors path="customer"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" value="Submit">
                    </td>
                </tr>
            </table>

            Current tags:
            <ul>
                <c:forEach items="${currentTags}" var="tag">
                    <li>${tag.name}</li>
                </c:forEach>
            </ul>

            All tags:
            <ul>
                <c:forEach items="${allTags}" var="tag">
                    <li>${tag.name}</li>
                </c:forEach>
            </ul>


        </form:form>
    </jsp:body>
</t:global>