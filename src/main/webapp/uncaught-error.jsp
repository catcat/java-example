<%@ page import="java.io.ByteArrayOutputStream" %>
<%@ page import="java.io.PrintStream" %>
uncaught-error.jsp

<%@ page isErrorPage="true" %>
...
<%= exception.getMessage() %>

With the following stack trace:

<%
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    exception.printStackTrace(new PrintStream(baos));
    out.print(baos);
%>