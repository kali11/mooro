<@common.page>
    <div class="container">
      Kategorie kursów:
      <table class="table">
        <thead>
          <tr>
            <td>Nazwa</td>
            <td>Operacje</td>
          </tr>
        </thead>
        <tbody>
          <#list categories as category>
            <tr>
              <td>${category.name}</td>
              <td>
                <a class="btn btn-primary btn-xs" href="<@spring.url '/admin/users/edit/' />${user.id}">Edytuj</a>
                <a class="btn btn-danger btn-xs confirm" data-placement="right" data-title="Czy na pewno?" data-btnOkLabel="Usuń" data-btnCancelLabel="Anuluj" data-href="<@spring.url '/admin/users/delete/' />${user.id}">Usuń</a>
              </td>
            </tr>
          </#list>
        </tbody>
      </table>
      <a class="btn btn-success" href="<@spring.url '/admin/categories/add'/>">Dodaj kategorię</a>
    </div>

  <script src="<@spring.url '/resources/scripts/bootstrap-confirmation.js' />" ></script>
  <script>
  $('.confirm').confirmation();
  </script>
</@common.page>