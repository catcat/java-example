<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<t:global>
    <jsp:attribute name="title">
        Edit Device
    </jsp:attribute>

    <jsp:body>
        <h2>Edit Device</h2>
        <form:form method="post" action="/my/device_edit_post.htm">
            <table>
                <tr>
                    <td><form:label path="id">Id</form:label></td>
                    <td>
                        ${row.id}
                        <form:hidden path="id"/>
                    </td>
                </tr>
                <tr>
                    <td><form:label path="name">Name</form:label></td>
                    <td><form:input path="name" /></td>
                </tr>
                <tr>
                    <td><form:label path="score">Score</form:label></td>
                    <td><form:input path="score" /></td>
                </tr>
                <tr>
                    <td>Customer</td>
                    <td>

                        <form:select path="customer">
                            <form:option value="NONE" label="Select One" />
                            <form:options items="${customers}" itemLabel="name" itemValue="id" />
                        </form:select>
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