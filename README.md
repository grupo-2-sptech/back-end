# back-end

# Melhores Práticas para Commits e Criação de Branches no Desenvolvimento Backend

## Introdução
No desenvolvimento de backend, a colaboração eficaz e a organização do código são fundamentais. O Git e plataformas como o GitHub oferecem ferramentas poderosas para gerenciar esse processo. Para garantir um fluxo de trabalho suave e colaborativo, é essencial seguir algumas melhores práticas ao fazer commits e criar novas branches.

## Commits

- **Commits Atômicos:** Realize commits que representem uma única alteração lógica. Isso facilita a compreensão das mudanças e permite reverter problemas com precisão, se necessário.

- **Mensagens Descritivas:** Escreva mensagens de commit claras e descritivas. Use um formato consistente, como "Adicionar", "Corrigir" ou "Atualizar", seguido de uma breve descrição do que foi feito.

  Exemplo: "Adicionar endpoint de autenticação /Corrigir validação de entrada no endpoint X"
  
- **Evite Commits Grandes:** Commits com muitas alterações dificultam a revisão e podem causar problemas na integração. Tente manter os commits focados e limitados ao escopo de uma funcionalidade.

## Branches

- **Nomenclatura Significativa:** Dê nomes descritivos às suas branches. Use padrões como "feature/nome-da-funcionalidade" ou "fix/nome-do-bug" para indicar o propósito da branch.

- **Crie Branches para Novas Funcionalidades ou Correções:** Sempre crie uma nova branch ao trabalhar em uma nova funcionalidade ou correção. Isso mantém o código principal (geralmente a branch "master" ou "main") limpo e estável.

- **Branches Curtas de Vida:** Evite manter branches longas e não utilizadas. Uma vez que a funcionalidade ou correção for concluída e revisada, faça um merge na branch principal e delete a branch secundária.

- **Pull Requests e Revisões de Código:** Ao concluir o trabalho em uma branch, crie um Pull Request para que outros membros da equipe revisem o código. Isso ajuda a identificar problemas antes de integrar as alterações.

## Prefixos usados ao criar Branches

- **feature/:** Usado para criar branches que implementam novas funcionalidades no código.

Exemplo: `feature/adicionar-autenticacao`

- **fix/:** Utilizado para corrigir bugs ou problemas no código existente.

Exemplo: `fix/corrigir-validacao-entrada`

- **hotfix/:** Similar a "fix/", usado para correções urgentes que precisam ser implementadas rapidamente.

Exemplo: `hotfix/corrigir-falha-crucial`

- **refactor/:** Indica que a branch é focada em refatorar o código existente.

Exemplo: `refactor/otimizar-rotina-de-dados`

- **chore/:** Usado para atividades de manutenção que não alteram o comportamento funcional do código.

Exemplo: `chore/atualizar-dependencias`

- **docs/:** Utilizado para atualizações ou adições à documentação do projeto.

Exemplo: `docs/atualizar-documentacao-api`

- **style/:** Indica mudanças que afetam apenas a formatação do código.

Exemplo: `style/ajustar-indentacao`

- **test/:** Utilizado para adicionar ou atualizar testes automatizados.

Exemplo: `test/adicionar-testes-autenticacao`

## DER
![collecta](https://github.com/grupo-2-sptech/back-end/assets/110926828/59158efd-3dca-4038-9278-38ae8526dd98)

