<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Fist run</title>
</head>
<body>
<h3>Hello!</h3>
<#list skins as skin>
    <dif>
        <p>${skin.id}</b> ${skin.price}</p>
    </dif>
    <#else>
    <h3>пусто</h3>
</#list>
</body>
</html>