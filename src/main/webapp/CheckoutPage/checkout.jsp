<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Đặt hàng</title>
</head>
<body>

<h2>Thông tin đặt hàng</h2>

<form action="checkout" method="post">
    <label>Họ tên:</label><br>
    <input type="text" name="name" required><br><br>

    <label>Số điện thoại:</label><br>
    <input type="text" name="phone" required><br><br>

    <label>Địa chỉ:</label><br>
    <textarea name="address" required></textarea><br><br>

    <button type="submit">Xác nhận đặt hàng</button>
</form>

</body>
</html>
