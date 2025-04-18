/**
@author MuhammadHasnatRasool
 PeriodicTable Controller class
 28/9/24

 all rights reserved!
 **/
package org.htech.interactiveperiodictable.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Dragboard;
import javafx.scene.input.KeyCharacterCombination;
import javafx.scene.input.KeyCode;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import org.htech.interactiveperiodictable.layout.CompoundFinder;
import org.htech.interactiveperiodictable.layout.Layouts;
import org.htech.interactiveperiodictable.modal.Element;

import static org.htech.interactiveperiodictable.utils.UtilityMethods.*;

public class PeriodicTableController extends Application {

    private Element currentElement;
    private final ArrayList<Element> combinedElements = new ArrayList();
    Scene scene;
    VBox vbox;
    GridPane grid;
    StackPane combinePane;
    StackPane circlePane;
    private boolean showStateColors = false;

    public PeriodicTableController() {
    }

    public void start(Stage primaryStage) {
        primaryStage.setTitle("Interactive Periodic Table");
        BorderPane root = new BorderPane();
        this.vbox = new VBox(6.0F);
        this.vbox.setMaxWidth(300.0F);
        this.vbox.setMaxHeight(700.0F);
        this.vbox.setLayoutX(900.0F);
        this.vbox.setLayoutY(0.0F);
        this.vbox.setStyle("-fx-border-color: black; -fx-border-width: 2;");
        this.grid = new GridPane();
        this.grid.setPadding(new Insets(10.0F));
        this.grid.setVgap(5.0F);
        this.grid.setHgap(5.0F);
        this.grid.add(this.vbox, 18, 0, 50, 10);
        TextField searchField = new TextField();
        searchField.setPromptText("Enter element name or atomic number");
        Button searchButton = new Button("Search");
        searchButton.setOnAction((var2) -> this.searchElement(searchField.getText()));

        Button toggleStateButton = new Button("Show States");
        toggleStateButton.setOnAction(e -> {
            this.showStateColors = !this.showStateColors;
            if (this.showStateColors) {
                toggleStateButton.setText("Hide States");
                updateElementStates();
            } else {
                toggleStateButton.setText("Show States");
                resetElementColors();
            }
        });

        this.combinePane = new StackPane();
        Tooltip tooltip = new Tooltip("Drag and drop elements here: ");
        Tooltip.install(this.combinePane, tooltip);
        this.combinePane.setPrefSize(200.0F, 200.0F);
        this.combinePane.setStyle("-fx-background-color: lightgray; -fx-border-color: black; -fx-border-width: 2px;");
        this.combinePane.setAlignment(Pos.CENTER);
        this.combinePane.setOnDragOver((event) -> {
            if (event.getGestureSource() != this.combinePane && event.getDragboard().hasContent(Element.ELEMENT_DATA_FORMAT)) {
                event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
            }

            event.consume();
        });
        this.combinePane.setOnDragDropped((event) -> {
            Dragboard db = event.getDragboard();
            if (db.hasContent(Element.ELEMENT_DATA_FORMAT)) {
                String data = (String)db.getContent(Element.ELEMENT_DATA_FORMAT);
                Element element = this.findElementBySymbol(data);
                if (element != null) {
                    this.combinedElements.add(element);
                    this.updateCombinePane();
                    this.updateCompoundInfo(this.combinedElements);
                }

                event.setDropCompleted(true);
            }

            event.consume();
        });
        Button clearBtn = new Button("Clear");
        clearBtn.setFont(new Font("Poppins", (double)14.0F));
        clearBtn.setStyle("-fx-background-color: white");
        clearBtn.setStyle("-fx-text-fill: black");
        clearBtn.setOnAction((var1) -> this.clearCombinedElements());
        StackPane circlePane = this.createCirclePane();
        circlePane.setOnDragOver((event) -> {
            if (event.getGestureSource() != circlePane && event.getDragboard().hasContent(Element.ELEMENT_DATA_FORMAT)) {
                event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
            }

            event.consume();
        });
        circlePane.setOnDragDropped((event) -> {
            Dragboard db = event.getDragboard();
            if (db.hasContent(Element.ELEMENT_DATA_FORMAT)) {
                String data = (String)db.getContent(Element.ELEMENT_DATA_FORMAT);
                Element element = this.findElementBySymbol(data);
                if (element != null) {
                    this.currentElement = element;
                    this.updateCirclePane();
                }

                event.setDropCompleted(true);
            }

            event.consume();
        });

        GridPane stateLegend = createStateLegend();

        this.vbox.getChildren().addAll(searchField, searchButton, toggleStateButton, stateLegend, circlePane, this.combinePane, clearBtn);
        Layouts.initializeElements();

        for(int row = 0; row < Layouts.elements.size(); ++row) {
            for(int col = 0; col < Layouts.elements.get(row).size(); ++col) {
                Element element = (Element)((ArrayList<?>)Layouts.elements.get(row)).get(col);
                StackPane pane = this.createElementPane(element);
                pane.getStyleClass().add("element-pane");
                this.grid.add(pane, col, row);
            }
        }

        root.setCenter(this.grid);
        this.scene = new Scene(root, 1200.0F, 650.0F);
        this.scene.setOnKeyPressed((event) -> {
            if (event.isControlDown() && event.getCode() == KeyCode.F) {
                boolean isVisible = searchField.isVisible();
                searchField.setVisible(!isVisible);
                searchButton.setVisible(!isVisible);
                searchField.requestFocus();
            } else if (event.isControlDown() && event.getCode() == KeyCode.H) {
                showAbout();
            }

        });
        primaryStage.getIcons().add(new Image(String.valueOf(this.getClass().getResource("/icons/periodic.jpeg"))));
        this.scene.getStylesheets().add(Objects.requireNonNull(this.getClass().getResource("/css/style.css")).toExternalForm());
        primaryStage.setScene(this.scene);
        primaryStage.setMinHeight(580.0F);
        primaryStage.setMinWidth(1050.0F);
        primaryStage.show();
        showAbout();
    }

    private GridPane createStateLegend() {
        GridPane legend = new GridPane();
        legend.setHgap(5);
        legend.setVgap(5);
        legend.setPadding(new Insets(5));
        legend.setStyle("-fx-border-color: black; -fx-border-width: 1px;");

        Circle solidCircle = new Circle(10, getStateColor("solid"));
        Text solidText = new Text("Solid");
        legend.add(solidCircle, 0, 0);
        legend.add(solidText, 1, 0);

        Circle liquidCircle = new Circle(10, getStateColor("liquid"));
        Text liquidText = new Text("Liquid");
        legend.add(liquidCircle, 0, 1);
        legend.add(liquidText, 1, 1);

        Circle gasCircle = new Circle(10, getStateColor("gas"));
        Text gasText = new Text("Gas");
        legend.add(gasCircle, 0, 2);
        legend.add(gasText, 1, 2);

        Circle unknownCircle = new Circle(10, getStateColor("unknown"));
        Text unknownText = new Text("Unknown/Synthetic");
        legend.add(unknownCircle, 0, 3);
        legend.add(unknownText, 1, 3);

        return legend;
    }

    private void updateElementStates() {
        for(int row = 0; row < Layouts.elements.size(); ++row) {
            for(int col = 0; col < Layouts.elements.get(row).size(); ++col) {
                Element element = (Element)((ArrayList<?>)Layouts.elements.get(row)).get(col);
                if (element != null) {
                    updateElementStateColor(row, col, element);
                }
            }
        }
    }

    private void updateElementStateColor(int row, int col, Element element) {
        for(Node node : this.grid.getChildren()) {
            if (node instanceof StackPane pane) {
                Integer rowIndex = GridPane.getRowIndex(pane);
                Integer colIndex = GridPane.getColumnIndex(pane);
                if (rowIndex != null && colIndex != null && rowIndex == row && colIndex == col) {
                    String state = getElementState(element.getAtomicNumber());
                    Color stateColor = getStateColor(state);
                    pane.setStyle("-fx-background-color: " + toRgbString(stateColor) + "; -fx-border-color: black; -fx-border-width: 1px;");

                    for (Node child : pane.getChildren()) {
                        if (child instanceof Text) {
                            Text stateIndicator = new Text(state.substring(0, 1).toUpperCase());
                            stateIndicator.setFont(new Font("Poppins", 10));
                            stateIndicator.setTranslateX(20);
                            stateIndicator.setTranslateY(-20);
                            stateIndicator.setStyle("-fx-font-weight: bold;");
                            if (!pane.getChildren().contains(stateIndicator)) {
                                pane.getChildren().add(stateIndicator);
                            }
                            break;
                        }
                    }
                    break;
                }
            }
        }
    }

    private void resetElementColors() {
        for(int row = 0; row < Layouts.elements.size(); ++row) {
            for(int col = 0; col < Layouts.elements.get(row).size(); ++col) {
                Element element = (Element)((ArrayList<?>)Layouts.elements.get(row)).get(col);
                if (element != null) {
                    resetElementColor(row, col, element);
                }
            }
        }
    }

    private void resetElementColor(int row, int col, Element element) {
        for(Node node : this.grid.getChildren()) {
            if (node instanceof StackPane pane) {
                Integer rowIndex = GridPane.getRowIndex(pane);
                Integer colIndex = GridPane.getColumnIndex(pane);
                if (rowIndex != null && colIndex != null && rowIndex == row && colIndex == col) {
                    pane.setStyle("-fx-background-color: " + toRgbString(element.getColor()) + "; -fx-border-color: black; -fx-border-width: 1px;");

                    ArrayList<Node> toRemove = new ArrayList<>();
                    for (Node child : pane.getChildren()) {
                        if (child instanceof Text text) {
                            if (text.getText().length() == 1 &&
                                    (text.getText().equals("S") || text.getText().equals("L") ||
                                            text.getText().equals("G") || text.getText().equals("U"))) {
                                toRemove.add(child);
                            }
                        }
                    }
                    pane.getChildren().removeAll(toRemove);
                    break;
                }
            }
        }
    }

    private StackPane createCirclePane() {
        this.circlePane = new StackPane();
        Circle circle = new Circle(100.0F, Color.LIGHTBLUE);
        circle.setStroke(Color.BLACK);
        circle.setStrokeWidth(2.0F);
        circle.setFill(Color.LIGHTBLUE);
        Text text = new Text("Electron Shells\nDrag elements here");
        text.setTextAlignment(TextAlignment.CENTER);
        text.setWrappingWidth(200.0F);
        this.circlePane.getChildren().addAll(circle, text);
        this.circlePane.setPrefSize(200.0F, 210.0F);
        this.circlePane.setLayoutX(0.0F);
        this.circlePane.setLayoutY(250.0F);
        this.circlePane.setMaxWidth(250.0F);
        this.circlePane.setMaxHeight(210.0F);
        this.circlePane.setStyle("-fx-background-color: lightgray; -fx-border-color: black; -fx-border-width: 2px;");
        this.circlePane.setAlignment(Pos.CENTER);
        Tooltip tooltip = new Tooltip("Drag and drop elements here: ");
        Tooltip.install(this.circlePane, tooltip);
        return this.circlePane;
    }

    private StackPane createElementPane(Element element) {
        StackPane pane = new StackPane();
        if (element != null) {
            String var10001 = toRgbString(element.getColor());
            pane.setStyle("-fx-background-color: " + var10001 + "; -fx-border-color: black; -fx-border-width: 1px;");
            pane.setPrefSize(100.0F, 100.0F);
            pane.setAlignment(Pos.CENTER);
            int var10002 = element.getAtomicNumber();
            Text text = new Text(var10002 + "\n" + element.getSymbol());
            text.setFont(new Font("Poppins", 14.0F));
            text.setTextAlignment(TextAlignment.CENTER);
            pane.getChildren().add(text);

            String state = getElementState(element.getAtomicNumber());
            String var10 = element.getName();
            Tooltip tooltip = new Tooltip(var10 + "\nMass: " + element.getMass() +
                    "\nState at room temp: " + state);
            Tooltip.install(pane, tooltip);

            pane.setOnDragDetected((event) -> {
                Dragboard db = pane.startDragAndDrop(TransferMode.MOVE);
                db.setDragView(pane.snapshot(null, null));
                db.setContent(element.getElementData());
                event.consume();
            });
            pane.setOnDragOver((event) -> {
                if (event.getGestureSource() != pane && event.getDragboard().hasContent(Element.ELEMENT_DATA_FORMAT)) {
                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                }

                event.consume();
            });
            pane.setOnDragDropped((event) -> {
                Dragboard db = event.getDragboard();
                if (db.hasContent(Element.ELEMENT_DATA_FORMAT)) {
                    String data = (String)db.getContent(Element.ELEMENT_DATA_FORMAT);
                    System.out.println("Dropped element: " + data);
                    event.setDropCompleted(true);
                }

                event.consume();
            });
            ContextMenu contextMenu = new ContextMenu();
            MenuItem details = new MenuItem("Show Details");
            details.setOnAction((var2) -> this.showElementDetails(element));
            MenuItem compare = new MenuItem("Compare with Another Element");
            compare.setOnAction((var2) -> this.compareWithAnotherElement(element));
            MenuItem viewImage = new MenuItem("View Element Image");
            viewImage.setOnAction((var2) -> this.showElementImage(element));
            MenuItem about = new MenuItem("About Application");
            about.setOnAction((var1) -> showAbout());
            about.setAccelerator(new KeyCharacterCombination("Ctrl+H"));
            contextMenu.getItems().addAll(details, compare,viewImage, about);
            pane.setOnContextMenuRequested((e) -> contextMenu.show(pane, e.getScreenX(), e.getScreenY()));
        } else {
            pane.setStyle("-fx-background-color: WHITESMOKE; -fx-border-color: black; -fx-border-width: 1px;");
            pane.setPrefSize((double)100.0F, (double)100.0F);
            pane.setAlignment(Pos.CENTER);
            Text text = new Text("");
            text.setTextAlignment(TextAlignment.CENTER);
            pane.getChildren().add(text);
        }

        return pane;
    }


    private void showElementDetails(Element element) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Element Details");
        String var10001 = element.getName();
        alert.setHeaderText(var10001 + " (" + element.getSymbol() + ")");

        String state = getElementState(element.getAtomicNumber());
        alert.setContentText(String.format("Atomic Number: %d\nMass: %.2f\nGroup: %d\nPeriod: %d\nState at room temperature: %s\n",
                element.getAtomicNumber(), element.getMass(), element.getGroup(),
                element.getPeriod(), state));

        alert.getButtonTypes().setAll(ButtonType.OK);
        alert.showAndWait();
    }

    private void showComparison(Element element1, Element element2) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Element Comparison");
        String var10001 = element1.getName();
        alert.setHeaderText("Comparing " + var10001 + " and " + element2.getName());

        String state1 = getElementState(element1.getAtomicNumber());
        String state2 = getElementState(element2.getAtomicNumber());

        String comparisonText = String.format(
                "         Element 1: %s (%s)\n" +
                        "         Atomic Number: %d\n" +
                        "         Mass: %.2f\n" +
                        "         Group: %d\n" +
                        "         Period: %d\n" +
                        "         State at room temp: %s\n\n" +
                        "         Element 2: %s (%s)\n" +
                        "         Atomic Number: %d\n" +
                        "         Mass: %.2f\n" +
                        "         Group: %d\n" +
                        "         Period: %d\n" +
                        "         State at room temp: %s\n\n",
                element1.getName(), element1.getSymbol(), element1.getAtomicNumber(),
                element1.getMass(), element1.getGroup(), element1.getPeriod(), state1,
                element2.getName(), element2.getSymbol(), element2.getAtomicNumber(),
                element2.getMass(), element2.getGroup(), element2.getPeriod(), state2);

        alert.setContentText(comparisonText);
        alert.getButtonTypes().setAll(ButtonType.OK);
        alert.showAndWait();
    }

    private Element findElementBySymbol(String symbol) {
        for(ArrayList<Element> row : Layouts.elements) {
            for(Element e : row) {
                if (e != null && e.getSymbol().equalsIgnoreCase(symbol)) {
                    return e;
                }
            }
        }

        return null;
    }

    private void compareWithAnotherElement(Element element) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Compare Elements");
        dialog.setHeaderText("Enter the symbol of the element to compare:");
        dialog.setContentText("Symbol:");
        dialog.showAndWait().ifPresent((symbol) -> {
            Element otherElement = this.findElementBySymbol(symbol);
            if (otherElement != null) {
                this.showComparison(element, otherElement);
            } else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Element Not Found");
                alert.setContentText("The element with symbol " + symbol + " could not be found.");
                alert.showAndWait();
            }

        });
    }

    private void searchElement(String query) {
        String normalizedQuery = query.trim().toLowerCase();
        this.resetHighlights();
        boolean found = false;

        for(int row = 0; row < Layouts.elements.size(); ++row) {
            for(int col = 0; col < Layouts.elements.get(row).size(); ++col) {
                Element element = (Element)((ArrayList<?>)Layouts.elements.get(row)).get(col);
                if (element != null && (element.getName().toLowerCase().contains(normalizedQuery) || String.valueOf(element.getAtomicNumber()).equals(normalizedQuery))) {
                    this.highlightElement(row, col);
                    found = true;
                    break;
                }
            }

            if (found) {
                break;
            }
        }

        if (!found) {
            this.showElementNotFoundAlert();
        }

    }

    private void resetHighlights() {
        for(Node node : this.grid.getChildren()) {
            if (node instanceof StackPane pane) {
                pane.setEffect(null);
            }
        }

    }

    private void highlightElement(int row, int col) {
        for(Node node : this.grid.getChildren()) {
            if (node instanceof StackPane pane) {
                Integer rowIndex = GridPane.getRowIndex(pane);
                Integer colIndex = GridPane.getColumnIndex(pane);
                if (rowIndex != null && colIndex != null && rowIndex == row && colIndex == col) {
                    pane.setEffect(new Glow(0.8));
                    break;
                }
            }
        }

    }

    private void showElementNotFoundAlert() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Element Not Found");
        alert.setHeaderText((String)null);
        alert.setContentText("No element found matching the query.");
        alert.showAndWait();
    }

    private void updateCombinePane() {
        String combinedResult = combineElements(this.combinedElements);
        this.combinePane.getChildren().clear();
        Text resultText = new Text("Combined Result: " + combinedResult);
        resultText.setTextAlignment(TextAlignment.CENTER);
        this.combinePane.getChildren().add(resultText);
    }


    private void updateCirclePane() {
        if (this.currentElement != null) {
            this.circlePane.getChildren().clear();
            Circle backgroundCircle = new Circle((double)100.0F, Color.LIGHTBLUE);
            backgroundCircle.setStroke(Color.BLACK);
            backgroundCircle.setStrokeWidth(2.0F);
            backgroundCircle.setFill(Color.LIGHTBLUE);
            this.circlePane.getChildren().add(backgroundCircle);
            int[] electrons = getElectronConfiguration(this.currentElement.getAtomicNumber());
            double radiusIncrement = 18.0F;
            double centerX = this.circlePane.getPrefWidth() / (double)2.0F;
            double centerY = this.circlePane.getPrefHeight() / (double)2.0F;

            for(int i = 0; i < electrons.length; ++i) {
                double radius = radiusIncrement * (double)(i + 1);
                Circle shell = new Circle(radius, Color.TRANSPARENT);
                shell.setStroke(Color.BLACK);
                shell.setStrokeWidth(2.0F);
                shell.setFill(Color.TRANSPARENT);
                shell.setCenterX(centerX);
                shell.setCenterY(centerY);
                Text text = new Text("" + electrons[i]);
                text.setTextAlignment(TextAlignment.CENTER);
                text.setStyle("-fx-font-size: 14px;");
                StackPane shellPane = new StackPane();
                shellPane.getChildren().addAll(shell, text);
                shellPane.setTranslateX(centerX - radius);
                shellPane.setTranslateY(centerY - radius);
                this.circlePane.getChildren().add(shellPane);
            }

        }
    }

    private void updateCompoundInfo(ArrayList<Element> combinedElements) {
        Node text = CompoundFinder.findCompound(combinedElements);
        if (text != null) {
            this.combinePane.getChildren().add(text);

        }

    }

    private void clearCombinedElements() {
        this.combinedElements.clear();
        this.updateCombinePane();
    }

    private void showElementImage(Element element) {
        try {
            Stage imageStage = new Stage();
            imageStage.setTitle(element.getName() + " Image");

            String imagePath = "/images/" + element.getName().toLowerCase() + ".jpg";
            Image elementImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)));

            ImageView imageView = new ImageView(elementImage);
            imageView.setFitWidth(300);
            imageView.setPreserveRatio(true);

            Button closeButton = new Button("Close");
            closeButton.setOnAction(e -> imageStage.close());

            VBox layout = new VBox(10);
            layout.setPadding(new Insets(10));
            layout.setAlignment(Pos.CENTER);
            layout.getChildren().addAll(imageView, closeButton);

            Scene scene = new Scene(layout);
            imageStage.setScene(scene);
            imageStage.show();
        } catch (Exception e) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Image Not Available");
            alert.setHeaderText(null);
            alert.setContentText("Image for " + element.getName() + " is not available.");
            alert.showAndWait();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}