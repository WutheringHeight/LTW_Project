<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Login</title>
    <link rel="stylesheet" href="style.css" />
  </head>
  <body>
    <img src="res/signup-bg.png" class="background" />
    <img src="res/logo.png" class="logo" />
    <div class="top-bar"></div>
    <section
      style="
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: flex-end;
        min-width: 100vw;
        min-height: 90vh;
      "
    >
      <div class="authentication-box">
        <h3>Login</h3>
        <form action="login" method="post" id="login-form">
          <section class="input-section">
          <p class="input-label">Email :</p>
          <input type="text" name="email" id="email"/>
        </section>
        <section class="input-section">
          <p class="input-label">Password :</p>
          <input type="password" name="password" id="password"/>
        </section>
        </form>
        <span style="color:red;"><p id="error-msg" style="margin:0px; padding:0px;"><c:out value="${error}"/></p></span>
        <button class="mid-button btn-with-anim" id="form-button">Login</button></a>
        <a href="password_recovery" style="margin-top:4px;">forgot your password?</a>
      </div>
    </section>
  </body>
</html>

<script>
  (function(){
    const email = document.getElementById("email")
    const password = document.getElementById("password")
    const btn = document.getElementById("form-button")
    const form = document.getElementById("login-form")
    const error = document.getElementById("error-msg")

    btn.addEventListener("click",() => {
      var emailval = email.value.trim()
      var passval = password.value.trim()
      if(emailval != "" && passval != "" ){
        form.submit()
        console.log("submitted")
      }else{
        if(emailval == ""){
          error.innerHTML = "email can't be blank."
        }else{
          error.innerHTML = "password can't be blank."
        }
      }
    })
  })();
</script>
