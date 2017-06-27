# chess-shortest-path

Search to find the shortest path between a given starting position and end position on a chess board.

Right now this is just for a Knight but can easily extended to other pieces using the ChessPiece interface.

Outputs shortest path moves a Knight should take from the starting position provided up to the end position within given moves range.

Using [Breadth-first search](https://en.wikipedia.org/wiki/Breadth-first_search) algorithm, open architecture so can be replaced with another algorithm.

## prerequisites
* Gradle
* Java 1.8

## Build and Deploy
`
$ gradle build installDist
`

## Run with CLI
```
$ cd build/install/chess-shortest-path/bin
$ chess-shortest-path -start E1 -end A7
```
Note:
Positions are in algebraic chess notation

## Run with UI
```
$ cd build/install/chess-shortest-path/bin
$ chess-shortest-path
```
![chess-ui](chess-ui.png)

## Build and Run with Gradle
#### CLI
`
$ gradle build run -Dexec.args="-start E1 -end A7"
`
##### Result
```
:compileJava
:compileGroovy UP-TO-DATE
:processResources UP-TO-DATE
:classes
:run
C2 A3 B5 A7
```
#### UI
`
$ gradle build run
`

