<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quản lý đơn hàng</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/adminOrderStyle.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/adminHeaderStyle.css"/>
</head>
<body>
<%@ include file="/templates/adminHeader.jsp" %>

<div class="order-container">
    <h1 class="page-title">Quản lý đơn hàng</h1>

    <c:if test="${not empty success}">
        <div class="alert success">${success}</div>
        <c:remove var="success" scope="session"/>
    </c:if>
    <c:if test="${not empty error}">
        <div class="alert error">${error}</div>
        <c:remove var="error" scope="session"/>
    </c:if>
    <div class="filter-search-wrapper">

        <!-- FILTER -->
        <form method="get" action="${pageContext.request.contextPath}/AdminOrder" class="filter-form">
            <div class="filter-group">
                <label>Trạng thái</label>
                <select name="status">
                    <option value="">Tất cả trạng thái</option>
                    <option value="Pending" ${status == 'Pending' ? 'selected' : ''}>Chờ xử lý</option>
                    <option value="Processing" ${status == 'Processing' ? 'selected' : ''}>Đang đóng gói</option>
                    <option value="Completed" ${status == 'Completed' ? 'selected' : ''}>Đã hoàn thành</option>
                    <option value="Cancelled" ${status == 'Cancelled' ? 'selected' : ''}>Đã hủy</option>
                </select>
            </div>

            <div class="filter-group">
                <label>Từ ngày</label>
                <input type="date" name="fromDate" value="${fromDate}">
            </div>

            <div class="filter-group">
                <label>Đến ngày</label>
                <input type="date" name="toDate" value="${toDate}">
            </div>

            <div class="filter-actions">
                <button type="submit" class="action-btn update-btn">
                    🔍 Lọc
                </button>
            </div>

        </form>
        <!-- SEARCH -->
        <form method="get" action="${pageContext.request.contextPath}/AdminOrder" class="search-form">
            <input type="hidden" name="status" value="${status}">
            <input type="hidden" name="fromDate" value="${fromDate}">
            <input type="hidden" name="toDate" value="${toDate}">

            <input type="text"
                   name="keyword"
                   value="${keyword}"
                   placeholder="🔍 ID / Tên KH / SĐT">

            <button type="submit" class="action-btn update-btn">
                Tìm
            </button>
        </form>
    </div>

    <table class="order-table">
        <thead>
        <tr>
            <th>ID</th>
            <th>Khách hàng</th>
            <th>SĐT</th>
            <th>Địa chỉ</th>
            <th>Tổng tiền</th>
            <th>Trạng thái</th>
            <th>Ngày tạo</th>
            <th>Cập nhật</th>
            <th>Sản phẩm</th>
            <th>Thao tác</th>
        </tr>
        </thead>
        <tbody>
        <c:choose>
            <c:when test="${not empty orders}">
                <c:forEach var="o" items="${orders}">
                    <tr>
                        <td>${o.id}</td>
                        <td>${o.customerName}</td>
                        <td>${o.phone}</td>
                        <td>${o.address}</td>
                        <td><fmt:formatNumber value="${o.totalPrice}" type="currency" currencySymbol="₫" maxFractionDigits="0" /></td>
                        <td><c:choose>
                            <c:when test="${o.status == 'PENDING'}">Chờ xử lý</c:when>
                            <c:when test="${o.status == 'Processing'}">Đang đóng gói</c:when>
                            <c:when test="${o.status == 'Completed'}">Đã hoàn thành</c:when>
                            <c:when test="${o.status == 'Cancelled'}">Đã hủy</c:when>
                            <c:otherwise>${o.status}</c:otherwise>
                        </c:choose></td>

                        <td>
                            <fmt:parseDate value="${o.createdAt}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="createdDate"/>
                            <fmt:formatDate value="${createdDate}" pattern="dd/MM/yyyy HH:mm"/>
                        </td>

                        <td>
                            <fmt:parseDate value="${o.updatedAt}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="updatedDate"/>
                            <fmt:formatDate value="${updatedDate}" pattern="dd/MM/yyyy HH:mm"/>
                        </td>

                        <td>
                            <ul class="item-list">
                                <c:forEach var="item" items="${o.items}">
                                    <li>
                                        - Tên: <span class="item-name">${item.productName}</span>
                                        - SL: ${item.quantity}
                                        - Giá: <fmt:formatNumber value="${item.price}" type="currency" currencySymbol="₫" maxFractionDigits="0" />
                                    </li>
                                </c:forEach>
                            </ul>
                        </td>

                        <td>
                            <form action="${pageContext.request.contextPath}/AdminOrder" method="post" style="display:inline;">
                                <input type="hidden" name="action" value="updateStatus"/>
                                <input type="hidden" name="id" value="${o.id}"/>
                                <select name="status" class="status-select">
                                    <option value="Pending" ${o.status == 'Pending' ? 'selected' : ''}>Chờ xử lý</option>
                                    <option value="Processing" ${o.status == 'Processing' ? 'selected' : ''}>Đang đóng gói</option>
                                    <option value="Completed" ${o.status == 'Completed' ? 'selected' : ''}>Đã hoàn thành</option>
                                    <option value="Cancelled" ${o.status == 'Cancelled' ? 'selected' : ''}>Đã hủy</option>
                                </select>
                                <button type="submit" class="action-btn update-btn">Cập nhật</button>
                            </form>
                            <form action="${pageContext.request.contextPath}/AdminOrder" method="post" style="display:inline;" onsubmit="return confirm('Xóa đơn hàng này?');">
                                <input type="hidden" name="action" value="delete"/>
                                <input type="hidden" name="id" value="${o.id}"/>
                                <button type="submit" class="action-btn delete-btn">Xóa</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <tr>
                    <td colspan="10" style="text-align: center;">Chưa có đơn hàng</td>
                </tr>
            </c:otherwise>
        </c:choose>
        </tbody>
    </table>
    <!-- Thanh phân trang -->
    <div class="pagination">
        <c:if test="${currentPage > 1}">
            <a href="${pageContext.request.contextPath}/AdminOrder?page=${currentPage - 1}" class="page-btn">« Trước</a>
        </c:if>

        <c:forEach var="i" begin="1" end="${totalPages}">
<%--            <a href="${pageContext.request.contextPath}/AdminOrder?page=${i}"--%>
<%--               class="page-btn ${i == currentPage ? 'active' : ''}">${i}</a>--%>
            <a href="${pageContext.request.contextPath}/AdminOrder?page=${i}&status=${status}&fromDate=${fromDate}&toDate=${toDate}&keyword=${keyword}"
               class="page-btn ${i == currentPage ? 'active' : ''}">
                    ${i}
            </a>
        </c:forEach>

        <c:if test="${currentPage < totalPages}">
            <a href="${pageContext.request.contextPath}/AdminOrder?page=${currentPage + 1}" class="page-btn">Sau »</a>
        </c:if>
    </div>
</div>

</body>
</html>
