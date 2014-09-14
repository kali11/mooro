<#macro page>
  <html>
  <head>
    <link href="resources/css/main.css" rel="stylesheet" type="text/css"/ >
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