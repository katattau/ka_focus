<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="pt-BR">

<head>

<meta charset="UTF-8" />
<meta name="description" content="avaliacao focus" />

<title>Amigo Secreto</title>

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
			<h1>Sorteio do Amigo Secreto</h1>
			<h2>Lista de quem tirou quem</h2>
			
			<div class="container-fluid">
				<div class="row-fluid">
	
					<table class="table table-striped table-bordered table-hover table-condensed">
	
						<thead>
							<tr>
								<th>Pessoa</th>
								<th>Saiu com...</th>
								<th>Pessoa</th>
							</tr>
						</thead>
	
						<tbody>
	
							<c:forEach items="${amigos}" var="amigo">
								<tr>
									<td>${amigo.pessoaUm.nome}</td>
									<td>saiu com</td>
									<td>${amigo.pessoaDois.nome}</td>
								</tr>
							</c:forEach>
						</tbody>
	
					</table>
				</div>
			</div>
	
			<div class="container-fluid">
				<div class="row-fluid">
					
					<a href="sorteio" class="btn span4 btn-primary ka_a_undln"
						onclick="return confirm('Deseja mesmo realizar um novo sorteio?');"
							title="Realizar novo sorteio">Novo Sorteio</a> 
							
					<a href="enviarEmail" class="btn span4 btn-success ka_a_undln"
						onclick="return confirm('Deseja mesmo enviar os e-mails?');"
							title="Envie um e-mail as pessoas da coluna da 'esquerda'">Enviar E-mail</a> 
							
					<a href="home" class="span4 btn btn-danger ka_a ka_a_undln" id="voltar"
						title="Voltar">Voltar</a>
				</div>
			</div>
		</aside>
	</section>
	
	<!-- chamando a página rodape.jsp que possui o rodapé pronto da pagina -->
	<c:import url="rodape.jsp" />


</body>
</html>