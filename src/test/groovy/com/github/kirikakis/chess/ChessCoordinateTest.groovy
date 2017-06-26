package com.github.kirikakis.chess

import spock.lang.Specification

class ChessCoordinateTest extends Specification {

    def "FromInt"() {
        expect:
        ChessCoordinate.AlgebraicXCoordinate.fromInt(1) == ChessCoordinate.AlgebraicXCoordinate.A
        ChessCoordinate.AlgebraicXCoordinate.fromInt(2) == ChessCoordinate.AlgebraicXCoordinate.B
        ChessCoordinate.AlgebraicXCoordinate.fromInt(3) == ChessCoordinate.AlgebraicXCoordinate.C
        ChessCoordinate.AlgebraicXCoordinate.fromInt(4) == ChessCoordinate.AlgebraicXCoordinate.D
        ChessCoordinate.AlgebraicXCoordinate.fromInt(5) == ChessCoordinate.AlgebraicXCoordinate.E
        ChessCoordinate.AlgebraicXCoordinate.fromInt(6) == ChessCoordinate.AlgebraicXCoordinate.F
        ChessCoordinate.AlgebraicXCoordinate.fromInt(7) == ChessCoordinate.AlgebraicXCoordinate.G
        ChessCoordinate.AlgebraicXCoordinate.fromInt(8) == ChessCoordinate.AlgebraicXCoordinate.H
    }

    def "FromInt IllegalArgumentException"() {
        when:
        ChessCoordinate.AlgebraicXCoordinate.fromInt(9)

        then:
        IllegalArgumentException ex = thrown()
        ex.message == "Invalid position co-ordinate for algebraic chess notation."
    }

    def "ChessCoordinate xy coordinate"() {
        given:
        ChessCoordinate chessCoordinate

        when:
        chessCoordinate = new ChessCoordinate(1,1)

        then:
        chessCoordinate.x == 1
        chessCoordinate.y == 1
        chessCoordinate.toString() == "A1"
    }

    def "ChessCoordinate xy coordinate IllegalArgumentException"() {
        when:
        new ChessCoordinate(11,1)

        then:
        IllegalArgumentException ex = thrown()
        ex.message == "Invalid position co-ordinate. Board size is: 8 by 8"
    }

    def "ChessCoordinate algebra coordinate"() {
        given:
        ChessCoordinate chessCoordinate

        when:
        chessCoordinate = new ChessCoordinate("A1")

        then:
        chessCoordinate.x == 1
        chessCoordinate.y == 1
        chessCoordinate.toString() == "A1"
    }

    def "ChessCoordinate algebra coordinate Length"() {
        when:
        new ChessCoordinate("A11")

        then:
        IllegalArgumentException ex = thrown()
        ex.message == "Invalid algebraic length. Board size is: 8 by 8"
    }

    def "ChessCoordinate algebra coordinate Invalid X"() {
        when:
        new ChessCoordinate("R1")

        then:
        IllegalArgumentException ex = thrown()
        ex.message == "Invalid horizontal algebraic co-ordinate. Board size is: 8 by 8"
    }

    def "ChessCoordinate algebra coordinate Invalid Y"() {
        when:
        new ChessCoordinate("Ae")

        then:
        IllegalArgumentException ex = thrown()
        ex.message == "Invalid vertical position co-ordinate. Board size is: 8 by 8"
    }

    def "isValidCoordinate() >> true"() {
        expect:
        ChessCoordinate.isValidCoordinate(8, 8)
    }

    def "isValidCoordinate() >> false"() {
        expect:
        !ChessCoordinate.isValidCoordinate(8, 9)
    }

    def "ChessCoordinate equals() >> true"() {
        given:
        ChessCoordinate chessCoordinate1 = new ChessCoordinate(2,7)
        ChessCoordinate chessCoordinate2 = new ChessCoordinate(2,7)

        expect:
        chessCoordinate1.equals(chessCoordinate2)
    }

    def "ChessCoordinate equals() >> false"() {
        given:
        ChessCoordinate chessCoordinate1 = new ChessCoordinate(2,7)
        ChessCoordinate chessCoordinate2 = new ChessCoordinate(2,8)

        expect:
        !chessCoordinate1.equals(chessCoordinate2)
    }

    def "ChessCoordinate equals() not instanceof ChessCoordinate >> false"() {
        given:
        ChessCoordinate chessCoordinate1 = new ChessCoordinate(2,7)

        String otherObject = new String()

        expect:
        //noinspection GrEqualsBetweenInconvertibleTypes
        !chessCoordinate1.equals(otherObject)
    }

    def "ChessCoordinate hashCode()"() {
        given:
        ChessCoordinate chessCoordinate1 = new ChessCoordinate(2,7)
        ChessCoordinate chessCoordinate2 = new ChessCoordinate(2,8)
        ChessCoordinate chessCoordinate3 = new ChessCoordinate(7,1)

        expect:
        chessCoordinate1.hashCode() == 27
        chessCoordinate2.hashCode() == 28
        chessCoordinate3.hashCode() == 71
    }
}
