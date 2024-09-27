Olá pessoa avaliadora,

Esse é o teste que implementa o proposto em : https://caju.notion.site/Desafio-T-cnico-para-fazer-em-casa-218d49808fe14a4189c3ca664857de72

Ainda não havia codado em Scala e preferi fazer o desafio utilizando essa linguagem.
Procurei algumas documentações na web e criei o pacote da aplicação com o seguinte comando :

'sbt new playframework/play-scala-seed.g8'

Acredito que pelo tempo seja a melhor escolha.

Avaliei parte da estrutura e parti pra solução do desafio proposto.
Tentei comentar para listar meu raciocínio, apesar do livro de clean code recomendar um código consiso, mas como é minha primeira aventura com Scala, releve isso por favor.

Apanhei um pouco com os testes, mas finalmente, os comandos :
sbt compile
sbt test
sbt run

Funcionam e testando a api via Postman consegui obter retorno também.
Pode ver um exemplo em 'RPostman.jpeg'

Criei também 'AuthorizerSpec' para testes, pode utilizar 'sbt test' para validar.


Resposta sobre o tópico L4 :

Pensei em colocar um indicador de uso na conta que só permite iniciar a operação de débito se estiver sinalizada como livre, desse modo, se ocorresse a simultâneidade a transação esperaria o tempo de uma transação completa (100ms) e tentaria novamente, como uma retentaativa na própria requisição, ou colocaria apenas um retorno de tente novamente.
