<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="td" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Home</title>
</head>
<body>
<div align="right">
    <h2><a href="/users">All Users</a></h2>
</div>
<div align="center">
    <h2>My discs</h2>
    <c:choose>
        <c:when test="${session.discs.size() == 0}">
            <table border="1" width="400">
                <th>ID</th>
                <th>Name</th>
                <th>Discs</th>
                <tr>
                    <td>${session.id}</td>
                    <td>${session.username}</td>
                    <td align="center"><h1>Empty</h1></td>
                </tr>
            </table>
        </c:when>
        <c:otherwise>
            <table border="1" width="400">
                <th>ID</th>
                <th>Name</th>
                <th>Discs</th>
                <tr>
                    <td>${session.id}</td>
                    <td>${session.username}</td>
                    <td>
                        <table width="400">
                            <th>ID</th>
                            <th>Name</th>
                            <th>Description</th>
                            <th>Actions</th>
                            <td:forEach var="disc" items="${session.discs}">
                                <tr>
                                    <td align="center"> ${disc.id} </td>
                                    <td align="center"> ${disc.name} </td>
                                    <td align="center"> ${disc.description} </td>
                                    <td align="center">
                                        <a href="/discstrn?disc_id=${disc.id}">Give back</a>
                                    </td>
                                </tr>
                            </td:forEach>
                        </table>
                    </td>
                </tr>
            </table>
        </c:otherwise>
    </c:choose>

</div>
<div align="center">
    <h1>Disc List</h1>
    <h2><a href="/new">New Disc</a></h2>
    <c:choose>
        <c:when test="${discs.size() == 0}">
            <table border="1" width="300">
                <th><h2>Message</h2></th>
                <tr>
                    <td align="center"><h1>All discs are taken</h1></td>
                </tr>
            </table>
        </c:when>
        <c:otherwise>
            <table border="1" width="500">
                <th>No</th>
                <th>Name</th>
                <th>Description</th>
                <th>Actions</th>

                <c:forEach var="disc" items="${discs}" varStatus="status">
                    <tr>
                        <td>${status.index + 1}</td>
                        <td>${disc.name}</td>
                        <td>${disc.description}</td>
                        <td align="center">
                            <a href="/new?disc_id=${disc.id}">Edit</a>
                            &nbsp;&nbsp;&nbsp;&nbsp;
                            <a href="/discstrn?disc_id=${disc.id}">Take disc</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>