<%@ page import="main.java.models.QueryForHoliday" %>
<%@ page import="main.java.models.Employee" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: mikhalkaivan
  Date: 25.07.2018
  Time: 21:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Manager Page</title>
    <link href="/static/css/bootstrap.min.css" type="text/css" rel="stylesheet"/>
    <link href="/static/style.css" type="text/css" rel="stylesheet"/>
</head>
<body>
<%
    Map<String, Object> data = (HashMap<String, Object>) request.getAttribute("data");
    Employee myself = (Employee) data.get("myself");
    QueryForHoliday query = (QueryForHoliday) data.get("query");
    String firstname = myself.getFirstName();
    String lastname = myself.getLastName();
    String password = myself.getPassword();
    String department = myself.getDepartment();
    String position = myself.getPosition();
    List<Employee> employees = (ArrayList<Employee>) data.get("listEmployees");
%>

<div class="container">
    <h3 style="text-align: center">Hello <% out.print(firstname);%>  <%out.print(lastname);%></h3>
</div>

<div class="container">
    <label><b>My profile:</b></label><br/>
    <% out.println(firstname + " " + lastname);%><br/>
    <% out.println(position + ", " + department + " department");%><br/>
</div>

<div class="container">
    <div class="col-md-8">
        <form method="post" action="ManagerResponseServlet">
            <table class="table">
                <thead>
                <tr>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Position</th>
                    <th>Salary</th>
                    <th>Request for holiday</th>
                </tr>
                </thead>
                <tbody>
                <% for (Employee empl : employees) {
                    out.println("<tr><td>" + empl.getFirstName() + "</td>" +
                            "<td>" + empl.getLastName() + "</td>" +
                            "<td>" + empl.getPosition() + "</td>" +
                            "<td>" + empl.getSalary() + "</td>" +
                            "<td>" + empl.getQueryStatus() + "</td>" +
                            "<td><input type='hidden' name='id' value='" + empl.getId() + "'></td>");
                    if (!empl.getQueryStatus().equals("DEFAULT")) {
                        out.println("<td>Accept<input type='radio' name='query_response' value='accept'></td>" +
                                "<td>Decline<input type='radio' name='query_response' value='decline'></td>");
                    }
                    out.println("</tr>");
                }%>
                </tbody>
            </table>
            <input type="submit" class="btn btn-primary" value="Submit">
        </form>
    </div>

</div>
<script src="/static/js/bootstrap.min.js"></script>
</body>
</html>
