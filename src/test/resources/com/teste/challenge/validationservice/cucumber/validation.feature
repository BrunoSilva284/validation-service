# language: pt
@validar
Funcionalidade: Validar senha conforme as regras:
  1- Nove ou mais caracteres
  2- Ao menos 1 dígito
  3- Ao menos 1 letra minúscula
  4- Ao menos 1 letra maiúscula
  5- Ao menos 1 caractere especial
    5.1- Considere como especial os seguintes caracteres: !@#$%^&*()-+
  6- Não possuir caracteres repetidos dentro do conjunto

  @testarSenha
  Esquema do Cenario: Validar uma senha
    Dado Que sera recebida uma senha criptografada
    Quando Receber a senha: <senha>
    Entao A senha sera validada conforme as regras e o resultado sera apresentado: <resultadoEsperado>
    Exemplos:
      | senha       | resultadoEsperado |
      | ""          | "false"             |
      | "aa"        | "false"             |
      | "ab"        | "false"             |
      | "AAAbbbCc"  | "false"             |
      | "AbTp9!foo" | "false"             |
      | "AbTp9!foA" | "false"             |
      | "AbTp9 fok" | "false"             |
      | "AbTp9!fok" | "true"              |