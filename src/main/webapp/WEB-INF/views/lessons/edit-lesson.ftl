<@common.page>
  <div id="main-container" class="container">
    <form role="form" action="<@spring.url '/lessons/save?moduleId=' + moduleId />" method="POST">
    <@spring.formHiddenInput 'lesson.id' />
      <div class="form-group">
        <label for="title">Tytuł</label>
        <@spring.formInput 'lesson.title' 'id="title" class="form-control" placeholder="Tytuł" required' />
      </div>
      <div class="form-group">
        <label for="description">Opis</label>
        <@spring.formTextarea 'lesson.description' 'id="description" class="form-control" placeholder="Opis" required' />
      </div>
      <div class="form-group">
          <label for="active">Aktywna</label>
          <@spring.formCheckbox 'lesson.active' 'id="active" class="form-control"' />
        </div>
      <button type="submit" class="btn btn-success"><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>&nbsp;Zapisz</button>
      <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    </form>
  </div>
</@common.page> 