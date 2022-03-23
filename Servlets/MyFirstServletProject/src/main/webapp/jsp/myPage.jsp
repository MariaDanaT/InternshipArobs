<!DOCTYPE html>
<html>
    <head><title>Add two numbers</title></head>
    <body><h1>Add two numbers</h1>
        <form action="./myPage" method = "POST">
        First number <input type = "text" name = "first" ><br>
        Second number <input type = "text" name = "second" ><br>
        <input type="submit" value="Add" ><br><br>
        <br>
        Sum: <input type="text" disabled ="true" name = "sum" value= '<%=request.getAttribute("sum")%>'>
    </form>
</body>
</html>