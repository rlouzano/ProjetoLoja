$(document).ready(function () {
    $("input[name=cep]").blur(function () {
        var cep = $(this).val().replace(/[^0-9]/, '');
        if (cep) {
            var url = 'https://viacep.com.br/ws/' + cep + /json/;
            $.ajax({
                url: url,
                dataType: 'json',
                crossDomain: true,
                contentType: "application/json",
                success: function (json) {
                    if (json.logradouro) {
                        $("input[name=logradouro]").val(json.logradouro);
                        $("input[name=complemento]").val(json.complemento);
                        $("input[name=bairro]").val(json.bairro);
                        $("input[name=localidade]").val(json.localidade);
                        $("input[name=uf]").val(json.uf);
                    }
                }
            });
        }
    });
});