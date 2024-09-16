<%--
  Created by IntelliJ IDEA.
  User: nhoclahola
  Date: 9/16/2024
  Time: 2:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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


        input {
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
    </style>
</head>
<body>
<div class="main">
    <h1>Register</h1>
    <form action="login" method="POST">
        <label for="username">
            Username:
        </label>
        <input type="text"
               id="username"
               name="username"
               placeholder="Enter your username" required>

        <label for="fullName">
            Full name:
        </label>
        <input type="text"
               id="fullName"
               name="fullName"
               placeholder="Enter your full name" required>

        <label for="email">
            Email:
        </label>
        <input type="text"
               id="email"
               name="email"
               placeholder="Enter your email" required>

        <label for="phone">
            Phone number:
        </label>
        <input type="text"
               id="phone"
               name="phone"
               placeholder="Enter your phone number" required>

        <label for="password">
            Password:
        </label>
        <input type="password"
               id="password"
               name="password"
               placeholder="Enter your password" required>

        <label for="re-password">
            Password:
        </label>
        <input type="password"
               id="re-password"
               name="re-password"
               placeholder="Re-enter your password" required>

        <div class="wrap">
            <button type="submit"
                    onclick="solve()">
                Register
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
    <p>Already have an account?
        <a href="login"
           style="text-decoration: none;">
            Login
        </a>
    </p>
</div>
</body>
</html>
