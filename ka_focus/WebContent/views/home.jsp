<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="pt-BR">

<head>

<meta charset="UTF-8" />
<meta name="description" content="avaliacao focus" />

<title>Home</title>

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
		<aside class="hero-unit ka-no-bdr-radius">
			<h1>Escolha abaixo o que deseja fazer!</h1>
			<h2>É divertido! :)</h2>

			<div class="container-fluid">
				<div class="row-fluid">

					<div class="span4">
						<form action="procurarPessoa" method="post" name="dados">
							<input type="text" class="span12" name="nome" placeholder="Nome" />
							<input type="text" class="span12" name="email" placeholder="exemplo@dominio.com" /> 
							<input type="submit" value="Buscar" id="search" class="btn-large span12 btn-info ka-btn-home-sml"
								title="Procurar pessoas" onclick="return validaCampos()" />
						</form>
					</div>

					<div class="span4">
						<a href="addPessoa" class="btn-large span12 btn-primary ka-btn-home ka_a ka_a_big" 
							title="Cadastrar uma pessoa" >Cadastrar</a>
					</div>

					<div class="span4">
						<a href="sorteio" class="btn-large span12 btn-success ka-btn-home ka_a ka_a_big"
							title="Realizar sorteio do amigo secreto">Sorteio</a>
					</div>
				</div>

				<c:if test="${error == 0}">
					<h2 style="color: #007A29;">Pessoa cadastrada!</h2>
				</c:if>

				<c:if test="${error == 2}">
					<h2 style="color: #007A29;">Pessoa alterada!</h2>
				</c:if>

				<c:if test="${error == 3}">
					<h2 style="color: #930000;">Impossível realizar sorteio!</h2>
					<h3 style="color: #930000;">São necessárias 3 ou mais pessoas cadastradas!</h3>
				</c:if>

				<c:if test="${error == 4}">
					<h3 style="color: #930000;">${email_error}</h3>
				</c:if>
				
				<c:if test="${error == 6}">
					<h3 style="color: #930000;">Pessoa não encontrada!</h3>
				</c:if>
				
				<c:if test="${error == 7}">
					<h2 style="color: #007A29;">E-mails enviados com sucesso!</h2>
				</c:if>

			</div>
		</aside>


		<div class="container-fluid">
			<div class="row-fluid">
			
				<h1>Pessoas Cadastradas</h1>
				<table class="table table-striped table-bordered table-hover table-condensed">
					

					<thead>
						<tr>
							<th>Nome</th>
							<th>E-mail</th>
							<th>Ação</th>
						</tr>
					</thead>

					<tbody>

						<c:forEach items="${pessoas}" var="pessoa">
							<tr>
								<td><a href="alterarPessoa?id=${pessoa.id}" title="${pessoa.nome}">${pessoa.nome}</a></td>
								<td>${pessoa.email}</td>
								<td><a href="deletarPessoa?id=${pessoa.id}" 
										onclick="return confirm('Deseja mesmo apagar esse registro?');"
											title="Remover pessoa">Remover</a></td>
							</tr>
						</c:forEach>

					</tbody>
				</table>
			</div>
		</div>
	</section>
	
	<!-- chamando a página rodape.jsp que possui o rodapé pronto da pagina -->
	<c:import url="rodape.jsp" />

	<script type="text/javascript">
	
			/* valida os campos */
			function validaCampos() {
				if ((document.dados.nome.value == "" || document.dados.nome.value.length == 0) &&
						(document.dados.email.value == "" || document.dados.email.value.length == 0)) {
					alert("Por favor, preencha um dos campos!");
					return false;
				} else {
					return true;
				}
			}
	</script>
</body>
</html>