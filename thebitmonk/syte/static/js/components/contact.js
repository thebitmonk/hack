
function setupContact(el) {
  var href = el.href;

  if($('#contact-profile').length > 0) {
    window.location = href;
    return;
  }

  var spinner = new Spinner(spin_opts).spin();
  $('#contact-link').append(spinner.el);

  require(["text!templates/contact-view.html"],
     function(contact_view) {

        var template = Handlebars.compile(contact_view);

        $(template()).modal().on('hidden', function () {
            adjustSelection('home');
        })

        spinner.stop();
     });
}
