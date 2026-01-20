<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Giỏ Hàng Của Tôi</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/headerFooterStyle.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/cartStyle.css"/>
    <style>
    .modal {
        display: none;
        position: fixed;
        z-index: 999;
        left: 0; top: 0;
        width: 100%; height: 100%;
        background: rgba(0,0,0,0.5);
    }

    .modal-content {
        background: #fff;
        width: 450px;
        margin: 10% auto;
        padding: 25px;
        border-radius: 6px;
        position: relative;
    }

    .close {
        position: absolute;
        right: 15px;
        top: 10px;
        font-size: 22px;
        cursor: pointer;
    }

    .modal-content input,
    .modal-content textarea {
        width: 100%;
        padding: 8px;
        margin-bottom: 10px;
    }
    .error {
        color: #e53935;
        font-size: 13px;
        margin-top: -6px;
        margin-bottom: 10px;
    }

    /* ===== MODAL CONFIRM ===== */
    .modalc {
        display: none;
        position: fixed;
        inset: 0;
        z-index: 1000;
        background: rgba(0, 0, 0, 0.45);
    }

    .modalc-content {
        background: #fff;
        width: 380px;
        margin: 15% auto;
        padding: 25px;
        border-radius: 6px;
        text-align: center;
        box-shadow: 0 8px 25px rgba(0,0,0,0.15);
        animation: popup 0.25s ease;
    }

    .modalc-content h3 {
        margin-bottom: 20px;
        font-size: 18px;
        color: #333;
    }

    .modalc-content button {
        min-width: 90px;
        padding: 8px 16px;
        margin: 0 6px;
        border-radius: 4px;
        border: none;
        font-size: 14px;
        font-weight: 600;
        cursor: pointer;
        transition: 0.2s;
    }

    .modalc-content button:first-of-type {
        background-color: #ff9800;
        color: #fff;
    }

    .modalc-content button:first-of-type:hover {
        background-color: #e68a00;
    }

    .modalc-content button:last-of-type {
        background-color: #eee;
        color: #333;
    }

    .modalc-content button:last-of-type:hover {
        background-color: #ddd;
    }


    </style>
</head>
<body>

<%@ include file="/templates/header.jsp" %>
<%@ include file="/CheckoutPage/checkout.jsp"%>

<div class="cart-container">
    <div class="cart-title-area">
        <h2>Giỏ Hàng Của Tôi</h2>
        <a href="home" class="link-more">Xem & lựa thêm</a>
    </div>

    <div class="cart-header">
        <div class="col-product">Sản phẩm</div>
        <div class="col-qty">Số lượng</div>
        <div class="col-total">Tổng</div>
    </div>

    <c:forEach var="item" items="${cartItems}">
        <div class="cart-item">
            <div class="product-info">
                <img src="${item.image}" class="product-img">
                <div class="product-detail">
                    <h6>${item.name}</h6>
                    <p>Loại tranh: ${item.type}</p>
                </div>
            </div>

            <div class="qty-control">
                <button class="qty-btn"
                        onclick="updateQty(${item.id}, -1)"
                        <c:if test="${item.quantity <= 1}">disabled</c:if>>
                    -
                </button>


                <input type="text" id="qty-${item.id}" class="qty-input" value="${item.quantity}" readonly>

                <button class="qty-btn"
                        id="btn-plus-${item.id}"
                        onclick="updateQty(${item.id}, 1)"
                        <c:if test="${item.quantity >= item.stock}">disabled</c:if>>
                    +
                </button>


                <a href="cart?action=remove&id=${item.id}" class="btn-remove">
                    <i class="fa-regular fa-trash-can"></i>
                </a>
            </div>

            <c:if test="${item.quantity >= item.stock}">
                <div id="stock-msg-${item.id}" class="error">
                    Số lượng trong kho đã đạt giới hạn (${item.stock})
                </div>
            </c:if>

            <div class="item-total" id="total-${item.id}">
                <fmt:formatNumber value="${item.total}" pattern="#,###"/>₫
            </div>
        </div>
    </c:forEach>

    <div class="cart-summary">
        <div class="total-label">
            Tổng đơn hàng:
            <span id="cartTotal">
             <fmt:formatNumber value="${totalCartPrice}" pattern="#,###"/>₫
            </span>
        </div>
        <div class="free-ship-info">
            <i class="fa-solid fa-truck-fast"></i>
            MIỄN PHÍ SHIP cho đơn hàng từ 800K. Hỗ trợ lắp đặt tại TPHCM và một số tỉnh lân cận.
        </div>
        <button class="btn-order" onclick="openCheckout()">Thanh toán</button>
    </div>
</div>

<%@ include file="/templates/footer.jsp" %>

<script>
    function updateQty(id, delta) {
        fetch("cart?action=update&id=" + id + "&delta=" + delta)
            .then(response => response.json())
            .then(data => {
                const qtyInput = document.getElementById("qty-" + id);
                qtyInput.value = data.quantity;

                const parent = qtyInput.parentElement;
                const btnMinus = parent.querySelector("button:first-child");
                const btnPlus = document.getElementById("btn-plus-" + id);

                btnMinus.disabled = (data.quantity <= 1);

                if (data.quantity >= data.maxStock) {
                    btnPlus.disabled = true;
                } else {
                    btnPlus.disabled = false;
                }

                document.getElementById("total-" + id).innerText =
                    formatMoney(data.itemTotal);

                document.getElementById("cartTotal").innerText =
                    formatMoney(data.cartTotal);

                document.getElementById("checkoutTotalText").innerText =
                    data.cartTotal.toLocaleString() + "₫";

                document.getElementById("checkoutTotalInput").value =
                    data.cartTotal;
            })
            .catch(err => console.error(err));
    }

    function formatMoney(number) {
        return new Intl.NumberFormat('vi-VN').format(number) + "₫";
    }

    function openCheckout() {
        document.getElementById("checkoutModal").style.display = "block";
    }

    function closeCheckout() {
        document.getElementById("checkoutModal").style.display = "none";
    }

    window.onload = function () {
        const params = new URLSearchParams(window.location.search);
        if (params.get("autoCheckout") === "true") { openCheckout(); }
        <c:if test="${autoCheckout}"> openCheckout(); </c:if>
    }
    window.onclick = function(event) {
        const modal = document.getElementById("checkoutModal");
        if (event.target === modal) {
            modal.style.display = "none";
        }
    }

        function showConfirm() {
        document.getElementById("confirmModal").style.display = "block";
    }
        function closeConfirm() {
        document.getElementById("confirmModal").style.display = "none";
    }
        function submitOrder() {
        document.querySelector("form[action$='checkout']").submit();
    }

</script>

</body>
</html>