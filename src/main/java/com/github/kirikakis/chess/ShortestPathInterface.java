package com.github.kirikakis.chess;

import java.util.ArrayList;

public interface ShortestPathInterface {

    ArrayList<ChessCoordinate> shortestPath(ChessCoordinate startPos,
                                            ChessCoordinate endPos,
                                            ChessPieceInterface pieceType);
}
