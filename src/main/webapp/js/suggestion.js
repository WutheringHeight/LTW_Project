document.addEventListener("DOMContentLoaded", function () {
    const contextPath = window.CONTEXT_PATH; // ✅ LẤY TỪ JSP

    const input = document.querySelector(".search-input");
    const box = document.getElementById("search-suggestions");
    const list = document.getElementById("suggestion-list");

    if (!input || !box || !list) {
        console.error("Missing search elements");
        return;
    }

    input.addEventListener("input", function () {
        const keyword = this.value.trim();

        if (keyword.length === 0) {
            box.style.display = "none";
            return;
        }

        fetch(contextPath + "/api/suggestion?q=" + encodeURIComponent(keyword))
            .then(res => res.json())
            .then(data => {
                console.log("SUGGEST DATA:", data); // 🔥 DEBUG

                list.innerHTML = "";

                if (!data || data.length === 0) {
                    box.style.display = "none";
                    return;
                }

                data.forEach(p => {
                    const li = document.createElement("li");
                    const url = contextPath + "/productdetail?id=" + p.id;

                    li.innerHTML = `
                        <a href="${url}">
                            <img src="${contextPath}/${p.thumbnail}" width="40" height="40"/>
                            <span>${p.productName}</span>
                            <small>${p.price}₫</small>
                        </a>
                    `;
                    list.appendChild(li);
                });

                box.style.display = "block";
            })
            .catch(err => {
                console.error("Suggestion error:", err);
                box.style.display = "none";
            });
    });
});
