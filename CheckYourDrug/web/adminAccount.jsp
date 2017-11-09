<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        <div style="text-align: center">
        <h1>Uzupelnij baze danych</h1><br/>
        <form action="CheckPassword" method="post">
            <p>Name:</p>
            <input type="text" name="name"/>
            <p>Substance:</p>
            <input type="text" name="substance"/>
            <p>Similar:</p>
            <input type="text" name="similar"/>
            <p>Price:</p>
            <input type="text" name="price"/><br/>
            <input type="submit" value="send"/>
        </form>
        
        
        </div>
    </body>
</html>
