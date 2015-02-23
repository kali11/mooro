<@common.page>
  <div id="main-container" class="container">
    <h2>Kursy</h2>
    <nav class="navbar navbar-default">
      <div class="container-fluid">
        <p class="navbar-text">Wybierz kategorię:</p>
        <div class="navbar-left navbar-positioned">
          <select name="category" class="multiselect" id="category" onchange=loadCourses($(this).val())>
            <option value=0>Wszystkie</option>
            <#list categories?keys as id>
                  <option value=${id}>${categories[id]}</option>
            </#list>
          </select>
        </div>
        <a class="btn btn-success navbar-btn navbar-right" href="<@spring.url '/courses/add'/>"><span class="glyphicon glyphicon-plus"></span>&nbspDodaj kurs</a>
      </div>
    </nav>
    <div id="courses">
      <#include "/courses/courses-list.ftl">
    </div>
  </div>

  <@common.multiselect />
  <script src="<@spring.url '/resources/scripts/jquery.plainoverlay.min.js' />" ></script>
  <script>
  var loadCourses = function(categ){
    $('#courses').plainOverlay('show');
    $.ajax({
        url: "<@spring.url '/courses' />",
        type: "POST",
        data: "${_csrf.parameterName}" + "=" + "${_csrf.token}&category="+categ,
        success: function(response){
            $("#courses").html(response);
            $('#courses').plainOverlay('hide');
        }
    });
  }
  </script>
</@common.page>
<#--
      <table class="table">
        <thead>
          <tr>
            <td>Nazwa</td>
            <td>Opis</td>
            <td>Aktywny</td>
            <td>Kategorie</td>
            <td>Operacje</td>
          </tr>
        </thead>
        <tbody>
          <#list courses as course>
            <tr>
              <td>${course.title}</td>
              <td>${course.description}</td>
              <td>${course.active?string('tak', 'nie')}</td>
              <td>
                <ul>
                  <#list course.categories as category>
                  <li>${category.name}</li>
                  </#list>
                </ul>
              </td>
              <td>
                <a class="btn btn-primary btn-xs" href="<@spring.url '/courses/edit/' />${course.id}">Edytuj</a>
                <a class="btn btn-danger btn-xs confirm" data-placement="right" data-title="Czy na pewno?" data-btnOkLabel="Usuń" data-btnCancelLabel="Anuluj" data-href="<@spring.url '/courses/remove/' />${course.id}">Usuń</a>
              </td>
            </tr>
          </#list>
        </tbody>
      </table>
-->