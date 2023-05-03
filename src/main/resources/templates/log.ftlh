<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Log</title>
</head>
<body>
<h3>This log</h3>
<#list linesLog as lineLog>
    <dif>
        <p>${lineLog}</p>
    </dif>
    <#else>
    <h3>пусто</h3>
</#list>
</body>
</html>