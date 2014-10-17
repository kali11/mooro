<@common.page>
    <div id="main-container" class="container">
      <div class="row">
        <#list courses as course>
          <#if course.active>
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
          </#if>
        </#list>
      </div>

      <a class="btn btn-success" href="<@spring.url '/courses/add'/>">Dodaj kurs</a>
    </div>

  <#include "/lib/confirm.ftl">
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