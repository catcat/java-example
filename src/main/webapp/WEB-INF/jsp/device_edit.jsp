<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:global>
    <jsp:attribute name="title">
        Edit Device
    </jsp:attribute>

    <jsp:body>
        <h2>Edit Device</h2>
        <form>
            <table>
                <tr>
                    <td>id</td>
                    <td>${row.id}</td>
                </tr>
                <tr>
                    <td>name</td>
                    <td><input type="text"></td>
                </tr>
                <tr>
                    <td>score</td>
                    <td><input type="text"></td>
                </tr>
                <tr>
                    <td>Customer</td>
                    <td>
                        <select>
                            <option>Item</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" value="Submit">
                    </td>
                </tr>
            </table>

        </form>
    </jsp:body>
</t:global>