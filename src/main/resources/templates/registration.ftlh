<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
Add new user
${message?ifExists}
<form action="/add" method="post">
    <div><label> User Name : <input type="text" name="username" placeholder="username"/> </label></div>
    <div><label> User Email : <input type="email" placeholder="email" name="email"/> </label></div>
    <div><label> Password: <input type="password" name="password" placeholder="password"/> </label></div>
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <div><input type="submit" value="Sign In"/></div>
</form>
</body>
</html>

