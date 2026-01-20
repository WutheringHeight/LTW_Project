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
            <c:if test="${product.stock == 0}">
                <div class="out-of-stock">
                    ❌ HẾT HÀNG
                </div>
            </c:if>
            <p class="product-price">
                <fmt:formatNumber value="${product.price}" type="number" groupingUsed="true"/>₫
            </p>

            <!-- Thông tin bổ sung -->
            <div class="product-meta">
                <p><strong>🎭 Thể loại:</strong> ${product.kind}</p>
                <p><strong>🔥 Đã bán:</strong> ${product.soldQuantity} sản phẩm</p>
                <p><strong>🔥 Còn:</strong> ${product.stock} sản phẩm</p>
            </div>

            <p class="product-note">🚚 Miễn phí ship cho đơn hàng từ 800K...</p>
            <p class="product-note">🚚 ${product.description}</p>

            <!-- Số lượng -->
            <c:if test="${product.stock > 0}">
                <div class="quantity-selector">
                    <button type="button" class="qty-btn" onclick="changeQuantity(-1)">-</button>
                    <input type="number" id="product-qty" value="1" min="1" max="${product.stock}"
                           onchange="validateInput(this)"/>
                    <button type="button" class="qty-btn" onclick="changeQuantity(1)">+</button>
                </div>
            </c:if>

            <!-- Nút hành động -->
            <c:if test="${product.stock > 0}">
                <div class="action-buttons">
                    <button class="add-to-cart" onclick="addToCart(${product.id})">
                        Thêm vào giỏ
                    </button>

                    <button class="quick-order" onclick="quickOrder(${product.id})">
                        Đặt hàng nhanh
                    </button>
                </div>
            </c:if>

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
        const maxStock = ${product.stock}; // Số lượng tồn kho từ Server
        let current = parseInt(qtyInput.value) || 1;

        let newValue = current + delta;

        // Ràng buộc giá trị trong khoảng [1, maxStock]
        if (newValue < 1) {
            newValue = 1;
        } else if (newValue > maxStock) {
            newValue = maxStock;
            alert("Chỉ còn " + maxStock + " sản phẩm trong kho!");
        }

        qtyInput.value = newValue;
        updateButtonStates(newValue, maxStock);
    }

    // Hàm cập nhật trạng thái bật/tắt của nút
    function updateButtonStates(value, max) {
        const btnMinus = document.querySelector(".quantity-selector button:first-child");
        const btnPlus = document.querySelector(".quantity-selector button:last-child");

        btnMinus.disabled = (value <= 1);
        btnPlus.disabled = (value >= max);
    }

    // Gọi hàm lần đầu để thiết lập trạng thái nút khi trang vừa load
    window.onload = function() {
        const maxStock = ${product.stock};
        const current = parseInt(document.getElementById("product-qty").value);
        updateButtonStates(current, maxStock);
    };
    function validateInput(input) {
        const maxStock = ${product.stock};
        let val = parseInt(input.value);

        if (isNaN(val) || val < 1) {
            input.value = 1;
        } else if (val > maxStock) {
            input.value = maxStock;
            alert("Số lượng yêu cầu vượt quá tồn kho!");
        }

        updateButtonStates(parseInt(input.value), maxStock);
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
