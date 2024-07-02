<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Productos Disponibles</title>
</head>
<body>
    <h1>Productos Disponibles</h1>
    
    <table border="1">
        <thead>
            <tr>
                <th>Código Artículo</th>
                <th>Nombre</th>
                <th>Stock</th>
                <th>Precio Unitario</th>
                <th>Acción</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${productos}" var="producto">
                <tr>
                    <td>${producto.codArt}</td>
                    <td>${producto.nombre}</td>
                    <td>${producto.stock}</td>
                    <td>${producto.precio}</td>
                    <td>
                        <form action="carrito" method="post">
                            <input type="hidden" name="accion" value="agregar">
                            <input type="hidden" name="codArt" value="${producto.codArt}">
                            <input type="number" name="cantidad" value="1" min="1" max="${producto.stock}">
                            <button type="submit">Agregar al Carrito</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    
    <br>
    <a href="carrito?accion=ver">Ver Carrito</a>
</body>
</html>
