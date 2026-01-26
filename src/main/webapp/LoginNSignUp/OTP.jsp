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
        <div class="OTP-box">
        <p>A <b>OTP</b> verfication code has been sent to your email</p>
        <form action="${otpCallBack}" style="padding: 4px; margin-bottom:0px;" id="form" method="post">
            <div class="otp-inputs">
                <input name="otp" id="otp" type="numberic" maxlength="6" value="XXXXXX" class="gray-text" style="min-width:20px;">
            </div>
            <span style="color:red; padding:0px;"><p id="error-msg">  <c:out value="${error}"/></p></span>
        </form>
        <button style="display:block; margin-right:0px; margin-left:auto; margin-bottom:0px; " class="mid-button btn-with-anim" id="form-btn">Confirm</button>
        </div>
    </section>
    
</body>

<script>
    (function(){
        const otp = document.getElementById("otp")
        const error = document.getElementById("error-msg")
        const form = document.getElementById("form")
        const btn = document.getElementById("form-btn")

        otp.addEventListener("focus",() => {
            if(otp.value == "XXXXXX"){
                otp.value = " "
                otp.classList.remove("gray-text")
            }
        })
        otp.addEventListener("blur",() => {
            error.innerHTML = " "
            if(otp.value.trim() == ""){
                otp.value = "XXXXXX"
                otp.classList.add("gray-text")
                error.innerHTML = " "
                return
            }
        })
        btn.addEventListener("click", () => {
            if(otp.value.trim().length != 6){
                error.innerHTML = "otp must be 6 digits long"
                return
            }
            if(/\D/.test(otp.value.trim())){
                error.innerHTML = "otp can only contain numbers"
                return
            }
            form.submit()
        })
    })();
</script>