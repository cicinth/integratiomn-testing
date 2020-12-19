#language: pt
@RunTests
Funcionalidade: Process CRUD


  Contexto:
    Dado usuario gostaria de salvar um processo
    E usuario informa vara com valor igual "Pernambuco"
    E usuario informa numero_processo com valor igual "700"
    E usuario informa natureza com valor igual "Civil"
    E usuario informa partes com valor igual "Paulo X Queitilin"
    E usuario informa urgente com valor igual "S"
    E usuario informa arbitramento com valor igual "S"
    E usuario informa assistente_social com valor igual "Cinthia"
    E usuario informa data_entrada com valor igual "2020-12-19"
    E usuario informa data_saida com valor igual "2021-01-03"
    E usuario informa data_agendamento com valor igual "2020-12-20"
    E usuario informa status com valor igual "Aguardando"
    E usuario informa observacao com valor igual "Testando com backgound"

  Cenario: Usuario gostaria de salvar um processo via POST
    Dado usuario gostaria de salvar um processo
    E usuario informa vara com valor igual "São Paulo"
    E usuario informa numero_processo com valor igual "30"
    E usuario informa natureza com valor igual "Trabalhista"
    E usuario informa partes com valor igual "Cinthia X Sheila"
    E usuario informa urgente com valor igual "S"
    E usuario informa arbitramento com valor igual "S"
    E usuario informa assistente_social com valor igual "Queitilin"
    E usuario informa data_entrada com valor igual "2020-12-19"
    E usuario informa data_saida com valor igual "2021-04-05"
    E usuario informa data_agendamento com valor igual "2021-01-20"
    E usuario informa status com valor igual "Aguardando"
    E usuario informa observacao com valor igual "Teste via POST "
    Quando usuario clica no botão salvar
    Então usuario devera ver a mensagem de "Salvo com sucesso"

  Cenario: Usuario visualiza processo criado via GET
    Dado usuario quer ver as informacoes do processo especifico "3444"
    Quando usuario clicar no botao exibir
    Então usuario devera ver a mensagem de "Sucesso"

  Cenario: Usuario visualiza processo  apos salvar o mesmo via POST
    Dado usuario deseja ver as informacoes do processo
    E usuario informa vara com valor igual "Bahia"
    E usuario informa numero_processo com valor igual "2323"
    E usuario informa natureza com valor igual "Cívil"
    E usuario informa partes com valor igual "Rene X Margarida"
    E usuario informa urgente com valor igual "N"
    E usuario informa arbitramento com valor igual "S"
    E usuario informa assistente_social com valor igual "Ramona"
    E usuario informa data_entrada com valor igual "2020-12-20"
    E usuario informa data_saida com valor igual "2021-04-02"
    E usuario informa data_saida com valor igual "2021-04-05"
    E usuario informa data_agendamento com valor igual "2021-01-20"
    E usuario informa status com valor igual "Concluida"
    E usuario informa observacao com valor igual "Teste via GET apos salvar"
    Quando usuario clica no botão salvar
    E usuario deseja ver as informacoes do processo
    Então usuario devera ver a mensagem de "Sucesso"
    E usuario podera ver numero_processo com valor "2323"
    E usuario podera ver data_agendamento com valor "2021-01-22"

  Cenario: Usuario atualiza um processo via PUT apos salvar via POST com Background
    Quando usuario clica no botão salvar
    E usuario informa vara com valor igual "São Paulo"
    E usuario informa numero_processo com valor igual "700"
    E usuario informa natureza com valor igual "Trabalhista"
    E usuario informa partes com valor igual "Cinthia X Paulo"
    E usuario informa urgente com valor igual "S"
    E usuario informa arbitramento com valor igual "S"
    E usuario informa assistente_social com valor igual "Queitilin"
    E usuario informa data_entrada com valor igual "2020-12-19"
    E usuario informa data_agendamento com valor igual "2020-12-20"
    E usuario informa status com valor igual "Concluído"
    E usuario informa observacao com valor igual "Teste editado"
    E usuario clica outra vez no botao salvar
    E usuario deseja ver as informacoes do processo
    Então usuario devera ver a mensagem de "Sucesso"
    E usuario podera ver numero_processo com valor "700"
    E usuario podera ver status com valor "Concluído"

  Cenario: Usuario deleta um processo apos salvar via POST
    Quando usuario clica no botão salvar
    E usuario clica em deletar
    Então usuario devera ver a mensagem de "Deletado com sucesso"

  Esquema do Cenario: : Validação de POST via Scenario Outline. Teste: <mensagem retorno>
    Dado usuario gostaria de salvar um processo
    E usuario informa vara com valor igual "São Paulo"
    E usuario informa numero_processo com valor igual "2020"
    E usuario informa natureza com valor igual "<natureza>"
    E usuario informa partes com valor igual "Fernanda X Gabriel"
    E usuario informa urgente com valor igual "S"
    E usuario informa arbitramento com valor igual "S"
    E usuario informa assistente_social com valor igual "Kild"
    E usuario informa data_entrada com valor igual "2020-12-10"
    E usuario informa data_saida com valor igual "2021-01-06"
    E usuario informa data_agendamento com valor igual "2020-12-27"
    E usuario informa status com valor igual "Concluído"
    E usuario informa observacao com valor igual "Cenario Outline"

    Exemplos:
      | natureza | mensagem retorno        |
      | Civil    | Salvo com sucesso       |
      | Crimical | Nao processado          |