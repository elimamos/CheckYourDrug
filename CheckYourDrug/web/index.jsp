<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CheckYourDrug</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bulma/0.6.1/css/bulma.min.css">
    </head>
    <body>
        <section class="hero" style="background-color:#e2e6fa ">
        <div style="text-align: right; margin:10px 10px 10px 10px;">
            <form method="post" action="logIn.jsp">
                <input class="button is-white" type="submit" value="Admin Panel">
            </form>           
        </div>
        <div class="hero-body">
          <div class="container">
            <h1 class="title">
              Check Your Drug
            </h1>
            <h2 class="subtitle">
                Distributed computing
            </h2>
          </div>
        </div>
     ` </section>
        <section class="section">
        <jsp:include page="/DatabaseAccess" />
        </section>

    </body>
</html>
