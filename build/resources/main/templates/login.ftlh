<!DOCTYPE html>
<html>
<head>
    <title>Spring Security Example </title>
</head>
<body>
${message?ifExists}
<form action="/login" method="post">
    <div><label> User Name : <input type="text" name="username"/> </label></div>
    <div><label> Password: <input type="password" name="password"/> </label></div>
    <div><label><input type="checkbox" name="remember-me" id="remember"/>Remember me </label></div>
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <div><input type="submit" value="Sign In"/></div>
</form>
</body>
</html>