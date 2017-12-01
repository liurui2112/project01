<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>
		测试数据：
	</h1>
	<ul>
		<c:forEach items="${lists }" var="lts">
			<%-- <li>${lts.proname }</li> --%>
			<c:forEach items="${lts.sbill }" var="lsbill">
				<li>${lts.proname }</li>
				<li>${lsbill.billcode }</li>
			</c:forEach>
		</c:forEach>
	</ul>
</body>
</html>