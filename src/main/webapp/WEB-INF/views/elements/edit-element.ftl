<form role="form">
  <div class="form-group">
    <label for="title">Tytuł:</label>
    <input class="form-control" id="title" name="title" type="text" />
  </div>
  <div class="form-group">
    <label for="type">Typ elementu:</label><br />
    <button name="text" type="button" class="btn btn-success btn-lg element-type"><span class="glyphicon glyphicon-font" aria-hidden="true"></span></button>
    <button name="image" type="button" class="btn btn-success btn-lg element-type"><span class="glyphicon glyphicon-picture" aria-hidden="true"></span></button>
    <button name="video" type="button" class="btn btn-success btn-lg element-type"><span class="glyphicon glyphicon-facetime-video" aria-hidden="true"></span></button>
    <button name="audio" type="button" class="btn btn-success btn-lg element-type"><span class="glyphicon glyphicon-volume-up" aria-hidden="true"></span></button>
    &nbsp;<span id="display-text"></span>
  </div>
</form>
<script>
  $('button.element-type').hover(function(){
    $(this).addClass("active");
    type = $(this).attr("name");
    switch(type) {
      case 'text':
        displayText = "Tekst";
      break;
      case 'image':
        displayText = "Obraz";
      break;
      case 'video':
        displayText = "Klip filmowy";
      break;
      case 'audio':
        displayText = "Klip dźwiękowy";
      break;
    }
    $('#display-text').text(displayText);
  }, function(){
    $(this).removeClass("active");
    $('#display-text').text("");
  });
</script>