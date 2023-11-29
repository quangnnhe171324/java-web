
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link   rel="stylesheet" href="style.css">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Raleway:wght@200;400;500;600;700&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@fortawesome/fontawesome-free@6.2.1/css/fontawesome.min.css">
        <title>Ecommerce | All products</title>
        <style>
            *{
                margin: 0;
                padding: 0;
                box-sizing: border-box;
            }

            body{
                font-family: 'Raleway', sans-serif;
            }

            .navbar{
                display: flex;
                align-items: center;
                padding: 20px;
            }

            nav{
                flex: 1;
                text-align: right;
            }

            nav ul{
                display: inline-block;
                list-style-type: none;
            }

            nav ul li{
                display: inline-block;
                margin-right: 20px;
            }

            a{
                text-decoration: none;
                color: #555;
            }

            p{
                color: #555;
            }

            .container{
                max-width: 1300px;
                margin: auto;
                padding-left: 25px;
                padding-right: 25px;
            }

            .row{
                display: flex;
                align-items: center;
                flex-wrap: wrap;
                justify-content: space-around;
            }

            .col-2{
                flex-basis: 50%;
                min-width: 300px;
            }


            .col-2 img{
                max-width: 100%;
                padding: 50px 0;
            }

            .col-2 h1{
                font-size: 50px;
                line-height: 60px;
                margin: 25px 0;
            }

            .btn{
                display: inline-block;
                background: #ff523b;
                color: #fff;
                padding: 8px 30px;
                margin: 30px 0;
                border-radius: 30px;
                transition: background 0.5s;
            }

            .btn:hover{
                background: #563434;
            }

            .header{
                background: radial-gradient(#fff,#ffd6d6);
            }

            .header .row{
                margin-top: 70px;
            }

            .categories{
                margin: 70px 0;
            }

            .col-3{
                flex-basis: 30%;
                min-width: 250px;
                margin-bottom: 30px;
            }

            .col-3 img{
                width: 100%;
            }

            .col-3 h3{
                color: #ff523b;
                text-align: center;
            }

            .small-container{
                max-width: 1080px;
                margin: auto;
                padding-left: 25px;
                padding-right: 25px;
            }

            .col-4{
                flex-basis: 25px;
                padding: 10px;
                min-width: 200px;
                margin-bottom: 50px;
                transition: transform 0.5s;
            }

            .col-4 img{
                width: 100%;
            }

            .title{
                text-align: center;
                margin: 0 auto 80px;
                position: relative;
                line-height: 60px;
                color: #555;
            }

            .title::after{
                content: '';
                background: #ff523b;
                width: 80px;
                height: 5px;
                border-radius: 5px;
                position: absolute;
                bottom: 0;
                left: 50%;
                transform: translateX(-50%);
            }

            h4{
                color: #555;
                font-weight: normal;
            }

            .col-4 p{
                font-size: 14px;

            }

            .col-4:hover{
                transform: translateY(-5px);
            }

            /*-------footer-----*/

            .footer{
                background: #000;
                color: #8a8a8a;
                font-size: 14px;
                padding: 60px 0 20px;
            }

            .footer p{
                color: #8a8a8a;
            }

            .footer h3{
                color: #fff;
                margin-bottom: 20px;
            }

            .footer-col-1, .footer-col-2, .footer-col-3, .footer-col-4{
                min-width: 250px;
                margin-bottom: 20px;
            }

            .footer-col-1{
                flex-basis: 30%;
            }

            .footer-col-2{
                flex: 1;
                text-align: center;
            }

            .footer-col-2 img{
                width: 180px;
                margin-bottom: 20px;
            }

            .footer-col-3, .footer-col-4{
                flex-basis: 12%;
                text-align: center;
            }

            ul{
                list-style-type: none;
            }

            .app-logo{
                margin-top: 20px;
            }

            .app-logo img{
                width: 140px;
            }

            .footer hr{
                border: none;
                background: #b5b5b5;
                height: 1px;
                margin: 20px 0;
            }

            .copyright{
                text-align: center;
            }

            /*-------all products------*/
            .row-2{
                justify-content: space-between;
                margin: 100px auto 50px;
            }

            select{
                border: 1px solid #ff523b;
                padding: 5px;
            }

            select:focus{
                outline: none;
            }

            .page-btn{
                margin: 0 auto 80px;
            }

            .col-4 h4{
                width: 100%;
                white-space: nowrap;
                overflow: hidden;
                text-overflow: ellipsis;
            }

            .page-btn a{
                display: inline-block;
                border: 1px solid #ff523b;
                margin: 10px;
                width: 40px;
                height: 40px;
                text-align: center;
                line-height: 40px;
                cursor: pointer;
            }

            .page-btn a.active {
                background: #ff523b;
                color:white;
            }

            .page-btn a:hover:not(.active){
                background: #ff523b;
                color: #fff;
            }

            /*single product details*/

            .single-product{
                margin-top: 80px;
            }

            .single-product .col-2 img{
                padding: 0;
                margin-left: 30px;

            }

            .single-product .col-2{
                padding: 20px;

            }

            .single-product h4{
                margin: 20px 0;
                font-size: 22px;
                font-weight: bold;
            }

            .single-product select{
                display: block;
                padding: 10px;
                margin-top: 20px;
            }

            .single-product input{
                width: 50px;
                height: 40px;
                padding-left: 10px;
                font-size: 20px;
                margin-right: 10px;
                border: 1px solid #ff523b;
            }

            input:focus{
                outline: none;
            }

            .small-img-row{
                display: flex;
                justify-content: space-between;
            }

            .small-img-col{
                flex-basis: 24%;
                cursor: pointer;
            }

            /*-----cart item details----*/

            .cart-page{
                margin: 80px auto;
            }

            table{
                width: 100%;
                border-collapse: collapse;
            }

            .cart-info{
                display: flex;
                flex-wrap: wrap;
            }

            th{
                text-align: left;
                padding: 5px;
                color: #fff;
                background: #ff523b;
                font-weight: normal;
            }

            td{
                padding: 10px 5px;
            }

            td input{
                width: 40px;
                height: 30px;
                padding: 5px;
            }

            td a{
                color: #ff523b;
                font-size: 12px;
            }

            td img{
                width: 80px;
                height: 80px;
                margin-right: 10px;
            }

            .total-price{
                display: flex;
                justify-content: flex-end;
            }
            .total-price table{
                border-top: 3px solid #ff523b;
                width: 100%;
                max-width: 400px;
            }

            td:last-child{
                text-align: right;
            }

            th:last-child{
                text-align: right;
            }

            /*---account page---*/

            .account-page{
                padding: 50px 0;
                background: radial-gradient(#fff,#ffd6d6);
            }

            .form-container{
                background: #fff;
                width: 300px;
                height: 400px;
                position: relative;
                text-align: center;
                padding: 20px 0;
                margin: auto;
                box-shadow: 0 0 20px 0px rgba(0, 0, 0, 0.1);
                overflow: hidden;
            }

            .form-container span{
                font-weight: bold;
                padding: 0 10px;
                color: #555;
                cursor: pointer;
                width: 100px;
                display: inline-block;
            }

            .form-btn{
                display: inline-block;
            }

            .form-container form{
                max-width: 300px;
                padding: 0 20px;
                position: absolute;
                top: 130px;
                transition: transform 1s;
            }

            form input{
                width: 100%;
                height: 30px;
                margin: 10px 0;
                border: 1px solid #ccc;
                border-radius: 1cm;
            }

            form .btn{
                width: 100%;
                border: none;
                cursor: pointer;
                margin: 10px 0;
            }

            form .btn:focus{
                outline: none;
            }

            #LoginForm{
                left: -300px;
            }

            #RegForm{
                left: 0;
            }

            form a{
                font-size: 12px;
            }

            #Indicator{
                width: 100px;
                border: none;
                background: #ff523b;
                height: 3px;
                margin-top: 8px;
                transform: translateX(100px);
                transition: transform 1s;
            }

            /*---search bar---*/
            .search-container {
                display: flex;
                justify-content: center;
                align-items: center;
                margin-bottom: 10px;
            }
            .search-box {
                position: relative;
                width: 300px;
                height: 40px;
                border-radius: 20px;
                background-color: #fff;
                box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
            }
            .search-box input {
                position: absolute;
                margin: 0 0;
                top: 0;
                left: 0;
                width: 100%;
                height: 100%;
                border: none;
                border-radius: 20px;
                padding: 0 20px;
                font-size: 16px;
                color: #333;
                background-color: transparent;
                box-sizing: border-box;
            }
            .search-box button {
                position: absolute;
                top: 0;
                right: 0;
                width: 60px;
                height: 100%;
                border: none;
                background-color: #ff523b;
                color: #fff;
                border-radius: 0 20px 20px 0;
                cursor: pointer;
                transition: background-color 0.3s ease;
                box-sizing: border-box;
            }
            .search-box button:hover {
                background-color: #555;
            }


            @media only screen and (max-width: 600px){
                .cart-info p{
                    display: none;
                }
            }
        </style>
    </head>
    <body>
        <div class="container">
            <%@include file="menu.jsp"%>

        </div>
        <div class="small-container">

            <div class="row row-2"> 
                <h2>All Products</h2>

                <form id="frm" method="post" action="product">
                    <div class="search-container">
                        <div class="search-box">
                            <input type="text" placeholder="Search..." name="search" value="${search}">
                            <button type="submit">&#128270;</button>
                        </div>
                    </div>

                    <select name="cate" onchange="change()">
                        <option value="0">All Category</option>
                        <c:forEach items="${data2}" var="item2">
                            <option value="${item2.getCid()}" ${cate == item2.getCid() ? 'selected' : ''}>${item2.getCname()}</option>
                        </c:forEach>
                    </select>

                    <select name="option" onchange="change()">
                        <option value="0" ${option == '0' ? 'selected' : ''}>Default Sorting</option>
                        <option value="1" ${option == '1' ? 'selected' : ''}>Price: Low to High</option>
                        <option value="2" ${option == '2' ? 'selected' : ''}>Price: High to Low</option>
                    </select>
                </form>
            </div>

            <c:forEach items="${data}" var="i" varStatus="loop">
                <c:if test="${loop.count % 4 == 1}">
                    <div class="row">
                    </c:if>
                    <div class="col-4">
                        <a href='pdetail?id=${i.getId()}'><img src="${i.getMainImg()}"></a>
                        <a href='pdetail?id=${i.getId()}'><h4>${i.getName()}</h4></a>
                        <p>${i.getPrice()}</p>

                        <form method="get" action="atc">
                            <input type="hidden" name="pid" value="${i.getId()}">
                            <input type="hidden" name="quantity" value="1"> <!-- You can adjust the quantity as needed -->
                            <input type="submit" class="btn" value="Add to cart">
                        </form>

                    </div>
                    <c:if test="${loop.count % 4 == 0}">
                    </div>
                </c:if>

            </c:forEach>



            <c:set var="page" value="${page}"/>
            <div class="page-btn">
                <c:forEach begin="${1}" end="${num}" var="i">
                    <a class="${i==page?"active":""}" href="product?page=${i}&option=${option}&cate=${cate}&search=${search}">${i}</a>
                </c:forEach>
            </div>

        </div>

        <%@include file="footer.jsp"%>

        <script>
            function change() {
                document.getElementById("frm").submit();
            }
        </script>
    </body>
</html>


