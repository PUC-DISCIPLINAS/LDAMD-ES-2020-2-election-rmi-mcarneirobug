# Eleições com Java RMI <img src="https://media.giphy.com/media/WUlplcMpOCEmTGBtBW/giphy.gif" width="30">
> Este trabalho é baseado numa proposta do livro do Coulouris, Dollimore, Kinberg, Blair (2013), pag. 227.
<h1 align="center">
    <a href="https://www.java.com/pt_BR/">🔗 Java</a>
</h1>  
<p align="center">🚀 utilizado para desenvolver um serviço <b>Election</b> utilizando o Java RMI.</p>

<p align="center">
 <a href="#instruções">Instruções</a> •
 <a href="#java-rmi">RMI</a> •   
 <a href="#aluno">Aluno</a> • 
 <a href="#professor-responsável">Professor</a> 
</p>

## Instruções 

Considere uma interface Election que fornece dois métodos remotos:

- **vote**: este método possui dois parâmetros por meio dos quais o cliente fornece o nome de um candidato (um string) e o “identificador de eleitor” (um hash MD5 usado para garantir que cada usuário vote apenas uma vez).
- Os identificadores de eleitor devem ser gerados a partir de uma função MD5 do nome completo do eleitor.
- **result**: este método possui dois parâmetros com os quais o servidor fornece para o cliente o nome de um candidato e o número de votos desse candidato.

- Desenvolva um sistema para o serviço Election utilizando o Java RMI, que garanta que seus registros permaneçam consistentes quando ele é acessado simultaneamente por vários clientes. O serviço Election deve garantir que todos os votos sejam armazenados com segurança, mesmo quando o processo servidor falha. Considerando que o Java RMI possui semântica at-most-once, implemente um mecanismo de recuperação de falha no cliente que consiga obter uma semântica exactly-once para o caso do serviço ser interrompido por um tempo inferior a 30 segundos.

## Java RMI

- O <a href="https://web.fe.up.pt/~eol/AIAD/aulas/JINIdocs/rmi1.html">RMI (Remote Method Invocation)</a> é uma tecnologia suportada pela plataforma Java que facilita o desenvolvimento de aplicações distribuídas. Como o próprio nome indica, o RMI permite ao programador invocar  métodos de objectos remotos, ou seja que estão alojados em máquinas virtuais Java distintas, duma forma muito semelhante às invocações a objectos locais. De certa forma, à custa de algum esforço adicional de engenharia de software, o programador pode desenvolver aplicações totalmente distribuídas como se de aplicações locais se tratassem, sendo quase toda a comunicação entre máquinas virtuais Java assegurada transparentemente pelo próprio RMI.

## Aluno

* Matheus Santos Rosa Carneiro - 613414 - [mcarneirobug](https://github.com/mcarneirobug)

## Professor responsável

* Hugo Bastos de Paula - [hugodepaula](https://github.com/hugodepaula)
