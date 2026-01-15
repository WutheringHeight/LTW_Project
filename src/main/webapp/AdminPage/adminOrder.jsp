<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
                        <td>${o.totalPrice}</td>
                        <td>${o.status}</td>
                        <td>${o.createdAt}</td>
                        <td>
                            <ul class="item-list">
                                <c:forEach var="item" items="${o.items}">
                                    <li>
                                        <span class="item-name">${item.productName}</span>
                                        - SL: ${item.quantity}
                                        - Giá: ${item.price}
                                    </li>
                                </c:forEach>
                            </ul>
                        </td>
                        <td>
                            <form action="${pageContext.request.contextPath}/AdminOrder" method="post" style="display:inline;">
                                <input type="hidden" name="action" value="updateStatus"/>
                                <input type="hidden" name="id" value="${o.id}"/>
                                <select name="status" class="status-select">
                                    <option value="Pending" ${o.status == 'Pending' ? 'selected' : ''}>Pending</option>
                                    <option value="Processing" ${o.status == 'Processing' ? 'selected' : ''}>Processing</option>
                                    <option value="Completed" ${o.status == 'Completed' ? 'selected' : ''}>Completed</option>
                                    <option value="Cancelled" ${o.status == 'Cancelled' ? 'selected' : ''}>Cancelled</option>
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
                <tr><td colspan="9">Chưa có đơn hàng</td></tr>
            </c:otherwise>
        </c:choose>
        <!-- Thanh phân trang -->
        <div class="pagination">
            <c:if test="${currentPage > 1}">
            <a href="${pageContext.request.contextPath}/AdminOrder?page=${currentPage - 1}" class="page-btn">« Trước</a>
            </c:if>

            <c:forEach var="i" begin="1" end="${totalPages}">
            <a href="${pageContext.request.contextPath}/AdminOrder?page=${i}"
               class="page-btn ${i == currentPage ? 'active' : ''}">${i}</a>
            </c:forEach>

            <c:if test="${currentPage < totalPages}">
            <a href="${pageContext.request.contextPath}/AdminOrder?page=${currentPage + 1}" class="page-btn">Sau »</a>
            </c:if>
        </tbody>
    </table>
</div>

</body>
</html>
