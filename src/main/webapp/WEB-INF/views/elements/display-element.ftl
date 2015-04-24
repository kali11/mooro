<#import "/lib/elements.ftl" as elements>
<#switch elementType>
  <#case 'text'>
    <@elements.elementText element />
  <#break>
  <#case 'video'>
    <@elements.elementVideo element />
  <#break>
  <#case 'audio'>
    <@elements.elementAudio element />
  <#break>
</#switch>