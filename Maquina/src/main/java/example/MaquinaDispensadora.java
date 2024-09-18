package example;

import java.util.HashMap;
import java.util.Map;

public class MaquinaDispensadora {
    private Cola colaProductos;
    private Map<String, Double> precios;
    private double dineroAcumulado;

    public MaquinaDispensadora() {
        colaProductos = new Cola();
        precios = new HashMap<>();
        dineroAcumulado = 0;
    }

    public String consumirProducto(String nombreProducto, double dineroIngresado) {
        if (colaProductos.estaVacia() || colaProductos.obtenerCantidad(nombreProducto) == 0) {
            return "Producto no disponible.";
        }

        double precioProducto = precios.getOrDefault(nombreProducto, 0.0);

        if (dineroIngresado >= precioProducto) {
            colaProductos.quitar(nombreProducto);
            dineroAcumulado += precioProducto;
            double cambio = dineroIngresado - precioProducto;
            return "Producto dispensado: " + nombreProducto + "\nCambio devuelto: " + cambio + " Pesos";
        } else {
            return "Dinero insuficiente. Faltan " + (precioProducto - dineroIngresado) + " Pesos";
        }
    }

    public void agregarProducto(String nombreProducto, double precio) {
        colaProductos.insertar(nombreProducto);
        precios.put(nombreProducto, precio);
    }

    public double retirarDinero() {
        double dineroRetirado = dineroAcumulado;
        dineroAcumulado = 0;
        return dineroRetirado;
    }

    public String obtenerListaProductos() {
        return colaProductos.obtenerListaProductos();
    }

    public Map<String, Double> obtenerPrecios() {
        return new HashMap<>(precios);
    }
}