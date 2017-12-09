
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
        <br>
        <div class="section">
            <div class="container">
        <div style="text-align: center; margin-top:20px; width:300px;">  
            <form method="post" class="" action="DatabaseAccess">
                <label class="label">Login:<input class="input" type="text" name="login" /> </label><br />
                <label class="label">Password:<input class="input" type="password" name="password" /> </label><br />
                <input type="submit" class="button is-danger" value="Log in">
            </form>
        </div>
            </div>
        </div>
    </body>
</html>
