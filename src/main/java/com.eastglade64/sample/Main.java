package com.eastglade64.sample;

import com.eastglade64.model.EventType;
import com.eastglade64.model.TriggerFamily;
import com.eastglade64.model.exception.TransformationException;
import com.eastglade64.transformation.RTriggerTransformation;

import java.util.Arrays;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    public void start(final Stage primaryStage) throws Exception {
        primaryStage.setTitle("Triggaware");
        HBox box1 = new HBox();

        box1.setPadding(new Insets(10.0D, 10.0D, 10.0D, 10.0D));
        TextArea userTextAreaInput = new TextArea();

        userTextAreaInput.setMinWidth(500.0D);
        userTextAreaInput.setMinHeight(800.0D);

        TextArea userTextAreaOutput = new TextArea();

        userTextAreaOutput.setMinWidth(800.0D);
        userTextAreaOutput.setMinHeight(800.0D);

        VBox vBoxPulsanti = new VBox(50.0D);
        vBoxPulsanti.setAlignment(Pos.CENTER);
        vBoxPulsanti.setPadding(new Insets(50.0D, 5.0D, 50.0D, 5.0D));

        ComboBox<EventType> eventTypeCB = new ComboBox<>(FXCollections.observableArrayList(EventType.values()));
        ComboBox<TriggerFamily> eventTypeRNR = new ComboBox<>(FXCollections.observableArrayList(TriggerFamily.values()));

        Button buttonTrigga = new Button("Trigga");
        Button buttonMassiva = new Button("Massiva");
        Button buttonExit = new Button("Exit");

        vBoxPulsanti.getChildren().addAll(Arrays.asList((Node[]) new Control[]{eventTypeCB, eventTypeRNR, buttonTrigga, buttonMassiva, buttonExit}));
        box1.getChildren().addAll(Arrays.asList((Node[]) new Region[]{userTextAreaInput, vBoxPulsanti, userTextAreaOutput}));

        buttonTrigga.setOnAction(event -> {
            if (eventTypeCB.getValue() == null) {
                userTextAreaOutput.setText("pirla valorizza il combo");
            } else {
                try {
                    String res = new RTriggerTransformation(eventTypeCB.getValue()).transform(userTextAreaInput.getText());
                    userTextAreaOutput.setText(res);
                } catch (TransformationException e) {
                    e.printStackTrace();
                    userTextAreaOutput.setText(e.getMessage());
                }
            }
        });

        buttonMassiva.setOnAction(event -> {
            if (eventTypeCB.getValue() == null) {
                userTextAreaOutput.setText("pirla valorizza il combo");
            } else {
                try {
                    String res = new RTriggerTransformation(eventTypeCB.getValue()).transform(userTextAreaInput.getText());
                    userTextAreaOutput.setText(res);
                } catch (TransformationException e) {
                    e.printStackTrace();
                    userTextAreaOutput.setText(e.getMessage());
                }
            }
        });

        buttonExit.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                primaryStage.close();
            }
        });

        primaryStage.setScene(new Scene(box1));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
