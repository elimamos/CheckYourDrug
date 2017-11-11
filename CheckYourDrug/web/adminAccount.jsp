<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Account</title>
    </head>
    <body>
       <jsp:include page="/AdminPage" />
        <div style="text-align: center">
        <h3>Add objects to database</h3><br/>
        <form action="AdminPage" method="post">
            <p>Name:</p>
            <input type="text" name="name"/>
            <p>Substance:</p>
            <input type="text" name="substance"/>
            <p>Similar:</p>
            <input type="text" name="similar"/>
            <p>Price:</p>
            <input type="text" name="price"/><br/><br />
            <input type="submit" value="send"/>
        </form>
        
        
        </div>
    </body>
</html>
