<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.vv.domain.TdoVV" %>

<html>
<head>
    <title>Home Page</title>
</head>
<body>
<h1>Welcome to the WebApp!</h1>

<!-- Dummy data setup -->
<%
    List<TdoVV> pageList = new ArrayList<>();
    pageList.add(new TdoVV("ABC123", "Model X", 10, "Units"));
    pageList.add(new TdoVV("DEF456", "Model Y", 20, "Pieces"));
    pageList.add(new TdoVV("GHI789", "Model Z", 30, "Kilograms"));

    // Set pageList in session
    session.setAttribute("pageList", pageList);
%>

<form action="export" method="POST">
    <button type="submit">Export to Excel</button>
</form>
</body>
</html>
