package com.eastglade64;

import com.eastglade64.model.EventType;
import com.eastglade64.model.exception.TransformationException;
import com.eastglade64.transformation.MassivaTriggerTransformation;
import com.eastglade64.transformation.RTriggerTransformation;
import com.eastglade64.transformation.TransformationFactory;
import com.eastglade64.transformation.TransformationResult;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Arrays;

public class Main extends Application {
    public void start(final Stage primaryStage) {
        primaryStage.setTitle("Triggaware");
        HBox box1 = new HBox();

        box1.setPadding(new Insets(10.0D, 10.0D, 10.0D, 10.0D));
        TextArea userTextAreaInput = new TextArea();

        userTextAreaInput.setPrefWidth(600);
        userTextAreaInput.setPrefHeight(600);

        TextArea userTextAreaOutput = new TextArea();

        userTextAreaOutput.setPrefWidth(600);
        userTextAreaOutput.setPrefHeight(600);

        TextArea infoBox = new TextArea();

        infoBox.setPrefWidth(600);
        infoBox.setPrefHeight(50);
        //infoBox.setMinHeight(30);

        SplitPane outputBox = new SplitPane();
        outputBox.setOrientation(Orientation.VERTICAL);
        outputBox.getItems().addAll(infoBox, userTextAreaOutput);
        outputBox.setDividerPosition(0, .2);

        VBox vBoxPulsanti = new VBox(50.0D);
        vBoxPulsanti.setAlignment(Pos.CENTER);
        vBoxPulsanti.setPadding(new Insets(50.0D, 5.0D, 50.0D, 5.0D));

        ComboBox<EventType> eventTypeCB = new ComboBox<>(FXCollections.observableArrayList(EventType.values()));

        Button buttonTrigga = new Button("Trigga");
        Button buttonMassiva = new Button("Massiva");
        Button buttonExit = new Button("Exit");

        vBoxPulsanti.getChildren().addAll(Arrays.asList(eventTypeCB, buttonTrigga, buttonMassiva, buttonExit));
        box1.getChildren().addAll(Arrays.asList(userTextAreaInput, vBoxPulsanti, outputBox));

        buttonTrigga.setOnAction(event -> {
            if (eventTypeCB.getValue() == null) {
                infoBox.setText("pirla valorizza il combo");
            } else {
                try {
                    TransformationResult res = TransformationFactory
                            .forEventType(eventTypeCB.getValue())
                            .transform(userTextAreaInput.getText());

                    userTextAreaOutput.setText(res.getOutput());
                    infoBox.setText(res.getInfo());
                } catch (TransformationException e) {
                    e.printStackTrace();
                    infoBox.setText(e.getMessage());
                }
            }
        });

        buttonMassiva.setOnAction(event -> {
            if (eventTypeCB.getValue() == null) {
                infoBox.setText("pirla valorizza il combo");
            } else {
                try {
                    TransformationResult res = new MassivaTriggerTransformation(eventTypeCB.getValue())
                            .transform(userTextAreaInput.getText());
                    userTextAreaOutput.setText(res.getOutput());
                    infoBox.setText(res.getInfo());
                } catch (TransformationException e) {
                    e.printStackTrace();
                    infoBox.setText(e.getMessage());
                }
            }
        });

        buttonExit.setOnAction(e -> primaryStage.close());

        primaryStage.setScene(new Scene(box1));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
