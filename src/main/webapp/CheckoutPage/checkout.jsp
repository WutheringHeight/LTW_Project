
<div id="checkoutModal" class="modal">
    <div class="modal-content">
        <span class="close" onclick="closeCheckout()">&times;</span>

        <h2>Xác nhận đặt hàng</h2>

        <!-- THÔNG TIN ĐƠN -->
        <div class="order-summary">
            <p><b>Tổng số lượng:</b> ${cartItems.size()} tranh</p>
            <p><b>Tổng tiền:</b>
                <fmt:formatNumber value="${totalCartPrice}" pattern="#,###"/>₫
            </p>
        </div>

        <!-- FORM GỬI CHECKOUT -->
        <form action="checkout" method="post">
            <label>Họ tên</label>
            <input type="text" name="name" required>

            <label>Số điện thoại</label>
            <input type="text" name="phone" required>

            <label>Địa chỉ</label>
            <textarea name="address" required></textarea>

            <!-- gửi kèm tổng tiền -->
            <input type="hidden" name="totalPrice" value="${totalCartPrice}">

            <button type="submit" class="btn-order">Xác nhận đặt hàng</button>
        </form>
    </div>
</div>