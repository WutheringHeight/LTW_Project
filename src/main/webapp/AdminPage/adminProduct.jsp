<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Admin</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/adminProductStyle.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/adminHeaderStyle.css"/>
</head>
<body>
<%@ include file="/templates/adminHeader.jsp" %>

<section class="add-product-container">
        <form action="Admin" method="post" enctype="multipart/form-data" class="product-form">
            <input type="hidden" name="action" value="add"/>
            <h2 class="form-title">Thêm sản phẩm</h2>

            <div class="form-group">
                <label>Tên sản phẩm</label>
                <input type="text" name="productName" required/>
            </div>
            <div class="form-group">
                <label>Giá</label>
                <input type="number" name="price" required/>
            </div>
            <div class="form-group">
                <label>Mô tả</label>
                <textarea name="description" required></textarea>
            </div>
            <div class="form-group">
                <label>Chủ đề</label>
                <div style="display:flex; align-items:center; gap:10px;">
                    <select name="category" required>
                        <c:forEach var="c" items="${categories}">
                            <option value="${c.name}">${c.name}</option>
                        </c:forEach>
                    </select>
                    <!-- Icon edit -->
                    <button type="button" class="action-btn edit-btn" onclick="toggleCategoryManager()">✎</button>
                </div>
            </div>

            <div class="form-group">
                <label>Loại</label>
                <div style="display:flex; align-items:center; gap:10px;">
                    <select name="kind" required>
                        <c:forEach var="k" items="${kinds}">
                            <option value="${k}">${k}</option>
                        </c:forEach>
                    </select>
                    <button type="button"
                            class="action-btn edit-btn"
                            onclick="toggleKindManager()">✎</button>
                </div>
            </div>

            <div class="form-group">
                <label>Tồn kho</label>
                <input type="number" name="stock" required/>
            </div>

            <div class="form-group image-upload">
                <label>Upload ảnh sản phẩm</label>
                <input type="file" name="thumbnail" accept="image/*" onchange="previewImage(this, 'preview0')"/>
                <img id="preview0" class="image-preview" src="${pageContext.request.contextPath}/image/placeholder.png"
                     alt="Preview 0"/>
            </div>
            <div class="form-group image-row">
                <div class="image-upload">
                    <label>Ảnh hiển thị:</label>
                    <input type="file" name="extraImage1" accept="image/*" onchange="previewImage(this, 'preview1')"/>
                    <img id="preview1" class="image-preview"
                         src="${pageContext.request.contextPath}/image/placeholder.png"
                         alt="Preview 1"/>
                </div>

                <div class="image-upload">
                    <label>Ảnh kích thước:</label>
                    <input type="file" name="extraImage2" accept="image/*" onchange="previewImage(this, 'preview2')"/>
                    <img id="preview2" class="image-preview"
                         src="${pageContext.request.contextPath}/image/placeholder.png"
                         alt="Preview 2"/>
                </div>
            </div>
            <button type="submit" class="submit-btn">THÊM SẢN PHẨM NGAY</button>
        </form>
</section>

<!-- Overlay -->
<div id="categoryOverlay" class="overlay" onclick="closeCategoryManager()"></div>

<!-- Category Manager -->
<div id="categoryManager" class="edit-category-container modal">
    <h3 class="form-title">Quản lý chủ đề</h3>

    <table class="category-table">
        <thead>
        <tr>
            <th>#</th>
            <th>Tên</th>
            <th>Thao tác</th>
            <th>Ảnh</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="c" items="${categories}">
            <tr>
                <td>${c.id}</td>

                <td>
                    <input type="text" class="category-input" value="${c.name}">
                    <input type="file" class="image-input">
                </td>

                <td>
                    <button class="btn-save">Lưu</button>
                    <button class="btn-delete">Xóa</button>
                </td>

                <td>
                    <img src="${pageContext.request.contextPath}/${c.pathImage}"
                         class="thumbnail">
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <h4 class="form-title">Thêm chủ đề mới</h4>
    <form action="CategoryController"
          method="post"
          enctype="multipart/form-data"
          class="category-form">

        <input type="hidden" name="action" value="add"/>

        <div class="form-group">
            <input type="text"
                   name="categoryName"
                   placeholder="Tên chủ đề mới"
                   required/>
        </div>

        <div class="form-group">
            <input type="file"
                   name="pathImage"
                   accept="image/*"
                   required/>
        </div>

        <button type="submit" class="submit-btn">
            Thêm
        </button>
    </form>
    <button class="close-btn" onclick="closeCategoryManager()">✕</button>
</div>

<div id="kindOverlay" class="overlay" onclick="closeKindManager()"></div>
<!-- Kind Manager -->
<div id="kindManager" class="edit-category-container modal">
    <h3 class="form-title">Quản lý loại sản phẩm</h3>

    <table class="category-table">
        <thead>
        <tr>
            <th>#</th>
            <th>Tên loại</th>
            <th>Thao tác</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="k" items="${kinds}" varStatus="i">
            <tr>
                <td>${i.index + 1}</td>

                <!-- UPDATE -->
                <td>
                    <form action="KindController" method="post" class="inline-form">
                        <input type="hidden" name="action" value="update"/>
                        <input type="hidden" name="oldName" value="${k}"/>

                        <input type="text" name="newName"
                               class="category-input"
                               value="${k}" required/>
                    <button type="submit" class="btn-save">Lưu</button>
                    </form>
                </td>
                <td>
                    <!-- DELETE -->
                    <form action="KindController" method="post" class="inline-form">
                        <input type="hidden" name="action" value="delete"/>
                        <input type="hidden" name="name" value="${k}"/>
                        <button type="submit" class="btn-delete"
                                onclick="return confirm('Xóa loại này?')">
                            Xóa
                        </button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <form id="addKindForm" class="category-form">
        <input type="hidden" name="action" value="add">
        <input type="text" name="kindName" placeholder="Tên loại mới" required/>
        <button type="submit" class="submit-btn">Thêm</button>
    </form>
    <div id="kindMessage"></div>
    <button class="close-btn" onclick="closeKindManager()">✕</button>
</div>

<!-- Danh sách sản phẩm -->
<section class="list-section">
    <h2 class="form-title">Danh sách sản phẩm</h2>
    <table class="product-table">
        <thead>
        <tr>
            <th>ID</th>
            <th>Tên</th>
            <th>Giá</th>
            <th>Chủ đề</th>
            <th>Loại</th>
            <th>Tồn</th>
            <th>Nền</th>
            <th>Chi tiết</th>
            <th>Kích thước</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="p" items="${products}">
            <tr>
                <td>${p.id}</td>
                <td>${p.productName}</td>
                <td><fmt:formatNumber value="${p.price}"/>đ</td>
                <td>${p.category_id}</td>
                <td>${p.kind}</td>
                <td>${p.stock}</td>
                <td><img src="${pageContext.request.contextPath}/${p.thumbnail}" class="thumbnail"/></td>
                <td>
                    <c:if test="${p.images.size() >= 1}">
                        <img src="${p.images[0].imageUrl}" class="thumbnail"/>
                    </c:if>
                </td>
                <td>
                    <c:if test="${p.images.size() >= 2}">
                        <img src="${p.images[1].imageUrl}" class="thumbnail"/>
                    </c:if>
                </td>

                <td>
                    <form action="Admin" method="post" style="display:inline;">
                        <input type="hidden" name="action" value="edit"/>
                        <input type="hidden" name="id" value="${p.id}"/>
                        <button type="submit" class="action-btn edit-btn">Sửa</button>
                    </form>
                    <form action="Admin" method="post" style="display:inline;">
                        <input type="hidden" name="action" value="delete"/>
                        <input type="hidden" name="id" value="${p.id}"/>
                        <button type="submit" class="action-btn delete-btn">Xóa</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</section>

<script>
    document.getElementById("addKindForm").addEventListener("submit", function (e) {
        e.preventDefault();

        const formData = new FormData(this);
        formData.append("action", "add");

        fetch("KindController", {
            method: "POST",
            body: formData
        })
            .then(res => res.json())
            .then(data => {
                const msg = document.getElementById("kindMessage");
                msg.textContent = data.message;
                msg.className = data.success ? "alert success" : "alert error";

                if (data.success) {
                    this.reset();
                    // 👉 có thể reload bảng kind bằng AJAX sau
                }
            });
    });
    function previewImage(input, previewId) {
        const preview = document.getElementById(previewId);
        if (input.files && input.files[0]) {
            const reader = new FileReader();
            reader.onload = function (e) {
                preview.src = e.target.result; // thay placeholder bằng ảnh thật
            };
            reader.readAsDataURL(input.files[0]);
        } else {
            // Nếu không chọn file, quay về placeholder
            preview.src = "images/placeholder.png";
        }
    }


    function toggleCategoryManager() {
        document.getElementById("categoryManager").style.display = "block";
        document.getElementById("categoryOverlay").style.display = "block";
    }

    function closeCategoryManager() {
        document.getElementById("categoryManager").style.display = "none";
        document.getElementById("categoryOverlay").style.display = "none";
    }
    function toggleKindManager() {
        document.getElementById("kindManager").style.display = "block";
        document.getElementById("kindOverlay").style.display = "block";
    }

    function closeKindManager() {
        document.getElementById("kindManager").style.display = "none";
        document.getElementById("kindOverlay").style.display = "none";
    }
</script>

</body>
</html>
