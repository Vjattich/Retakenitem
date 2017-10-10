<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Add\Edit</title>
</head>
<body>
<div align="center">
    <h1>Add\Edit</h1>
    <table>
        <%--@elvariable id="user" type="toggle.spring.models.Disc"--%>
        <form:form action="savedisc" method="post" modelAttribute="disc">
            <form:hidden path="id"/>
            <tr>
                <td>Name:</td>
                <td><form:input path="name"/></td>
            </tr>
            <tr>
                <td>Description:</td>
                <td><form:input path="description"/></td>
            </tr>
            <tr>
                &nbsp;&nbsp;&nbsp;&nbsp;
                <td colspan="2" align="center">
                    <input type="submit" value="Save">
                </td>
            </tr>
        </form:form>
    </table>
</div>

</body>
</html>