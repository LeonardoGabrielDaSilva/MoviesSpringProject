#MoviesSpringProject
##Visão Geral
Este projeto Java é uma API baseada no framework Spring, projetada para integrar-se de forma eficiente às APIs do Imdb e Tmdb, obtendo detalhes de filmes e populando um banco de dados. Além de lidar com dados de filmes, a API inclui recursos como autenticação de usuários, listas de filmes favoritos com classificações por estrelas e um sistema de classificação para exibir os filmes mais bem avaliados. O projeto utiliza diversas tecnologias e frameworks para garantir funcionalidade robusta e segurança.

##Recursos e Componentes
###1. Spring API
Construído sobre o framework Spring, este projeto fornece uma base sólida para o desenvolvimento de serviços RESTful, com ferramentas para manipulação de solicitações, injeção de dependências e gerenciamento eficiente de dados.

###2. Integração com APIs do Imdb e Tmdb
A API integra-se perfeitamente às APIs do Imdb e Tmdb, obtendo detalhes essenciais de filmes, como títulos, avaliações, datas de lançamento e informações sobre o elenco. Essas APIs externas servem como fontes valiosas de dados para construir e atualizar o banco de dados local de filmes.

###3. Autenticação de Usuários e Filmes Favoritos
Os usuários podem se cadastrar e fazer login na API, permitindo que criem e gerenciem suas listas de filmes favoritos. Cada filme favorito pode receber uma classificação por estrelas, permitindo que os usuários personalizem suas preferências.

###4. Classificação por Estrelas
O projeto inclui um sistema de classificação por estrelas que calcula e exibe os filmes mais bem avaliados com base nas avaliações dos usuários. Esse mecanismo de classificação aprimora a experiência do usuário, ajudando-os a descobrir filmes altamente avaliados no banco de dados.

###5. Feign
Feign, um cliente declarativo para serviços web, simplifica as interações com as APIs do Imdb e Tmdb, abstraindo a comunicação HTTP subjacente e oferecendo uma interface clara para consumir endpoints externos.

###6. Spring Security com JWT
Para garantir acesso seguro à API, o Spring Security é implementado em conjunto com Tokens de Web JSON (JWT). Essa combinação facilita a autenticação e autorização do usuário, permitindo que apenas usuários autenticados com tokens válidos executem ações específicas.

###7. Spring Data
O Spring Data simplifica operações de banco de dados, oferecendo uma abstração de nível mais alto para operações CRUD e gerenciamento de entidades.

###8. Roles
O controle de acesso baseado em papéis é implementado por meio do Spring Security, permitindo a atribuição de diferentes papéis (por exemplo, usuário e administrador) com privilégios e níveis de acesso específicos.

###9. Cache Hazelcast
O cache Hazelcast é integrado ao projeto para aumentar o desempenho, armazenando em cache dados de filmes frequentemente acessados. Esse mecanismo de cache reduz os tempos de resposta para solicitações subsequentes, aprimorando a experiência do usuário.

###10. CircuitBreaker Fallbacks
Fallbacks do CircuitBreaker aumentam a tolerância a falhas, evitando que falhas em APIs externas, como Imdb e Tmdb, causem falhas em cascata no aplicativo, fornecendo mecanismos alternativos em caso de falhas.

###11. Banco de Dados H2
O banco de dados H2 é utilizado para armazenar os dados de filmes obtidos das APIs do Imdb e Tmdb. Conhecido por sua simplicidade e rapidez, o H2 é uma escolha adequada para fins de desenvolvimento e teste.

###12. Documentação Swagger
A documentação da API é realizada com o Swagger, oferecendo uma interface clara e interativa para que os desenvolvedores compreendam e testem os endpoints.

##Como Começar
Siga estas etapas para configurar e executar o MoviesSpringProject localmente:

Clone o repositório.
Configure as chaves da API para Imdb e Tmdb.
Configure o banco de dados usando o H2 ou sua solução de banco de dados preferida.
Compile e execute o projeto usando o Maven ou sua ferramenta de construção preferida.
