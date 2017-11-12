<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CheckingYourDrugs</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bulma/0.6.1/css/bulma.min.css">
    </head>
    <body>
        <section class="hero" style="background-color:#e2e6fa ">
        <div style="text-align: right; margin:10px 10px 10px 10px;">
            <form method="post" action="logIn.jsp">
                <input class="button is-white" type="submit" value="Panel admina">
            </form>           
        </div>
        <div class="hero-body">
          <div class="container">
            <h1 class="title">
              Check Your Drug
            </h1>
            <h2 class="subtitle">
              Przetwarzanie rozproszone
            </h2>
          </div>
        </div>
     ` </section>
        </br>
        <section class="section">
           <div class="container">
        <jsp:include page="/DatabaseAccess" />
           </div>
        </section>

    </body>
</html>
