<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Empresas</title>
</head>
<body>
<h2>Empresas</h2>
<ul>
    <c:forEach items="${empresas}" var="empresa">
        <li>${empresa}</li>
    </c:forEach>
</ul>
</body>
</html>
