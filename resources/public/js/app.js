/* TODO: use fnjs */

$(function () {
  var url       = '/fnjs';
  var ellipsis  = '/* ... */';
  var example   = '(console.log "Hello, World!")';

  var cm_in = CodeMirror (
    $('#in')[0], {
      value: example, mode: 'clojure', lineNumbers: true
    }
  );

  var cm_out = CodeMirror (
    $('#out')[0], {
      value: ellipsis, mode: 'javascript', lineNumbers: true,
      readOnly: 'nocursor'
    }
  );

  var update = function () {
    var data = {
      data: cm_in.getValue (),
      ugly: $('#ugly').prop ('checked'),
    };

    $.post (url, data, function (data_) {
      // console.log (data, '-->', data_);

      if (data_.error) {
        $('#info').removeClass ('ok');
        $('#info').addClass ('error');
      } else {
        $('#info').removeClass ('error');
        $('#info').addClass ('ok');
      }

      $('#info').text (data_.error || 'OK');
      cm_out.setValue (data_.result || ellipsis);
    }, 'json');
  };

  var run = function () {
    eval (cm_out.getValue ());
  };

  $('body').keypress (function (e) {
    if (e.which == 13) {
      if (e.ctrlKey) {
        update ();
        return false;
      } else if (e.altKey) {
        run ();
        return false;
      }
    }
  });

  $('#fnjs').click (update);
  $('#js').click (run);
});
