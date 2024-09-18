package example;

public class ListaDoblementeEnlazada {
    private Nodo cabeza;
    private Nodo fin;

    public ListaDoblementeEnlazada() {
        this.cabeza = null;
        this.fin = null;
    }

    // Agregar al final de la lista
    public void agregarAlFinal(String nombre) {
        Nodo nuevo = new Nodo(nombre, null, null);
        if (cabeza == null) {  // Lista vacía
            cabeza = fin = nuevo;
        } else {
            fin.setSiguiente(nuevo);
            nuevo.setAnterior(fin);
            fin = nuevo;
        }
    }

    // Obtener la lista de productos como cadena
    public String obtenerListaProductos() {
        StringBuilder sb = new StringBuilder();
        if (cabeza == null) {
            sb.append("lista vacia\n");
        } else {
            Nodo apuntador = cabeza;
            while (apuntador != null) {
                sb.append(apuntador.getNombre()).append("\n");
                apuntador = apuntador.getSiguiente();
            }
        }
        return sb.toString();
    }

    // Obtener el frente de la lista (primer nodo)
    public String obtenerFrente() {
        if (cabeza != null) {
            return cabeza.getNombre();
        }
        return null;
    }

    // Quitar el frente de la lista (eliminar el primer nodo)
    public void quitarFrente() {
        if (cabeza != null) {
            cabeza = cabeza.getSiguiente();
            if (cabeza != null) {
                cabeza.setAnterior(null);
            } else {
                fin = null; // Lista vacía
            }
        }
    }

    // Mostrar la lista en consola
    public void mostrar() {
        if (cabeza != null) {
            Nodo apuntador = cabeza;
            while (apuntador != null) {
                System.out.println(apuntador.getNombre());
                apuntador = apuntador.getSiguiente();
            }
        } else {
            System.out.println("lista vacia");
        }
    }

    // Eliminar un nodo por nombre
    public void eliminar(String nombre) {
        Nodo apuntador = cabeza;
        while (apuntador != null) {
            if (apuntador.getNombre().equals(nombre)) {
                if (apuntador == cabeza) {  // Eliminar el primer nodo
                    cabeza = cabeza.getSiguiente();
                    if (cabeza != null) {
                        cabeza.setAnterior(null);
                    } else {
                        fin = null;  // Lista queda vacía
                    }
                } else if (apuntador == fin) {  // Eliminar el último nodo
                    fin = fin.getAnterior();
                    if (fin != null) {
                        fin.setSiguiente(null);
                    } else {
                        cabeza = null;  // Lista queda vacía
                    }
                } else {  // Eliminar un nodo intermedio
                    Nodo anterior = apuntador.getAnterior();
                    Nodo siguiente = apuntador.getSiguiente();
                    anterior.setSiguiente(siguiente);
                    siguiente.setAnterior(anterior);
                }
                return;
            }
            apuntador = apuntador.getSiguiente();
        }
        System.out.println("nodo con nombre '" + nombre + "' no encontrado.");
    }

    // Invertir la lista
    public void invertirLista() {
        if (cabeza == null || cabeza == fin) {
            return; // Lista vacía o con un solo elemento, no necesita inversión
        }

        Nodo actual = cabeza;
        Nodo temp;

        while (actual != null) {
            temp = actual.getAnterior();
            actual.setAnterior(actual.getSiguiente());
            actual.setSiguiente(temp);
            actual = actual.getAnterior();
        }

        // Intercambiar cabeza y fin
        temp = cabeza;
        cabeza = fin;
        fin = temp;
    }

    // Eliminar nodos repetidos basado en ID
    public void eliminarRepetidos() {
        if (cabeza == null) return;

        Nodo actual = cabeza;
        while (actual != null) {
            Nodo comparar = actual.getSiguiente();
            while (comparar != null) {
                if (extraerID(actual.getNombre()) == extraerID(comparar.getNombre())) {
                    Nodo siguiente = comparar.getSiguiente();
                    eliminarNodo(comparar);
                    comparar = siguiente;
                } else {
                    comparar = comparar.getSiguiente();
                }
            }
            actual = actual.getSiguiente();
        }
    }

    // Extraer ID del nombre (asume que el ID está al final del nombre)
    private int extraerID(String nombre) {
        String[] partes = nombre.split(" ");
        return Integer.parseInt(partes[partes.length - 1]);
    }

    // Eliminar un nodo específico
    private void eliminarNodo(Nodo nodo) {
        if (nodo == cabeza) {
            cabeza = nodo.getSiguiente();
            if (cabeza != null) {
                cabeza.setAnterior(null);
            } else {
                fin = null;
            }
        } else if (nodo == fin) {
            fin = nodo.getAnterior();
            if (fin != null) {
                fin.setSiguiente(null);
            }
        } else {
            nodo.getAnterior().setSiguiente(nodo.getSiguiente());
            nodo.getSiguiente().setAnterior(nodo.getAnterior());
        }
    }
}
