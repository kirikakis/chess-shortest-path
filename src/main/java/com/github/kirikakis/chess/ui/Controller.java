package com.github.kirikakis.chess.ui;

import com.github.kirikakis.chess.ChessCoordinate;
import com.github.kirikakis.chess.Main;
import com.github.kirikakis.chess.ShortestPathInterface;
import com.github.kirikakis.chess.pieces.Knight;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class Controller {

    @FXML
    private GridPane chessGrid;

    @FXML
    private Button resetBtn;

    @FXML
    private void initialize() {
        addGridITems();
        resetBtn.setOnMouseClicked(e -> {
            ArrayList<Node> imagesToRemove = new ArrayList<>();
            for (Node node : chessGrid.getChildren()) {
                if(node instanceof TextArea) {
                    node.setStyle(null);
                    ((TextArea) node).setText("");
                }
                else if(node instanceof ImageView) {
                    imagesToRemove.add(node);
                }
            }
            for(Node node : imagesToRemove) {
                chessGrid.getChildren().remove(node);
            }

            isStartSet = false;
            isStopSet = false;
        });
    }

    private boolean isStartSet = false;
    private boolean isStopSet = false;


    ChessCoordinate startPosition = new ChessCoordinate(1,1);

    private ShortestPathInterface shortestPathAlgorithm;
    private int maxMoves;

    public Controller(ShortestPathInterface shortestPathAlgorithm, int maxMoves) {
        this.shortestPathAlgorithm = shortestPathAlgorithm;
        this.maxMoves = maxMoves;
    }

    private void addGridITems() {

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

                TextArea label = new TextArea();
                label.setPrefRowCount(i);
                label.setPrefColumnCount(j);
                chessGrid.add(label, j, i);

                GridPane.setFillWidth(label, true);
                GridPane.setFillHeight(label, true);

                label.setOnMouseClicked(e -> {
                    setClickEventHandler(label);
                });
            }
        }
    }

    private void setClickEventHandler(TextArea label) {
        ChessCoordinate stopPosition;
        if(!isStartSet) {
            label.setText("START");
            label.setStyle("-fx-background-color: green;");

            isStartSet = true;
            startPosition = new ChessCoordinate(label.getPrefColumnCount() + 1,
                    8 - label.getPrefRowCount());

            addImageToGridItem("startImage", label.getPrefColumnCount(), label.getPrefRowCount());
            System.out.println("Start Position:" + startPosition.toString());
        }
        else if(!isStopSet) {
            label.setText("END");
            label.setStyle("-fx-background-color: orange;");
            isStopSet = true;
            stopPosition = new ChessCoordinate( label.getPrefColumnCount() + 1,
                    8 - label.getPrefRowCount());

            addImageToGridItem("stopImage", label.getPrefColumnCount(), label.getPrefRowCount());
            System.out.println("Stop Position:" + stopPosition.toString());

            calculateShortestPathAndFindGridItem(startPosition, stopPosition);
        }
    }

    private void addImageToGridItem(String imageId, int xIndex, int yIndex) {
        ImageView startImage = new ImageView();
        startImage.setId(imageId);
        startImage.setImage(new Image(getClass().getClassLoader().getResource("knight.jpg").toString()));
        chessGrid.add(startImage, xIndex, yIndex);
        GridPane.setHalignment(startImage, HPos.CENTER);
    }

    private void calculateShortestPathAndFindGridItem(ChessCoordinate startPosition, ChessCoordinate stopPosition) {
        ArrayList<ChessCoordinate> chessCoordinates = shortestPathAlgorithm.shortestPath( startPosition,
                stopPosition,
                new Knight());

        for (int k = 0; k < chessCoordinates.size(); k++) {
            ChessCoordinate chessCoordinate = chessCoordinates.get(k);
            for (Node node : chessGrid.getChildren()) {
                if(node instanceof TextArea) {
                    int x = ((TextArea) node).getPrefColumnCount() + 1;
                    int y = 8 - ((TextArea) node).getPrefRowCount();
                    ChessCoordinate nodeChessCoordinate = new ChessCoordinate(x, y);
                    if( nodeChessCoordinate.equals(chessCoordinate) &&
                            !nodeChessCoordinate.equals(stopPosition)) {

                        node.setStyle("-fx-background-color: red;");
                        ((TextArea) node).setText(String.valueOf(k + 1));
                    }
                }
            }
        }
        if(chessCoordinates.size() > this.maxMoves) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Out of Moves");
            alert.setContentText("Moves needed are more than " + this.maxMoves);
            alert.show();
        }
    }
}
