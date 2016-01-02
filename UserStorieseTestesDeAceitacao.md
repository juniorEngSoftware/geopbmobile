# USERSTORIES e TESTES DE ACEITAÇÃO - Projeto 2 #

**Lista de User Stories**


**US01** - Leitura de arquivo XML no dispositivo móvel que represente os campos de um formulário. O usuário irá fornecer numa interface no dispositivo móvel o link para um xml contido na Web.

> TA1.1 - Serão passados vários arquivos XML no formato correto, que deve ser lido pelo parser. O software deverá aceitar e ler esses arquivos.

> TA1.2 - Serão passados vários arquivos XML incorretos. O software deverá rejeitar esses arquivos.


**US02** - Criação de formulários no dispositivo móvel a partir do XML - somente campos textuais e numéricos.

> TA2.1 - O software vai receber vários arquivos XML contendo instruções para a criação de campos textuais e numéricos na aplicação. Esses campos podem ter restrições (como, por exemplo, os números devem ser maiores que 0). Essas restrições devem ser respeitadas. O resultado deve ser a aplicação acrescida desses componentes.


**US03** - Criação de formulários no dispositivo móvel a partir do XML - campos de checkbox, seleção simples e seleção múltipla.

> TA3.1 - O software vai receber vários arquivos XML contendo instruções para a criação de checkbox, seleção simples e seleção mútipla na aplicação. O resultado deve ser a aplicação acrescida desses componentes.


**US04** - Criação de formulários no dispositivo móvel a partir do XML - campo de georreferenciamento (acessa o GPS ou mostra o mapa para o usuário mostrar a localização).

> TA4.1 - O software vai receber vários arquivos XML contendo instruções para a criação de um campo de georreferenciamento. O resultado deve ser a aplicação acrescida desse componente.


**US05** - Criação de formulários no dispositivo móvel a partir do XML - campo de captura de arquivos multimídia (acessa o sistema de arquivos ou acessa a câmera para capturar fotografia ou filmar).

> TA5.1 - O software vai receber vários arquivos XML contendo instruções para a criação de campos para conteúdo multimídia (fotos ou vídeos). O resultado deve ser a aplicação acrescida desses componentes.


**US06** - Gerar um arquivo XML com os dados preenchidos no formulário.

> TA6.1 - Com a aplicação já concluída após a leitura do arquivo XML, os campos do formulário serão preenchidos com dados pré-estabelecidos. O resultado deve ser a criação de um arquivo XML contendo esses dados corretamente.


**US07** - Enviar o XML com os dados do formulário para o servidor (o servidor deve ser especificado no XML de configuração do formulário).

> TA7.1 - Com o arquivo pronto, enviá-lo para o servidor e, em seguida, recuperá-lo do servidor e compará-lo com o arquivo original.


**US08** - Criar um XML com a descrição de um formulário para ser realizada a auditoria in loco de uma obra.

> TA8.1 - Baseando-se em um arquivo XML correto, a aplicação deverá criar um arquivo XML contendo todos os campos necessários para a realização de uma auditoria in loco de alguma obra. Depois disso, o arquivo resultante será comparado com o original.


**US09** - Parser no servidor dos dados preenchidos no formulário de auditoria in loco.

> TA9.1 - O componente servidor da arquitetura deverá analisar um arquivo XML, corretamente enviado pela componente cliente, verificando possíveis erros  para manipulá-lo antes de salvar no banco de dados


**US10** - Armazenamento no banco de dados dos dados de auditoria in loco.

> TA10.1 - O componente servidor da arquitetura deverá analisar um arquivo XML, corretamente enviado pela componente cliente, realizar o parser e salvá-lo no banco de dados de forma coerente.


**US11** - Visualização das informações de auditoria in loco de uma obra no mapa através de KML.

> TA11.1 - A aplicação cliente enviará um formulário de auditoria in loco corretamente preenchido. O componente servidor deverá analisá-lo, salvar as informações e criar um arquivo kml correspondente para ser exibido através de aplicação de mapas do dispositivo móvel.


# USERSTORIES e TESTES DE ACEITAÇÃO - Projeto 1 #

**Lista de User Stories**


**US01** - Implementar visualizaçao de obras públicas.

> TA1.1 - O usuário abrirá o mapa e irá visualizar as obras que estão na área visível do mapa.

> TA1.2 - O usuário realizará operações de "panning", sendo sempre mostradas as obras que estiverem na área visível.

> TA1.3 - O usuário realizará operações de "zooming", sendo sempre mostradas as obras que estiverem na área visível.

> TA1.4 - Quando o GPS estiver acionado, o mapa irá acompanhar a localização do usuário.

> TA1.5 - Quando o mapa for alterado para acompanhar a localização do usuário, as obras devem ser atualizadas.

> Estimativa inicial: 45h


**US02** - Visualização de informações das obras.

> TA2.1 - O usuário irá clicar em uma obra e as informações textuais desta serão exibidas.

> TA2.2 - Na janela de informações da obra, o usuário irá selecionar a aba de fotografias e as fotografias relacionadas a esta obra serão mostradas.

> TA2.3 - O usuário poderá visualizar cada fotografia com detalhes podendo ampliar para ver os detalhes e passar para outra fotografia.

> Estimativa inicial: 45h



**US03** -  Implementar a Denuncia textual.

> TA3.1 - O usuário irá clicar em uma obra e um botão destinado para denúncia será exibido.

> TA3.2 - O usuário irá clicar no botão de denúncia e será exibido um formulário para realização da denúncia.

> TA3.3 - O usuário irá preencher o campo textual e submeter a denúncia.

> Estimativa inicial: 45h



**US04** - Implementar a funcionalidade de denuncia atraves de upload de fotos.

> TA4.1 - Após o usuário clicar no botão de denúncia, será exibido também um campo para anexar um arquivo.

> TA4.2 - O usuário irá selecionar o arquivo a ser anexado e em seguida submeter a denúncia.

> Estimativa inicial: 45h



**US05** -  Implementar a funcionalidade para auditoria in loco.

> OBS.: Essa US foi remanejada para disciplina de Projeto 2.

> Testes de Aceitação: A serem descritos pelo cliente posteriormente.

> Estimativa inicial: 45h