// Espera o jquery ser carregado primeiro
function waitForJQuery() {
  if (typeof jQuery !== 'undefined') {
    $(document).ready(function () {
      carregarAnalises();
    });
  } else {
    setTimeout(waitForJQuery, 50);
  }
}
waitForJQuery();

// javascript

function validarNota(input) {
  var nota = parseInt(input.value);

  if (isNaN(nota) || nota < 0 || nota > 10) {
    document.getElementById("notaAviso").innerHTML = "Digite um número válido de 0 a 10.";
  } else {
    document.getElementById("notaAviso").innerHTML = "";
  }
}

function enviarAnalise() {
  var formData = {
    descricao: $('#formAnalise textarea').val(),
    nota: $('#formAnalise input[name="nota"]').val(),
    filme: {
      id: idDoFilme
    }
  };

  $.ajax({
    url: '/analises/adicionar/' + idDoFilme,
    method: 'POST',
    data: JSON.stringify(formData),
    contentType: 'application/json',
    success: function (analise) {
      // alert('Análise enviada com sucesso!');
      $('#formAnalise')[0].reset();
      carregarAnalises();
    },
    error: function () {
      alert('Não foi possível enviar a análise.');
    }
  });
}

$(document).ready(function () {
  carregarAnalises();
});

function deletarAnalise(id) {
  $.ajax({
    url: '/analises/excluir/' + id,
    method: 'DELETE',
    success: function () {
      // alert('Análise removida com sucesso!');
      carregarAnalises();
    },
    error: function () {
      alert('Não foi possível deletar a análise.');
    }
  });
}

function carregarAnalises() {
  $.ajax({
    url: '/analises/buscar/' + idDoFilme,
    method: 'GET',
    success: function (analises) {
      atualizarListaAnalises(analises);
    },
    error: function () {
      alert('Não foi possível carregar as análises.');
    }
  });
}

function atualizarListaAnalises(analises) {
  var listaAnalises = $('#listaAnalises');
  listaAnalises.empty();

  for (var i = 0; i < analises.length; i++) {
    var analise = analises[i];
    var li = $('<li>').addClass('mb-1');

    var notaText = $('<span>').text('Nota: ').append($('<strong>').text(analise.nota));
    var descricaoText = $('<span>').text(' - ' + analise.descricao);

    var editarLink = $('<a>').text('Editar').addClass('btn btn-sm btn-info ml-2').attr('href', '/editar-analise/' + analise.id); // Ajuste para espaçamento entre a descrição e o botão Editar
    var excluirButton = $('<button>').text('Excluir').addClass('btn btn-sm btn-info ml-2').attr('onclick', 'deletarAnalise(' + analise.id + ')'); // Ajuste para espaçamento entre os botões

    li.append(notaText, descricaoText, editarLink, excluirButton);
    listaAnalises.append(li);
  }
}

function importScript(src) {
  var script = document.createElement('script');
  script.src = src;
  script.type = 'text/javascript';
  script.defer = true;
  document.head.appendChild(script);
}
