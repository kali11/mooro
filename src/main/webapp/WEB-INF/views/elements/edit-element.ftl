<#import "/lib/elements.ftl" as elements>
<form role="form">
  <div class="form-group">
    <label for="title">Tytuł:</label>
    <input class="form-control" id="title" name="title" type="text" />
  </div>
  <div class="form-group">
    <label for="type">Typ elementu:</label><br />
    <button name="text" type="button" class="btn btn-success btn-lg element-type"><span class="glyphicon glyphicon-font" aria-hidden="true"></span></button>
    <button name="video" type="button" class="btn btn-success btn-lg element-type"><span class="glyphicon glyphicon-facetime-video" aria-hidden="true"></span></button>
    <button name="audio" type="button" class="btn btn-success btn-lg element-type"><span class="glyphicon glyphicon-volume-up" aria-hidden="true"></span></button>
    <button name="quiz" type="button" class="btn btn-success btn-lg element-type"><span class="glyphicon glyphicon-check" aria-hidden="true"></span></button>
    <button name="file" type="button" class="btn btn-success btn-lg element-type"><span class="glyphicon glyphicon-file" aria-hidden="true"></span></button>
    &nbsp;<span id="display-text"></span>
  </div>
  <div class="form-group">
    <div id="element-details">
    </div>
  </div>
</form>

<script>
  $('button.element-type').hover(function(){
    $(this).addClass("active");
    type = $(this).attr("name");
    switch(type) {
      case 'text':
        displayText = "Tekst z obrazkami";
      break;
      case 'quiz':
        displayText = "Quiz";
      break;
      case 'video':
        displayText = "Klip filmowy";
      break;
      case 'audio':
        displayText = "Klip dźwiękowy";
      break;
      case 'file':
        displayText = "Plik do pobrania";
      break;
    }
    $('#display-text').text(displayText);
  }, function(){
    $(this).removeClass("active");
    $('#display-text').text("");
  });
  
  $('button.element-type').click(function(){
    type = $(this).attr("name");
    switch(type) {
      case 'text':
        $("#element-details").html(<@common.jsStr><@elements.elementText /></@>);
      break;
    }
  })
</script>