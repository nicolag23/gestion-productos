package com.nicolas.gestionproductos.model;

import com.nicolas.gestionproductos.dto.ProductoDto;

import java.util.ArrayList;

public class ProductosModel {
    private final ArrayList<ProductoDto> productos = new ArrayList<>();

    public ArrayList<ProductoDto> getProductos() {
        return productos;
    }

    public void agregarProducto(ProductoDto producto) {
        productos.add(producto);
    }

    public void eliminarProducto(ProductoDto producto) {
        productos.remove(producto);
    }

    public void actualizarProducto(ProductoDto productoActualizado) {
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getId().equals(productoActualizado.getId())) {
                productos.set(i, productoActualizado);
                break;
            }
        }
    }
}