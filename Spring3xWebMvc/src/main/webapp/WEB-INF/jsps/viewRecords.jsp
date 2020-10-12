<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Spring 3x web mvc</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/jquery.dataTables.min.css" rel="stylesheet">
<script src="js/jquery-3.5.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/jquery.dataTables.min.js"></script>

<script type="text/javascript">
	$(function() {
		$('#developerTable').DataTable();
	});
</script>

</head>
<body>
	<div class="container">
		<div class="container-fluid">
			<table class="table table-striped table-bordered table-hover"
				id="developerTable">
				<thead>
					<tr>
						<th colspan="2" style="text-align:center;">Developer Detail</th>
					</tr>
					<tr>
						<th>Id</th>
						<th>Name</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${developerList}" var="developer">
						<tr>
							<td>${developer.id}</td>
							<td>${developer.name}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>
