<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="pt-BR">

<head>
<meta charset="UTF-8" />
<meta name="description" content="avaliacao focus" />
<title>Adicionar/Alterar Pessoa</title>

<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/bootstrap/css/bootstrap.css" />" />

<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/bootstrap/css/bootstrap-responsive.css" />" />

<script type="text/javascript"
	src="<c:url value="/resources/bootstrap/js/bootstrap.js" />"></script>

<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/ka_customizacao/css/default.css" />" />

</head>

<body>

	<!-- chamando a página cabecalho.jsp que possui o cabecalho pronto da pagina -->
	<c:import url="cabecalho.jsp" />

	<section>
		<aside class="hero-unit ka-aside-addpessoa">
			<h1>Cadastre ou altere os dados da Pessoa!</h1>
			<h2>*Preencha todos os campos.</h2>

			<div class="container-fluid">
				<div class="row-fluid">
					<form action="adicionaPessoa" method="post" name="dados">
						
						<div class="span3">
							<input type="hidden" name="id" value="${pessoa.id}" />
							<input type="text" class="span12 ka-inp" placeholder="Nome"
								name="nome" id="nome" oninput="validacao()"
								value="${pessoa.nome}" />
						</div>

						<div class="span3">
							<input type="text" class="span12 ka-inp"
								placeholder="exemplo@dominio.com" name="email"
								oninput="validacao()" value="${pessoa.email}" />
						</div>

						<div class="span3">
							<input type="submit" value="Salvar" id="add"
								class="btn-large span12 btn-success" style="display: none;" 
									title="Salvar" onclick="return validaTamanhoCampos()" />
						</div>

						<div class="span3">
							<a href="home" class="btn-large span12 btn-danger ka_a ka_a_medium"
								title="Voltar" id="voltar">Voltar</a>
						</div>
					</form>
				</div>
				
				<c:if test="${error == 1}">
   					<h2 style="color:#930000;">Pessoa não cadastrada!</h2>
   					<h3 style="color:#930000;">Já existe uma pessoa com este e-mail!</h3>
				</c:if>
				
				<c:if test="${error == 5}">
   					<h2 style="color:#930000;">Pessoa não alterada!</h2>
   					<h3 style="color:#930000;">Já existe uma pessoa com este e-mail!</h3>
				</c:if>
				
				<form:errors path="pessoa.nome" cssStyle="color: #930000" />
				<form:errors path="pessoa.email" cssStyle="display: block; margin-top: 10px; color:#930000" />
				
				<c:if test="${error == 7}">
   					<span style="color:display: block; margin-top: 10px; color:#930000;">E-mail inválido</span>
				</c:if>
				
			</div>
		</aside>
	</section>
	
	<!-- chamando a página rodape.jsp que possui o rodapé pronto da pagina -->
	<c:import url="rodape.jsp" />

	<script type="text/javascript">
		/* valida o campo nome */
		function validanome() {
			if (document.dados.nome.value == ""
					|| document.dados.nome.value.length < 3) {
				return false;
			} else {
				return true;
			}
		}

		/* valida o campo e-mail */
		function validaemail() {
			var re = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
			return re.test(document.dados.email.value);
		}

		/* valida os campos do formulario. habilita o botao enviar se estiver tudo ok */
		function validacao() {
			if (validanome()) {
				if (validaemail()) {
					document.getElementById('add').style.display = "block";
				} else {
					document.getElementById('add').style.display = "none";
				}
			} else {
				document.getElementById('add').style.display = "none";
			}
		}
		
		/* valida o tamanho dos campos*/
		function validaTamanhoCampos() {
			if (document.dados.nome.value.length > 100 || document.dados.email.value.length > 100) {
				alert("Nenhum dos campos pode ultrapassar 100 caracteres!");
				return false;
			} else {
				
				if ((document.dados.nome.value == "" || document.dados.nome.value.length < 3) ||
						(document.dados.email.value == "" || document.dados.email.value.length < 6)) {
					alert("Erro: Nome deve ter no mínimo 3 caracteres, e e-mail deve ser válido!");
					return false;
				} else {
					if(validaemail()){
						return true;
					} else {
						alert("E-mail inválido!");
						return false;
					}
				}
			}
		}
		
	</script>

</body>
</html>