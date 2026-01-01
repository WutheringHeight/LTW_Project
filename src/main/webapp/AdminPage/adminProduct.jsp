<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Admin - Product Management</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/adminProductStyle.css"/>
</head>
<body>
<h1>Quản lí sản phẩm</h1>

<!-- Form thêm sản phẩm -->
<section class="add-product-container">
    <h2 class="form-title">Thêm sản phẩm</h2>
    <form action="Admin" method="post" enctype="multipart/form-data" class="product-form">
        <input type="hidden" name="action" value="add"/>

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
            <select name="category" required>
                <option value="">-- Chọn chủ đề --</option>
                <option value="Tranh Hoa">Tranh Hoa</option>
                <option value="Phong Cảnh">Phong Cảnh</option>
                <option value="Động Vật">Động Vật</option>
                <option value="Trừu Trượng">Trừu Trượng</option>
                <option value="Hoạt Hình">Hoạt Hình</option>
                <option value="Phong Thủy">Phong Thủy</option>
                <option value="Bản Đồ">Bản Đồ</option>
                <option value="Tối Giản">Tối Giản</option>
            </select>
        </div>

        <div class="form-group">
            <label>Loại</label>
            <select name="kind" required>
                <option value="">-- Chọn loại --</option>
                <option value="Canvas">Canvas</option>
                <option value="Sơn Dầu">Sơn Dầu</option>
                <option value="Tráng Gương">Tráng Gương</option>
            </select>
        </div>

        <div class="form-group">
            <label>Tồn kho</label>
            <input type="number" name="stock" required/>
        </div>

        <div class="form-group image-upload">
            <label>Upload ảnh sản phẩm</label>
            <input type="file" name="thumbnail" accept="image/*" onchange="previewImage(this, 'preview0')"/>
            <img id="preview0" class="image-preview" src="${pageContext.request.contextPath}/image/placeholder.png" alt="Preview 0"/>
        </div>
        <div class="form-group image-row">
            <div class="image-upload">
                <label>Ảnh hiển thị:</label>
                <input type="file" name="extraImage1" accept="image/*" onchange="previewImage(this, 'preview1')"/>
                <img id="preview1" class="image-preview" src="${pageContext.request.contextPath}/image/placeholder.png" alt="Preview 1"/>
            </div>

            <div class="image-upload">
                <label>Ảnh kích thước:</label>
                <input type="file" name="extraImage2" accept="image/*" onchange="previewImage(this, 'preview2')"/>
                <img id="preview2" class="image-preview" src="${pageContext.request.contextPath}/image/placeholder.png" alt="Preview 2"/>
            </div>
        </div>
        <button type="submit" class="submit-btn">THÊM SẢN PHẨM NGAY</button>
    </form>
</section>


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
</script>

</body>
</html>
