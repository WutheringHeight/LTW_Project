<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <img src="res/signup-bg.png" class ="background">
    <div class="top-bar"></div>
    <section style="display: flex; flex-direction:column; justify-content: center; align-items: center; min-width: 100vw; min-height: 80vh;">
        <div class="white-box">
            <h3>Recover Password</h3><br>
            <form action="password_recovery" method="post">
                <section class="input-section"><p class="input-label">Email :</p><input id="email" name="email"></section><br><br>
                
            </form>
            <button class="mid-button btn-with-anim" >Send verification code</button>
        </div>
    </section>  
</body>

<script>
    (function(){
        const email = document.getElementById("email")


    })();
</script>