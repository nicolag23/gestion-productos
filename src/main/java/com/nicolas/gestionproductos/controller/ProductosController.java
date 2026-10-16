package com.nicolas.gestionproductos.controller;

import com.nicolas.gestionproductos.dto.ProductoDto;
import com.nicolas.gestionproductos.model.ProductosModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class ProductosController {

    ProductoDto productoSeleccionado;

    @FXML
    public TableView tableProductos;
    @FXML
    public TableColumn colId;
    @FXML
    public TableColumn colCodigo;
    @FXML
    public TableColumn colNombre;
    @FXML
    public TableColumn colPrecio;
    @FXML
    public TableColumn colStock;

    @FXML
    public TextField txtId;
    @FXML
    public TextField txtCodigo;
    @FXML
    public TextField txtNombre;
    @FXML
    public TextField txtPrecio;
    @FXML
    public TextField txtStock;

    @FXML
    public Button btnAgregar;
    @FXML
    public Button btnActualizar;
    @FXML
    public Button btnEliminar;

    private ProductosModel productosModel;
    private ObservableList<ProductoDto> productos;

    private Integer idProducto = 0;

    public ProductosController() {
        this.productosModel = new ProductosModel();
    }

    @FXML
    public void initialize() {
        this.colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        this.colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        this.colStock.setCellValueFactory(new PropertyValueFactory<>("stock"));

        this.productos = FXCollections.observableArrayList();
        this.tableProductos.setItems(this.productos);
    }

    @FXML
    public void handleAgregarProducto(ActionEvent actionEvent) {
        try {
            if (txtCodigo.getText().trim().isEmpty() ||
                    txtNombre.getText().trim().isEmpty() ||
                    txtPrecio.getText().trim().isEmpty() ||
                    txtStock.getText().trim().isEmpty()) {
                throw new Exception("Datos no pueden estar vacio");
            }

            ProductoDto productoDto = new ProductoDto();
            productoDto.setCodigo(txtCodigo.getText().trim());
            productoDto.setNombre(txtNombre.getText().trim());
            productoDto.setPrecio(Double.parseDouble(txtPrecio.getText().trim()));
            productoDto.setStock(Integer.parseInt(txtStock.getText().trim()));
            productoDto.setId(this.getIdProducto());

            this.productosModel.agregarProducto(productoDto);
            this.productos.setAll(this.productosModel.getProductos());

            this.limpiarCampos();

        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("Precio y Stock deben ser numeros");
            alert.showAndWait();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    public void handleSeleccionar(MouseEvent mouseEvent) {
        ProductoDto productoDto = (ProductoDto) this.tableProductos.getSelectionModel().getSelectedItem();
        if (productoDto != null) {
            this.txtId.setText(productoDto.getId().toString());
            this.txtCodigo.setText(productoDto.getCodigo());
            this.txtNombre.setText(productoDto.getNombre());
            this.txtPrecio.setText(productoDto.getPrecio().toString());
            this.txtStock.setText(productoDto.getStock().toString());

            this.btnEliminar.setDisable(false);
            this.btnActualizar.setDisable(false);
            this.productoSeleccionado = productoDto;
        }
    }

    @FXML
    public void handleActualizarProducto(ActionEvent actionEvent) {
        try {
            if (this.productoSeleccionado == null) {
                throw new Exception("Debe seleccionar un producto para actualizar");
            }

            if (txtCodigo.getText().trim().isEmpty() ||
                    txtNombre.getText().trim().isEmpty() ||
                    txtPrecio.getText().trim().isEmpty() ||
                    txtStock.getText().trim().isEmpty()) {
                throw new Exception("Datos no pueden estar vacio");
            }

            this.productoSeleccionado.setCodigo(txtCodigo.getText().trim());
            this.productoSeleccionado.setNombre(txtNombre.getText().trim());
            this.productoSeleccionado.setPrecio(Double.parseDouble(txtPrecio.getText().trim()));
            this.productoSeleccionado.setStock(Integer.parseInt(txtStock.getText().trim()));

            this.productosModel.actualizarProducto(this.productoSeleccionado);
            this.productos.setAll(this.productosModel.getProductos());

            this.limpiarCampos();

        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("Precio y Stock deben ser numeros");
            alert.showAndWait();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    public void handleEliminarProducto(ActionEvent actionEvent) {
        if (this.productoSeleccionado != null) {
            this.productosModel.eliminarProducto(this.productoSeleccionado);
            this.productos.setAll(this.productosModel.getProductos());
            this.limpiarCampos();
        }
    }

    private void limpiarCampos() {
        this.txtId.setText("");
        this.txtCodigo.setText("");
        this.txtNombre.setText("");
        this.txtPrecio.setText("");
        this.txtStock.setText("");
        this.btnEliminar.setDisable(true);
        this.btnActualizar.setDisable(true);
        this.productoSeleccionado = null;
        this.txtCodigo.requestFocus();
    }

    private Integer getIdProducto() {
        this.idProducto = this.idProducto + 1;
        return idProducto;
    }
}