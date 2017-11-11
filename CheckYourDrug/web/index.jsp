<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CheckingYourDrugs</title>
    </head>
    <body>
        <div class="top" style="text-align: center"><h1>CheckingYourDrugs</h1></div>
        
        <div style="text-align: center;">  
            <form method="post" action="logIn.jsp">
                <input type="submit" value="Log In" style="padding: 5px 60px; background-color: white; border-radius: 8px;">
            </form>           
        </div>
        </br>
        
<jsp:include page="/DatabaseAccess" />

    </body>
</html>
