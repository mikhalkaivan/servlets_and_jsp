<%--
  Created by IntelliJ IDEA.
  User: mikhalkaivan
  Date: 25.07.2018
  Time: 20:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Log in</title>
    <link href="/static/css/bootstrap.min.css" type="text/css" rel="stylesheet"/>
    <link href="/static/css/style.css" type="text/css" rel="stylesheet"/>
</head>
<body>
    <br/>
    <br/>
    <div class="container">
        <div class="col-xs-6">
                <form method="post" action="LoginSevlet">
                    <div class="form-group">
                        <label>First name:</label >
                        <input type="text" class="form-control" placeholder="input your first name" name="firstname">
                    </div>
                    <div class="form-group">
                        <label>Last name:</label>
                        <input type="text"  class="form-control" placeholder="input your last name" name="lastname">
                    </div>
                    <div class="form-group">
                        <label>Password:</label>
                        <input type="password"  class="form-control" placeholder="input your password" name="password">
                    </div>
                    <div class="form-group">
                        <label>Submit:</label>
                        <input type="submit" class="btn btn-primary" value="Log in">
                    </div>
                </form>
        </div>
    </div>

    <script src="/static/js/bootstrap.min.js"></script>
</body>
</html>
