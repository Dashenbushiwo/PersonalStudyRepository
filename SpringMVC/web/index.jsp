<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>主页</title>
</head>
<body>
    <a href="test/helloWorld">helloWorld</a><br />

    <a href="test/pathVariable/999">pathVariable</a><br />
    <a href="test/requestParam?id=999">requestParam</a><br />
    <form action="test/pojo" method="post">
        id:<input type="number" name="id" value="1"><br />
        name:<input type="text" name="name" value="比尔高特"><br />
        birth:<input type="text" name="birth" value="2023-07-31"><br />
        province:<input type="text" name="address.province" value="上海"><br />
        city:<input type="text" name="address.city" value="北京"><br />
        <input type="submit" value="提交" /><br />
    </form>
    <a href="test/servletApi">servletApi</a><br />
    <a href="test/returnModelAndView">returnModelAndView</a><br />
    <a href="test/mapModel">mapModel</a><br />
    <a href="test/testView">testView</a><br />
    <a href="test/testRedirect">testRedirect</a><br />
    <a href="test/testConvert?name=city">testConvert</a><br />
    <a href="test/testException">testException</a><br />
    <a href="test/testExceptionResponseStatus">testExceptionResponseStatus</a><br />
    <a href="test/testSimpleMappingExceptionResolver">testSimpleMappingExceptionResolver</a><br />
</body>
</html>
