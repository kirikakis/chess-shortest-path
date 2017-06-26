package com.github.kirikakis.chess.algorithms

import com.github.kirikakis.chess.ChessCoordinate
import com.github.kirikakis.chess.pieces.Knight
import spock.lang.Shared
import spock.lang.Specification

class BreadthFirstAlgorithmTest extends Specification {

    @Shared BreadthFirstAlgorithm breadthFirstAlgorithm

    void setupSpec() {
        breadthFirstAlgorithm = new BreadthFirstAlgorithm()
    }

    def "BreadthFirstAlgorithm E1 A7"() {
        given:
        ArrayList<ChessCoordinate> expectedShortestPath = new ArrayList<>()
        expectedShortestPath.add(new ChessCoordinate("C2"))
        expectedShortestPath.add(new ChessCoordinate("A3"))
        expectedShortestPath.add(new ChessCoordinate("B5"))
        expectedShortestPath.add(new ChessCoordinate("A7"))

        expect:
        breadthFirstAlgorithm.shortestPath( new ChessCoordinate("E1"),
                new ChessCoordinate("A7"),
                new Knight()) == expectedShortestPath
    }

    def "BreadthFirstAlgorithm E2 B5"() {
        given:
        ArrayList<ChessCoordinate> expectedShortestPath = new ArrayList<>()
        expectedShortestPath.add(new ChessCoordinate("C3"))
        expectedShortestPath.add(new ChessCoordinate("B5"))

        expect:
        breadthFirstAlgorithm.shortestPath( new ChessCoordinate("E2"),
                                            new ChessCoordinate("B5"),
                                            new Knight()) == expectedShortestPath
    }

    def "BreadthFirstAlgorithm A1 H8"() {
        given:
        ArrayList<ChessCoordinate> expectedShortestPath = new ArrayList<>()
        expectedShortestPath.add(new ChessCoordinate("B3"))
        expectedShortestPath.add(new ChessCoordinate("A5"))
        expectedShortestPath.add(new ChessCoordinate("B7"))
        expectedShortestPath.add(new ChessCoordinate("D6"))
        expectedShortestPath.add(new ChessCoordinate("F7"))
        expectedShortestPath.add(new ChessCoordinate("H8"))

        expect:
        breadthFirstAlgorithm.shortestPath( new ChessCoordinate("A1"),
                                            new ChessCoordinate("H8"),
                                            new Knight()) == expectedShortestPath
    }

    def "BreadthFirstAlgorithm B2 B6"() {
        given:
        ArrayList<ChessCoordinate> expectedShortestPath = new ArrayList<>()
        expectedShortestPath.add(new ChessCoordinate("A4"))
        expectedShortestPath.add(new ChessCoordinate("B6"))

        expect:
        breadthFirstAlgorithm.shortestPath( new ChessCoordinate("B2"),
                                            new ChessCoordinate("B6"),
                                            new Knight()) == expectedShortestPath
    }

    def "BreadthFirstAlgorithm H1 C5"() {
        given:
        ArrayList<ChessCoordinate> expectedShortestPath = new ArrayList<>()
        expectedShortestPath.add(new ChessCoordinate("F2"))
        expectedShortestPath.add(new ChessCoordinate("D3"))
        expectedShortestPath.add(new ChessCoordinate("C5"))

        expect:
        breadthFirstAlgorithm.shortestPath( new ChessCoordinate("H1"),
                                            new ChessCoordinate("C5"),
                                            new Knight()) == expectedShortestPath
    }

    def "BreadthFirstAlgorithm G8 H3"() {
        given:
        ArrayList<ChessCoordinate> expectedShortestPath = new ArrayList<>()
        expectedShortestPath.add(new ChessCoordinate("E7"))
        expectedShortestPath.add(new ChessCoordinate("D5"))
        expectedShortestPath.add(new ChessCoordinate("F4"))
        expectedShortestPath.add(new ChessCoordinate("H3"))

        expect:
        breadthFirstAlgorithm.shortestPath( new ChessCoordinate("G8"),
                                            new ChessCoordinate("H3"),
                                            new Knight()) == expectedShortestPath
    }
}
