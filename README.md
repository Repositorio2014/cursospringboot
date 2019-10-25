# Sistema-Desafio Service Net
TECNOLOGIAS GERAIS:
	
	Java 8;
	Spring Boot;
	Thymeleaf;
	MySql;
	GitHub.
	
DESCRIÇÃO: 

Sistema web de cadastro de clientes com login e autenticação

PASSOS PARA EXECUSSÃO DO SISTEMA:

1 - CRIAR BANCO MYSQL DE NOME: eventosapp;
2 - RODAR A APLICAÇÃO PARA GERAR AS TABELAS DO BANCO;
3 - RODAR OS SCRIPTS PARA A CRIAÇÃO DAS ROLES:
	
	INSERT INTO `eventosapp`.`role`
	(`nome_role`)
	VALUES
	(`ROLE_ADMIN`);
	
	INSERT INTO `eventosapp`.`role`
	(`nome_role`)
	VALUES
	(`ROLE_USER`);
	
4 - RODAR OS SCRIPTS PARA A GERAÇÃO DO USUÁRIO:

	INSERT INTO `eventosapp`.`usuario`
	(`login`,
	`nome_completo`,
	`senha`)
	VALUES
	(`admin`,
	`Usuario Admin`,
	`$2a$10$X6Nb6q5UHjaWM.4ulXz3q.JSDMRLENQ6MzcX0deLZtbIijoWGmzMy`);
	
	OBS: A SENHA CRIPTOGRAFADA EQUIVALE AOS NUMEROS '123'. ESTA SENHA É CRIPTOGRAFADA 
		 CADA VEZ QUE A APLICAÇÃO É RODADA, DE MODO QUE ASSIM QUE O PROGRAMA É COMPILADO,
		 É POSSÍVEL VER NOVA CRIPTORAFIA EM BAIXO DOS LOGS NO TERMINAL DA IDE ECLIPSE.
		 
5 - RODAR OS SCRIPTS PARA ASSOSSIAR A REGRA ADMIN AO USUÁRIO CRIADO:

	INSERT INTO `eventosapp`.`usuarios_roles`
	(`usuario_id`,
	`role_id`)
	VALUES
	(`admin`,
	`ROLE_ADMIN`);
	
6 - ACESSAR APLICAÇÃO NO BROWSER:
	http://localhost:8080
	
7 - LOGIN: admin
	SENHA: 123

