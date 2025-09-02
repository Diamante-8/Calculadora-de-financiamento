Calculadora de Financiamentos e Inflações em Java
Uma aplicação Java para cálculo de financiamentos e correção de valores pela inflação, desenvolvida para aprimorar o raciocínio lógico e a manipulação de dados numéricos.

Funcionalidades:
Cálculo de Financiamentos:
- Sistema Price (Parcelas fixas)
- Sistema SAC (Amortização Constante)
- Comparação entre diferentes taxas de juros


Correção por Inflação:
- Baseada em dados históricos do IPCA
- Cálculo de perda do poder de compra
- Projeção de valores futuros considerando inflação


Cálculos Financeiros:
- Juros compostos
- Taxa real (descontando a inflação)
- Amortização de dívidas


Como Executar:
Pré-requisitos
Java JDK 8 ou superior instalado


Compilação e Execução:
- Clone ou baixe o arquivo CalculadoraFinanciamento.java
- Abra o terminal na pasta do arquivo
- Compile o programa:

bash
javac CalculadoraFinanciamento.java
Execute o programa:

bash
java CalculadoraFinanciamento


Como Usar
Ao executar o programa, você verá um menu com as opções:

Calcular Financiamento:
Informe o valor, taxa de juros, número de parcelas e sistema de amortização
Escolha se deseja corrigir o valor pela inflação
Corrigir Valor pela Inflação:
Informe o valor a ser corrigido e o período em meses
Veja o valor corrigido e a perda do poder de compra

Comparar Financiamentos:
Compare duas opções de financiamento com taxas diferentes
Veja a diferença no valor total pago e nas parcelas

Exemplo de Saída:
text
=== CALCULADORA DE FINANCIAMENTOS E INFLAÇÃO ===
1 - Calcular Financiamento
2 - Corrigir Valor pela Inflação
3 - Comparar Financiamentos
Escolha uma opção: 1

=== CÁLCULO DE FINANCIAMENTO ===
Valor financiado: R$ 20000
Taxa de juros mensal (%): 1.5
Número de parcelas: 24
Sistema (1-Price, 2-SAC): 1
Corrigir pela inflação? (1-Sim, 2-Não): 1

=== RESULTADOS ===
Valor financiado: R$ 20000.00
Taxa de juros: 1.50% ao mês
Número de parcelas: 24
Inflação acumulada estimada: 15.23%
Valor da parcela: R$ 998.38
Total pago: R$ 23961.12
Total de juros: R$ 3961.12
Valor corrigido pela inflação: R$ 23046.00


Tecnologias Utilizadas:

Java: Linguagem de programação principal

Scanner: Para entrada de dados do usuário

ArrayList: Para armazenamento das parcelas no sistema SAC

Math: Para cálculos matemáticos complexos (potência, etc.)

Conceitos Financeiros Implementados:
- Sistema Price: Parcelas constantes com juros decrescentes
- Sistema SAC: Amortização constante com parcelas decrescentes
- Juros Compostos: Cálculo de montante com juros sobre juros
- Inflação: Correção monetária baseada em dados do IPCA
- Taxa Real: Taxa de juros descontada a inflação

Desenvolvimento de Habilidades: 
Este projeto foi desenvolvido para aprimorar:
Raciocínio Lógico: Implementação de algoritmos financeiros complexos
Manipulação de Dados Numéricos: Trabalho com valores monetários e percentuais
Orientação a Objetos: Organização do código em classes e métodos
Interface de Usuário: Interação via terminal de forma intuitiva

Licença
Este projeto é open source e está disponível sob a licença MIT.

🤝 Contribuições
Contribuições são sempre bem-vindas! Sinta-se à vontade para:

Fork este repositório

Criar uma branch para sua feature (git checkout -b feature/AmazingFeature)

Commit suas mudanças (git commit -m 'Add some AmazingFeature')

Push para a branch (git push origin feature/AmazingFeature)

Abrir um Pull Request

📧 Contato
Se você tiver alguma dúvida ou sugestão, sinta-se à vontade para entrar em contato.
