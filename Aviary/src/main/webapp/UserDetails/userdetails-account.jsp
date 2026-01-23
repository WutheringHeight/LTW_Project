<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Details</title>
    <link rel="stylesheet" href="style.css">
    <script src="script.js"></script>
</head>
<body>
    <div class="top-bar">
        <a href="../HomePage/homepage.html"><img src="res/logo.png" class="large-icon" style="filter: invert(1.0); transform: scale(1.5); padding-top: 2px;"></a>
        <div class="search-bar">
            <img src="res/search_icon.png" class="large-icon">
            <input alt="Search">
        </div>
        <section class="util-icons">
           <section><a href="../CartPage/index.html"><button><img src="res/purse_icon.png" class="mini-icon"></button></a></section>
            <section><a><button onclick="toggleDisplay('notif-box'); makeNonDisplay('nav-box');"><img src="res/notif_icon.png" class="mini-icon"></button></a>
            <div class="notif-box non-display" id="notif-box">
                <div class="notif-header"><h3>Notification</h3></div>
                <c:forEach items="${notifications}" var="notif">
                    <div class="notif">
                        <p><b>${notif.accountName}</b></p>
                        <% String message = notif.message;
                            int maxMsgLen = 50;
                            if(message.length() > maxMsgLen){
                                message = message.subString(0,maxMsgLen);
                            }
                        %>
                        <p> <%= message %> </p>
                    </div>
                </c:forEach>
            </div></section>
            <section><a><button onclick="toggleDisplay('nav-box'); makeNonDisplay('notif-box');"><img src="res/three_dash_icon.png" class="mini-icon"></button></a>
            <div class="nav-box non-display" id="nav-box">
                    <a href="userdetails_account"><button class="nav-option"><img src="res/user_icon.png" class="mid-icon"><p>Account</p></button></a>
                    <a href="userdetails_bookmark"><button class="nav-option"><img src="res/setting_icon.png" class="mid-icon"><p>Bookmark</p></button></a>
                    <a href="userdetails_notification"><button class="nav-option"><img src="res/notif_icon.png" class="mid-icon"><p>Notification</p></button></a>
                    <a href="userdetails_payment"><button class="nav-option"><img src="res/credit_card_icon.png" class="mid-icon"><p>Payment</p></button></a>
                    <a href="userdetails_setting"><button class="nav-option"><img src="res/setting_icon.png" class="mid-icon"><p>Settings</p></button></a>
                    <a href="userdetails_faq"><button class="nav-option"><img src="res/chatbubble_icon.png" class="mid-icon"><p>FAQs</p></button></a>
            </div></section>
        </section>
    </div>
    <section class="userdetails-box">
        <div class="userdetails-options">
            <a href="userdetails_account"><button class="userdetails-option" style="border-top-left-radius: 20px;"><img src="res/user_icon.png" class="large-icon"><p>Account</p></button></a>
            <a href="userdetails_bookmark"><button class="userdetails-option"><img src="res/setting_icon.png" class="large-icon"><p>Bookmark</p></button></a>
            <a href="userdetails_notification"><button class="userdetails-option"><img src="res/notif_icon.png" class="large-icon"><p>Notification</p></button></a>
            <a href="userdetails_payment"><button class="userdetails-option"><img src="res/credit_card_icon.png" class="large-icon"><p>Payment</p></button></a>
            <a href="userdetails_setting"><button class="userdetails-option"><img src="res/setting_icon.png" class="large-icon"><p>Settings</p></button></a>
            <a href="userdetails_faq"><button class="userdetails-option"><img src="res/chatbubble_icon.png" class="large-icon"><p>FAQs</p></button></a>
        </div>
        <section class="userdetails-content"> 
            <br>
            <section style="display: flex; flex-direction:row; align-items: center; padding-bottom:20px;">
                <p class="large-text"><b>Profile picture:</b></p>
                <img src="res/user_icon.png" style="border-radius:50px; border: 2px gray solid; max-width:80px; min-width:80px; margin-left: 20px;">
                <button class="mini-button btn-with-anim" style="margin-left: 20px; margin-top: 45px;">Change</button>
            </section>
            
            <hr>
            <h2>Account</h2>
            
            <div class="setting-section">
                <section class="setting-option" id="Email-sect">
                        <section class ="info" style="display:flex;">
                            <label>Email: </label>
                            <p>a <c:out value="${user.email}"/></p>
                        </section>  
                </section>
                <form action="userAccount_Update" method="post" id="userdetail-form">
                    <section class="setting-option" id="Username-sect">
                        <section class ="info" style="display:flex;">
                            <label>Username: </label>
                            <p>a<c:out value="${userDetail.fullname}"/></p>
                            <input type="text" name="username" id="username" class="non-display">
                        </section>  
                    </section>
                    <section class="setting-option" id="PhoneNumber-sect">
                        <section class ="info" style="display:flex;">
                            <label>PhoneNumber: </label>
                            <p>a <c:out value="${userDetail.phoneNumber}"/></p>
                            <input type="text" name="phoneNumber" id="phoneNumber" class="non-display">
                        </section>  
                    </section>
                    <section class="setting-option" id="TFA-sect">
                        <section class ="info" style="display:flex;">
                            <label>Enable Two-Factor Authentication: </label>
                            <% if(userdetail.twoFactorEnabled){ %>
                                <p> Enabled</p>
                                <input checked type="checkbox" class="toggle-button" name="TFA" id="TFA" class="non-display"> 
                            <% }else{ %>
                                <p> Disabled</p>
                                <input type="checkbox" class="toggle-button" name="TFA" id="TFA" class="non-display">
                            <% } >
                        </section> 
                    </section>
                </form>
            </div>
            <button class="mini-button btn-with-anim" id="change-btn">Change</button>
            <button class="mini-button btn-with-anim non-display" id="cancel-btn">Cancel</button>
            <button class="mini-button btn-with-anim non-display" id="save-btn">Save</button>
            <hr><br><br><br><br>
            <button class="mini-button btn-with-anim" style="margin-left: auto; margin-right:0px;">Log out</button>
        </section>
    </section>
    <div class="company-infos">
        <section class="company-info">
            <section style="display: flex; flex-direction:row; align-items: center;"><img src="res/logo.png" class="small-logo"><h3>Aviary & Co.</h3></section>
            <p>hotline: +84 66298525</p>
            <p>Zalo</p>
            <p>Telegram</p>
        </section>
        <section class="company-info">
            <h3>Company</h3>
            <p>Mission</p>
            <p>Team</p>
            <p>Team</p>
            <p>Contact</p>
        </section>
        <section class="company-info">
            <h3>Resources</h3>
            <p>Blog</p>
            <p>Artist Guide</p>
            <p>FAQs</p>
            <p>Cookie Settings</p>
        </section>
        <section class="company-info">
            <h3>Legal</h3>
            <p>Terms & Conditions</p>
            <p>Privacy</p>
            <p>Cookie Policy</p>
        </section>
    </div>
    <section class="chat">
        <button class="chat-btn" onclick="toggleDisplay('help-chatbox')"><img class="large-icon" src="res/chatbubble_icon.png"></button>
        <div class="chatbox non-display" id="help-chatbox">
            <section class="chat-contactlist">
                <div class="chat-contact chat-contact-chose">
                    <img src="res/user_icon.png" class="mid-icon">
                    <p>Helpful bot</p>
                </div>
                <div class="chat-contact">
                    <img src="res/user_icon.png" class="mid-icon">
                    <p>Admin John</p>
                </div>
            </section>
            <section class="chat-area">
                <div class="chat-area-header">
                <p>Customer Service</p>
            </div>
            <section class="chat-area-messages">
                <section class="chat-area-message">
                    <p>Helpful bot</p>
                    <div>
                        <p>Hello, how can I help you today?</p>
                    </div>
                </section>
            </section>
            <div class="chat-area-footer">
                <input name="chatbar">
                <button><img src="res/send_icon.png" class="mid-icon"></button>
            </div>
            </section>
        </div>
    </section>
</body>
</html>

<script>
    (function(){
        console.log("ran")
        const changeBtn = document.getElementById("change-btn")
        const cancelBtn = document.getElementById("cancel-btn")
        const saveBtn = document.getElementById("save-btn")

        const form = document.getElementById("userdetail-form")

        changeBtn.addEventListener("click",() => {
            changeBtn.classList.add("non-display")
            cancelBtn.classList.remove("non-display")
            saveBtn.classList.remove("non-display")

            form.querySelectorAll("p").forEach( (e, i, arr) => {
                e.classList.add("non-display")
            })
            form.querySelectorAll("input").forEach( (e, i, arr) => {
                e.classList.remove("non-display")
            })
        })

        cancelBtn.addEventListener("click", () => {
            changeBtn.classList.remove("non-display")
            cancelBtn.classList.add("non-display")
            saveBtn.classList.add("non-display")

            form.querySelectorAll("p").forEach( (e, i, arr) => {
                e.classList.remove("non-display")
            })
            form.querySelectorAll("input").forEach( (e, i, arr) => {
                if(e.type == "text"){
                    e.value = ""
                }else{
                    e.checked = false
                }
                e.classList.add("non-display")
            })
        })

        saveBtn.addEventListener("click", () => {
            form.submit() 
            form.querySelectorAll("input").forEach( (e, i, arr) => {
                if(e.type == "text"){
                    e.value = ""
                }else{
                    e.checked = false
                }
                e.classList.add("non-display")
            })   
        })
    })();
</script>