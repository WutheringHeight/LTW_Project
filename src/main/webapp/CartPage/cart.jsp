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
</head>
<body>

<%@ include file="/templates/header.jsp" %>

<div class="cart-container">
    <div class="cart-title-area">
        <h2>Giỏ Hàng Của Tôi</h2>
        <a href="products" class="link-more">Xem & lựa thêm</a>
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
                <button class="qty-btn" onclick="updateQty(${item.id}, -1)">-</button>
                <input type="text" class="qty-input" value="${item.quantity}" readonly>
                <button class="qty-btn" onclick="updateQty(${item.id}, 1)">+</button>
                <a href="cart?action=remove&id=${item.id}" class="btn-remove">
                    <i class="fa-regular fa-trash-can"></i>
                </a>
            </div>

            <div class="item-total">
                <fmt:formatNumber value="${item.total}" pattern="#,###"/>₫
            </div>
        </div>
    </c:forEach>

    <div class="cart-summary">
        <div class="total-label">
            Tổng đơn hàng: <fmt:formatNumber value="${totalCartPrice}" pattern="#,###"/>₫
        </div>
        <div class="free-ship-info">
            <i class="fa-solid fa-truck-fast"></i>
            MIỄN PHÍ SHIP cho đơn hàng từ 800K. Hỗ trợ lắp đặt tại TPHCM và một số tỉnh lân cận.
        </div>
        <button class="btn-order">Đặt hàng</button>
    </div>
</div>

<%@ include file="/templates/footer.jsp" %>

<script>
    function updateQty(id, delta) {
        // Bạn có thể dùng AJAX ở đây để không bị load lại trang
        window.location.href = "cart?action=update&id=" + id + "&delta=" + delta;
    }
</script>

</body>
</html>