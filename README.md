# Eleições com Java RMI <img src="https://media.giphy.com/media/WUlplcMpOCEmTGBtBW/giphy.gif" width="30">
> Este trabalho é baseado numa proposta do livro do Coulouris, Dollimore, Kinberg, Blair (2013), pag. 227.
<h1 align="center">
    <a href="https://www.java.com/pt_BR/">🔗 Java</a>
</h1>  
<p align="center">🚀 utilizado para desenvolver um serviço <b>Election</b> utilizando o Java RMI.</p>

## Instruções 

Considere uma interface Election que fornece dois métodos remotos:

- **vote**: este método possui dois parâmetros por meio dos quais o cliente fornece o nome de um candidato (um string) e o “identificador de eleitor” (um hash MD5 usado para garantir que cada usuário vote apenas uma vez).
- Os identificadores de eleitor devem ser gerados a partir de uma função MD5 do nome completo do eleitor.
- **result**: este método possui dois parâmetros com os quais o servidor fornece para o cliente o nome de um candidato e o número de votos desse candidato.

- Desenvolva um sistema para o serviço Election utilizando o Java RMI, que garanta que seus registros permaneçam consistentes quando ele é acessado simultaneamente por vários clientes. O serviço Election deve garantir que todos os votos sejam armazenados com segurança, mesmo quando o processo servidor falha. Considerando que o Java RMI possui semântica at-most-once, implemente um mecanismo de recuperação de falha no cliente que consiga obter uma semântica exactly-once para o caso do serviço ser interrompido por um tempo inferior a 30 segundos.

## Aluno

* Matheus Santos Rosa Carneiro - 613414 - [mcarneirobug](https://github.com/mcarneirobug)

## Professor responsável

* Hugo Bastos de Paula - [hugodepaula](https://github.com/hugodepaula)
