/* TODO: use fnjs */

$(function () {
  var url     = '/fnjs';
  var ell     = '/* ... */';
  var example = '(console.log "Hello, World!")';

  var cm_in = CodeMirror (
    $('#in')[0], {
      value: example, mode: 'clojure', lineNumbers: true
    }
  );

  var cm_out = CodeMirror (
    $('#out')[0], {
      value: ell, mode: 'javascript', lineNumbers: true,
      readOnly: 'nocursor'
    }
  );

  var update = function () {
    var data = cm_in.getValue ();

    $.post (url, { data: data }, function (data_) {
      // console.log (data, '-->', data_);

      if (data_.error) {
        alert (data_.error); // TODO
      }

      cm_out.setValue (data_.result || ell);
    }, 'json');
  };

  var run = function () {
    eval (cm_out.getValue ());
  };

  $('#fnjs').click (update);
  $('#js').click (run);
});
