package aed;

import java.util.*;

// Todos los tipos de datos "Comparables" tienen el método compareTo()
// elem1.compareTo(elem2) devuelve un entero. Si es mayor a 0, entonces elem1 > elem2
public class ABB<T extends Comparable<T>> {
    private Nodo _raiz;
    private int _cardinal;
    private int _altura;

    private class Nodo {
        T valor;
        Nodo izq;
        Nodo der;
        Nodo padre;

        Nodo(T v) {
            valor = v;
            izq = null;
            der = null;
            padre = null;
        }
    }

    public ABB() {
        _raiz = null;
        _cardinal = 0;
        _altura = 0;
    }

    public int cardinal() {
        return _cardinal;
    }

    public T minimo(){
        Nodo actual = _raiz;
        while (actual != null & actual.izq != null) {
            actual = actual.izq;
        }
        return actual.valor;
    }

    public T maximo(){
        Nodo actual = _raiz;
        while (actual != null & actual.der != null) {
            actual = actual.der;
        }
        return actual.valor;
    }

    public void insertar(T elem){
        Nodo actual = _raiz;
        if (actual == null) {
            _raiz = new Nodo(elem);
            _cardinal += 1;
        } else {
            while (true) {
                if (elem.compareTo(actual.valor) > 0) {
                    if (actual.der != null) {
                        actual = actual.der;
                    } else {
                        actual.der = new Nodo(elem);
                        actual.der.padre = actual;
                        _cardinal += 1;
                        return;
                    }
                } else if (elem.compareTo(actual.valor) < 0) {
                    if (actual.izq != null) {
                        actual = actual.izq;
                    } else {
                        actual.izq = new Nodo(elem);
                        actual.izq.padre = actual;
                        _cardinal += + 1;
                        return;
                    }
                } else {
                    return;
                }
            }
        }
    }

    public boolean pertenece(T elem){
        Nodo actual = _raiz;
        if (actual == null) {
            return false;
        } else {
            while (true) {
                if (elem.compareTo(actual.valor) > 0) {
                    if (actual.der != null) {
                        actual = actual.der;
                    } else {
                        return false;
                    }
                } else if (elem.compareTo(actual.valor) < 0) {
                    if (actual.izq != null) {
                        actual = actual.izq;
                    } else {
                        return false;
                    }
                } else {
                    return true;
                }
            }
        }
    }

    public void eliminar(T elem){
        Nodo eliminar = _raiz;
        if (pertenece(elem)) {
            _cardinal -= 1;
            while (eliminar.valor != elem) {
                if (elem.compareTo(eliminar.valor) > 0) {
                    eliminar = eliminar.der;
                } else {
                    eliminar = eliminar.izq;
                }
            }
            if(eliminar.der == null & eliminar.izq == null){
                if (eliminar != _raiz) {
                    if(eliminar.valor.compareTo(eliminar.padre.valor) > 0){
                        eliminar.padre.der = null;
                    } else {
                        eliminar.padre.izq = null;
                    }
                } else {
                    _raiz = null;
                }
                
            } else if (eliminar.der != null & eliminar.izq == null) {
                if (eliminar != _raiz) {
                    eliminar.der.padre = eliminar.padre;
                    if(eliminar.valor.compareTo(eliminar.padre.valor) > 0){
                        eliminar.padre.der = eliminar.der;
                    } else {
                        eliminar.padre.izq = eliminar.der;
                    }
                } else {
                    _raiz = eliminar.der;
                    eliminar.der.padre = null;
                }
            } else if (eliminar.der == null & eliminar.izq != null) {
                if (eliminar != _raiz) {
                    eliminar.izq.padre = eliminar.padre;
                    if(eliminar.valor.compareTo(eliminar.padre.valor) > 0){
                        eliminar.padre.der = eliminar.izq;
                    } else {
                        eliminar.padre.izq = eliminar.izq;
                    }
                } else {
                    _raiz = eliminar.izq;
                    eliminar.izq.padre = null;
                }
            } else if (eliminar.der != null & eliminar.izq != null) {
                Nodo sucesor_inmediato = eliminar.izq;
                while (sucesor_inmediato.der != null) {
                    sucesor_inmediato = sucesor_inmediato.der;
                }
                if (eliminar != _raiz) {
                    
                } else {
                    _raiz = sucesor_inmediato;
                }
            }
        }
    }

    public String toString(){
        throw new UnsupportedOperationException("No implementada aun");
    }

    public class ABB_Iterador {
        private Nodo _actual;

        public boolean haySiguiente() {            
            return _actual.der != null;
        }
    
        public T siguiente() {
            _actual = _actual.der;
            return _actual.valor;
        }
    }

    public ABB_Iterador iterador() {
        return new ABB_Iterador();
    }

}
