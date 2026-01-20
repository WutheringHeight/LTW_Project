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

<c:if test="${not empty sessionScope.msg}">
    <div id="messageModal" class="modalc" style="display:block">
        <div class="modalc-content">
            <p>${sessionScope.msg}</p>
            <button onclick="closeMessage()">OK</button>
        </div>
    </div>
    <c:remove var="msg" scope="session"/>
</c:if>
<section class="add-product-container">
    <form action="Admin" method="post" enctype="multipart/form-data" class="product-form">
        <input type="hidden" name="action" value="add"/>
        <h2 class="form-title">Thêm sản phẩm</h2>

        <div class="form-group">
            <label>Tên sản phẩm</label>
            <input type="text" name="productName" />
        </div>
        <div class="form-group">
            <label>Giá</label>
            <input type="text" id="priceDisplay" placeholder="Nhập giá"
                   oninput="formatPrice(this)" />
            <input type="hidden" name="price" id="priceValue"/>
        </div>
        <div class="form-group">
            <label>Mô tả</label>
            <textarea name="description" ></textarea>
        </div>
        <div class="form-group">
            <label>Chủ đề</label>
            <div style="display:flex; align-items:center; gap:10px;">
                <select name="category" >
                    <c:forEach var="c" items="${categories}">
                        <option value="${c.id}">${c.name}</option>
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
                        onclick="toggleKindManager()">✎
                </button>
            </div>
        </div>

        <div class="form-group">
            <label>Tồn kho</label>
            <input type="number" name="stock" />
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
                <!-- UPDATE -->
                <td>
                    <form action="CategoryController" method="post" enctype="multipart/form-data" class="category-form">
                        <input type="hidden" name="action" value="update"/>
                        <input type="hidden" name="id" value="${c.id}"/>
                        <input type="text" name="categoryName" value="${c.name}" required/>
                        <input type="file" name="pathImage" accept="image/*"/>
                        <button type="submit" class="btn-save">Lưu</button>
                    </form>
                </td>
                <!-- DELETE -->
                <td>
                    <form action="CategoryController" method="post" class="category-form">
                        <input type="hidden" name="action" value="delete"/>
                        <input type="hidden" name="id" value="${c.id}"/>
                        <button type="submit" class="btn-delete"
                                onclick="return confirm('Xóa chủ đề này?')">
                            Xóa
                        </button>
                    </form>
                </td>

                <td>
                    <img src="${pageContext.request.contextPath}/${c.pathImage}" class="thumbnail">
                </td>
            </tr>
        </c:forEach>

        </tbody>
    </table>
    <h4 class="form-title">Thêm chủ đề mới</h4>
    <form action="CategoryController"
          method="post"
          enctype="multipart/form-data"
          class="category-form" >

        <input type="hidden" name="action" value="add"/>

        <div class="form-group">
            <input type="text"
                   name="categoryName"
                   placeholder="Tên chủ đề mới"
                   />
        </div>

        <div class="form-group">
            <input type="file"
                   name="pathImage"
                   accept="image/*"
                   />
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
                    <form action="KindController" method="post" class="category-form">
                        <input type="hidden" name="action" value="update"/>
                        <input type="hidden" name="oldName" value="${k}"/>
                        <input type="text" name="newName" value="${k}" required/>
                        <button type="submit" class="submit-btn">Lưu</button>
                    </form>
                </td>
                <td>
                    <!-- DELETE -->
                    <form action="KindController" method="post" class="category-form">
                        <input type="hidden" name="action" value="delete"/>
                        <input type="hidden" name="name" value="${k}"/>
                        <button type="submit" class="btn-delete "
                                onclick="return confirm('Xóa loại này?')">
                            Xóa
                        </button>
                    </form>
                </td>
            </tr>
        </c:forEach>

        </tbody>
    </table>

    <form action="KindController" method="post" class="category-form">
        <input type="hidden" name="action" value="add">
        <input type="text" name="kindName" placeholder="Tên loại mới" />
        <button type="submit" class="submit-btn">Thêm</button>
    </form>

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
                <td>
                    <c:forEach var="c" items="${categories}">
                        <c:if test="${c.id == p.category_id}">
                            ${c.name}
                        </c:if>
                    </c:forEach>
                </td>
                <td>${p.kind}</td>
                <td>${p.stock}</td>
                <td><img src="${pageContext.request.contextPath}/${p.thumbnail}" class="thumbnail"/></td>
                <td>
                    <c:if test="${not empty p.images and p.images.size() >= 1}">
                        <img src="${pageContext.request.contextPath}/${p.images[0].imageUrl}" class="thumbnail"/>
                    </c:if>
                </td>
                <td>
                    <c:if test="${not empty p.images and p.images.size() >= 2}">
                        <img src="${pageContext.request.contextPath}/${p.images[1].imageUrl}" class="thumbnail"/>
                    </c:if>
                </td>

                <td>
                    <button type="button" class="action-btn edit-btn"
                            onclick="openProductManager(this)" data-id="${p.id}" data-name="${p.productName}"
                            data-price="${p.price}" data-stock="${p.stock}" data-description="${p.description}"
                            data-thumbnail="${p.thumbnail}" data-category="${p.category_id}" data-kind="${p.kind}"
                    >
                        ✎
                    </button>
                    <form action="Admin" method="post" style="display:inline;">
                        <input type="hidden" name="action" value="delete"/>
                        <input type="hidden" name="id" value="${p.id}"/>
                        <button type="submit" class="action-btn delete-btn" onsubmit="return showConfirm('Xóa?')" >Xóa</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <!-- Overlay -->
    <div id="productOverlay" class="overlay" onclick="closeProductManager()"></div>

    <!-- Product manager -->
    <div id="productManager" class="edit-manager modal">
        <h3 class="form-title">Sửa sản phẩm</h3>
        <form action="Admin" method="post" enctype="multipart/form-data" class="product-form" onsubmit="return showConfirm('Lưu thay đổi?')">
            <input type="hidden" name="action" value="edit"/>
            <input type="hidden" id="editProductId" name="id"/>

            <div class="form-group">
                <label>Tên sản phẩm</label>
                <input type="text" id="editProductName" name="productName" required/>
            </div>

            <div class="form-group">
                <label>Giá</label>
                <input type="number" id="editPrice" name="price" required/>
            </div>

            <div class="form-group">
                <label>Mô tả</label>
                <textarea id="editDescription" name="description" required></textarea>
            </div>
            <div class="form-group">
                <label>Chủ đề</label>
                <select id="editCategory" name="category" required>
                    <c:forEach var="c" items="${categories}">
                        <option value="${c.id}">${c.name}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="form-group">
                <label>Loại</label>
                <select id="editKind" name="kind" required>
                    <c:forEach var="k" items="${kinds}">
                        <option value="${k}">${k}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="form-group">
                <label>Kho</label>
                <input type="number" id="editStock" name="stock" required/>
            </div>

            <div class="form-group image-upload">
                <label>Ảnh nền</label>
                <input type="file" name="thumbnail" accept="image/*" onchange="previewImage(this,'previewThumb')"/>
                <img id="previewThumb" class="image-preview"
                     src="${pageContext.request.contextPath}/image/placeholder.png"/>
            </div>

            <div class="form-group image-row">
                <div class="image-upload">
                    <label>Ảnh hiển thị:</label>
                    <input type="file" name="extraImage1" accept="image/*"
                           onchange="previewImage(this,'previewExtra1')"/>
                    <img id="previewExtra1" class="image-preview"
                         src="${pageContext.request.contextPath}/image/placeholder.png"/>
                </div>
                <div class="image-upload">
                    <label>Ảnh kích thước:</label>
                    <input type="file" name="extraImage2" accept="image/*"
                           onchange="previewImage(this,'previewExtra2')"/>
                    <img id="previewExtra2" class="image-preview"
                         src="${pageContext.request.contextPath}/image/placeholder.png"/>
                </div>
            </div>

            <button type="submit" class="btn-save">Lưu</button>
            <button type="button" class="close-btn" onclick="closeProductManager()">✕</button>
        </form>
    </div>

    <!-- Thanh phân trang -->
    <div class="pagination">
        <c:if test="${currentPage > 1}">
            <a href="${pageContext.request.contextPath}/Admin?page=${currentPage - 1}" class="page-btn">« Trước</a>
        </c:if>

        <c:forEach var="i" begin="1" end="${totalPages}">
            <a href="${pageContext.request.contextPath}/Admin?page=${i}"
               class="page-btn ${i == currentPage ? 'active' : ''}">${i}</a>
        </c:forEach>

        <c:if test="${currentPage < totalPages}">
            <a href="${pageContext.request.contextPath}/Admin?page=${currentPage + 1}" class="page-btn">Sau »</a>
        </c:if>
    </div>
</section>

<script>
    function formatPrice(input) {
        // Lấy số, bỏ hết ký tự không phải số
        let raw = input.value.replace(/\D/g, '');

        if (raw === '') {
            document.getElementById('priceValue').value = '';
            input.value = '';
            return;
        }

        // Gán giá trị thật (để submit)
        document.getElementById('priceValue').value = raw;

        // Format hiển thị: 1.000.000
        input.value = Number(raw).toLocaleString('vi-VN');
    }
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
    function showMessage(msg) {
        document.getElementById("messageText").innerText = msg;
        document.getElementById("messageModal").style.display = "block";
    }

    function closeMessage() {
        document.getElementById("messageModal").style.display = "none";
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

    function toggleProductManager() {
        document.getElementById("productManager").style.display = "block";
        document.getElementById("productOverlay").style.display = "block";
    }

    function closeProductManager() {
        document.getElementById("productManager").style.display = "none";
        document.getElementById("productOverlay").style.display = "none";
    }

    function openProductManager(btn) {
        document.getElementById("editProductId").value = btn.dataset.id;
        document.getElementById("editProductName").value = btn.dataset.name;
        document.getElementById("editPrice").value = btn.dataset.price;
        document.getElementById("editStock").value = btn.dataset.stock;
        document.getElementById("editDescription").value = btn.dataset.description;
        document.getElementById("previewThumb").src = btn.dataset.thumbnail;

        // set selected category
        const categorySelect = document.getElementById("editCategory");
        if (categorySelect) categorySelect.value = btn.dataset.category;

        // set selected kind
        const kindSelect = document.getElementById("editKind");
        if (kindSelect) kindSelect.value = btn.dataset.kind;

        document.getElementById("productManager").style.display = "block";
        document.getElementById("productOverlay").style.display = "block";
    }



</script>

</body>
</html>
