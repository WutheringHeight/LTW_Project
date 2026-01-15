<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 04/01/2026
  Time: 1:25 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar">
    <div class="navbar-container">
        <div class="navbar-logo">
            <a href="${pageContext.request.contextPath}/Admin">Admin Panel</a>
        </div>
        <ul class="navbar-menu">
            <li><a href="${pageContext.request.contextPath}/Admin">Sản phẩm</a></li>
            <li><a href="${pageContext.request.contextPath}/AdminUsers">Người dùng</a></li>
            <li><a href="${pageContext.request.contextPath}/AdminOrder">Đơn hàng</a></li>
        </ul>
    </div>
</nav>


