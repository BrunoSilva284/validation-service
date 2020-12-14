# Validation Service

Aplicação construída em Java utilizando framework Spring para validação de senhas com as seguintes definições:

- Nove ou mais caracteres;
- Ao menos 1 dígito;
- Ao menos 1 letra minúscula;
- Ao menos 1 letra maiúscula;
- Ao menos 1 caractere especial;
    - Válidos apenas os seguintes caracteres: !@#$%^&*()-+
- Não possuir caracteres repetidos dentro do conjunto;

## Instalação e Pré-Requisitos:
* GitBash
* Docker Client / Docker Desktop com suporte  a Docker Compose

## Passo a Passo :

* Realize o clone do seguinte repositório:
```sh
git clone https://github.com/BrunoSilva284/validation-service.git
```
* Acesse o diretório “validation-service”
```sh
cd validation-service
```
* Execute o comando para iniciar a aplicação:
```sh
docker run -p 8090:8090 -d brunosilva284/validation-service
```
* Acesse a aplicação em http://localhost:8090/swagger-ui/


## Decisões tomadas
* Foram utilizados os princípios SOLID onde:
  * A Service que faz a validação de senhas possui apenas essa responsabilidade;
  * É utilizada a inversão de dependência para acessar a função de validação através de interface;
  

* Foram criados testes unitários com alto coverage (100%) para testar possíveis ocorrências de senhas inválidas;
  * Calculado atráves do IntelliJ (Run Tests With Coverage);


* Foram criados testes de integração utilizando a biblioteca Cucumber:
  * Esses testes são executados utilizando REST e validando os cenários escritos em **validation.feature**
  * 95% de Coverage - calculado atráves do IntelliJ (Run Tests With Coverage);  


* Para validar a String de senha foi utilizado uma Regex que verifica:
  * A ocorrência de letras minúsculas, maiúsculas, números de 0-9 e os seguintes caracteres especiais: !@#$%^&*()-+
  * Escolhi utilizar Regex pois assim a solução fica mais elegante e com menos linhas de código do que utilizando diversas condições usando IFs;
  

* Para a validação de caracteres duplicados foi desenvolvida uma lógica para percorrer a String e contar o número de ocorrências para cada caracter.
Caso o caracter tenha mais que uma ocorrência a validação retorna uma booleana falsa;
  
  
* Durante a validação da senha, o processo é interrompido na primeira regra infringida;
  * Assim não é gasto um processamento testando todas as demais regras quando alguma delas já foi infringida; 

## Premissas
* Tive como premissa receber uma senha criptografada como input da API, e após sua decriptação, seguir com a validação da mesma.
  * Não é seguro transitar uma senha descriptografada, dessa forma imaginei que em uma implementação real deve ser feita uma lógica de descriptografia ao recebe-la
    e só entao fazer o processo de validação;