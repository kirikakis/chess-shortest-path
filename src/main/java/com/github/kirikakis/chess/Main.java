package com.github.kirikakis.chess;

import com.beust.jcommander.JCommander;
import com.github.kirikakis.chess.algorithms.BreadthFirstAlgorithm;
import com.github.kirikakis.chess.cli.ChessArgs;
import com.github.kirikakis.chess.pieces.Knight;
import com.github.kirikakis.chess.ui.Controller;

import java.awt.*;
import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private final int maxMoves = 3;

    @Override
    public void start(Stage primaryStage) throws Exception {

        ShortestPathInterface shortestPathAlgorithm = new BreadthFirstAlgorithm();

        if(!getParameters().getRaw().isEmpty()) {
            ChessArgs chessArgs = new ChessArgs();
            JCommander commander = JCommander.newBuilder().addObject(chessArgs).build();
            commander.setProgramName("chess-shortest-path");
            List<String> args = getParameters().getUnnamed();
            commander.parse(args.toArray(new String[args.size()]));

            boolean argsPassed = true;

            if (chessArgs.startPosition == null) {
                System.err.println("Must include start position\r\n");
                argsPassed = false;
            }

            if (chessArgs.endPosition == null) {
                System.err.println("Must include end position\r\n");
                argsPassed = false;
            }

            if (!argsPassed) {
                commander.usage();
            }
            else {
                if(shortestPathAlgorithm.shortestPath( new ChessCoordinate(chessArgs.startPosition),
                                                    new ChessCoordinate(chessArgs.endPosition),
                                                    new Knight()).size() > maxMoves) {
                    System.out.println("Moves needed are more than " + this.maxMoves);
                    System.exit(0);
                }
            }
        }
        else {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("chess.fxml"));
            Controller controller = new Controller(shortestPathAlgorithm, maxMoves);
            loader.setController(controller);
            Parent root = loader.load();

            primaryStage.setTitle("Knight Shortest Path");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
