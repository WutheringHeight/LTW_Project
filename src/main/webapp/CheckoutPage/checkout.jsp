<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="checkoutModal" class="modal">
    <div class="modal-content">
        <span class="close" onclick="closeCheckout()">&times;</span>
        <h2>Xác nhận đặt hàng</h2>

        <p>
            <b>Tổng tiền:</b>
            <span id="checkoutTotalText">
            <fmt:formatNumber value="${totalCartPrice}" pattern="#,###"/>₫
            </span>
        </p>

        <input type="hidden" name="totalPrice" id="checkoutTotalInput"
               value="${totalCartPrice}">

        <form action="checkout" method="post">

            <label>Họ tên</label>
            <input type="text" name="name"
                   value="${oldName}">
            <c:if test="${errors.name != null}">
                <div class="error">${errors.name}</div>
            </c:if>

            <label>Số điện thoại</label>
            <input type="text" name="phone"
                   value="${oldPhone}">
            <c:if test="${errors.phone != null}">
                <div class="error">${errors.phone}</div>
            </c:if>

            <label>Địa chỉ</label>
            <textarea name="address">${oldAddress}</textarea>
            <c:if test="${errors.address != null}">
                <div class="error">${errors.address}</div>
            </c:if>

            <button type="button" class="btn-order" onclick="showConfirm()">
                Xác nhận đặt hàng
            </button>
        </form>
        <!-- Modal -->
        <div id="confirmModal" class="modalc">
            <div class="modalc-content">
                <h3>Bạn có chắc chắn muốn đặt hàng?</h3>
                <button onclick="submitOrder()">Có</button>
                <button onclick="closeConfirm()">Không</button>
            </div>
        </div>

    </div>
</div>