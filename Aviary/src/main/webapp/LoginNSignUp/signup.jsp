<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sign Up</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/LoginNSignUp/style.css">
</head>
<body>
    <img src="res/signup-bg.png" class ="background">
    <!-- <img src="res/logo.png" class ="logo"> -->
    <div class="top-bar"></div>
    <section style="display: flex; flex-direction:column; justify-content: center; align-items: flex-end; min-width: 100vw; min-height: 90vh;">
        <div class="authentication-box">
        <h3>Sign up</h3>
        <form action="signup" method="post" style="display: flex; flex-direction:column; justify-content: center; align-items: center;">
            <section class="input-section"><label class="input-label">Email : </label><input id="email" name="email"></section>
            <section class="input-section"><label class="input-label">Password : </label><input id="password" name="password"></section>
            <button class="mid-button btn-with-anim" >Sign up</button>
        </form>
        <p>already have an account?<a href="login.html">Log in</a></p>
        <button class="long-button btn-with-anim"><img src="res/google_logo.png" class="logo-icon">Sign in with Google</button>
        <button class="long-button btn-with-anim"><img src="res/fb_logo.png" class="logo-icon">Sign in with Facebook</button>
    </div>
    </section>
</body>