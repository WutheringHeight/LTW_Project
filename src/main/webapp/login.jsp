<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Đăng nhập</title>
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            background-color: #f5f5f5;
            margin: 0;
            padding: 0;
        }
        .signup-container {
            max-width: 400px;
            margin: 80px auto;
            background-color: #fff;
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            text-align: center;
        }
        .signup-container h1 {
            font-size: 28px;
            margin-bottom: 10px;
        }
        .signup-container p {
            font-size: 14px;
            color: #666;
            margin-bottom: 30px;
        }
        .signup-container input[type="email"],
        .signup-container input[type="password"] {
            width: 100%;
            padding: 12px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 6px;
            font-size: 14px;
        }
        .signup-container button {
            width: 100%;
            padding: 12px;
            background-color: #5433eb;
            color: white;
            border: none;
            border-radius: 6px;
            font-size: 16px;
            cursor: pointer;
        }
        .signup-container button:hover {
            background-color: #180a5b;
        }
        .signup-container .link-login {
            margin-top: 20px;
            font-size: 14px;
        }
        .signup-container .link-login a {
            color: #5433eb;
            text-decoration: none;
        }
    </style>
</head>
<body>
<div class="signup-container">
    <h1>Đăng nhập</h1>
    <p>Đăng nhập để bắt đầu</p>

    <form method="post" action="signup">
        <input type="email" name="email" placeholder="Email" required />
        <input type="password" name="password" placeholder="Mật khẩu" required />
        <button type="submit">Đăng Nhập</button>
    </form>

    <div class="link-login">
        Chưa có tài khoản? <a href="signin.jsp">Đăng kí</a>
    </div>
</div>
</body>
</html>
