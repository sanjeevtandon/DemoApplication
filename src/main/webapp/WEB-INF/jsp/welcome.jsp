<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
<head>
<title>Welcome to Site</title>
<script>
var app = angular.module("myApp", []);
</script>
<style type="text/css">
table {
    border-collapse: collapse;
}

table, th, td {
    border: 1px solid black;
}

tr{
background-color:#b0cdfc;
}
</style>
</head>
<body ng-app="myApp">

	<form action="/submitData" method="post">
		<table>
			<tr>
				<td colspan=3 style='background-color:#083172;color:#ffffff'><b>Demo Application</b></td>
			</tr>
			<c:if test="${not empty errorMsg}">
				<tr>
					<td colspan=3 style="color: red;"><b>${errorMsg}</b></td>
				</tr>
			</c:if>
			<c:if test="${not empty successMsg}">
				<tr>
					<td colspan=3 style="color: green;"><b>${successMsg}</b></td>
				</tr>
			</c:if>
			<tr>
				<td><b>Enter a Number</b></td>
				<td><input name="enterNumber" type="number" required/></td>
				<td><input type="submit" name="save" value="Add Number" /></td>
			</tr>
			<tr>
				<td colspan=2><b>Time taken for the sorting is </b></td>
				<td>${timeTaken}</td>
			</tr>
			<tr>
				<td colspan=2><b>Number of Positions changed for sorting are </b>
				<td>${changedPositions}</td>
			</tr>			
			<tr>
				<td colspan=3><b>Entered list of numbers in sorting order are </b></td>
			</tr>
			<tr ng-repeat="number in ${data}">
				<td colspan=3>{{number}}</td>
			</tr>

		</table>
	</form>
</body>

</html>
