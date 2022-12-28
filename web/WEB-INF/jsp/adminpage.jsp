<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Admin Page</title>
</head>
<body>

<%@include file="header.jsp" %>

<form action="/adminpage" method="post" >
  <label><br>
    <br>
    Список всех комнат:
    <ul><c:forEach var="room" items="${requestScope.roomlist}">
      <li>
        <a href="/room?id=${room.id}">Описание(номер комнаты, статус): ${room.number} - ${room.status}</a>
      </li>
    </c:forEach>
    </ul>
  </label><br>

</form>

<%@include file="footer.jsp"%>

</body>
</html>