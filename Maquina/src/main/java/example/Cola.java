package example;

import java.util.HashMap;
import java.util.Map;

public class Cola {
    private Map<String, Integer> productos;

    public Cola() {
        productos = new HashMap<>();
    }

    public void insertar(String elemento) {
        productos.put(elemento, productos.getOrDefault(elemento, 0) + 1);
    }

    public boolean quitar(String elemento) {
        if (productos.containsKey(elemento) && productos.get(elemento) > 0) {
            int cantidad = productos.get(elemento);
            if (cantidad == 1) {
                productos.remove(elemento);
            } else {
                productos.put(elemento, cantidad - 1);
            }
            return true;
        }
        return false;
    }

    public boolean estaVacia() {
        return productos.isEmpty();
    }

    public int obtenerCantidad(String elemento) {
        return productos.getOrDefault(elemento, 0);
    }

    public int tamaño() {
        return productos.values().stream().mapToInt(Integer::intValue).sum();
    }

    public String obtenerListaProductos() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Integer> entry : productos.entrySet()) {
            sb.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        return sb.toString();
    }
}