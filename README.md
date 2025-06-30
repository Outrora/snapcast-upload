# 📦 Snapcast Upload 
![JAVA](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![](https://img.shields.io/badge/Amazon_AWS-FF9900?style=for-the-badge&logo=amazonaws&logoColor=white)
![Quarkus](https://img.shields.io/badge/QUARKUS-009CAB?style=for-the-badge&logo=quarkus&logoColor=white)
![Kakfa](https://img.shields.io/badge/Apache_Kafka-231F20?style=for-the-badge&logo=apache-kafka&logoColor=white)

## 📝 Descrição
Serviço responsável pelo upload de vídeos para o SnapCast.

## ⚡ Principais Funcionalidades
 - 🤝 Integração com AWS Cognito para autenticação OAuth2
 - 🪪 Suporte a token Bearer
 - 🚪 Configuração de caminhos públicos que não requerem autenticação
 - ⚡ Cache de chaves JWKS para melhor performance
 - 📚 Documentação API
 - 🧭 Interface Swagger/OpenAPI disponível


### 📖 Endpoints de documentação:
```declarative
/swagger-ui
/swagger
/openapi
```

## ⚙️ Configurações Técnicas
- ⏱️ Timeout de conexão OIDC: 30 segundos
- 🗝️ Cache de chaves JWKS: 10 entradas
- ⏳ Tempo de vida do cache: 10 minutos

## 🛠️ Como Executar

### 🔧 Configuração
Necessário configurar as seguintes variáveis de ambiente:

```
KAFKA_URL=
BUCKET=
AWS_REGION=
CLIENT_ID=
USER_POOL_ID=
DOMAIN=
```
E depois roda o comando para iniciar o serviço:
```shell
./mvnw quarkus:dev
```



### 🧪 Testes
Execute:
```shell script
./mvnw test
```

## Diagrama de Arquitetura

[![](https://mermaid.ink/img/pako:eNpNUt1u2jAUfpUj96aTAsoPBPDFtAClnaZtSLSbtISL08QhFomdOTalAx6pT9EXm0Ngmq_Oz_d958fnQFKZMUJJXsqXtECl4XGeCLAvip8ag4rLNTDvE_R6H493YscRdu9vGZNHmMYrgXWKjYanupSYQbT8vO7I0zM-MpoJzVPLsbzv1i18OMIsjn6uYCY3gmt5IcxaAiwOP5jiuWUoeJRbJk7QpRdnvRWvjnAfL5VMWdNYTKR-G767anSgb2h7e4gXvIJMwqI0-2u-Mc8bhXUBFwHZhe87bSx3CNgJHmEe364CmJp0y_SH9f_ApXku25HYzs5mkXfxbVRjWjD4gvkWr2AmskQkotGvJYMHyHlZ0pup3z6n0coOR2-CILjYvRee6YIO6n0i7LYPgIJXqLkUFPJ2w6dEEIdsFM8I1cowh1RMVdi65NBWTIguWMUSQq2ZodomJBEny6lR_JKyutKUNJuC0BzLxnqmzlCzOUe7mOpfVNnmmZpJIzShnh8GZxVCD2RPqD92--F4OJ64w8CdBO7QIa-EBmF_OPHd0cT3xq4XhIOTQ_6cy7r9iRcGg4EbDgcjd-R6oUNYZn9efe1O73yBp784AM3S?type=png)](https://mermaid.live/edit#pako:eNpNUt1u2jAUfpUj96aTAsoPBPDFtAClnaZtSLSbtISL08QhFomdOTalAx6pT9EXm0Ngmq_Oz_d958fnQFKZMUJJXsqXtECl4XGeCLAvip8ag4rLNTDvE_R6H493YscRdu9vGZNHmMYrgXWKjYanupSYQbT8vO7I0zM-MpoJzVPLsbzv1i18OMIsjn6uYCY3gmt5IcxaAiwOP5jiuWUoeJRbJk7QpRdnvRWvjnAfL5VMWdNYTKR-G767anSgb2h7e4gXvIJMwqI0-2u-Mc8bhXUBFwHZhe87bSx3CNgJHmEe364CmJp0y_SH9f_ApXku25HYzs5mkXfxbVRjWjD4gvkWr2AmskQkotGvJYMHyHlZ0pup3z6n0coOR2-CILjYvRee6YIO6n0i7LYPgIJXqLkUFPJ2w6dEEIdsFM8I1cowh1RMVdi65NBWTIguWMUSQq2ZodomJBEny6lR_JKyutKUNJuC0BzLxnqmzlCzOUe7mOpfVNnmmZpJIzShnh8GZxVCD2RPqD92--F4OJ64w8CdBO7QIa-EBmF_OPHd0cT3xq4XhIOTQ_6cy7r9iRcGg4EbDgcjd-R6oUNYZn9efe1O73yBp784AM3S)

## 📄 Documentação Técnica
 - 🏷️ Versão da API: 1.0.0
 - 📜 Licença: Apache 2.0
 - 📧 Contato: mrpauloii@gmail.com

