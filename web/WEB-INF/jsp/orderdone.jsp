<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Заказ на обработке</title>
</head>
<body>
<%@include file="header.jsp" %>
<form action="/orderdone" method="get">

    Заказ на обработке. Подтверждение будет выслано на почту

</form>
<%@include file="footer.jsp" %>
</body>
</html>
