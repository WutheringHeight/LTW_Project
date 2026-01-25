<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Details</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/LoginNSignUp/style.css">
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
            <section><a><button onclick="toggleDisplay('notif-box')"><img src="res/notif_icon.png" class="mini-icon"></button></a>
            <div class="notif-box non-display" id="notif-box">
                <div class="notif-header"><h3>Notification</h3></div>
                <div class="notif">
                    <p><b>Aviary.Co</b></p>
                    <p>Hello ArtEnjoyerGuy723, congratualation for making your account successfully</p>
                </div>
                 <div class="notif">
                    <p><b>System</b></p>
                    <p>Account successfully created.</p>
                </div>
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
            <h2>Payments & Billing</h2>
            <div class="setting-section">  
                <section class="setting-option">
                    <section class="setting-option" id="address-sect">
                        <section class ="info" style="display:flex;">
                            <label>Delivery Address: </label>
                            <p id="address-p">a<c:out value="${payment.deliveryAddress}"/></p>
                            <input type="text" name="deliveryAddress" id="deliveryAddress" class="non-display">
                        </section>  
                    </section>
                    <button class="mini-button btn-with-anim" id="address-change-btn">Change</button>
                    <button class="mini-button btn-with-anim non-display" id="address-save-btn">Save</button>
                </section>
                <section class="setting-option">
                    <section class="setting-option" id="Username-sect">
                        <section class ="info" style="display:flex;">
                            <label>Payment method: </label>
                            <select class="drop-box"><option>Banking</option></select>
                        </section>  
                    </section>
                </section>
                <h3>Banking:</h3>
                <section class="banking-box" id="banking-sect">
                    <img src="res/bank_icon.png" class="mega-icon">
                    <section class="banking-input">
                        <p id="bank-code-p">Bank Code</p>
                        <input id="bank-code" name="bankCode" value="${payment.bankCode}">
                        <p id="card-number-p">Card Number</p>
                        <input id="card-number" name="cardNumber" value="${payment.cardNumber}">
                        <br>
                        <button class="mid-button btn-with-anim non-display" id="banking-save-btn">Save</button>
                    </section>
                </section>
            </div>
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
        const addressSect = document.getElementById('address-sect')
        const addressChangeBtn = document.getElementById('address-change-btn')
        const addressSaveBtn = document.getElementById('address-save-btn')
        const addressP = addressSect.querySelector('p')
        const addressInput = addressSect.querySelector('input')

        const bankingSect = document.getElementById('banking-sect')
        const bankcodeP = document.getElementById('bank-code-p')
        const bankcodeIn = document.getElementById('bank-code')
        const cardNumberP = document.getElementById('card-number-p')
        const cardNumberIn = document.getElementById('card-number')
        const bankingBtn = document.getElementById('banking-save-btn')

        addressChangeBtn.addEventListener("click",() => {
            addressChangeBtn.classList.add("non-display")
            addressSaveBtn.classList.remove('non-display')

            addressP.classList.add('non-display')
            addressInput.classList.remove('non-display')
            addressInput.value = addressP.textContent
        })
        addressSaveBtn.addEventListener("click",() => {
            addressChangeBtn.classList.remove("non-display")
            addressSaveBtn.classList.add('non-display')

            addressP.classList.remove('non-display')
            addressInput.classList.add('non-display')
            addressP.textContent = addressInput.value

            update('paymentInfo_Update','deliveryAddress',addressP.textContent)

            addressInput.value = ''
        })
        bankcodeIn.addEventListener('change',() => {
            bankcodeP.textContent = 'Bank Code (unsaved)'
            bankcodeIn.classList.add('red-outline')
            bankingBtn.classList.remove('non-display')
        })

       cardNumberIn.addEventListener('change',() => {
            cardNumberP.textContent = 'Card Number (unsaved)'
            cardNumberIn.classList.add('red-outline')
            bankingBtn.classList.remove('non-display')
        })

        bankingBtn.addEventListener("click", () => {
            bankcodeP.textContent = 'Bank Code'
            cardNumberP.textContent = 'Card Number'
            bankcodeIn.classList.remove('red-outline')
            cardNumberIn.classList.remove('red-outline')

            update('paymentInfo_Update','bankCode',bankcodeP.textContent)
            update('paymentInfo_Update','cardNumber',cardNumberP.textContent)

            bankingBtn.classList.add('non-display')

        })


    })();
</script>