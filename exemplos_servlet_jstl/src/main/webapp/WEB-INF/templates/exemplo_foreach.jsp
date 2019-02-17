<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<fmt:setLocale value="${pageContext.request.locale}"/>
<fmt:setBundle basename="mensagem"/>
<head>
    <title><fmt:message key="titulo_pagina_cachorros"/></title>
</head>
<body>
<ul>
    <c:forEach items="${cachorros}" var="cachorro" varStatus="status">
        <li>
                ${status.count} - ${cachorro}
        </li>
    </c:forEach>
</ul>
<c:url var="horario" value="/executa?tarefa=TarefaHorarioServidor"/>
<a href="${horario}">Horario Servidor</a>
</body>
</html>
