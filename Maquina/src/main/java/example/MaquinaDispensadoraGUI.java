package example;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class MaquinaDispensadoraGUI extends JFrame {
    private MaquinaDispensadora maquina;
    private JTextArea productosArea;
    private JComboBox<String> productosCombo;
    private JTextField dineroField;
    private JButton comprarButton;
    private JButton agregarButton;
    private JButton retirarDineroButton;

    public MaquinaDispensadoraGUI() {
        maquina = new MaquinaDispensadora();
        inicializarComponentes();
        inicializarMaquina();
    }

    private void inicializarComponentes() {
        setTitle("maquina dispensadora");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panelPrincipal = new JPanel(new BorderLayout());

        productosArea = new JTextArea(10, 30);
        productosArea.setEditable(false);
        JScrollPane panelDesplazable = new JScrollPane(productosArea);
        panelPrincipal.add(panelDesplazable, BorderLayout.CENTER);

        JPanel panelControl = new JPanel(new GridLayout(4, 2, 5, 5));

        productosCombo = new JComboBox<>();
        panelControl.add(new JLabel("producto:"));
        panelControl.add(productosCombo);

        dineroField = new JTextField();
        panelControl.add(new JLabel("dinero (Pesos):"));
        panelControl.add(dineroField);

        comprarButton = new JButton("comprar");
        comprarButton.addActionListener(e -> comprarProducto());
        panelControl.add(comprarButton);

        agregarButton = new JButton("agregar Producto");
        agregarButton.addActionListener(e -> agregarProducto());
        panelControl.add(agregarButton);

        retirarDineroButton = new JButton("retirar Dinero");
        retirarDineroButton.addActionListener(e -> retirarDinero());
        panelControl.add(retirarDineroButton);

        panelPrincipal.add(panelControl, BorderLayout.SOUTH);

        add(panelPrincipal);
    }

    private void inicializarMaquina() {
        maquina.agregarProducto("agua", 2000);
        maquina.agregarProducto("refresco", 3000);
        maquina.agregarProducto("galleta", 1500);
        maquina.agregarProducto("papas", 2500);
        maquina.agregarProducto("chocolate", 4000);
        actualizarAreaProductos();
        actualizarComboProductos();
    }

    private void actualizarAreaProductos() {
        StringBuilder sb = new StringBuilder("productos disponibles:\n");
        String productos = maquina.obtenerListaProductos();
        Map<String, Double> precios = maquina.obtenerPrecios();
        for (String linea : productos.split("\n")) {
            String[] partes = linea.split(": ");
            if (partes.length == 2) {
                String nombre = partes[0];
                int cantidad = Integer.parseInt(partes[1]);
                Double precio = precios.get(nombre);
                sb.append(nombre).append(" - cantidad: ").append(cantidad)
                        .append(" - Precio: ").append(precio).append(" Pesos\n");
            }
        }
        productosArea.setText(sb.toString());
    }

    private void actualizarComboProductos() {
        productosCombo.removeAllItems();
        for (String producto : maquina.obtenerPrecios().keySet()) {
            productosCombo.addItem(producto);
        }
    }

    private void comprarProducto() {
        String productoSeleccionado = (String) productosCombo.getSelectedItem();
        try {
            double dinero = Double.parseDouble(dineroField.getText());
            String resultado = maquina.consumirProducto(productoSeleccionado, dinero);
            mostrarMensaje(resultado, "resultado de la compra");
            actualizarAreaProductos();
            dineroField.setText("");
        } catch (NumberFormatException e) {
            mostrarMensaje("por favor, ingrese una cantidad valida de dinero.", "Error");
        }
    }

    private void agregarProducto() {
        String nuevoProducto = JOptionPane.showInputDialog(this, "Ingrese el nombre del nuevo producto:");
        if (nuevoProducto != null && !nuevoProducto.trim().isEmpty()) {
            String precioStr = JOptionPane.showInputDialog(this, "Ingrese el precio del nuevo producto (en Pesos):");
            try {
                double precio = Double.parseDouble(precioStr);
                maquina.agregarProducto(nuevoProducto, precio);
                actualizarAreaProductos();
                actualizarComboProductos();
                mostrarMensaje("Producto agregado exitosamente.", "Informacion");
            } catch (NumberFormatException e) {
                mostrarMensaje("Precio invalido. El producto no se ha agregado.", "Error");
            }
        }
    }

    private void retirarDinero() {
        double dineroRetirado = maquina.retirarDinero();
        mostrarMensaje("Dinero retirado: " + dineroRetirado + " Pesos", "Retiro de dinero");
    }

    private void mostrarMensaje(String mensaje, String titulo) {
        JOptionPane.showMessageDialog(this, mensaje, titulo, JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MaquinaDispensadoraGUI gui = new MaquinaDispensadoraGUI();
            gui.setVisible(true);
        });
    }
}