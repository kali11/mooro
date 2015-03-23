<@common.page>
  <div id="main-container" class="container">
    <h2>Witaj w kursie: ${course.title}</h2>
    <nav class="navbar navbar-default">
      <div class="container-fluid">
        <p class="navbar-text">Witaj w kursie!</p>
        <div class="navbar-left navbar-positioned">
        </div>
        
      </div>
    </nav>
    <div class="row">
        <div class="col-md-3">
          <div class="panel panel-primary">
            <div class="panel-heading">
              Moduły w kursie
            </div>
            <div class="panel-body modules-list">
              <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
                <#list course.modules as module>
                  <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="heading${module.id}">
                      <a data-toggle="collapse" data-parent="#accordion" href="#collapse${module.id}" aria-expanded="true" aria-controls="collapse${module.id}">
                      ${module.title}
                      </a>
                    </div>
                    <div id="collapse${module.id}" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading${module.id}">
                      <div class="panel-body">
                      ${module.description}
                      <hr />
                      asds
                      </div>
                      <div class="panel-footer">
                        &nbsp;<a class="btn btn-xs btn-info edit_module" href="<@spring.url '/modules/edit/'+module.id+'?courseId='+course.id />"><span class="glyphicon glyphicon-pencil"></span>&nbsp;Edytuj moduł</a>
                      </div>
                    </div>
                  </div>
                </#list>
              </div>
            </div>
            <div class="panel-footer">
            <a class="btn btn-success" href="<@spring.url '/modules/add?courseId='+course.id />"><span class="glyphicon glyphicon-plus"></span>&nbsp;Dodaj moduł</a>
            </div>
          </div>
        </div>
    
        <div class="col-md-9">
          <div class="panel panel-info">
            <div class="panel-heading">Aktualności</div>
            <div class="panel-body">
              Panel content
            </div>
          </div>
          <div class="panel panel-info">
            <div class="panel-heading">O kursie</div>
            <div class="panel-body">
              ${course.description}
            </div>
          </div>
        </div>
    </div>
  </div>
</@common.page>