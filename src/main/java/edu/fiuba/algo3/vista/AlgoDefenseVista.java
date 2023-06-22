package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.controladores.SiguienteTurnoHandler;
import edu.fiuba.algo3.controladores.UbicarTorreHandler;
import edu.fiuba.algo3.controladores.UbicarTrampaHandler;
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
    private static final int CELL_SIZE = 50;

    private AlgoDefense juego;
    private Coordenada ultimaCoordenada;
    private Parcela ultimaParcela;


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

        GridPane rootPane = new GridPane();
        rootPane.setPadding(new Insets(10));

        GridPane gridPane = createGridPane();
        configureGridConstraints(gridPane);
        displayMap(gridPane);

        rootPane.add(gridPane, 0, 0);
        rootPane.add(createButtonBox(), 1, 0);
        rootPane.setStyle("-fx-background-color: #FCFCF9; -fx-width: 100%;"); // Set the desired background color here

        scene.setRoot(rootPane);
        if (this.juego.finDelJuego() != null ) {
            this.nextVista.mostrar(scene);
        }
    }

    private GridPane createGridPane() {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
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

        gridPane.getStyleClass().add("tablero");
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
                e -> this.ultimaParcela = parcela);
        cellPane.getChildren().add(imageView);
        cellPane.getStyleClass().add("vistaParcela");

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
            case "S":
                return "file:" + basePath + "S.png";
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
        boton2.setOnAction(new UbicarTorreHandler(juego,this,"p"));

        Button boton3 = new Button("Torre Blanca");
        boton3.setOnAction(new UbicarTorreHandler(juego,this,"b"));

        Button boton4 = new Button("Trampa Arenosa");
        boton4.setOnAction(new UbicarTrampaHandler(juego,this));

        HBox buttonBox = new HBox(10, ejecutarTurnoButton, boton2, boton3, boton4);
        //buttonBox.setAlignment(Pos.CENTER_LEFT);
        buttonBox.getStyleClass().add("contenedorBotones");
        BorderPane container = new BorderPane();
        container.setRight(buttonBox);
        container.setPadding(new Insets(10));
        return new HBox(container);
    }

    public Coordenada getUltimaCoordenada(){

        return ultimaCoordenada;
    };

    public Parcela getUltimaParcela() {
        return this.ultimaParcela;
    }


}
