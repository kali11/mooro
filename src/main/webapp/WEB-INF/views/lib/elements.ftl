<#macro elementText lessonId>
  <label for="text">Tekst:</label>
  <textarea class="form-control" id="text" name="text" type="text">
  </textarea>
  <script>
      $(function() {
          $('#text').editable({
          language: 'pl',
          spellcheck: true,
          imageUploadURL: "<@spring.url '/elements/upload/image?${_csrf.parameterName}=${_csrf.token}' />",
          imageUploadParams: {lessonId: ${lessonId}},
          imageDeleteURL: "<@spring.url '/elements/delete/image/${lessonId}?${_csrf.parameterName}=${_csrf.token}' />",
          //buttons: ['save','insertHorizontalRule','uploadFile', 'fullscreen','table', 'redo' , 'sep', 'bold', 'italic', 'underline', 'uploadFile', 'table', 'insertImage'],
          buttons: ["bold", "italic", "underline", "strikeThrough", "subscript", "superscript", 
          "fontFamily", "fontSize", "color", "formatBlock", "blockStyle", "inlineStyle", "align", 
          "insertOrderedList", "insertUnorderedList", "outdent", "indent", "createLink", 
          "insertImage", "insertVideo", "table", "insertHorizontalRule", "undo", "redo", "removeFormat", "sep", "html"],
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

<#macro elementVideo lessonId>
  <div id="video">
  </div>
  <script>
    $(function(){
      $('#video').editable({
        language: 'pl',
        buttons: ["insertVideo"],
        //height: 0,
        inlineMode: false
      });
    });
  </script>
</#macro>
