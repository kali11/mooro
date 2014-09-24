<#macro page>
  <!DOCTYPE html>
  <html>
  <head>
    <meta charset="UTF-8">
    <link href="resources/css/main.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" href="webjars/bootstrap/3.2.0/css/bootstrap.min.css" />
    <script src="webjars/jquery/2.1.1/jquery.min.js"></script>
    <title>LMS</title>
  </head>
  <body>
    <div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">Platforma E-learningowa</a>
          <#if username??>
            ${username}
            <form method="POST" action="logout">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                <button type="submit" class="btn btn-success">Wyloguj</button>
            </form>
          </#if>
        </div>
        
        
      </div>
    </div>
    

 
    <#-- This processes the enclosed content:  -->
    <#nested>
    <footer>
      <p>Piotr Kalinowski</p>
    </footer>
  </body>
  </html>
</#macro>

<#macro otherExample p1 p2>
  <p>The parameters were: ${p1}, ${p2}</p>
</#macro>