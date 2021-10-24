<!DOCTYPE html>
<html lang="en">
<head>
  <title>Hello World!</title>
</head>
<body>
<h1>Hello, User</h1>
<form action="/logout" method="post">
  <input type="submit" value="Log out"/>
  <input type="hidden" name="_csrf" value="${_csrf.token}">
</form>
<form action="/hello" method="post" enctype="multipart/form-data">
  <input type="file" name="item_img">
  <input type="hidden" name="_csrf" value="${_csrf.token}">
    <button type="submit">Добавить</button>
</form>
<div>
  <#if itemName??>
    <img src="/static/images/${itemName}">
  </#if>
</div>

</body>
</html>