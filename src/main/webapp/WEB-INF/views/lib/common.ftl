<#macro page>
  <html>
  <head>
    <link href="resources/css/main.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" href="webjars/bootstrap/3.2.0/css/bootstrap.min.css" />
    <script src="webjars/jquery/2.1.1/jquery.min.js"></script>
    <title>LMS</title>
  </head>
  <body>
 
    <#-- This processes the enclosed content:  -->
    <#nested>
  </body>
  </html>
</#macro>

<#macro otherExample p1 p2>
  <p>The parameters were: ${p1}, ${p2}</p>
</#macro>