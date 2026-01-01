<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Kết quả</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/productStyle.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/headerFooterStyle.css"/>
</head>
<body>
<!-- HEADER -->
<%@ include file="/templates/header.jsp" %>
<h1 style="text-align: center;margin-top: 40px">Tất cả sản phẩm</h1>
<div class="container">

    <!-- Bộ lọc bên trái -->
    <div class="filter-panel">
        <h3>Bộ Lọc</h3>
        <form method="post" action="search">
            <input type="hidden" name="q" value="${param.q}" />
            <input type="hidden" name="categoryId" value="${param.categoryId}" />
            <!-- GIÁ -->
            <div class="filter-group">
                <div class="filter-title">GIÁ</div>
                <label><input type="radio" name="price" value=""
                ${empty price ? 'checked' : ''}/> Tất cả</label>
                <label><input type="radio" name="price" value="0-500000"
                ${price eq '0-500000' ? 'checked' : ''}/> Dưới 500,000₫</label>
                <label><input type="radio" name="price" value="500000-1000000"
                ${price eq '500000-1000000' ? 'checked' : ''}/> 500,000₫ - 1,000,000₫</label>
                <label><input type="radio" name="price" value="1000000-2000000"
                ${price eq '1000000-2000000' ? 'checked' : ''}/> 1,000,000₫ - 2,000,000₫</label>
                <label><input type="radio" name="price" value="2000000+"
                ${price eq '2000000+' ? 'checked' : ''}/> Trên 2,000,000₫</label>
            </div>

            <!-- CHỦ ĐỀ -->
            <div class="filter-group">
                <div class="filter-title">CHỦ ĐỀ</div>
                <label><input type="radio" name="categoryId" value=""
                ${empty category ? 'checked' : ''}/> Tất cả</label>
                <c:forEach var="c" items="${categories}"><label>
                    <input type="radio" name="categoryId" value="${c.id}"
                        ${category eq c.id ? 'checked' : ''}/> ${c.name}
                </label> </c:forEach>
            </div>
            <!-- THỂ LOẠI -->
            <div class="filter-group">
                <div class="filter-title">THỂ LOẠI</div>
                <label><input type="radio" name="kind" value=""
                ${empty kind ? 'checked' : ''}/> Tất cả</label>
                <label><input type="radio" name="kind" value="Canvas"
                ${kind eq 'Canvas' ? 'checked' : ''}/> Canvas</label>
                <label><input type="radio" name="kind" value="Sơn dầu"
                ${kind eq 'Sơn dầu' ? 'checked' : ''}/> Sơn dầu</label>
                <label><input type="radio" name="kind" value="Tráng gương"
                ${kind eq 'Tráng gương' ? 'checked' : ''}/>Tráng gương</label>
            </div>
            <button type="submit">Lọc</button>
        </form>
    </div>


    <!-- Sản phẩm bên phải -->
    <div class="product-section">
        <!-- Thanh sắp xếp -->
        <div class="sort-bar">
            <form method="post" action="search">
                <input type="hidden" name="q" value="${param.q}" />
                <!-- giữ lại filter đã chọn -->
                <input type="hidden" name="price" value="${param.price}"/>
                <input type="hidden" name="categoryId" value="${param.categoryId}"/>
                <input type="hidden" name="kind" value="${param.kind}"/>

                <label for="sort">Sắp xếp:</label>
                <select name="sort" onchange="this.form.submit()">
                    <option value="popular" ${sort eq 'popular' ? 'selected' : ''}>Phổ biến</option>
                    <option value="newest" ${sort eq 'newest' ? 'selected' : ''}>Mới nhất</option>
                    <option value="priceAsc" ${sort eq 'priceAsc' ? 'selected' : ''}>Giá tăng dần</option>
                    <option value="priceDesc" ${sort eq 'priceDesc' ? 'selected' : ''}>Giá giảm dần</option>
                </select>
            </form>
        </div>
        <!-- Lưới sản phẩm -->
        <div class="product-grid">
            <c:forEach var="p" items="${products}">
                <div class="product-card">
                    <a href="productdetail?id=${p.id}" style="text-decoration: none">
                        <img src="${p.thumbnail}" alt="${p.productName}"/>
                        <h3>${p.productName}</h3>
                        <span>Từ <fmt:formatNumber value="${p.price}" type="number" groupingUsed="true"/>₫</span>
                    </a>
                </div>
            </c:forEach>
        </div>

        <!-- Phân trang -->
        <div class="pagination">
            <a href="?page=1" class="active">1</a>
            <a href="?page=2">2</a>
            <a href="?page=3">3</a>
            <a href="?page=4">4</a>
            <a href="?page=5">5</a>
        </div>
    </div>
</div>
<!-- Footer -->
<%@include file="/templates/footer.jsp" %>
</body>
</html>
