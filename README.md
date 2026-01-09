# Gravação de Feedback

Serviço responsável por gravar feedbacks dos alunos.

Para cada feedback gravado, uma função lambda de análise de avaliação é acionada para processá-lo.

Foi desenvolvido para ser utilizado como uma função lambda na AWS. É necessário configurar um banco de dados relacional (MySQL, PostgreSQL, etc.).


## Instalação

1 - Clone o repositório:
```bash
git clone https://github.com/raclug/feedback.git
cd feedback
```

2 - Gere o artefato jar:
```bash
./mvnw clean package
```

3 - Faça o deploy na AWS Lambda:

- Crie uma nova função Lambda na AWS.

- Faça o upload do arquivo `target/avalidacao-service-0.0.1-SNAPSHOT-aws.jar`

- Configure o handler como `org.springframework.cloud.function.adapter.aws.FunctionInvoker`

- Adicione as variáveis de ambiente necessárias: `SPRING_DATASOURCE_URL, SPRING_DATASOURCE_USERNAME e SPRING_DATASOURCE_PASSWORD` com os valores do banco de dados e `AWS_LAMBDA_TARGET_FUNCTION_NAME` com o nome da lambda de análise de avaliação.

- Coloque a Lambda na mesma VPC do banco de dados, se aplicável.

- Configure um private endpoint para o serviço Lambda.

- Na role da lambda adicine uma política com permissão para executar a função lambda de análise de avaliação.



## Execução

A função lambda espera receber um payload no seguinte formato JSON:

```json
{
  "descricao": "string",
  "nota": 10
}
```