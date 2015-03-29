<#import "/lib/navbar.ftl" as navbar>
<@common.page>
  <div id="main-container" class="container">
    <@common.breadcrumb />
    <@navbar.courseNavbar />
    <div class="row">
        <div class="col-md-7">
          <div class="panel panel-info">
            <div class="panel-heading">Aktualności</div>
            <div class="panel-body">
              Panel content
            </div>
          </div>
        </div>
        <div class="col-md-5">
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