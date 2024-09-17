<%--
  Created by IntelliJ IDEA.
  User: nhoclahola
  Date: 9/16/2024
  Time: 3:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="core" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>

<html>
<head>
    <title>HTML Login Form</title>
    <link rel="stylesheet" href="style.css">
    <style>
        body {
            display: flex;
            align-items: center;
            justify-content: center;
            font-family: sans-serif;
            line-height: 1.5;
            min-height: 100vh;
            background: #f3f3f3;
            flex-direction: column;
            margin: 0;
        }

        .main {
            background-color: #fff;
            border-radius: 15px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.2);
            padding: 10px 20px;
            transition: transform 0.2s;
            width: 500px;
            text-align: center;
        }

        h1 {
            color: blue;
            font-family: "Century Gothic", ui-monospace;
        }

        label {
            display: block;
            width: 100%;
            margin-top: 10px;
            margin-bottom: 5px;
            text-align: left;
            color: #555;
            font-weight: bold;
        }


        .input-a {
            display: block;
            width: 100%;
            margin-bottom: 15px;
            padding: 10px;
            box-sizing: border-box;
            border: 1px solid #ddd;
            border-radius: 5px;
        }

        button {
            padding: 15px;
            border-radius: 10px;
            margin-top: 15px;
            margin-bottom: 15px;
            border: none;
            color: white;
            cursor: pointer;
            background-color: blue;
            width: 100%;
            font-size: 16px;
            transition: background-color 0.3s, transform 0.3s;
        }

        button:hover {
            background-color: darkblue; 
            transform: scale(1.05);
        }

        .wrap {
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .remember-me {
            display: flex;
            align-items: center;
            justify-content: flex-start;
        }

        .remember-me label {
            font-size: 12px;
        }

        .remember-me input {
            margin-right: 10px;
        }
    </style>
</head>
<body>
<div class="main">
    <h1>LOGIN</h1>
    <h3>Enter your login credentials</h3>
    <form action="login" method="POST">
        <label for="username">
            Username:
        </label>
        <input class="input-a" type="text"
               id="username"
               name="username"
               placeholder="Enter your username" required>

        <label for="password">
            Password:
        </label>
        <input class="input-a" type="password"
               id="password"
               name="password"
               placeholder="Enter your password" required>

        <div class="remember-me">
            <input type="checkbox" id="remember" name="remember">
            <label for="remember">Remember me</label>
        </div>


        <div class="wrap">
            <button type="submit"
                    onclick="solve()">
                Login
            </button>
        </div>
    </form>
    <h5 style="color: red">
        <%
            Object alert = request.getAttribute("alert");
            if (alert != null) {
                out.print(alert);
            }
        %>
    </h5>
    <p>Not registered?
        <a href="register"
           style="text-decoration: none;">
            Create an account
        </a>
    </p>
</div>
</body>
</html>
