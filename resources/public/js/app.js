/* TODO: use fnjs */

$(function () {
  var url_fnjs  = '/fnjs';
  var url_ex    = '/examples/';
  var dflt_file = 'overview.fnjs';
  var ellipsis  = '/* ... */';

  var cm_in = CodeMirror (
    $('#in')[0], {
      mode: 'clojure', lineNumbers: true
    }
  );

  var cm_out = CodeMirror (
    $('#out')[0], {
      value: ellipsis, mode: 'javascript', lineNumbers: true,
      readOnly: 'nocursor'
    }
  );

  var pipe_fnjs = function () {
    var data = {
      data: cm_in.getValue (),
      ugly: $('#ugly').prop ('checked'),
    };

    $.post (url_fnjs, data, function (data_) {
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

  var run_js = function () {
    eval (cm_out.getValue ());
  };

  var load_ex = function () {
    var url = url_ex + $('#ex').val ();

    $.get (url, function (data) {
      cm_in.setValue (data);
    }, 'text');
  };

  var on_key = function (e) {
    if (e.which == 13) {
      if (e.ctrlKey) {
        pipe_fnjs ();
        return false;
      } else if (e.altKey) {
        run_js ();
        return false;
      }
    }
  };

  $('#ex').val (dflt_file); load_ex ();

  $('body').keypress (on_key);
  $('#fnjs').click (pipe_fnjs);
  $('#js').click (run_js);
  $('#load').click (load_ex);
});
