<#macro editElementText lessonId>
  <label for="text">Tekst:</label>
  <textarea class="form-control" id="text" name="text" type="text"></textarea>
  </textarea>
  <script>
      $(function() {
          $('#text').editable({
          language: 'pl',
          spellcheck: true,
          minHeight: 200,
          imageUploadURL: "<@spring.url '/elements/upload/image?${_csrf.parameterName}=${_csrf.token}' />",
          imageUploadParams: {lessonId: ${lessonId}},
          imageDeleteURL: "<@spring.url '/elements/delete/image/${lessonId}?${_csrf.parameterName}=${_csrf.token}' />",
          buttons: ["bold", "italic", "underline", "strikeThrough", "subscript", "superscript", 
          "fontFamily", "fontSize", "color", "formatBlock", "blockStyle", "inlineStyle", "align", 
          "insertOrderedList", "insertUnorderedList", "outdent", "indent", "createLink", 
          "insertImage", "table", "insertHorizontalRule", "undo", "redo", "removeFormat", "sep", "html"],
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
  <label for="description">Opis:</label>
  <textarea class="form-control" id="description" name="description" type="text"></textarea>
  <label for="src">Link do filmu z portalu YouTube:</label>
  <input class="form-control" id="src" name="src" type="text" required>
</#macro>

<#macro elementText element>
  ${element.text}
  <script>
  $(function(){
    console.log($("table").attr("border", "1"));
  });
  </script>
</#macro>

<#macro elementVideo element>
<iframe style="display:block; margin:auto;" width="640" height="360" src="${element.src}" frameborder="0" allowfullscreen=""></iframe>
<p>${element.description}</p>
</#macro> 
