      <div class="row">
        <#list courses as course>
            <div class="col-md-4">
              <div class="panel panel-default">
                <div class="panel-heading">
                  ${course.title}
                </div>
                <div class="panel-body">
                  <p>${course.description}</p>
                  <a class="btn btn-info" href="<@spring.url '/courses/subscribe/' />${course.id}"><span class="glyphicon glyphicon-pencil"></span>&nbsp;Zapisz się</a>
                </div>
              </div>
            </div>
        </#list>
      </div>
