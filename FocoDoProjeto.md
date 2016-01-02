**Foco do Projeto**

O foco do projeto mudou completamente. O que antes seria uma aplicação própria desenvolvida na linguagem C# para rodar em dispositivos móveis Windows Mobile, agora será uma camada adicionada a aplicação Google Maps Mobile.

O Google disponibiliza uma aplicação gratuita chamada Google Maps Mobile que disponibiliza diversos serviços para usuários utilizarem informações de posicionamento geográfico entre outros.

Dentre os diversos serviços disponibilizados, é possível adicionar uma camada desenvolvida separadamente para exibir pontos sobre os mapas já disponibilizados pelo Google. Essa camada nada mais é que um arquivo KML que feito de acordo com os padrões do Google pode ser interpretado pela aplicação e exibido pontos no mapa.

Nosso projeto utilizará essa funcionalidade para exibir as obras do Tribunal de Contas do Estado da Paraíba (TCE-PB). O KML será gerado por uma aplicação desenvolvida pelos envolvidos no projeto e disponíbilizada no servidor http://buchada.dsc.ufcg.edu.br/geopbmobile. Os dados contidos no KML são obtidos através de requisições http. Os dados são requisitados diretamente ao servidor de dados do TCE-PB.
Uma vez que o usuário pesquise no Google Maps Mobile a camada disponibilizada por nosso projeto, será exibida as obras do TCE-PB.

Inicialmento o KML possuiria um _networklink_. Isto significa que a cada operação do usuário uma nova requisição seria feita a esse _networklink_ alterando apenas o _bouding_ _box_ referente a área exibida no mapa. Foi identificado que diferentemente do Google Maps tradicional (do _browser_), o Google Maps Mobile não suporta _networklink_. Desta maneira o KML será carregado por completo (cada obra, uma por uma). Este fato gerou um novo risco ao projeto.

Adicionalmente, é possível associar eventos ao clique nos pontos que estão sendo exibidos. É com essa funcionalidade que nossa aplicação irá mostrar os detalhes de uma obra do TCE-PB e disponibilizar as operações de denuncia textual, upload de fotos e auditoria _in_ _loco_. Essas funcionalidades serão implementadas com o uso de html associado ao arquivos KML que é interpretado pelo Google Maps Mobile.