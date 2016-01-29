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
	$(document).ready(
			function() {
				$('#exchanges').dataTable(
						{
							"order" : [ [ 0, "desc" ] ],
							"pageLength" : 100,
							"lengthMenu" : [ [ 10, 25, 50, 100, -1 ],
									[ 10, 25, 50, 100, "All" ] ]
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
					<li><a href="${pageContext.request.contextPath}/${provider.name().toLowerCase()}">${provider.name()}
							quotes</a></li>
				</c:forEach>
				<li><a href="${pageContext.request.contextPath}/login?logout">Logout</a></li>
			</ul>
		</div>
	</div>
	<br />
	<c:set value="${quotes.get(0).getClass().getSimpleName()}" var="objName"/>
	<div class="datagrid" align="center">
		<table>
			<tr>
				<td align="right"><font color="grey">Provider</font></td>
				<td>${provider.description()}</td>
			</tr>
			<tr>
				<td align="right"><font color="grey">Source</font></td>
				<c:choose>
					<c:when test="${objName=='NBPQuote'}">
						<td>PLN</td>
					</c:when>
					<c:when test="${objName=='ECBQuote'}">
						<td>EUR</td>
					</c:when>
					<c:otherwise>
						<td>USD</td>
					</c:otherwise>
				</c:choose>
			</tr>
			<tr>
				<td align="right"><font color="grey">Currency</font></td>
				<td>${quotes.get(0).currency.code}</td>
			</tr>
			<tr>
				<td></td>
				<td>${quotes.get(0).currency.description}</td>
			</tr>

		</table>
	</div>
	<div align="center">
		<table id="exchanges" class="display compact" width="60%"
			cellspacing="0">
			<thead>
				<tr>
					<c:if test="${objName=='NBPQuote'}">
						<th colspan="2">Table</th>
						<th colspan="2">Quote</th>
					</c:if>
				</tr>
				<tr>
					<c:choose>
						<c:when test="${objName=='NBPQuote'}">
							<th>Publicated</th>
							<th>Table</th>
							<th>Scalar</th>
							<th>Rate</th>
						</c:when>
						<c:otherwise>
							<th>Publicated</th>
							<th>Rate</th>
						</c:otherwise>
					</c:choose>
				</tr>
			</thead>
			<tfoot>
				<tr>
					<c:choose>
						<c:when test="${objName=='NBPQuote'}">
							<th>Publicated</th>
							<th>Table</th>
							<th>Scalar</th>
							<th>Rate</th>
						</c:when>
						<c:otherwise>
							<th>Publicated</th>
							<th>Rate</th>
						</c:otherwise>
					</c:choose>
				</tr>
			</tfoot>
			<tbody>
				<c:forEach var="quote" items="${quotes}">
					<fmt:formatDate pattern="yyyy-MM-dd" value="${quote.publicated}" var="strDate"/>
					<tr>
						<c:choose>
							<c:when test="${objName=='NBPQuote'}">
								<td align="center"><a href="${pageContext.request.contextPath}/nbp/on/${strDate}">${strDate}</a></td>
								<td align="center">${quote.tableId}</td>
								<td align="right"><fmt:formatNumber type="number"
										pattern="#,##0" value="${quote.scalar}" /></td>
								<td align="right"><fmt:formatNumber type="number"
										pattern="#,##0.00000" value="${quote.rate}" /></td>
							</c:when>
							<c:otherwise>
								<c:choose>
									<c:when test="${objName=='ECBQuote'}">
										<td align="center"><a href="${pageContext.request.contextPath}/ecb/on/${strDate}">${strDate}</a></td>
									</c:when>
									<c:otherwise>
										<td align="center"><a href="${pageContext.request.contextPath}/fed/on/${strDate}">${strDate}</a></td>
									</c:otherwise>
								</c:choose>
								<td align="right"><fmt:formatNumber type="number"
										pattern="#,##0.00000" value="${quote.rate}" /></td>
							</c:otherwise>
						</c:choose>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>