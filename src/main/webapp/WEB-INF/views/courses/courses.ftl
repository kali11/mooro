<@common.page>
    <div class="container">
      Kursy:
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
      <a class="btn btn-success" href="<@spring.url '/courses/add'/>">Dodaj kurs</a>
    </div>

  <#include "/lib/confirm.ftl">
</@common.page>