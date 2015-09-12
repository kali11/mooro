<#macro editElementText>
    <div class="form-group">
        <label for="text">Tekst:</label>
        <textarea class="form-control" id="text" name="text" type="text"></textarea>
        </textarea>
    </div>
    <script>
      $(function() {
          $('#text').editable({
          language: 'pl',
          spellcheck: true,
          minHeight: 200,
          imageUploadURL: "<@spring.url '/files/upload/image?${_csrf.parameterName}=${_csrf.token}' />",
          imageDeleteURL: "<@spring.url '/files/delete/image?${_csrf.parameterName}=${_csrf.token}' />",
          buttons: ["bold", "italic", "underline", "strikeThrough", "subscript", "superscript", 
          "fontFamily", "fontSize", "color", "formatBlock", "blockStyle", "inlineStyle", "align", 
          "insertOrderedList", "insertUnorderedList", "outdent", "indent", "createLink", 
          "insertImage", "table", "insertHorizontalRule", "undo", "redo", "removeFormat", "sep", 
          "html"],
          inlineMode: false
          })
          .on('editable.imageError', function (e, editor, error) {
            console.log(error);
          })
          .on('editable.afterRemoveImage', function (e, editor, $img) {
            editor.deleteImage($img);
          });
      });

    </script>
</#macro>

<#macro editElementVideo>
    <div class="form-group">
        <label for="description">Opis:</label>
        <textarea class="form-control" id="description" name="description" type="text"></textarea>
    </div>
    <div class="form-group">
        <label for="src">Link do filmu z portalu YouTube:</label>
        <input class="form-control" id="src" name="src" type="text" required>
    </div>
</#macro>

<#macro editElementAudio>
    <div class="form-group">
        <label for="description">Opis:</label>
        <textarea class="form-control" id="description" name="description" type="text"></textarea>
    </div>
    <div class="form-group">
        <label for="audio-upload">Plik audio (.mp3, .ogg lub .wav):</label><br/>
    <span name="audio-upload" id="audio-upload" class="btn btn-info fileinput-button">
      <span class="glyphicon glyphicon-upload"></span>&nbsp;Dodaj plik
      <input id="fileupload" type="file" name="file" accept="audio/*">
    </span><span id="filename"></span>
    </div>
    <div class="form-group">
        <div id="upload-progress"></div>
        <span id="upload-progress-text"></span>
    </div>
    <input id="fileId" type="hidden" name="fileId"/>
    <script>
    $('#fileupload').fileupload({
      url: "<@spring.url '/files/upload/audio?${_csrf.parameterName}=${_csrf.token}' />",
      acceptFileTypes: /(\.|\/)(mp3|ogg|wav)$/i,
      
      send: function(e, data) {
        $("#upload-progress").plainOverlay("show");
        $("#upload-progress-text").text("Trwa wysyłanie...");
      },
      done: function(e, data) {
        $("#upload-progress").plainOverlay("hide");
        $("#upload-progress-text").text("");
        $("#filename").text(data.result.fileName);
        $("#fileId").val(data.result.fileId);
        $('#fileupload').fileupload(
          'option',
          'formData',
          {oldFileId: data.result.fileId}
        );
      },
      fail: function(e, data) {
        $("#filename").text("Wysyłanie nie powiodło się!");
      }
    });

    </script>
</#macro>

<#macro editElementFile>
    <div class="form-group">
        <label for="text">Tekst (Aby dodać plik naciśnik ikonę agrafki):</label>
        <textarea class="form-control" id="text" name="text" type="text"></textarea>
        </textarea>
    </div>
    <script>
      $(function() {
          $('#text').editable({
          language: 'pl',
          spellcheck: true,
          minHeight: 200,
          fileUploadURL: "<@spring.url '/files/upload/other?${_csrf.parameterName}=${_csrf.token}' />",
          buttons: ["bold", "italic", "underline", "insertOrderedList", "insertUnorderedList",
          "insertHorizontalRule", "undo", "redo", "sep", "uploadFile"],
          inlineMode: false
          })
          .on('editable.imageError', function (e, editor, error) {
            console.log(error);
          })
          .on('editable.afterRemoveImage', function (e, editor, img) {
            editor.deleteImage(img);
          });
      });

    </script>
</#macro>

<#macro modifyElement element>
    <div class="btn-group">
        <a data-href="<@spring.url '/elements/delete/'+element.id />" class="btn btn-danger confirm"
           data-placement="bottom" data-title="Czy na pewno?" data-btnOkLabel="Usuń" data-btnCancelLabel="Anuluj"><span
                class="glyphicon glyphicon-remove"></span>&nbsp;Usuń element</a>
        <a href="<@spring.url '/elements/edit/'+element.id />" class="btn btn-warning"><span
                class="glyphicon glyphicon-edit"></span>&nbsp;Edytuj element</a>
    </div>
    <hr/>
    <@common.confirmation />
</#macro>

<#macro elementText element>
    <@modifyElement element />
    ${element.text}
    <script>
  $(function(){
    $("table").attr("border", "1");
  });

    </script>
</#macro>

<#macro elementVideo element>
    <@modifyElement element />
    <iframe style="display:block; margin:auto;" width="640" height="360" src="${element.src}" frameborder="0"
            allowfullscreen=""></iframe>
    <br/>

    <p>${element.description}</p>
</#macro>

<#macro elementAudio element>
    <@modifyElement element />
    <audio controls>
        <source src="${filePath}">
    </audio>
    <p>${element.description}</p>
</#macro>

<#macro elementFile element>
    <@modifyElement element />
    ${element.text}
</#macro>
