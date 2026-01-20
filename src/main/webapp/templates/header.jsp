<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- HEADER -->
<header class="main-header">
    <div class="top-bar">
        <!-- Logo -->
        <div class="logo">
            <a href="home" style="text-decoration: none">
                <img src="${pageContext.request.contextPath}/image/logo.png" alt="Viet Canvas" width="60px"
                     height="50px"/>
            </a>
        </div>

        <!-- Navbar bên trái -->
        <nav class="navbar">
            <ul>
                <li><a href="product">Shop</a></li>
                <li><a href="gallery#gallery">Gallery</a></li>
<%--                <li><a href="#">Reviews</a></li>--%>
<%--                <li><a href="#">Về Chúng Tôi</a></li>--%>
            </ul>
        </nav>

        <div class="nav-icons">
            <!-- Icon search -->

            <div id="search-wrapper" class="search-wrapper" >
                <form action="${pageContext.request.contextPath}/search" method="get" class="search-form">
                    <input type="text" name="q" placeholder="Tìm kiếm" class="search-input"/>
                    <button type="submit" class="search-button">
                        <img src="${pageContext.request.contextPath}/image/search.png" alt="Search"/>
                    </button>
                </form>
<%--                <div id="search-suggestions" class="search-suggestions" style="display:flex;">--%>
<%--                    <ul id="suggestion-list"></ul>--%>
<%--                </div>--%>
            </div>

            <a href="cart"><img src="${pageContext.request.contextPath}/image/cart.png" alt="Cart" class="icon"
                             width="30px" height="30px"/></a>
            <a href="${pageContext.request.contextPath}/userdetails.jsp"><img src="${pageContext.request.contextPath}/image/account.png" alt="User" class="icon"
                             width="30px" height="30px"/></a>
        </div>


    </div>
    <script>
        window.CONTEXT_PATH = "${pageContext.request.contextPath}";
    </script>
<%--    <script src="/js/suggestion.js"></script>--%>

</header>