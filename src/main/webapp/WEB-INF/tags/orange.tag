<%@tag description="Orange tag" dynamic-attributes="myItems" pageEncoding="UTF-8"%>
<%@attribute name="titleFragment" fragment="true" %>
<%@attribute name="title"  %>
<!--%@variable %-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<table>

    <%
        out.println("Hello 1999");
    %>

    <ul>
        <c:forEach items="${myItems}" var="myItem">
            <li>${myItem} ${myItem.value}</li>
        </c:forEach>
    </ul>
    <tr>
        <td>Title</td>
        <td>TitleFragment</td>
        <td>Body</td>

        <td>Scriplet</td>
    </tr>
    <tr>
        <td>${title}</td>
        <td><jsp:invoke fragment="titleFragment"/></td>
        <td><jsp:doBody/></td>

        <td><%= title %></td>
    </tr>

</table>
