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
                        ${command.id}
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

        </form:form>
    </jsp:body>
</t:global>