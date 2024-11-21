package org.Adivina;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Optional;
import java.util.Random;

public class AdivinaApp extends Application {

    private Button comprobarButton;
    private Label introduceNum;
    private TextField adivinaNum;
    private int numeroScreto;

    @Override
    public void start(Stage stage) throws Exception {

        Random random = new Random();
        numeroScreto = random.nextInt(100) + 1;

        // Texto para indicar el número
        introduceNum = new Label("Introduce un número del 1 al 100");

        // Cuadro de texto
        adivinaNum = new TextField();
        adivinaNum.setPrefColumnCount(5);
        adivinaNum.setPrefHeight(30);


        // Configuración del botón
        comprobarButton = new Button("Comprobar");
        comprobarButton.setDefaultButton(true);




        // Estilo del HBox
        HBox adivinaBox = new HBox(10, new Label(), adivinaNum);
        adivinaBox.setAlignment(Pos.CENTER);  // Alinear al centro

        //evento boton (llamen a dios)

        comprobarButton.setOnAction(e -> {
            try {
                int numeroAdivinado = Integer.parseInt(adivinaNum.getText());

                //(recordatorio bajar el rango para poder ver la pestaña ganadora)
                if (numeroAdivinado < 1 || numeroAdivinado > 100) {
                    // Alerta de error
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Dialog");
                    alert.setHeaderText("Entrada no válida");
                    alert.setContentText("¡Oops! El número debe estar entre 1 y 100.");
                    alert.showAndWait();
                    return;
                }

                // alerta positiva :)
                if (numeroAdivinado == numeroScreto) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmación");
                    alert.setHeaderText("¡Has ganado!");
                    alert.setContentText("¡Felicidades! Adivinaste el número. ¿Quieres jugar de nuevo?");

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.isPresent() && result.get() == ButtonType.OK) {
                        // Reiniciar el juego (recordatorio bajar el rango para poder ver la pestaña ganadora)
                        numeroScreto = random.nextInt(100) + 1;
                    }
                } else {
                    // Alerta de advertencia
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Advertencia");
                    alert.setHeaderText("Número incorrecto");
                    alert.setContentText("¡Inténtalo de nuevo!");
                    alert.showAndWait();
                }
            } catch (NumberFormatException ex) {
                // Alerta de error si la entrada no es un número válido
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText("Entrada no válida");
                alert.setContentText("¡Oops! Por favor, introduce un número del 1 al 100.");
                alert.showAndWait();
            }
        });


        // Estilo del VBox
        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(introduceNum, adivinaBox, comprobarButton);

        // Escena
        Scene scene = new Scene(vbox, 300, 200);
        stage.setScene(scene);
        stage.setTitle("AdivinApp");
        stage.show();
    }

}



