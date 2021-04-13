<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta http-equiv="X-UA-Compatible" content="IE=edge">
            <meta name="viewport"
                content="width=device-width, initial-scale=1.0">
            <title>Ninja Gold</title>
            <link rel="stylesheet" href="css/style.css">
        </head>

        <body>
            <div id="wrapper">
                <header class="page_header">
                    <h1>Ninja Gold</h1>
                </header>
                <main class="main_content">
                    <header class="content_header">
                        <p>Your Gold:</p>
                        <p class="gold_display">
                            <c:out value="${gold}" />
                        </p>
                    </header>
                    <div class="content_box_container">
                        <c:forEach items="${locations}" var="location">
                            <div class="location_box">

                                <form action="/processGold" method="POST">
                                    <h3>
                                        <c:out value="${location.key}"></c:out>
                                    </h3>
                                    <p class="earnings_description">
                                        ( <c:out value="${location.value}">
                                        </c:out> )
                                    </p>
                                    <input type="hidden" name="location.key" value="${location.key}">
                                    <input type="hidden" name="location.value" value="${location.value}">
                                    <button type="submit" name="find_gold_btn">Find Gold</button>
                                </form>

                            </div>
                        </c:forEach>
                    </div>
                    <div class="activities_container">
                        <h4>Activities:</h4>
                        <ul class="activities_list">
                            <c:forEach items="${activities}" var="logItem">
                                <li>
                                    <c:out value="${logItem}"></c:out>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                </main>
                <footer class="page_footer">
                    <a href="/reset"><button>Reset Data</button></a>
                </footer>
            </div>
        </body>

        </html>