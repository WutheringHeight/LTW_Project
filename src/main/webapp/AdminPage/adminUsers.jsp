<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quản lý người dùng</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/adminUserStyle.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/adminHeaderStyle.css"/>
</head>
<body>
<%@ include file="/templates/adminHeader.jsp" %>

<div class="user-container">
    <h1 class="page-title">Quản lý người dùng</h1>

    <c:if test="${not empty success}">
        <div class="alert success">${success}</div>
        <c:remove var="success" scope="session"/>
    </c:if>
    <c:if test="${not empty error}">
        <div class="alert error">${error}</div>
        <c:remove var="error" scope="session"/>
    </c:if>

    <div class="filter-search-wrapper">
        <form method="get" action="${pageContext.request.contextPath}/AdminUser" class="filter-form">
            <div class="filter-group">
                <label>Vai trò</label>
                <select name="role">
                    <option value="">Tất cả vai trò</option>
                    <option value="ADMIN" ${role == 'ADMIN' ? 'selected' : ''}>Quản trị viên</option>
                    <option value="USER" ${role == 'USER' ? 'selected' : ''}>Khách hàng</option>
                </select>
            </div>

            <div class="filter-group">
                <label>Trạng thái</label>
                <select name="status">
                    <option value="">Tất cả</option>
                    <option value="Active" ${status == 'Active' ? 'selected' : ''}>Hoạt động</option>
                    <option value="Locked" ${status == 'Locked' ? 'selected' : ''}>Bị khóa</option>
                </select>
            </div>

            <div class="filter-actions">
                <button type="submit" class="action-btn filter-btn">🔍 Lọc</button>
            </div>
        </form>

        <form method="get" action="${pageContext.request.contextPath}/AdminUser" class="search-form">
            <input type="text" name="keyword" value="${keyword}" placeholder="🔍 Tên / Email / SĐT">
            <button type="submit" class="action-btn update-btn">Tìm kiếm</button>
        </form>
    </div>

    <table class="user-table">
        <thead>
        <tr>
            <th>ID</th>
            <th>Ảnh</th>
            <th>Họ và Tên</th>
            <th>Email</th>
            <th>Số điện thoại</th>
            <th>Vai trò</th>
            <th>Trạng thái</th>
            <th>Ngày tham gia</th>
            <th>Thao tác</th>
        </tr>
        </thead>
        <tbody>
        <c:choose>
            <c:when test="${not empty users}">
                <c:forEach var="u" items="${users}">
                    <tr>
                        <td>${u.id}</td>
                        <td>
                            <img src="${not empty u.avatar ? u.avatar : pageContext.request.contextPath.concat('/image/default-avatar.png')}"
                                 alt="Avatar" class="user-avatar">
                        </td>
                        <td><span class="user-name">${u.fullName}</span></td>
                        <td>${u.email}</td>
                        <td>${u.phone}</td>
                        <td>
                            <span class="role-badge ${u.role == 'ADMIN' ? 'role-admin' : 'role-user'}">
                                    ${u.role}
                            </span>
                        </td>
                        <td>
                            <span class="status-dot ${u.status == 'Active' ? 'dot-active' : 'dot-locked'}"></span>
                                ${u.status == 'Active' ? 'Hoạt động' : 'Bị khóa'}
                        </td>
                        <td>
                            <fmt:formatDate value="${u.createdAt}" pattern="dd/MM/yyyy"/>
                        </td>
                        <td>
                            <div class="action-group">
                                <form action="${pageContext.request.contextPath}/AdminUser" method="post" style="display:inline;">
                                    <input type="hidden" name="action" value="toggleStatus"/>
                                    <input type="hidden" name="id" value="${u.id}"/>
                                    <button type="submit" class="action-btn ${u.status == 'Active' ? 'lock-btn' : 'unlock-btn'}">
                                            ${u.status == 'Active' ? 'Khóa' : 'Mở khóa'}
                                    </button>
                                </form>
                                <a href="${pageContext.request.contextPath}/AdminUser/Edit?id=${u.id}" class="action-btn edit-btn">Sửa</a>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <tr>
                    <td colspan="9" style="text-align: center;">Không tìm thấy người dùng nào</td>
                </tr>
            </c:otherwise>
        </c:choose>
        </tbody>
    </table>

    <div class="pagination">
        <c:forEach var="i" begin="1" end="${totalPages}">
            <a href="?page=${i}&role=${role}&status=${status}&keyword=${keyword}"
               class="page-btn ${i == currentPage ? 'active' : ''}">${i}</a>
        </c:forEach>
    </div>
</div>

</body>
</html>