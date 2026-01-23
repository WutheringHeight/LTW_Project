<!DOCTYPE html>
<html lang="vi">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Home</title>
    <link rel="stylesheet" href="css/style.css" />
    <script src="/project/UserDetails/script.js"></script>
  </head>
  <body>
    <!-- Header -->
    <header>
      <div class="top-bar">
        <a href="/homepage.html"><img src="/project/UserDetails/res/logo.png" class="large-icon" style="filter: invert(1.0); transform: scale(1.5); padding-top: 2px;"></a>
          <section class="util-icons">
              <section><a href="../CartPage/cart.html"><button><img src="/project/UserDetails/res/purse_icon.png" class="mini-icon"></button></a></section>
              <section><a><button onclick="toggleDisplay('notif-box'); makeNonDisplay('nav-box');"><img src="/project/UserDetails/res/notif_icon.png" class="mini-icon"></button></a>
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
              <section><a><button onclick="toggleDisplay('nav-box'); makeNonDisplay('notif-box');"><img src="/project/UserDetails/res/three_dash_icon.png" class="mini-icon"></button></a>
              <div class="nav-box non-display" id="nav-box">
                <a href="/project/UserDetails/userdetails-account.html"
                  ><button class="nav-option">
                    <img src="/project/UserDetails/res/user_icon.png" class="mid-icon" />
                    <p>Account</p>
                  </button></a
                >
                <a href="/project/UserDetails/userdetails-bookmark.html"
                  ><button class="nav-option">
                    <img src="/project/UserDetails/res/setting_icon.png" class="mid-icon" />
                    <p>Bookmark</p>
                  </button></a
                >
                <a href="/project/UserDetails/userdetails-notification.html"
                  ><button class="nav-option">
                    <img src="/project/UserDetails/res/notif_icon.png" class="mid-icon" />
                    <p>Notification</p>
                  </button></a
                >
                <a href="/project/UserDetails/userdetails-payment.html"
                  ><button class="nav-option">
                    <img src="/project/UserDetails/res/credit_card_icon.png" class="mid-icon" />
                    <p>Payment</p>
                  </button></a
                >
                <a href="/project/UserDetails/userdetails-setting.html"
                  ><button class="nav-option">
                    <img src="/project/UserDetails/res/setting_icon.png" class="mid-icon" />
                    <p>Settings</p>
                  </button></a
                >
                <a href="/project/UserDetails/userdetails-faq.html"
                  ><button class="nav-option">
                    <img src="/project/UserDetails/res/chatbubble_icon.png" class="mid-icon" />
                    <p>FAQs</p>
                  </button></a
                >
              </div>
            </section>

          </section>
      </div>
    </header>
        <!-- Banner -->
      <div class="banner">
        <img class="bannerImg active" src="images/banner2.jpg" alt="Banner"/>
        <img class="bannerImg" src="images/banner3.jpg" alt="Banner" />
        <img class="bannerImg" src="images/banner.jpg" alt="Banner" />
        <div class="logo">
          <img class="img1" src="/project/userDetails/res/logo.png" />
        </div>
        <div class="search-box">
          <input type="text" placeholder="🔍Search" />
        </div>
      </div>
    </div>

    <!-- Main Content -->
    <main>
      <section class="category">
        <h2>Oil Painting</h2>
        <div class="gallery">
          <a class="item" href="/project/productDetail/product.html">
            <img
              src="/project/HomePage/images/monalisa.png"
              alt="Oil Painting"
            />
            <p class="title">Monalisa</p>
            <div class="sell">
              <div class="start" style="color: #ffa41c">
                ★<span style="color: black"> 5.0</span>
              </div>
              <p class="selled" style="color: gray">Đã bán: 35</p>
            </div>
            <p class="price">200,000đ</p>
          </a>
          <a class="item" href="/project/productDetail/product.html">
            <img src="/project/HomePage/images/red.png" alt="Oil Painting" />
            <p class="title">Countryside</p>
            <div class="sell">
              <div class="start" style="color: #ffa41c">
                ★<span style="color: black"> 5.0</span>
              </div>
              <p class="selled" style="color: gray">Đã bán: 25</p>
            </div>
            <p class="price">230,000đ</p>
          </a>
          <a class="item" href="/project/productDetail/product.html">
            <img
              src="/project/HomePage/images/fullstart.png"
              alt="Oil Painting"
            />
            <p class="title">Full Start</p>
            <div class="sell">
              <div class="start" style="color: #ffa41c">
                ★<span style="color: black"> 5.0</span>
              </div>
              <p class="selled" style="color: gray">Đã bán: 5</p>
            </div>
            <p class="price">100,000đ</p>
          </a>
          <a class="item" href="/project/productDetail/product.html">
            <img src="/project/HomePage/images/flower.png" alt="Oil Painting" />
            <p class="title">Flower</p>
            <div class="sell">
              <div class="start" style="color: #ffa41c">
                ★<span style="color: black"> 5.0</span>
              </div>
              <p class="selled" style="color: gray">Đã bán:35</p>
            </div>
            <p class="price">999,000đ</p>
          </a>
        </div>
        <button onclick="window.location.href='/project/homepage/morePage.html'" class="more-btn">More</button>
      </section>
      <section class="category">
        <h2>WaterColor Painting</h2>
        <div class="gallery">
          <a class="item" href="/project/productDetail/product.html">
            <img src="/project/HomePage/images/wc1.jpg" alt="Watercolor" />
            <p class="title">Lemon</p>
            <div class="sell">
              <div class="start" style="color: #ffa41c">
                ★<span style="color: black"> 5.0</span>
              </div>
              <p class="selled" style="color: gray">Đã bán: 57</p>
            </div>
            <p class="price">20,000đ</p>
          </a>
          <a class="item" href="/project/productDetail/product.html">
            <img src="/project/HomePage/images/wc2.jpg" alt="Watercolor" />
            <p class="title">Tree</p>
            <div class="sell">
              <div class="start" style="color: #ffa41c">
                ★<span style="color: black"> 5.0</span>
              </div>
              <p class="selled" style="color: gray">Đã bán: 55</p>
            </div>
            <p class="price">40,000đ</p>
          </a>
          <a class="item" href="/project/productDetail/product.html">
            <img src="/project/HomePage/images/wc3.jpg" alt="Watercolor" />
            <p class="title">Purple</p>
            <div class="sell">
              <div class="start" style="color: #ffa41c">
                ★<span style="color: black"> 5.0</span>
              </div>
              <p class="selled" style="color: gray">Đã bán: 5</p>
            </div>
            <p class="price">99,000đ</p>
          </a>
          <a class="item" href="/project/productDetail/product.html">
            <img src="/project/HomePage/images/wc4.jpg" alt="Watercolor" />
            <p class="title">Artum</p>
            <div class="sell">
              <div class="start" style="color: #ffa41c">
                ★<span style="color: black"> 5.0</span>
              </div>
              <p class="selled" style="color: gray">Đã bán: 65</p>
            </div>
            <p class="price">110,000đ</p>
          </a>
          <a class="item" href="/project/productDetail/product.html">
            <img src="/project/HomePage/images/wc5.jpg" alt="Watercolor" />
            <p class="title">Valley</p>
            <div class="sell">
              <div class="start" style="color: #ffa41c">
                ★<span style="color: black"> 5.0</span>
              </div>
              <p class="selled" style="color: gray">Đã bán: 35</p>
            </div>
            <p class="price">60,000đ</p>
          </a>
          <a class="item" href="/project/productDetail/product.html">
            <img src="/project/HomePage/images/wc6.jpg" alt="Watercolor" />
            <p class="title">Light House</p>
            <div class="sell">
              <div class="start" style="color: #ffa41c">
                ★<span style="color: black"> 5.0</span>
              </div>
              <p class="selled" style="color: gray">Đã bán: 5</p>
            </div>
            <p class="price">999,999đ</p>
          </a>
          <a class="item" href="/project/productDetail/product.html">
            <img src="/project/HomePage/images/wc6.jpg" alt="Watercolor" />
            <p class="title">Light House</p>
            <div class="sell">
              <div class="start" style="color: #ffa41c">
                ★<span style="color: black"> 5.0</span>
              </div>
              <p class="selled" style="color: gray">Đã bán: 5</p>
            </div>
            <p class="price">999,999đ</p>
          </a>
        </div>
        <button onclick="window.location.href='/project/homepage/morePage.html'" class="more-btn">More</button>
      </section>
      <!-- Canvas picture-->
      <section class="category">
        <h2>printed canvas painting</h2>
        <div class="gallery">
          <a class="item" href="/project/productDetail/product.html">
            <img src="/project/HomePage/images/canvas1.png" alt="Canvas" />
            <p class="title">World Map</p>
            <div class="sell">
              <div class="start" style="color: #ffa41c">
                ★<span style="color: black"> 5.0</span>
              </div>
              <p class="selled" style="color: gray">Đã bán: 5</p>
            </div>
            <p class="price">200,000đ</p>
          </a>
        </div>
        <button onclick="window.location.href='/project/homepage/morePage.html'" class="more-btn">More</button>
      </section>
      <!-- chatbox-->
      <section class="chat">
        <button class="chat-btn" onclick="toggleDisplay('help-chatbox')">
          <img class="large-icon" src="/project/UserDetails/res/chatbubble_icon.png" />
        </button>
        <div class="chatbox non-display" id="help-chatbox">
          <section class="chat-contactlist">
            <div class="chat-contact chat-contact-chose">
              <img src="/project/UserDetails/res/user_icon.png" class="mid-icon" />
              <p>Helpful bot</p>
            </div>
            <div class="chat-contact">
              <img src="/project/UserDetails/res/user_icon.png" class="mid-icon" />
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
              <input name="chatbar" />
              <button><img src="/project/UserDetails/res/send_icon.png" class="mid-icon" /></button>
            </div>
          </section>
        </div>
      </section>

    </main>
    <!-- Footer -->
    <footer>
      <div class="footer-container">
        <div class="footer-column">
          <h3>Về chúng tôi</h3>
          <p>
            Lorem ipsum dolor, sit amet consectetur adipisicing elit. Fugiat,
            commodi.
          </p>
        </div>

        <div class="footer-column">
          <h3>Liên hệ</h3>
          <ul>
            <li>Email: <u>webbantrang@gmail.com</u></li>
            <li>Điện thoại: 0999 456 789</li>
            <li>Địa chỉ: TP.HCM</li>
          </ul>
        </div>

        <div class="footer-column">
          <h3>Sản phẩm</h3>
          <ul>
            <li class="link"><a href="#">Oil Painting</a></li>
            <li class="link"><a href="#">WaterColor Painting</a></li>
          </ul>
        </div>
      </div>

      <div class="footer-bottom">
        <p>&copy; 2025 BanTranh</p>
      </div>
    </footer>
    <script>
      const images = document.querySelectorAll('.banner .bannerImg');
      let current = 0;

      function changeBanner() {
      images[current].classList.remove('active');

      current = (current + 1) % images.length;

      images[current].classList.add('active');
      }
      setInterval(changeBanner, 2300);
    </script>
  </body>
</html>
