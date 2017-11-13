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
        <div class="columns">
        <jsp:include page="/AdminPage"/>
        <div class="column" style="text-align: center; margin-top:20px; margin:  auto; width:250px;"> 
        <form  action="AdminPage" method="post">
            <label class="label">Name:
            <input class="input" type="text" name="name"/></label>
            <label class="label">Substace:
            <input class="input" type="text" name="substance"/></label>
            <label class="label">Similar:
            <input class="input" type="text" name="similar"/></label>
            <label class="label">Price:
            <input class="input" type="text" name="price"/></label><br/>
            <input class="button is-primary" type="submit" value="Add to database"/>
        </form>   
        </div>
        </div>
    </body>
</html>
