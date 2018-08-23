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
    <title>Sign up</title>
    <link href="/static/css/bootstrap.min.css" type="text/css" rel="stylesheet"/>
    <link href="/static/style.css" type="text/css" rel="stylesheet"/>
</head>
<body>


<div class="container">
    <div class="col-md-6">
            <form method="post" action="SignupServlet">
                <div class="form-group">
                    <label>First name:</label >
                    <input type="text" class="form-control" placeholder="input your first name" name="firstname">
                </div>
                <div class="form-group">
                    <label>Last name:</label>
                    <input type="text" class="form-control" placeholder="input your last name" name="lastname">
                </div>
                <div class="form-group">
                    <label>Password:</label>
                    <input type="password" class="form-control" placeholder="input your password" name="password">
                </div>
                <div class="form-group">
                    <label>Department:</label><br/>
                    <label class="radio-inline"><input type="radio" class="form-control" value="HR" name="department">
                    HR
                    </label>
                    <label class="radio-inline"><input type="radio" class="form-control" value="Support" name="department">
                    Support
                    </label>
                    <label class="radio-inline"><input type="radio" class="form-control" value="Production" name="department">
                    Production
                    </label>
                </div>
                <div class="form-group">
                    <label>Position:</label><br/>
                    <label class="radio-inline"><input type="radio" class="form-control" value="worker" name="position">
                    Worker
                    </label>
                    <label class="radio-inline"><input type="radio" class="form-control" value="manager" name="position">
                    Manager
                    </label>
                    <label class="radio-inline"><input type="radio" class="form-control" value="top-manager" name="position">
                    Top Manager
                    </label>
                </div>
                <div class="form-control">
                    <label>Input your salary:</label>
                    <input type="text"  class="form-control" placeholder="input your salary" name="salary">
                </div>
                <div class="form-control">
                    <label>Submit:</label>
                    <input type="submit" class="btn btn-primary" value="Sign me">
                </div>
            </form>
    </div>
</div>

<script src="/static/js/bootstrap.min.js"></script>

</body>
</html>
