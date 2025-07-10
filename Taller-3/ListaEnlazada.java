package aed;

import java.util.*;

public class ListaEnlazada<T> implements Secuencia<T> {
    // Completar atributos privados

    private int longitud;
    private Nodo cabeza;
    private Nodo cola;

    private class Nodo {
        T valor;
        Nodo siguiente;
        Nodo anterior;
        Nodo (T v){ 
            valor = v;
        }
    }

    public ListaEnlazada() {
        this.cabeza = null;
        this.cola = null;
        this.longitud = 0;
    }

    public ListaEnlazada(ListaEnlazada<T> lista) {
        for (int j=0; j < lista.longitud(); j++){
            this.agregarAtras(lista.obtener(j));
        }
    }


    //metodos getters
    public int longitud() {
        return longitud;
    }

    public Nodo getCabeza(){
        return this.cabeza;
    }

    public Nodo getCola(){
        return this.cola;
    }

    ///////

    public void agregarAtras(T elem) {
       
        if (longitud == 0){
            Nodo primerNodo = new Nodo(elem);
            primerNodo.valor = elem;
            primerNodo.siguiente = null;
            primerNodo.anterior = null;
            this.cabeza = primerNodo;
            this.cola = primerNodo;
        }
        else{
            Nodo nuevoNodo = new Nodo(elem);
            nuevoNodo.valor = elem;
            nuevoNodo.siguiente = null;
            nuevoNodo.anterior = this.cola; //quiero que el "anterior" de mi nuevo modo apunte al "siguiente" del nodo anterior
            this.cola.siguiente = nuevoNodo; //quiero que el "siguiente" de mi nodo anterior apunte al "anterior" de mi nodo nuevo
            this.cola = nuevoNodo; //quiero que la cola de mi ListaEnlazada pase a ser mi nuevo nodo
        }
        longitud++; //incremento la longitud
    }

    public void agregarAdelante(T elem) {
       
        if (longitud == 0){
            Nodo primerNodo = new Nodo(elem);
            primerNodo.valor = elem;
            primerNodo.siguiente = null;
            primerNodo.anterior = null;
            this.cabeza = primerNodo;
            this.cola = primerNodo;
        }
        else{
            Nodo nuevoNodo = new Nodo(elem);
            nuevoNodo.valor = elem;
            nuevoNodo.siguiente = this.cabeza;
            nuevoNodo.anterior = null; 
            this.cabeza.anterior = nuevoNodo; 
            this.cabeza = nuevoNodo; 
        }
        longitud++; //incremento la longitud
    }

    public T obtener(int i) {
        Nodo nodoParaRecorrer = cabeza;
        for (int j = 0; j < i; j++){
            nodoParaRecorrer = nodoParaRecorrer.siguiente;
        }
        return nodoParaRecorrer.valor;
    }

    public void eliminar(int i) {
        Nodo nodoParaRecorrer = cabeza;
        for (int j = 0; j < i; j++){
            nodoParaRecorrer = nodoParaRecorrer.siguiente;
        }

        if (nodoParaRecorrer.anterior != null){
            nodoParaRecorrer.anterior.siguiente = nodoParaRecorrer.siguiente;
        } else {
            cabeza = nodoParaRecorrer.siguiente;
        }

        if (nodoParaRecorrer.siguiente != null){
            nodoParaRecorrer.siguiente.anterior = nodoParaRecorrer.anterior;            
        } else {
            cola = nodoParaRecorrer.anterior;
        }
        longitud--; //disminuyo la longitud
    }

    public void modificarPosicion(int indice, T elem) {
        Nodo nodoParaRecorrer = cabeza;
        for (int j = 0; j < indice; j++){
            nodoParaRecorrer = nodoParaRecorrer.siguiente;
        }
        nodoParaRecorrer.valor = elem;
    }

    @Override
    public String toString() {
        Nodo nodoParaRecorrer = cabeza;
        int i = this.longitud;
        String res = "[";

        for (int j = 0; j < i; j++){
            if (j == i - 1){
                res = res + nodoParaRecorrer.valor + "]";
            } else{ res = res + nodoParaRecorrer.valor + ", ";
            nodoParaRecorrer = nodoParaRecorrer.siguiente;
        }
        }

        return res;
    }

private class ListaIterador implements Iterador<T> {

        private Nodo nodoSiguiente;
        private Nodo nodoAnterior; 

        public ListaIterador(Nodo cabezaInicial){
            this.nodoSiguiente = cabezaInicial;
            this.nodoAnterior = null;
        }

        public boolean haySiguiente() {
            return nodoSiguiente != null;
        }

        public T siguiente() {

            Nodo actual = nodoSiguiente;
            nodoSiguiente = nodoSiguiente.siguiente;
            nodoAnterior = actual;
            return actual.valor;
        }

        public boolean hayAnterior() {
            return nodoAnterior != null;
        }

        public T anterior() {

            Nodo actual = nodoAnterior;
            nodoAnterior = nodoAnterior.anterior;
            nodoSiguiente = actual;
            return actual.valor;
        }
    }
    public Iterador<T> iterador() {
        return new ListaIterador(cabeza);
    }

}
