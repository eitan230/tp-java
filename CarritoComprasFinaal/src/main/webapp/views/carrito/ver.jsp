<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Carrito de Compras</title>
</head>
<body>
    <h1>Carrito de Compras</h1>
    <table border="1">
        <thead>
            <tr>
                <th>Producto</th>
                <th>Cantidad</th>
                <th>Precio Unitario</th>
                <th>Subtotal</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="item" items="${carrito.items}">
                <tr>
                    <td>${item.producto.nombre}</td>
                    <td>
                        <form action="carrito" method="post">
                            <input type="hidden" name="accion" value="actualizar">
                            <input type="hidden" name="codArt" value="${item.producto.codArt}">
                            <input type="number" name="cantidad" value="${item.cantidad}" min="1">
                            <input type="submit" value="Actualizar">
                        </form>
                    </td>
                    <td>${item.producto.precio}</td>
                    <td>${item.producto.precio * item.cantidad}</td>
                    <td>
                        <form action="carrito" method="post">
                            <input type="hidden" name="accion" value="eliminar">
                            <input type="hidden" name="codArt" value="${item.producto.codArt}">
                            <input type="submit" value="Eliminar">
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <h2>Total: ${carrito.calcularTotal()}</h2>
    <form action="carrito" method="post">
        <input type="hidden" name="accion" value="comprar">
        <input type="submit" value="Comprar">
    </form>
     <form action="carrito" method="get">
        <input type="hidden" name="accion" value="cliente">
        <input type="submit" value="Seguir Comprando">
    </form>
</body>
</html>