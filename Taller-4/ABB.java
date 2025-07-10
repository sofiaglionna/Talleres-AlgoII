package aed;

import java.util.*;

// Todos los tipos de datos "Comparables" tienen el método compareTo()
// elem1.compareTo(elem2) devuelve un entero. Si es mayor a 0, entonces elem1 > elem2
public class ABB<T extends Comparable<T>> implements Conjunto<T> {

    private Nodo raiz;
    private int cardinal;

    private class Nodo {
        T valor;
        Nodo izquierdo;
        Nodo derecho;

        public Nodo(T v) {
            this.valor = v;
            this.izquierdo = null;
            this.derecho = null;
        }
    }

    public ABB() {
        this.raiz = null;
        this.cardinal = 0;
    }

    public int cardinal() {
        return this.cardinal;
    }

    public T minimo(){
        if (this.raiz == null) {
            return null;
        }
        Nodo actual = this.raiz;
        while (actual.izquierdo != null) {
            actual = actual.izquierdo;
        }
        return actual.valor;
    }

    public T maximo(){
        if (this.raiz == null) {
            return null;
        }
        Nodo actual = this.raiz;
        while (actual.derecho != null){
            actual = actual.derecho;
        }
        return actual.valor;
    }

    public void insertar(T elem){
        if (!pertenece(elem)) {
            if (this.raiz == null) {
                this.raiz = new Nodo(elem);
            } else {
                buscarEInsertar(this.raiz, elem);
            }
            this.cardinal++;
        }
    }

    public void buscarEInsertar(Nodo n, T elem){
        Nodo actual = n;
        while (actual != null) {
            if (elem.compareTo(actual.valor) < 0) {
                if (actual.izquierdo == null) {
                    actual.izquierdo = new Nodo(elem);
                    break;
                } else {
                    actual = actual.izquierdo;
                }
            } else if (elem.compareTo(actual.valor) > 0) {
                if (actual.derecho == null) {
                    actual.derecho = new Nodo(elem);
                    break;
                } else {
                    actual = actual.derecho;
                }
            } else {
                // elem ya existe, no hago nada
                break;
            }
        }
    }


    public boolean pertenece(T elem) {
        if (this.raiz == null) {
            return false;
        }
        if (elem.compareTo(this.raiz.valor) == 0) {
            return true;
        } else {
            Nodo actual = this.raiz;
            while (actual != null) {
                if (elem.compareTo(actual.valor) == 0) {
                    return true;}
                if (elem.compareTo(actual.valor) < 0) {
                    if (actual.izquierdo == null) { return false;
                    } else {
                        actual = actual.izquierdo;}
                } else { if (actual.derecho == null) { return false;
                    } else {
                        actual = actual.derecho;
                    }}
            }
            return false;
        }
    }


    public void eliminar(T elem) {
        this.raiz = eliminar(this.raiz, elem);
    }
    
    private Nodo eliminar(Nodo nodo, T elem) {
        if (nodo == null) return null; //si no hay nada en la raiz, lo devuelvo igual (el elem ya no pertenecia de todas formas)
    
        int cmp = elem.compareTo(nodo.valor);
    
        if (cmp < 0) { // acá busco el nodo que tiene a mi elem dentro del arbol
            nodo.izquierdo = eliminar(nodo.izquierdo, elem);
        } else if (cmp > 0) {
            nodo.derecho = eliminar(nodo.derecho, elem);
        } else { // (o sea ya encontré el nodo que tiene mi elem)

            // Caso 1: sin hijos
            if (nodo.izquierdo == null && nodo.derecho == null) {
                cardinal--;
                return null;
            }
    
            // Caso 2: un solo hijo
            if (nodo.izquierdo == null) {
                cardinal--;
                return nodo.derecho;
            }
            if (nodo.derecho == null) {
                cardinal--;
                return nodo.izquierdo;
            }
    
            // Caso 3: dos hijos
            // busco el sucesor (mínimo del subárbol derecho)
            Nodo sucesor = encontrarMinimo(nodo.derecho); // no uso la función minimo que había hecho antes pq no le puedo pasar como parámetro un nodo (o sea es como si recortase mi arbol, y mi raiz pase a ser lo que yo le paso como parametro)
            nodo.valor = sucesor.valor; // reemplazo el valor
            nodo.derecho = eliminar(nodo.derecho, sucesor.valor); // elimino el sucesor duplicado
        }
    
        return nodo;
    }
    
    private Nodo encontrarMinimo(Nodo nodo) {
        while (nodo.izquierdo != null) {
            nodo = nodo.izquierdo;
        }
        return nodo;
    }
    

    @Override
    public String toString() {
        String resultado = recorridoEnOrden(raiz); 
        if (!resultado.isEmpty()) {
            resultado = resultado.substring(0, resultado.length() - 1); // saca la ultima coma agregada al final de la cadena antes de cerrarla cn el }
        }
        return "{" + resultado + "}";
    }

    private String recorridoEnOrden(Nodo nodo) { // recorre los valres de menor a mayor del ABB
        if (nodo == null) {
            return ""; }

        String izquierdo = recorridoEnOrden(nodo.izquierdo);
        String actual = nodo.valor + ",";
        String derecho = recorridoEnOrden(nodo.derecho);

        return izquierdo + actual + derecho;
    }

    


    private class ABB_Iterador implements Iterador<T> {
        private ArrayList<T> elementos;
        private int indice;

        // idea: la primera vez recorro todo el arbol, guardando los elementos en orden y dsp itero sobre esa lista, devolviendo un elemento a la vez

        public ABB_Iterador() {
            elementos = new ArrayList<>();
            indice = 0;
            cargarEnOrden(raiz);
        }

        private void cargarEnOrden(Nodo nodo) {
            if (nodo == null) {
                return;}
            cargarEnOrden(nodo.izquierdo);
            elementos.add(nodo.valor);
            cargarEnOrden(nodo.derecho);
        }

        public boolean haySiguiente() {            
            return indice < elementos.size();
        }
    
        public T siguiente() {
            T valor = elementos.get(indice);
            indice++;
            return valor;
        }
    }
    

    public Iterador<T> iterador() {
        return new ABB_Iterador();
    }

}
