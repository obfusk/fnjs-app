/* TODO: use fnjs */

$(function () {
  var url = '/fnjs'

  var cm_in = CodeMirror (
    $('#in')[0], {
      value: '...', mode: 'clojure', lineNumbers: true
    }
  );

  var cm_out = CodeMirror (
    $('#out')[0], {
      value: '...', mode: 'javascript', lineNumbers: true,
      readOnly: 'nocursor'
    }
  );

  var update = function () {
    var data = cm_in.getValue ();

    $.post (url, data, function (data_) {
      cm_out.setValue (data_);
    });
  };

  // ...
});
