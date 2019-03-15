<%--
  Created by IntelliJ IDEA.
  User: hylio
  Date: 19.12.2018
  Time: 21:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<%--<form action="controller" method="post">--%>
<%--<input type="hidden" name="command" value="home">--%>
<c:redirect url="controller">
    <c:param name="command" value="home"/>
</c:redirect>
<%--</form>--%>
</body>
</html>
