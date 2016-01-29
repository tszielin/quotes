<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
<title>${provider.name()} exchanges</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/menu.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/jquery.dataTables.min.css" />
<script type="text/javascript"
	src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
<script type="text/javascript"
	src="https://cdn.datatables.net/1.10.10/js/jquery.dataTables.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/scripts/menu.js"></script>
<script type="text/javascript" class="init">
	$(document).ready(function() {
		$('#exchanges').dataTable({
			"order" : [ [ 0, "asc" ] ],
			"pageLength" : 100,
			"lengthMenu": [[10, 25, 50, 100, -1], [10, 25, 50, 100, "All"]]
		});

		$('#exchanges tbody').on('mouseenter', 'td', function() {
			var colIdx = table.cell(this).index().column;

			$(table.cells().nodes()).removeClass('highlight');
			$(table.column(colIdx).nodes()).addClass('highlight');
		});
	});
</script>
</head>
<body>
	<div id="sse1">
		<div id="sses1">
			<ul>
				<c:forEach var="provider" items="${providers}">
					<li><a href="/quotes/${provider.name().toLowerCase()}">${provider.name()}
							quotes</a></li>
				</c:forEach>
				<li><a href="/quotes/login?logout">Logout</a></li>
			</ul>
		</div>
	</div>
	<br/>
	<div class="datagrid" align="center">
		<table>
			<tr>
				<td align="right"><font color="grey">Provider</font></td>
				<td>${provider.description()}</td>
			</tr>
			<tr>
				<td align="right"><font color="grey">Source</font></td>
				<td>EUR</td>
			</tr>
		</table>
	</div>
	<div align="center">
		<table id="exchanges" class="display compact" width="60%" cellspacing="0">
			<thead>
				<tr>
					<th colspan="2">Currency</th>
					<th colspan="2">Quote</th>
				</tr>
				<tr>
					<th>Code</th>
					<th>Description</th>
					<th>Publicated</th>
					<th>Rate</th>
				</tr>
			</thead>
			<tfoot>
				<tr>
					<th>Code</th>
					<th>Description</th>
					<th>Publicated</th>
					<th>Rate</th>
				</tr>
			</tfoot>
			<tbody>
				<c:forEach var="quote" items="${quotes}">
					<fmt:formatDate pattern="yyyy-MM-dd" value="${quote.publicated}" var="strDate"/>
					<tr>
						<td align="center"><a href="/quotes/ecb/${quote.currency.code}">${quote.currency.code}</a></td>
						<td align="left">${quote.currency.description}</td>
						<td align="center"><a href="/quotes/ecb/on/${strDate}">${strDate}</a></td>
						<td align="right"><fmt:formatNumber type="number"
								pattern="#,##0.00000" value="${quote.rate}" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>
