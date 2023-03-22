<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Fist run</title>
</head>
<body>
<h3>Hello!</h3>
<#list weapons as weapon>
    <dif>
        <p>${weapon.name}</b> ${weapon.price}</p>
    </dif>
    <#else>
    <h3>пусто</h3>
</#list>
</body>
</html>