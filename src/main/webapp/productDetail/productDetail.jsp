<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Chi tiết sản phẩm</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/productDetailStyle.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/headerFooterStyle.css"/>
</head>
<body>
<!-- HEADER -->
<%@ include file="/templates/header.jsp" %>

<!-- BODY sản phẩm -->
<section class="product-detail">
    <div class="product-layout">
        <!-- Bên trái: 2 ảnh -->
        <div class="product-images">
            <c:forEach var="image" items="${product.images}">
            <img src="${image.imageUrl}" alt="Ảnh trên" class="top-img"/>
            </c:forEach>
        </div>

        <!-- Bên phải: thông tin sản phẩm -->
        <div class="product-info">
            <h2>${product.productName}</h2>
            <p class="product-price">
                <fmt:formatNumber value="${product.price}" type="number" groupingUsed="true"/>₫
            </p>

            <!-- Thông tin bổ sung -->
            <div class="product-meta">
                <p><strong>🎭 Thể loại:</strong> ${product.kind}</p>
                <p><strong>🔥 Đã bán:</strong> ${product.soldQuantity} sản phẩm</p>
            </div>

            <p class="product-note">🚚 Miễn phí ship cho đơn hàng từ 800K...</p>
            <p class="product-note">🚚 ${product.description}</p>

            <!-- Số lượng -->
            <div class="quantity-selector">
                <button type="button" onclick="changeQuantity(-1)">-</button>
                <input type="number" id="product-qty" value="1" min="1" readonly/>
                <button type="button" onclick="changeQuantity(1)">+</button>
            </div>

            <!-- Nút hành động -->
            <div class="action-buttons">
                <button class="add-to-cart" onclick="addToCart(${product.id})">Thêm vào giỏ </button>

                <button class="quick-order" onclick="quickOrder(${product.id})">Đặt hàng nhanh</button>
            </div>

            <!-- Cam kết dịch vụ -->
            <ul class="service-guarantees">
                <li>🚀 Ship hoả tốc toàn quốc...</li>
                <li>✅ Kiểm hàng đảm bảo...</li>
                <li>🎨 Cam kết tranh đẹp...</li>
                <li>🔁 Miễn phí bảo hành...</li>
            </ul>

            <!-- Thương hiệu -->
            <div class="brand-contact">
                <strong>VIET CANVAS</strong><br>
                Hotline: 0983.859.614
            </div>
        </div>

    </div>
</section>

    <!-- Footer -->
    <%@include file="/templates/footer.jsp" %>
<script>
    function changeQuantity(delta) {
        const qtyInput = document.getElementById("product-qty");
        let current = parseInt(qtyInput.value);
        if (current + delta >= 1) {
            qtyInput.value = current + delta;
        }
    }

    function addToCart(productId) {
        const qty = (document.getElementById("product-qty")).value;
        window.location.href =
            "cart?action=add&id=" + productId + "&quantity=" + qty;
        console.log("Đang gửi yêu cầu tới: " + qty + "||" +productId)
    }

    function quickOrder(productId) {
        const qty = document.getElementById('product-qty').value;
        console.log("Đang gửi yêu cầu tới: " + qty + "||" +productId)
        window.location.href = "cart?action=add&id="+ productId + "&quantity=" + qty + "&redirect=checkout&autoCheckout=true";
    }
</script>
</body>
</html>
