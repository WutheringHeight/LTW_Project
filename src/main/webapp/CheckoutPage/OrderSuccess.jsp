<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Đặt hàng thành công</title>
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            background-color: #f8f8f8;
            text-align: center;
            padding-top: 80px;
        }
        .success-box {
            background: #fff;
            width: 500px;
            margin: auto;
            padding: 40px;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.05);
        }
        .success-box h2 {
            color: #4CAF50;
            margin-bottom: 15px;
        }
        .success-box p {
            color: #555;
            font-size: 15px;
            line-height: 1.6;
        }
        .success-box a {
            display: inline-block;
            margin-top: 25px;
            padding: 12px 30px;
            background: #ff9800;
            color: white;
            text-decoration: none;
            border-radius: 4px;
            font-weight: bold;
        }
        .success-box a:hover {
            background: #e68a00;
        }
    </style>
</head>
<body>

<div class="success-box">
    <h2>🎉 Đặt hàng thành công!</h2>

    <p>
        Cảm ơn bạn đã tin tưởng và đặt hàng tại cửa hàng của chúng tôi.<br>
        Đơn hàng của bạn đã được ghi nhận thành công.
    </p>

    <p>
        📞 <b>Chúng tôi sẽ sớm liên hệ với bạn</b> qua số điện thoại đã cung cấp<br>
        để xác nhận đơn hàng và hỗ trợ giao hàng trong thời gian sớm nhất.
    </p>

    <p>
        Nếu bạn cần hỗ trợ thêm, vui lòng liên hệ với chúng tôi bất cứ lúc nào.
    </p>

    <a href="${pageContext.request.contextPath}/home">Tiếp tục mua sắm</a>
</div>

</body>
</html>
