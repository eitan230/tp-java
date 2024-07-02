<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Actualizar producto</title>
</head>
<body>
<h1>Editar</h1>
<form action="productos" method="post">
	<input type="hidden" value="update" name="accion">
	<p> 
		ID: <input value="${producto.codArt }" name="codArt"/>
	</p>
		<p> 
		Nombre: <input value="${producto.nombre }" name="nombre"/>
	</p>
		<p> 
		Edad: <input value="${producto.stock }" name="stock"/>
	</p>
		<p> 
		Sueldo: <input value="${producto.precio }" name="precio"/>
	</p>
	<input type="submit" value="Editar">

</form>
</body>
</html>