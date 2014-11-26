<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Recommended items</title>
</head>
<body>
<ul>
    <c:forEach items="${items}" var="item">
        <li>${item.itemID}</li>
    </c:forEach>
</ul>
</body>
</html>
