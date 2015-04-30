<#import "/lib/course-helper.ftl" as courseHelper>
<@common.page styles=['/resources/css/bootstrap-multiselect.css']>
    <div id="main-container" class="container">
    <@common.breadcrumb "Edytujesz:" />
      <div class="row">
        <div class="col-md-6 col-md-offset-1">
          <@courseHelper.editCourse true />
          <button onclick="$('#edit-course').submit()" type="button" class="btn btn-success"><span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>&nbsp;Zapisz</button>
        </div>
        <div class="col-md-4">
          <p>
            <button data-toggle="modal" data-target="#edit-course-modal" class="btn btn-warning"><span class="glyphicon glyphicon-bookmark"></span>&nbsp;Edytuj informacje o kursie</button>
          </p>
          <p>
            <a data-href="<@spring.url '/courses/delete/'+course.id />" class="btn btn-danger confirm" data-placement="bottom" data-title="Czy na pewno?" data-btnOkLabel="Usuń" data-btnCancelLabel="Anuluj"><span class="glyphicon glyphicon-remove"></span>&nbsp;Usuń kurs</a>
          </p>
        </div>
      </div>
  </div>
  <div class="modal fade" id="edit-course-modal" tabindex="-1" role="dialog" aria-labelledby="edit-course-label" aria-hidden="true">
      <div class="modal-dialog modal-lg">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title" id="edit-course-label">Edytuj informacje o kursie</h4>
          </div>
          <div class="modal-body">
            <@courseHelper.editCourseDetails />
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">Anuluj</button>
            <button onclick="$('#edit-course').submit()" type="button" class="btn btn-success"><span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>&nbsp;Zapisz</button>
          </div>
          
        </div>
      </div>
  </div>
<@common.confirmation />
</@common.page> 