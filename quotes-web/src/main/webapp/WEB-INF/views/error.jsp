<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isErrorPage="true"%>
<html>
<head>
<title>Error</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/menu.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/menu.js"></script></head>
</head>
<body>
	<div id="sse1">
		<div id="sses1">
			<ul>
				<li><a href="javascript:history.back()">Back</a></li>
			</ul>
		</div>
	</div>
	<table width="100%" border="0">
		<tr valign="top">
			<td width="10%" align="right"><font size="2" color="gray">Error:</font></td>
			<td><font size="2" color="red">${pageContext.exception}</font></td>
		</tr>
		<tr valign="top">
			<td align="right"><font size="2" color="gray">URI:</font></td>
			<td><font size="2">${pageContext.errorData.requestURI}</font></td>
		</tr>
		<tr valign="top">
			<td align="right"><font size="2" color="gray">Status code:</font></td>
			<td><font size="2">${pageContext.errorData.statusCode}</font></td>
		</tr>
		<tr valign="top">
			<td align="right"><font size="2" color="gray">Stack trace:</font></td>
			<td><font size="1">
    			<c:forEach var="trace" items="${pageContext.exception.stackTrace}">
					${trace}<br>
				</c:forEach>
    		</font></td>
		</tr>
	</table>
	<div id="sse1">
		<div id="sses1">
			<ul>
				<li><a href="javascript:history.back()">Back</a></li>
			</ul>
		</div>
	</div>
</body>
</html>
