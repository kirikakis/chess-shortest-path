package com.github.kirikakis.chess;

import java.util.ArrayList;

public interface ChessPieceInterface {
    ArrayList<ChessCoordinate> validMoves(ChessCoordinate startPosition);
}
