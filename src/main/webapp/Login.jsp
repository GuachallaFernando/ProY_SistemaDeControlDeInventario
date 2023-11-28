<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Iniciar Sesión</title>
</head>
<body>
    <h2>Iniciar Sesión</h2>
    <form action="LogiServlet" method="post">
        <label for="usuario">Usuario:</label>
        <input type="text" id="usuario" name="usuario" required><br>
        
        <label for="passwrd">Contraseña:</label>
        <input type="password" id="passwrd" name="passwrd" required><br>
        
        <input type="hidden" name="action" value="login">
        
        <button type="submit">Iniciar Sesión</button>
    </form>
</body>
</html>
