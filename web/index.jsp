<!DOCTYPE html>
<html>
<head>
    <title>Login Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-image: url('images/th.jpeg'); /* Ruta de la imagen */
            background-size: 50%; /* Hace que la imagen cubra todo el fondo */
            background-position: center; /* Centra la imagen */
            background-repeat: no-repeat; /* Evita que la imagen se repita */
            height: 100vh; /* Asegura que el fondo cubra toda la altura de la página */
            margin: 0;
            display: flex;
            justify-content: center;
            align-items: center;
        }
        .container {
            width: 300px;
            padding: 20px;
            background-color: rgba(255, 255, 255, 0.8); /* Fondo blanco semi-transparente */
            border-radius: 5px;
            box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
        }
        .form-group {
            margin-bottom: 15px;
        }
        .form-group label {
            display: block;
            margin-bottom: 5px;
        }
        .form-group input {
            width: 100%;
            padding: 8px;
            box-sizing: border-box;
        }
        .form-group button {
            width: 100%;
            padding: 10px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        .form-group button:hover {
            background-color: #45a049;
        }
        .error-message {
            color: red;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Iniciar Sesión</h2>
        <form action="Controlador" method="get"> 
            <input type="hidden" name="menu" value="inicio"> 
            <div class="form-group">
                <label for="username">Cuenta:</label>
                <input type="text" id="login" name="login" required>
            </div>
            <div class="form-group">
                <label for="password">Contraseña:</label>
                <input type="password" id="contrasenia" name="contrasenia" required>
            </div>
            <div class="form-group">
                <button type="submit" name="accion" value="authenticate">Login</button>
            </div>
            <% 
                String errorMessage = (String) request.getAttribute("errorMessage");
                if (errorMessage != null) { 
            %>
                <div class="error-message"><%= errorMessage %></div>
            <% 
                } 
            %>
        </form>
    </div>
</body>
</html>
