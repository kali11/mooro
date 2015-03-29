<ol class="breadcrumb">
  Jesteś w:
  <li><a href="<@spring.url '/courses/'+course.id />">${course.title}</a></li>
  <#if module??>
    <li><a href="<@spring.url '/modules/'+module.id />" >${module.title}</a></li>
  </#if>
</ol>