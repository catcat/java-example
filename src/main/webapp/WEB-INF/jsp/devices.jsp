<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<t:global>
    <jsp:attribute name="title">
        Devices
    </jsp:attribute>

    <jsp:body>
        <h1>Simple forEach</h1>
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

        <h1>displaytag.sf.net</h1>
        <display:table name="rows">
            <display:column property="id" sortName="id" title="id" sortable="true" style="width:80px" />
            <display:column property="name" sortName="name" title="name" sortable="true" style="width:300px "/>
            <display:column property="score" sortName="score" title="score" sortable="true" sortProperty="score" style="width:200px"/>
            <display:column property="customer.name" sortName="customer.name" title="Customer" sortable="true" sortProperty="customer.name" style="width:200px" />
        </display:table>

        <hr/>

        <t:orange
                hello0="word0"
                hello1="word1"
                hello2="word2"
                hello3="word3"
                hello4="word4"
                >
                <jsp:attribute name="title">
                    My<b>Title</b>
                </jsp:attribute>
                <jsp:attribute name="titleFragment">
                    My<b>Title</b>
                </jsp:attribute>
                <jsp:body>
                    My<b>Body</b>
                </jsp:body>

        </t:orange>

    </jsp:body>
</t:global>