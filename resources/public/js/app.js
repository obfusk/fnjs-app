//  --                                                            {{{1
//
//  File        : js/app.js
//  Maintainer  : Felix C. Stegerman <flx@obfusk.net>
//  Date        : 2012-06-06
//
//  Copyright   : Copyright (C) 2012  Felix C. Stegerman
//  Licence     : GPLv2 or EPLv1
//
//  Depends     : ...
//  Description : ...
//
//  TODO        : ...
//
//  --                                                            }}}1

/* TODO: use fnjs */

$(function () {
  // --

  var url_fnjs  = '/fnjs';
  var url_ex    = '/examples/';
  var dflt_file = 'overview.fnjs';
  var ellipsis  = '/* ... */';

  // --

  var cm_in = CodeMirror (
    $('#in')[0], { mode: 'clojure', lineNumbers: true }
  );

  var cm_out = CodeMirror (
    $('#out')[0], {
      value: ellipsis, mode: 'javascript', lineNumbers: true,
      readOnly: 'nocursor'
    }
  );

  // --

  var do_ajax = function (type, url, data, dt, f) {           //  {{{1
    $.ajax ({
      type: type, url: url, data: data, dataType: dt, success: f,
      error: function (xhr, stat, err) {
        alert (
          'AJAX ' + stat + ' [' + url + ']: ' + (err || '(failed)')
        );
      },
    });
  };                                                          //  }}}1

  var pipe_fnjs = function () {                               //  {{{1
    var data = {
      data: cm_in.getValue (),
      ugly: $('#ugly').prop ('checked'),
    };

    do_ajax ('POST', url_fnjs, data, 'json', function (data_) {
      if (data_.error) {
        $('#info').removeClass ('ok');
        $('#info').addClass ('error');
      } else {
        $('#info').removeClass ('error');
        $('#info').addClass ('ok');
      }

      $('#info').text (data_.error || 'OK');
      cm_out.setValue (data_.result || ellipsis);
    });
  };                                                          //  }}}1

  var run_js = function () {
    eval (cm_out.getValue ());
  };

  var load_ex = function () {                                 //  {{{1
    var url = url_ex + $('#ex').val ();

    do_ajax ('GET', url, null, 'text', function (data) {
      cm_in.setValue (data);
    });
  };                                                          //  }}}1

  var on_key = function (e) {                                 //  {{{1
    if (e.which == 13) {
      if (e.ctrlKey) {
        pipe_fnjs ();
        return false;
      } else if (e.altKey) {
        run_js ();
        return false;
      }
    }
  };                                                          //  }}}1

  // --

  $('#ex').val (dflt_file); load_ex ();

  $('body').keypress (on_key);
  $('#fnjs').click (pipe_fnjs);
  $('#js').click (run_js);
  $('#load').click (load_ex);

  // --
});

// vim: set tw=70 sw=2 sts=2 et fdm=marker :
