<#import "/lib/navbar.ftl" as navbar>
<#import "/lib/elements.ftl" as elements>
<@common.page styles=['/webjars/font-awesome/4.3.0/css/font-awesome.min.css', 
'/webjars/FroalaWysiwygEditor/1.2.6/css/froala_editor.min.css', 
'/webjars/FroalaWysiwygEditor/1.2.6/css/froala_style.min.css',
'/webjars/FroalaWysiwygEditor/1.2.6/css/froala_content.min.css']>
<@common.froala />
  <div id="main-container" class="container">
   <@common.breadcrumb />
   <@navbar.courseNavbar />
   <div class="row">
        <div class="col-md-3">
          <div class="panel panel-primary">
            <div class="panel-heading">
              Lekcje w module
            </div>
            <div class="panel-body lessons-list">
              <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
                <#list module.lessons as lesson>
                  <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="heading${lesson.id}">
                      <a class="lesson-title" data-toggle="collapse" data-parent="#accordion" href="#collapse${lesson.id}" aria-expanded="false" aria-controls="collapse${lesson.id}">
                      ${lesson.title}
                      </a>
                    </div>
                    <div id="collapse${lesson.id}" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading${lesson.id}">
                      <div class="panel-body">
                      ${lesson.description}
                      <hr />
                      W tej lekcji:
                      <div class="list-group">
                          <#list lesson.elements as element>
                          <div id="element-${element.id}" class="list-group-item list-group-item-info element-title">
                            ${element.title}
                          </div>
                          </#list>
                      </div>
                      <ul>
                      </ul>
                      <button class="btn btn-xs btn-success" onclick=editElement(${lesson.id})><span class="glyphicon glyphicon-plus"></span>&nbsp;Dodaj element</button>
                      </div>
                      <div class="panel-footer">
                        <div class="modules-edit">
                          <a class="btn btn-xs btn-success"><span class="glyphicon glyphicon-arrow-up"></span></a>
                          <a class="btn btn-xs btn-success"><span class="glyphicon glyphicon-arrow-down"></span></a>
                          <a class="btn btn-xs btn-info" href="<@spring.url '/modules/edit/'+lesson.id+'?moduleId='+module.id />"><span class="glyphicon glyphicon-pencil"></span>&nbsp;Edytuj</a>
                          <a class="btn btn-xs btn-danger"><span class="glyphicon glyphicon-remove"></span>&nbsp;Usuń</a>
                        </div>
                      </div>
                    </div>
                  </div>
                </#list>
              </div>
            </div>
            <div class="panel-footer">
            <a class="btn btn-success" href="<@spring.url '/lessons/add?moduleId='+module.id />"><span class="glyphicon glyphicon-plus"></span>&nbsp;Dodaj lekcję</a>
            </div>
          </div>
        </div>
        <div class="col-md-9">
          <div id="panel-element-content" class="panel panel-primary" style="display:none">
            <div id="element-title" class="panel-heading">
            </div>
            <div id="element-content" class="panel-body">
            </div>
          </div>
        </div>
    </div>
  </div>
  <script src="<@spring.url '/resources/scripts/jquery.plainoverlay.min.js' />" ></script>
  <script>
  $("#collapse${activeLessonId!}").addClass('in');
  var editElement = function(lessonId){
    $('#element-title').text("Dodaj element");
    $('#panel-element-content').show();
    $('#element-content').plainOverlay('show');
    $.ajax({
        url: "<@spring.url '/elements/add' />",
        type: "POST",
        data: "${_csrf.parameterName}" + "=" + "${_csrf.token}&lessonId="+lessonId,
        success: function(response){
            $("#element-content").html(response);
            $('#element-content').plainOverlay('hide');
        }
    });
  }
  
  $(".element-title").click(function(){
    id = $(this).attr("id").split("-")[1];
    $('#element-title').text($(this).text());
    $('#panel-element-content').show();
    $('#element-content').plainOverlay('show');
    $.ajax({
        url: "<@spring.url '/elements/' />" + id,
        type: "POST",
        data: "${_csrf.parameterName}" + "=" + "${_csrf.token}",
        success: function(response){
            $("#element-content").html(response);
            $('#element-content').plainOverlay('hide');
        }
    });
  });

  </script>
</@common.page> 