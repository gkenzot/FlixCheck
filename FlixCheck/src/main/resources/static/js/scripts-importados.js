// src/main/resources/static/js/scripts-importados.js

// jQuery
importScript('https://code.jquery.com/jquery-3.7.1.min.js');

// Popper.js
importScript('https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js');

// Bootstrap JS
importScript('https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js');
  
function importScript(src) {
  var script = document.createElement('script');
  script.src = src;
  script.type = 'text/javascript';
  document.head.appendChild(script);
}

