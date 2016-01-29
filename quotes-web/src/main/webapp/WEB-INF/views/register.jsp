<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="utf-8">
<title>Registration</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/form.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/jquery-validate.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#registerForm').validate({
			rules : {
				password : {
					required : true,
					minlength : 5,
					maxlength : 25
				},
				rep_password : {
					required : true,
					minlength : 5,
					maxlength : 25,
					equalTo : "#password"
				},
				firstname : {
					required : true,
					minlength : 2,
					maxlength : 30
				},
				lastname : {
					required : true,
					minlength : 2,
					maxlength : 50
				},
				email : {
					required : true,
					email : true
				}
			},
			messages : {
				password : {
					required : "Please provide a password",
					minlength : jQuery.validator.format("At least {0} characters required!")
				},
				rep_password : {
					required : "Please provide a password",
					minlength : jQuery.validator.format("At least {0} characters required!"),
					equalTo : "Please enter the same password as above"
				},
				email : {
					required : "Please enter an email address",
					email : "Your email address must be in the format of name@domain.xx"
				},
				firstname : {
					required : "Please enter a first name",
					minlength : jQuery.validator.format("At least {0} characters required!")
				},
				lastname : {
					required : "Please enter a last name",
					minlength : jQuery.validator.format("At least {0} characters required!")
				}
			}
		});
	});
</script>
</head>
<body>
	<div id="register-box" class="container">
	<form class="form" id="registerForm" method="POST" action="${pageContext.request.contextPath}/register">
		<c:if test="${not empty error}"> 
			<div class="errorText" align="center">${error}</div>
		</c:if>
		<table border="0" cellpadding="5" cellspacing="0" width="50%">
			<tr>
				<td width="20%"><font size="2" color="gray">Email
						address:</font></td>
				<td><input id="email" name="email" type="email" maxlength="100"
					style="width: 300px;" required title="User's email" /></td>
			</tr>
			<tr>
				<td width="20%"><font size="2" color="gray">Password:</font></td>
				<td><input id="password" name="password" type="password"
					maxlength="50" style="width: 300px;" required title="User's password" /></td>
			</tr>
			<tr>
				<td width="20%"><font size="2" color="gray">Repeat password:</font></td>
				<td><input id="rep_password" name="rep_password"
					type="password" maxlength="50" style="width: 300px;" required 
					value="${password}"/></td>
			</tr>
			<tr>
				<td width="20%"><font size="2" color="gray">First name:</font></td>
				<td><input id="firstname" name="firstname" type="text"
					maxlength="30" style="width: 300px;" required value="${firstname}" title="First name"/></td>
			</tr>
			<tr>
				<td width="20%"><font size="2" color="gray">Last name:</font></td>
				<td><input id="lastname" name="lastname" type="text"
					maxlength="50" style="width: 300px;" required title="Last name"
					value="${lastname}"/></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input class="submit" type="submit" value="Create" /></td>
			</tr>
		</table>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	</form>
	</div>
</body>
</html>