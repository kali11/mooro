<#macro editCourse active = false>
<form role="form" id="edit-course" action="<@spring.url '/courses/save'/>" method="POST">
              <@spring.formHiddenInput 'course.id' />
                <div class="form-group">
                  <label for="title">Tytuł kursu</label>
                  <@spring.formInput 'course.title' 'id="title" class="form-control" placeholder="Tytuł" required' />
                </div>
                <#if active>
                <div class="form-group">
                  <label for="active">Aktywny</label>
                  <@spring.formCheckbox 'course.active' 'id="active" class="form-control"' />
                </div>
                </#if>
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
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
             </form>
</#macro>

<#macro editCourseDetails>
dfdfdf
</#macro>