<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/LoginNSignUp/style.css">
</head>
<body>
    <img src="res/signup-bg.png" class ="background">
    <div class="top-bar"></div>
    <section style="display: flex; flex-direction:column; justify-content: center; align-items: center; min-width: 100vw; min-height: 80vh;">
        <div class="white-box">
            <h3>Recover Password</h3><br>
            <form action="password_recovery" method="post">
                <section class="input-section"><p class="input-label">Email :</p><input id="email" name="email"></section><br><br>
                <button disabled class="mid-button disabled-button" id="form-button">Send verification code</button>
            </form>
           
        </div>
    </section>  
</body>

<script>
    (function(){
        const email = document.getElementById("email")
        const btn = document.getElementById("form-button")

        email.addEventListener("blur",() => {
            console.log("event fired")
            if(email.value.trim() != ""){
                btn.removeAttribute("disabled")
                btn.classList.remove("disabled-button")
                btn.classList.add("btn-with-anim")
            }else{
                btn.setAttribute("disabled","")
                btn.classList.add("disabled-button")
                btn.classList.remove("btn-w-anim")
            }
        })

    })();
</script>