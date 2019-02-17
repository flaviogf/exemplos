<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <title>Horário Servidor</title>
</head>
<body>
<h2>
    ${dataFormatada}
</h2>
<h2>
    <fmt:formatDate value="${dataNaoFormatada.time}" pattern="HH:mm:ss - dd/MM/yyyy"/>
</h2>
</body>
</html>
