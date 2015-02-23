<@common.page>
  <div id="main-container" class="container">
    <h2>Witaj w kursie: ${course.title}</h2>
    <nav class="navbar navbar-default">
      <div class="container-fluid">
        <p class="navbar-text">Witaj w kursie!</p>
        <div class="navbar-left navbar-positioned">
        </div>
        <a class="btn btn-success navbar-btn navbar-right" href="<@spring.url currentUrl+'/modules/add' />"><span class="glyphicon glyphicon-plus"></span>&nbsp;Dodaj moduł</a>
      </div>
    </nav>
    <div class="col-md-4">
      <div class="panel panel-primary">
        <div class="panel-heading">
          Moduły w kursie
          
        </div>
        <div class="panel-body">
          Panel content
        </div>
      </div>
    </div>
    <div class="col-md-8">
      <div class="panel panel-info">
        <div class="panel-heading">Aktualności</div>
        <div class="panel-body">
          Panel content
        </div>
      </div>
    </div>
    
    <div class="col-md-8 col-md-offset-4">
      <div class="panel panel-info">
        <div class="panel-heading">O kursie</div>
        <div class="panel-body">
          ${course.description}
        </div>
      </div>
    </div>
  </div>
</@common.page>