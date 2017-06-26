package com.github.kirikakis.chess.algorithms;

import com.github.kirikakis.chess.ChessPieceInterface;
import com.github.kirikakis.chess.ShortestPathInterface;
import com.github.kirikakis.chess.ChessCoordinate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstAlgorithm implements ShortestPathInterface {

    @Override
    public ArrayList<ChessCoordinate> shortestPath(ChessCoordinate startPos,
                                                   ChessCoordinate endPos,
                                                   ChessPieceInterface pieceType) {

        ArrayList<ChessCoordinate> shortestPathCoordinates = new ArrayList<>();

        //Keep track of visited nodes and the parents of visited nodes (for finding the shortest path)
        HashMap<ChessCoordinate, ChessCoordinate> parentNode = new HashMap<>();

        //Queue of nodes to visit
        Queue<ChessCoordinate> positionQueue = new LinkedList<>();

        //intially add the starting node
        parentNode.put(startPos,null);
        positionQueue.add(startPos);

        //Breadth first search
        while (positionQueue.peek() != null) //check if anymore nodes to visit
        {
            ChessCoordinate currentPosition = positionQueue.poll();

            if (currentPosition.equals(endPos))
            {
                break; //we have reached the end position on the graph via the shortest path so stop searching
            }

            //otherwise get adjacent nodes (possible moves from current position for knight)
            ArrayList<ChessCoordinate> nextPositions = pieceType.validMoves(currentPosition);
            for (ChessCoordinate adjacentPosition : nextPositions)
            {
                //if this adjacent nodes is one that hasn't been visited add it to the queue
                //also keep track of the adjacent node's parent (the current node)
                if (!parentNode.containsKey(adjacentPosition))
                {
                    parentNode.put(adjacentPosition,currentPosition);
                    positionQueue.add(adjacentPosition);
                }
            }
        }

        //traverse back from end position coordinate to start position using the parent map to get shortest path
        //build up string of shortest path at same time
        ChessCoordinate currentNode = endPos; //start at the end node
        String shortestPath = "";
        while (parentNode.get(currentNode) != null) //stop once we are at the start node
        {
            shortestPathCoordinates.add(0, currentNode);
            shortestPath = String.format("%s %s", currentNode.toString(), shortestPath);
            currentNode = parentNode.get(currentNode);
        }

        if (shortestPath.length() == 0) //When start position = end position
        {
            shortestPath = startPos.toString();
        }

        //Print out the shortest path found, excluding start position and including end position
        System.out.println(shortestPath);

        return shortestPathCoordinates;
    }
}
