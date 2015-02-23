<#macro page styles = []>
  <!DOCTYPE html>
  <html>
  <head>
    <meta charset="UTF-8">
    <link href="<@spring.url '/resources/css/main.css' />" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" href="<@spring.url '/webjars/bootstrap/3.2.0/css/bootstrap.min.css' />" />
    <#list styles as style>
    <link rel="stylesheet" href="<@spring.url style />" />
    </#list>
    <script src="<@spring.url '/webjars/jquery/2.1.1/jquery.min.js' />" ></script>
    <script src="<@spring.url '/webjars/bootstrap/3.2.0/js/bootstrap.min.js' />"></script>
    <title>LMS</title>
  </head>
  <body>
    <div id="navigation" class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">Platforma E-learningowa</a>
        </div>
        <div class="navbar-collapse collapse">
          <#if userlogin??>
            <form class="navbar-form navbar-right" method="POST" action="<@spring.url '/logout'/>">
              <div class="btn-group">
                <button type="button" class="btn btn-success dropdown-toggle" data-toggle="dropdown">Użytkownik:&nbsp;${userlogin}&nbsp;<span class="caret"></span></button>
                <ul class="dropdown-menu" role="menu">
                  <li><a href="<@spring.url '/home'/>">Moje kursy</a></li>
                  <li><a href="/">Profil</a></li>
                  <li class="divider"></li>
                  <li>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                    <a href="#" onclick="$(this).closest('form').submit()">Wyloguj</a>
                  </li>
                </ul>
              </div>
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

<#macro confirmation>
<script src="<@spring.url '/resources/scripts/bootstrap-confirmation.js' />" ></script>
<script>
$('.confirm').confirmation();
</script>
</#macro>

<#macro multiselect>
<script src="<@spring.url '/resources/scripts/bootstrap-multiselect.js' />"></script>
<script>
$('.multiselect').multiselect({
    nonSelectedText: 'Wybierz przynajmniej jedną',
});
</script>
</#macro>

<#macro otherExample p1 p2>
  <p>The parameters were: ${p1}, ${p2}</p>
</#macro>