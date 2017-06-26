package com.github.kirikakis.chess.pieces

import com.github.kirikakis.chess.ChessCoordinate
import spock.lang.Specification

class KnightTest extends Specification {

    void setup() {
    }

    void cleanup() {
    }

    def "ValidMoves"() {
        given:
        Knight knightPiece = new Knight()

        ChessCoordinate givenCoordinate1 = new ChessCoordinate(1,1)

        ArrayList<ChessCoordinate> expectedValidMoves1 = new ArrayList<>()
        expectedValidMoves1.add(new ChessCoordinate(2,3))
        expectedValidMoves1.add(new ChessCoordinate(3,2))

        ChessCoordinate givenCoordinate2 = new ChessCoordinate(5,5)

        ArrayList<ChessCoordinate> expectedValidMoves2 = new ArrayList<>()
        expectedValidMoves2.add(new ChessCoordinate(3,4))
        expectedValidMoves2.add(new ChessCoordinate(3,6))
        expectedValidMoves2.add(new ChessCoordinate(4,3))
        expectedValidMoves2.add(new ChessCoordinate(4,7))
        expectedValidMoves2.add(new ChessCoordinate(6,3))
        expectedValidMoves2.add(new ChessCoordinate(6,7))
        expectedValidMoves2.add(new ChessCoordinate(7,4))
        expectedValidMoves2.add(new ChessCoordinate(7,6))

        expect:
        knightPiece.validMoves(givenCoordinate1) == expectedValidMoves1
        knightPiece.validMoves(givenCoordinate2) == expectedValidMoves2
    }
}