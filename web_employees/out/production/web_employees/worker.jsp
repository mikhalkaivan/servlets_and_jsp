<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="main.java.models.Employee" %>
<%@ page import="main.java.models.QueryForHoliday" %><%--
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
        <title>Worker Page</title>
        <link href="/static/css/bootstrap.min.css" type="text/css" rel="stylesheet"/>
        <link href="/static/style.css" type="text/css" rel="stylesheet"/>
    </head>
    <body>
    <%
        Map<String, Object> data = (HashMap<String, Object>)request.getAttribute("data");
        Employee myself = (Employee)data.get("myself");
        QueryForHoliday query = (QueryForHoliday)data.get("query");
        String firstname = myself.getFirstName();
        String lastname = myself.getLastName();
        String password = myself.getPassword();
        String department = myself.getDepartment();
        String position = myself.getPosition();
        String queryStatus = query.getHolidayQuery();
        String responseStatus = query.getManagerResponce();
    %>
    <div class="container">
        <h3 style="text-align: center">Hello <% out.print(firstname);%>  <%out.print(lastname);%></h3>
    </div>

    <div class="container">
        <label><b>My profile:</b></label><br/>
        <% out.println(firstname + " " + lastname);%><br/>
        <% out.println(position + ", " + department + " department");%><br/>
        Query for holiday status: <% out.println(queryStatus); %><br/>
        Manager response status: <% out.println(responseStatus); %>
    </div>

    <div class="container">
            <form action="QueryForHolidayServlet" method="post">
                <input type="hidden" name="first_name" value="<% out.println(firstname);%>">
                <input type="hidden" name="last_name" value="<% out.println(lastname);%>">
                <input type="hidden" name="department" value="<% out.println(department);%>">
                <input type="hidden" name="position" value="<% out.println(position);%>">
                <div class="form-group">
                    <label>Send query for holiday:</label>
                    <input type="submit" class="btn btn-primary" value="Send">
                </div>
            </form>
        </div>
        <script src="/static/js/bootstrap.min.js"></script>
    </body>
</html>
