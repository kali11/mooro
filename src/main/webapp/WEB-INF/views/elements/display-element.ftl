<#import "/lib/elements.ftl" as elements>
<#switch elementType>
  <#case 'text'>
    <@elements.elementText element />
  <#break>
  <#case 'video'>
    <@elements.elementVideo element />
  <#break>
</#switch>