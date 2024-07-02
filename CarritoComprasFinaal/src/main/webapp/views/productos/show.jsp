<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Producto</h1>
	<p> Nombre: <c:out value="${producto.nombre }"></c:out> </p>
	<p> Stock: <c:out value="${producto.stock }"></c:out> </p>
	<p> Precio: <c:out value="${producto.precio }"></c:out> 
	</p>
	<form action="productos" method="post">
		<input type="hidden" name="codArt" value="${producto.codArt}">
		<input type="hidden" name="accion" value="delete">
		<input type="submit" value="Eliminar producto">
	
	</form>
</body>
</html>