<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container">
            <div class="col-lg-6">
                <h1>Agregar Persona</h1>
                <form action="Controlador">
      
                    Primer Nombre: <br>
                    <input class="form-control" type="text" name="txtNom1"><br>
                    Segundo Nombre: <br>
                    <input class="form-control" type="text" name="txtNom2"><br>
                    Primer Apellido: <br>
                    <input class="form-control" type="text" name="txtAp1"><br>
                    Segundo Apellido: <br>
                    <input class="form-control" type="text" name="txtAp2"><br>
                    Login: <br>
                    <input class="form-control" type="text" name="txtLog"><br>
                    Contraseña: <br>
                    <input class="form-control" type="text" name="txtCont"><br>
                    Nit: <br>
                    <input class="form-control" type="text" name="txtNit"><br>
                    Puesto: <br>
                    <input class="form-control" type="text" name="txtPuesto"><br>
                    Rol: <br>
                    <input class="form-control" type="number" name="txtRol"><br>
                    
                    
                    <input class="btn btn-primary" type="submit" name="accion" value="Agregar">
                    <a href="Controlador?accion=listar">Regresar</a>
                </form>
            </div>

        </div>
    </body>
</html>
