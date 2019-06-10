<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title></title>
  </head>
  <body>


<div id="id01" class="modal">

  <form class="modal-content animate" action="checklogin.php" method="post">
    <div class="imgcontainer">
      <span onclick="document.getElementById('id01').style.display='none'" class="close" title="Close Modal">&times;</span>
      <img src="img/avatar.png" alt="Avatar" class="avatar">
    </div>

    <div class="container">
      <label for="uname"><b>Usuario</b></label>
      <input type="text" placeholder="Ingresar Usuario" name="username" required>

      <label for="psw"><b>Contraseña</b></label>
      <input type="password" placeholder="Ingresa tu Contraseña" name="password" required>

      <button type="submit">Login</button>
      <label>
        <input type="checkbox" checked="checked" name="remember"> Recordarme
      </label>
    </div>

    <div class="container" style="background-color:#f1f1f1">

      <button type="button" onclick="location.href='index.html'">Registrarse</button>
      <span class="psw">Olvidé <a href="#">contraseña</a></span>
    </div>
  </form>
</div>

  </body>
</html>
