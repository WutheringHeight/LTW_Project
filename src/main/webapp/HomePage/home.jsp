<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Trang chủ</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/homeStyle.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/headerFooterStyle.css"/>
</head>
<body>
<!-- HEADER -->
<%@ include file="/templates/header.jsp" %>
<!-- BANNER -->
<section class="banner">
    <div class="banner-overlay"></div>
    <img src="${pageContext.request.contextPath}/image/banner.png" alt="Banner" class="banner-img"/>
    <div class="banner-text">
        <h2>Thương hiệu tranh trang trí hàng đầu VN</h2>
        <h1>TRANH ĐẸP TREO TƯỜNG VIET CANVAS</h1>
        <button onclick="location.href='product'" class="banner-btn">Xem 20.000+ Bộ Tranh</button>
    </div>
</section>

<!-- BODY -->
<!-- Sản phẩm nổi bật -->
<section class="product-section">
    <h2 style="font-family: 'Futura Regular'">Sản Phẩm Nổi Bật</h2>
    <div class="product-grid">
        <c:forEach var="p" items="${popularProducts}">
            <div class="product-card">
                <a href="productdetail?id=${p.id}" style="text-decoration: none">
                    <img src="${p.thumbnail}" alt="${p.productName}"/>
                    <h3>${p.productName}</h3>
                    <span>Từ <fmt:formatNumber value="${p.price}" type="number" groupingUsed="true"/>₫</span>
                </a>
            </div>
        </c:forEach>
    </div>

    <div class="view-more">
        <a href="product" style="text-decoration: none">
            <button class="view-more-btn">Xem thêm</button>
        </a>
    </div>
    <!-- Sản phẩm mới -->
    <h2>Sản Phẩm Mới</h2>
    <div class="product-grid">
        <c:forEach var="p" items="${latestProducts}">
            <div class="product-card">
                <a href="productdetail?id=${p.id}" style="text-decoration: none">
                    <img src="${p.thumbnail}"/>
                    <h3>${p.productName}</h3>
                    <span>Từ <fmt:formatNumber value="${p.price}" type="number" groupingUsed="true"/>₫</span>
                </a>
            </div>
        </c:forEach>
    </div>
    <div class="view-more">
        <a href="product" style="text-decoration: none">
            <button class="view-more-btn">Xem thêm</button>
        </a>
    </div>
    <!-- Bộ sưu tập -->
    <h2 style="font-size: 28px;
    text-align: left;
    margin-bottom: 30px; margin-top: 20px">Bộ Sưu Tập</h2>
    <div class="collection-grid" id="gallery">
        <c:forEach var="c" items="${categories}">
            <a href="${pageContext.request.contextPath}/search?categoryId=${c.id}" style="text-decoration: none">
            <div class="collection-card"><img src="${pageContext.request.contextPath}/${c.pathImage}" alt="${c.name}"/>
                <h2>${c.name}</h2>
            </div>
            </a>
        </c:forEach>
    </div>
</section>
<!-- Footer -->
<%@include file="/templates/footer.jsp" %>
</body>
</html>
