# AutomatoFTC - ptBR
Aplicativo que constrói um autômato finito de N estados e verifica se dada uma palavra se ela pertence a gramática
representado pelo autômato.

### Como usar
Todas as linhas devem acabar com ponto e vígula e comentários são suportados e devem iniciar com // (dupla barra).

A primeira linha deve conter todos os estados do autômato, devidadamente nomeados sendo que cada estado deve estar separado
por um espaço em branco.

A segunda linha deve conter o alfabeto o qual deve obecer as mesmas regras de formatação para os estados.

As próximas linhas devem conter as transições, onde cada transição está em uma linha diferente, sendo que somente a última
linha das transições deve acabar com ponto e vírgula, todas as outras devem acabar com somente vírgula. Cada linha equivale
as transições de um estado e eles serão interpretados na ordem que informados, logo a primeira linha contém todas as
transições possíveis para o primeiro estado na ordem que o alfabeto foi informado.

As próximas duas linhas correspondem aos estados inicial e final (pode ser mais de um estado final), nesta ordem.
Logo após isso, as próximas linhas estão sujeitas as mesmas regras das linhas de transições e devem conter as palavras
que se deseja testar.

Exemplo:
> input.txt
>
> 1 2 3; // Estados
> 
> a b; // alfabeto
>
> //Transicoes
> 2 3, // Corresponde ao primeiro estado '1'. Logo vai de 1 para 2 com a letra 'a' e de 1 para 3 com a letra 'b'
>
> 1 3, // Corresponde ao segundo estado '2'. Sai de 2 para 1 com a letra a e sai de 2 para 3 com a letra b
>
> 3 3; // Corresponde ao terceiro estado '3'
>
> 1; // Estado inicial
>
> 3; // Estado final
>
> //Palavras
> abbabb,
> aba,
> bbb;

Esse arquivo representa este autômato:

![example](https://github.com/jpmoura/AutomatoFTC/blob/master/example.png?raw=true)


### A fazer

- Interface gráfica;
- Adicionar comentários em inglês no arquivo-fonte;

# AutomatoFTC - enUS
Application that builds a finite state automata that verifies if a word belongs to a grammar or not.

### How to use
All lines must end with semicolon and comments are supported and must initialize with // (double slash)

The first line must contain all automata states named properly and every state must be separated by a blank space.

Second line must have the alphabet e is subjected to the same rules from first line.

In the next lines the transitions are necessary. Each transaction line represents all transitions of one state, i.e. the first
transition line must contain all transitions for the first state informed in the first line and so on.
Only the last line ends with a semicolon, all the others transitions lines must end with comma.

The next two lines represents the initial and final states in that order. Right after those lines, the follow lines are
subject to the same rules of the transitions lines and must have the words which will be tested.

Example:
> input.txt
>
> 1 2 3; // States
> 
> a b; // Alphabet
>
> //Transitions
> 2 3, // Represents the first state. Goes to 2 from 1 with A and goes to 3 from 1 with B
>
> 1 3, // Represents the second state. Goes to 1 from 2 with A and goes to 3 from 2 with B
>
> 3 3; // Represents de third state. It's a loop.
>
> 1; // Initial state
>
> 3; // Final state
>
> // Words
> abbabb,
> aba,
> bbb;

This file represents the follow automata:

![example](https://github.com/jpmoura/AutomatoFTC/blob/master/example.png?raw=true)

### TODO

- Graphical User Interface;
- Add english comments in the source files;