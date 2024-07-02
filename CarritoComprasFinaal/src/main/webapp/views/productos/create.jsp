<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Alta</h1>
	
	<form action="productos" method="post">
	
		<input type="hidden" value="insert" name="accion">
		
			<p> 
				Nombre: <input value="" name="nombre"/>
			</p>
			
			<p> 
				Stock: <input value="" name="stock"/>
			</p>
			
			<p> 
				Precio: <input value="" name="precio"/>
			</p>
		
		<input type="submit" value="Agregar">
	
	</form>
</body>
</html>