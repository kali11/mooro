<#macro elementText>
  <label for="text">Tekst:</label>
  <textarea class="form-control" id="text" name="text" type="text">
  </textarea>
  <script>
      $(function() {
          $('#text').editable({
          language: 'pl',
          imageUploadURL: "<@spring.url '/elements/upload/image?${_csrf.parameterName}=${_csrf.token}' />",
          imageUploadParams: {${_csrf.parameterName} : '${_csrf.token}'},
          imageDeleteURL: "<@spring.url '/elements/delete/image?${_csrf.parameterName}=${_csrf.token}' />",
          buttons: ['undo', 'redo' , 'sep', 'bold', 'italic', 'underline', 'uploadFile', 'table', 'insertImage'],
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
