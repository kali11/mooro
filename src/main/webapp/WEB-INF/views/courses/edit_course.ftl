<@common.page styles=['/resources/css/bootstrap-multiselect.css']>
    <div id="main-container" class="container">
      <form role="form" action="<@spring.url '/courses/save'/>" method="POST">
      <@spring.formHiddenInput 'course.id' />
        <div class="form-group">
          <label for="title">Nazwa</label>
          <@spring.formInput 'course.title' 'id="title" class="form-control" placeholder="Tytuł" required' />
        </div>
        <div class="form-group">
          <label for="description">Opis</label>
          <@spring.formTextarea 'course.description' 'id="description" class="form-control" placeholder="Opis" required' />
        </div>
        <div class="form-group">
          <label for="active">Aktywny</label>
          <@spring.formCheckbox 'course.active' 'id="active" class="form-control"' />
        </div>
        <div class="form-group">
          <label for="category">Kategoria</label>
          <select name="categoryIds" class="form-control multiselect" id="category" multiple="multiple" required>
            <#list categories?keys as id>
                <#if course_categories?? && course_categories?seq_contains(id)>
                  <option value=${id} selected>${categories[id]}</option>
                <#else>
                  <option value=${id}>${categories[id]}</option>
                </#if>
            </#list>
          </select>
        </div>
        <button type="submit" class="btn btn-default">Zapisz</button>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
      </form>
  </div>
  
</@common.page> 