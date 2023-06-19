package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.controladores.SiguienteTurnoHandler;
import edu.fiuba.algo3.controladores.UbicarDefensaHandler;
import edu.fiuba.algo3.modelo.AlgoDefense;
import edu.fiuba.algo3.modelo.Observer;
import edu.fiuba.algo3.modelo.convertidor.FormatoJSONInvalidoException;
import edu.fiuba.algo3.modelo.defensas.Defensa;
import edu.fiuba.algo3.modelo.enemigos.Enemigo;
import edu.fiuba.algo3.modelo.juego.NombreInvalido;
import edu.fiuba.algo3.modelo.mapa.Coordenada;
import edu.fiuba.algo3.modelo.mapa.Mapa;
import edu.fiuba.algo3.modelo.parcelas.Parcela;
import edu.fiuba.algo3.modelo.parcelas.ParcelaDeTierra;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.List;

public class AlgoDefenseVista implements Observer, Vista {
    private Vista nextVista;
    private Scene scene;
    private List<Enemigo> enemigos;
    private Mapa mapa;
    private static final int GRID_SIZE = 15;
    private static final int CELL_SIZE = 30;

    private AlgoDefense juego;


    @Override
    public void update() {
        if (scene != null) {
            this.enemigos = juego.getEnemigos();
            this.mapa = juego.getMapa();
            mostrar(scene);
        }
    }

    @Override
    public void setNextVista(Vista nextVista) {
        this.nextVista = nextVista;
        if (scene != null) {
            mostrar(scene);
        }
    }

    @Override
    public void mostrar(Scene scene) {
        this.scene = scene;

        BorderPane rootPane = new BorderPane();
        rootPane.setPadding(new Insets(10));

        GridPane gridPane = createGridPane();
        configureGridConstraints(gridPane);
        displayMap(gridPane);

        rootPane.setCenter(gridPane);
        rootPane.setTop(new Pane());
        rootPane.setBottom(createButtonBox());

        scene.setRoot(rootPane);
    }

    private GridPane createGridPane() {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setStyle("-fx-background-color: #FCFCF9;"); // Set the desired background color here
        return gridPane;
    }

    private void configureGridConstraints(GridPane gridPane) {
        double cellSize = Math.min(scene.getWidth() / GRID_SIZE, scene.getHeight() / GRID_SIZE);

        for (int i = 0; i < GRID_SIZE; i++) {
            ColumnConstraints columnConstraints = new ColumnConstraints();
            columnConstraints.setPercentWidth(100.0 / GRID_SIZE);
            columnConstraints.setMaxWidth(cellSize);
            gridPane.getColumnConstraints().add(columnConstraints);
        }

        for (int i = 0; i < GRID_SIZE; i++) {
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setPercentHeight(100.0 / GRID_SIZE);
            rowConstraints.setMaxHeight(cellSize);
            gridPane.getRowConstraints().add(rowConstraints);
        }

        gridPane.setAlignment(Pos.CENTER);
    }

    private void displayMap(GridPane gridPane) {
        for (Parcela parcela : mapa.getParcelas()) {
            StackPane cellPane = createCellPane(parcela);
            int x = parcela.getCoordenada().getAbscisa();
            int y = parcela.getCoordenada().getOrdenada();

            gridPane.add(cellPane, x, y);
            displayEnemyInParcela(parcela, cellPane);
            displayDefenseInParcela(parcela,cellPane);
        }
    }

    private StackPane createCellPane(Parcela parcela) {
        StackPane cellPane = new StackPane();
        ImageView imageView = createParcelaImageView(parcela);
        imageView.setOnMouseClicked(
                e -> 			System.out.println("Clicked on parcela at position: " + parcela.getCoordenada()));
        cellPane.getChildren().add(imageView);
        return cellPane;
    }

    private ImageView createParcelaImageView(Parcela parcela) {
        String imagePath = getImagePath(parcela); // Get the path to the image based on the letter of the Parcela
        Image image = new Image(imagePath, CELL_SIZE, CELL_SIZE, true, true);
        ImageView imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        imageView.setOpacity(0.9);
        return imageView;
    }

    private String getImagePath(Parcela parcela) {
        String basePath = "src/resources/";
        switch (parcela.toString()) {
            case "T":
                return "file:" + basePath + "T.png";
            case "R":
                return "file:" + basePath + "R.png";
            default:
                return "file:" + basePath + "C.png";
        }
    }

    private void displayEnemyInParcela(Parcela parcela, StackPane cellPane) {
        for (Enemigo enemigo : enemigos) {
            Parcela pasarelaActual = enemigo.getPasarelaActual();
            Coordenada coordenada = pasarelaActual.getCoordenada();//
            //Si esa parcela en el gridpane tiene un enemigo,lo coloco ahi
            if (parcela.getCoordenada().equals(coordenada)) {
                Label enemyLabel = createEnemyLabel(enemigo);
                cellPane.getChildren().add(enemyLabel);
            }
        }
    }


    private void displayDefenseInParcela(Parcela parcela, StackPane cellPane) {
        for (ParcelaDeTierra parcela1 : juego.getDefensas()) {
            Coordenada coordenada = parcela1.getCoordenada();//
            //Si esa parcela en el gridpane tiene un enemigo,lo coloco ahi
            if (parcela.getCoordenada().equals(coordenada)) {
                Label enemyLabel = createDefenseLabel(parcela1.getDefensa());
                cellPane.getChildren().add(enemyLabel);
            }
        }
    }

    private Label createEnemyLabel(Enemigo enemigo) {
        Label enemyLabel = new Label(enemigo.toString());
        enemyLabel.setStyle("-fx-font-size: 8px; -fx-text-fill: red");
        return enemyLabel;
    }


    private Label createDefenseLabel(Defensa defensa) {
        Label enemyLabel = new Label(defensa.toString());
        enemyLabel.setStyle("-fx-font-size: 8px; -fx-text-fill: red");
        return enemyLabel;
    }

    public void setAlgoDefense(AlgoDefense algoDefense) {
        this.enemigos = algoDefense.getEnemigos();
        this.mapa = algoDefense.getMapa();
        this.juego = algoDefense;
        try {
            this.juego.agregarJugador("pepito");
        } catch (NombreInvalido e) {
            throw new RuntimeException(e);
        } catch (FormatoJSONInvalidoException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private HBox createButtonBox() {
        Button ejecutarTurnoButton = new Button("Ejecutar Turno IA");
        ejecutarTurnoButton.setOnAction(new SiguienteTurnoHandler(juego, this));

        Button boton2 = new Button("Torre plateada");
        boton2.setOnAction(new UbicarDefensaHandler(juego,this));

        Button boton3 = new Button("Boton 3");
        boton3.setOnAction(e -> {
            // Lógica del botón 3
        });

        Button boton4 = new Button("Boton 4");
        boton4.setOnAction(e -> {
            // Lógica del botón 4
        });

        HBox buttonBox = new HBox(10, ejecutarTurnoButton, boton2, boton3, boton4);
        buttonBox.setAlignment(Pos.CENTER_LEFT);
        BorderPane container = new BorderPane();
        container.setLeft(buttonBox);
        container.setPadding(new Insets(10));
        return new HBox(container);
    }



}
