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
            <c:out value="${error}"/>
        <form action="${otpCallBack}" style="padding: 4px;">
            <div class="otp-inputs">
                    <input name="otp1" type="text" maxlength="1">
                    <input name="otp2" type="text" maxlength="1">
                    <input name="otp3" type="text" maxlength="1">
                    <input name="otp4" type="text" maxlength="1">
                    <input name="otp5" type="text" maxlength="1">
                    <input name="otp6" type="text" maxlength="1">
            </div>
                <button style="display:block; margin-right:0px; margin-left:auto;" class="mid-button btn-with-anim">Confirm</button>
        </form>
        </div>
    </section>
    
</body>