<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<section class="userdetails-content">
    <h2>Thanh toán & Hóa đơn</h2>

    <div class="setting-section">
        <!-- Địa chỉ giao hàng -->
        <div class="setting-option">
            <p>Địa chỉ giao hàng: 102 That Street, Glutenbergs</p>
            <button class="mini-button btn-with-anim">Thay đổi</button>
        </div>

        <!-- Phương thức thanh toán -->
        <div class="setting-option">
            <p>Phương thức thanh toán: Thẻ tín dụng / Chuyển khoản ngân hàng</p>
            <button class="mini-button btn-with-anim">Thay đổi</button>
        </div>

        <!-- Thông tin ngân hàng -->
        <h3>Ngân hàng</h3>
        <div class="banking-box">
            <img src="image/userdetails/bank.png" class="mega-icon" alt="Biểu tượng ngân hàng">
            <div class="banking-input">
                <label>
                    <p>Mã ngân hàng</p>
                    <input type="text" name="bankCode" placeholder="Nhập mã ngân hàng">
                </label>
                <label>
                    <p>Số thẻ</p>
                    <input type="text" name="cardNumber" placeholder="Nhập số thẻ">
                </label>
                <br>
                <button class="mid-button btn-with-anim">Lưu</button>
            </div>
        </div>
    </div>
</section>

</body>
</html>
