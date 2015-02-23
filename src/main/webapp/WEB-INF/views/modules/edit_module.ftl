<@common.page>
  <div id="main-container" class="container">
    <form role="form" action="<@spring.url '/courses/'+courseId+'/modules/save'/>" method="POST">
    <@spring.formHiddenInput 'module.id' />
      <div class="form-group">
        <label for="title">Nazwa</label>
        <@spring.formInput 'module.title' 'id="title" class="form-control" placeholder="Tytuł" required' />
      </div>
      <div class="form-group">
        <label for="description">Opis</label>
        <@spring.formTextarea 'module.description' 'id="description" class="form-control" placeholder="Opis" required' />
      </div>
      <button type="submit" class="btn btn-default">Zapisz</button>
      <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    </form>
  </div>
  ${courseId}
</@common.page> 