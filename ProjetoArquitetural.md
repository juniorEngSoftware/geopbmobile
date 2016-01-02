# Projeto Arquitetural #

## Introdução ##

Este documento consiste numa visão geral da arquitetura do projeto GeoPB Mobile, descrevendo sua finalidade, funcionalidades e as várias dimensões de um projeto arquitetural.


## Finalidade do sistema ##
O monitoramento de obras públicas é uma tarefa pertencente ao Tribunal de Contas de cada estado. Entretanto, a fiscalização destas pelos cidadãos pode ajudar neste controle, para isso este necessita de um canal de comunicação e de informação ao qual possa denunciar e verificar as informações decorrentes destas obras. Buscando incorporar a utilização de dispositivos móveis equipados com GPS e câmeras digitais na fiscalização de obras públicas pelos cidadãos, neste projeto é proposto o desenvolvimento de uma aplicação para dispositivos móveis para o acompanhamento de obras públicas através de imagens de satélite e um sistema que permita que os cidadãos realizem denúncias.


## Stakeholders ##
O projeto possui os seguintes stakeholders envolvidos:

  * Cliente: TCE
  * Usuários: auditores do TCE e cidadãos comuns
  * Equipe do projeto nos papéis de desenvolvedores, testadores e gerentes.


## Requisitos Arquiteturais ##
Os requisitos arquiteturais são:

Requisitos funcionais :

  * prover a visualização de obras públicas com auxílio do GPS.
  * prover denúncia textual por parte dos cidadãos.
  * prover denúncia por parte dos cidadãos via upload de fotos.
  * prover formulário para auditoria _in_ _loco_ dos auditores do TCE.

Requisitos não-funcionais:

  * A aplicação deve ser desenvolvida para executar no Sistema Operacional Windows Mobile 6.0 ou superior;
  * Preferencialmente, os dispositivos deverão ter acesso à câmera digital e ao GPS;
  * Os dispositivos que não possuírem câmera digital e GPS executarão o sistema, porém não serão capazes de executar todas as funcionalidades providas pela aplicação;
  * A aplicação deve utilizar mapas do google para processamento de informações geográficas;
  * Utilização de sistema de controle de versões;
  * Utilização de linguagem C# para desenvolvimento no dispositivo móvel e Java no servidor;
  * ser intuitivo, fácil de usar e prover bom desempenho.


## Visão Lógica ##
Essa seção apresenta aspectos detalhados sobre a estrutura da arquitetura do
GeoPB Mobile, tais diagramas de componentes e comentários sobre suas respectivas camadas. Dessa forma, tal seção se endereça a stakeholders tais como: desenvolvedores, testadores, mantenedores, arquiteto e o gerente de projetos.


### Aspectos Arquiteturais ###
  * Manutenibilidade e reusabilidade: Com o sistema sendo projetado seguindo o padrão MVC, a manutenibilidade e reusabilidade serão favorecidas. O isolamento das camadas facilitará a adição de novas funcionalidades, por exemplo.
  * Disponibilidade: está baseada na estratégia de tolerância a falhas adotadas nos servidores. Como não redundância dos dados, ao ocorrer falhas, o sistema poderá ficar "fora do ar"(sem mostrar informações das obras, por exemplo).


### Estrutura da Arquitetura - Atual ###

OBS: Com a mudança de foco no projeto, percebida através de um estudo inicial de viabilidade, o projeto arquitetural sofreu algumas mudanças. O sistema, ao final da disciplina projeto1, consiste em aplicativo _server_ _side_ .(Mais detalhes: http://code.google.com/p/geopbmobile/wiki/FocoDoProjeto).
Mesmo com algumas mudanças, os componentes foram mantidos, porém simplificados.


![http://geopbmobile.googlecode.com/files/proj.atual.jpg](http://geopbmobile.googlecode.com/files/proj.atual.jpg)





### Estrutura da Arquitetura - Inicial ###

![http://geopbmobile.googlecode.com/files/proj.arquitetural.jpg](http://geopbmobile.googlecode.com/files/proj.arquitetural.jpg)

### Camada de Apresentação ###

A camada de apresentação será desenvolvida no dispositivo móvel com a linguagem de programação C#, usando APIs do GPS e google maps. Nessa camada, as obras públicas serão apresentadas através de mapas do google, podendo ter a localização geográfica configurada com o auxílio do GPS do dispositivo(se este tiver GPS disponível). Também será possível acessar a câmera digital do dispositivo (se este tiver câmera disponível), durante a "navegação" nos mapas.
Analisando a estrutura da arquitetura, temos:
  * Módulo em verde escuro: componente do próprio dispositivo móvel (Câmera Digital);
  * Módulos em verde claro: componentes para interface do usuário;

### Camada de Negócio ###

A camada de negócio está dividida no dispositivo móvel e no servidor GeoDenúncia. No dispositivo, temos a lógica e a comunicação com os servidores web: GeoPB, o qual contém as informações das obras públicas; GeoServer, o qual contém bancos de mapas; e GeoDenúncia, o qual servirá para tratar denúncias e informações de auditoria.
Observação: os servidores GeoPB e GeoServer já estão desenvolvidos. Apenas iremos acessá-los conforme é mostrado na figura acima.
Analisando a estrutura da arquitetura, temos:
  * Módulos em branco: componentes cliente para acessarem os respectivos servidores.
  * Módulos em rosa: servidores

No servidor GeoDenúncia, que será desenvolvido, teremos um controlador (struts) que transforma eventos gerados pela interface em ações de negócio, alterando o modelo. O modelo (lógica no servidor) será responsável por acessar a camada de acessos ao dados, a qual seguirá o padrão DAO. Desse modo, termos uma arquitetura dividida em camadas seguindo o padrão MVC.

### Camada de Persistência ###
Como discutido anteriormente, os dados serão armazenados conforme o padrão DAO, sendo responsabilidade do modelo.
Analisando a estrutura da arquitetura, temos:
  * Módulo em laranja: camada de acesso aos dados.
  * Módulos em cinza: banco de dados