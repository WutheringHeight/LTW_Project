<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Người Dùng</title>

    <link rel="stylesheet" href="css/headerFooterStyle.css"/>
    <link rel="stylesheet" href="css/userDetailsStyle.css"/>
</head>
<body>
<!-- HEADER -->
<%@ include file="templates/header.jsp" %>

<!-- Main Content -->
<main class="userdetails-container">

    <!-- Sidebar -->
    <aside class="sidebar">
        <a href="userdetails.jsp?section=account" style="text-decoration: none">
            <button class="sidebar-btn">
                <img src="image/userdetails/userProfile.png" class="icon"/> Tài khoản
            </button>
        </a>

        <a href="userdetails.jsp?section=bookmark" style="text-decoration: none">
            <button class="sidebar-btn">
                <img src="image/userdetails/bookmark.png" class="icon"/> Đã lưu
            </button>
        </a>

        <a href="userdetails.jsp?section=notification" style="text-decoration: none">
            <button class="sidebar-btn">
                <img src="image/userdetails/notification.png" class="icon"/> Thông báo
            </button>
        </a>

        <a href="userdetails.jsp?section=payment" style="text-decoration: none">
            <button class="sidebar-btn">
                <img src="image/userdetails/payment.png" class="icon"/> Thanh toán
            </button>
        </a>

        <a href="userdetails.jsp?section=setting" style="text-decoration: none">
            <button class="sidebar-btn">
                <img src="image/userdetails/setting.png" class="icon"/> Cài đặt
            </button>
        </a>

        <a href="userdetails.jsp?section=faq" style="text-decoration: none">
            <button class="sidebar-btn">
                <img src="image/userdetails/faq.png" class="icon"/> Hỏi đáp
            </button>
        </a>
    </aside>

    <!-- Content Area -->
    <section class="content-area">
        <%
            String section = request.getParameter("section");
            if(section == null || section.isEmpty()) {
                section = "account"; // mặc định
            }
            request.setAttribute("sectionPage", "sections/" + section + ".jsp");
        %>

        <c:import url="${sectionPage}" />
    </section>

</main>

<!-- FOOTER -->
<%@ include file="templates/footer.jsp" %>
</body>
</html>
