<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Productos</title>
</head>
<body>

	<a href="productos?accion=create">Agregar producto</a>

	<table border="1">
		<thead>
		   <tr>
		   <th>CodArt</th>
		   <th>Nombre</th>
		   <th>Stock</th>
		   <th>Precio</th>
		   <th></th>
		   <th></th>
		   </tr>
		</thead>
		<tbody>
			<c:forEach var="producto" items="${productos}">
				<tr>
					<td> <c:out value="${producto.codArt}"></c:out> </td>
					<td> <c:out value="${producto.nombre}"></c:out> </td>
					<td> <c:out value="${producto.stock}"></c:out> </td>
					<td> <c:out value="${producto.precio}"></c:out> </td>
					<td><a href="productos?accion=show&codArt=${producto.codArt}">ver</a></td>
					<td><a href="productos?accion=edit&codArt=${producto.codArt}">editar</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</body>
</html>