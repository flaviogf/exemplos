<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<c:if test="${usuarioLogado != null}">
    <h3>Bem vindo ${usuarioLogado.email}</h3>
</c:if>
<h2>Adicione um empresa</h2>
<form action="/empresa" method="post">
    <input type="text" name="nome">
    <button type="submit">Salvar</button>
</form>

<h2>Login</h2>
<form action="/login" method="post">
    <input type="text" name="email">
    <input type="text" name="senha">
    <button type="submit">Entrar</button>
</form>

<h2>Deslogar</h2>
<form action="/logout" method="post">
    <button type="submit">Sair</button>
</form>
</body>
</html>
