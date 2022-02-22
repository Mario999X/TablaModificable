package com.example.tablamodificable;

import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class AppPrincipalController implements Initializable {

    private boolean desplegado;
    private TranslateTransition animation, animationBtn;

    @FXML
    private HBox hBoxDatos;

    @FXML
    private Button btnMenu;

    @FXML
    private TextField introducirNombre, introducirFecha;

    @FXML
    private TableView vistaTabla;

    @FXML
    private TableColumn<Pelicula, String> columnNombre, columnFecha;

    @FXML
    private ObservableList<Pelicula> datosLista;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        hBoxDatos.setTranslateY(120);
        desplegado = false;
        datosLista = FXCollections.observableArrayList();
        cargarTabla();

    }

    @FXML
    private void desplegacion(){
        animation = new TranslateTransition(Duration.millis(300), hBoxDatos);
        animationBtn = new TranslateTransition(Duration.millis(300), btnMenu);

        if (!desplegado) {
            animation.setFromY(hBoxDatos.getHeight());
            animation.setToY(0);
            animationBtn.setFromY(0);
            animationBtn.setToY(-hBoxDatos.getHeight());
            desplegado = true;
        } else {
            animation.setFromY(0);
            animation.setToY(hBoxDatos.getHeight());
            animationBtn.setFromY(-hBoxDatos.getHeight());
            animationBtn.setToY(0);
            desplegado = false;
        }
        animation.play();
        animationBtn.play();
    }


    @FXML
    private void introducirDatos(){

        if (!Objects.equals(introducirNombre.getText(), "") && !Objects.equals(introducirFecha.getText(), "")){
            datosLista.add(new Pelicula(introducirNombre.getText(), introducirFecha.getText()));
        } else {
            System.out.println("Uno de los dos valores esta vacio");
        }

    }

    private void cargarTabla(){
        vistaTabla.setEditable(true);
        columnNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnNombre.setCellFactory(TextFieldTableCell.forTableColumn());
        columnNombre.setOnEditCommit(data ->{
            if (data != null) {
                data.getRowValue().setNombre(data.getNewValue());
            }
        });
        columnFecha.setCellValueFactory(new PropertyValueFactory<>("fechaLanzamiento"));
        columnFecha.setCellFactory(TextFieldTableCell.forTableColumn());
        columnFecha.setOnEditCommit(data ->{
            if (data != null) {
                data.getRowValue().setFechaLanzamiento(data.getNewValue());
            }
        });

        vistaTabla.setItems(datosLista);
    }
}
