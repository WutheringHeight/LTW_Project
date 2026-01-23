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
            <h3>Set new password</h3>
            <form action="password_reset" method="post" id="form">
                <p class="input-label">Password:</p><br>
                <input type="password" name="password1" id="password1">
                <p class="input-label">Password(repeat):<span style="color:red;" id="error-msg"><c:out value="${error}"/></span></p><br>
                <input type="password" name="password1" id="password2">

                <button disabled class="mid-button disabled-button" id="form-button" style="margin-top:20px;">Confirm</button>
            </form>
        </div>
    </section>  
</body>

<script>
    (function(){
        const input1 = document.getElementById("password1")
        const input2 = document.getElementById("password2")
        const error = document.getElementById("error-msg")
        const btn = document.getElementById("form-button")

        input1.addEventListener("blur", () => {
            if(input1.value != input2.value && input2.value != ""){
                error.innerHTML = "the passwords must be the same"
                
            }else{
                error.innerHTML = ""
            }
            if(input1.value == input2.value && input2.value != ""){
                btn.removeAttribute("disabled")
                btn.classList.remove("disabled-button")
                btn.classList.add("btn-with-anim")
            }else{
                btn.setAttribute("disabled","")
                btn.classList.add("disabled-button")
                btn.classList.remove("btn-w-anim")
            }
        })
        input2.addEventListener("blur", () => {
            if(input1.value != input2.value && input2.value != ""){
                error.innerHTML = "the passwords must be the same"
            }else{
                error.innerHTML = ""
            }
            if(input1.value == input2.value && input2.value != ""){
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