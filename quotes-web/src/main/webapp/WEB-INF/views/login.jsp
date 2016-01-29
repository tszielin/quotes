<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>Login</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/login.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/jquery-validate.js"></script>
<script type="text/javascript">	
	$(document).ready(function() {
		$('#loginForm').validate({
			rules : {
				username : {
					required : true,
					minlength : 5,
					email : true
				},
				password : {
					required : true,
					minlength : 5,
					maxlength : 64
				}
			},
			messages : {
				username : {
					required : "Please enter an user's e-mail",
					minlength : jQuery.validator.format("At least {0} characters required!")
				},
				password : {
					required : "Please enter a password",
					minlength : jQuery.validator.format("At least {0} characters required!")
				}
			}
		});
	});
</script>
</head>
<body>
	<div id="login-box" class="container">
		<c:url var="loginUrl" value="/login" />
		<form class="form" id="loginForm" action="${pageContext.request.contextPath}/login" method="POST">
			<c:if test="${not empty param['error']}"> 
				<div class="errorText" align="center">
					Your login attempt was not successful due to:<br/>
        			<c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>.
        		</div>
			</c:if>
			<c:if test="${not empty msg}">
				<div class="msgText" align="center">${msg}</div>
			</c:if>
			<table border="0" cellpadding="5" cellspacing="0" >
				<tr>
					<td align="right"><font size="2" color="gray">E-mail:</font></td>
					<td><input type='email' name='username' id="username" maxlength="64" 
						title="User's email as a login" required/></td>
				</tr>
				<tr>
					<td align="right"><font size="2" color="gray">Password:</font></td>
					<td><input type='password' name='password' id='password' title="User's password" required/></td>
				</tr>
				<tr>
					<td colspan='2' align="center"><input name="submit"
						type="submit" value="Login" /></td>
				</tr>
				<tr>
					<td colspan='2' align="right"><a
						href="${pageContext.request.contextPath}/register"><font size="1">Create new user account</font></a></td>
				</tr>
			</table>
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		</form>
	</div>
</body>
</html>