<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
</head>
<body>
   Resource not found
   <hr/>
   ${exception.message}
   <hr/>
   <c:forEach items="${exception.stackTrace}" var="trace">
       <c:out value="${trace}" />
   </c:forEach>
</body>
</html>