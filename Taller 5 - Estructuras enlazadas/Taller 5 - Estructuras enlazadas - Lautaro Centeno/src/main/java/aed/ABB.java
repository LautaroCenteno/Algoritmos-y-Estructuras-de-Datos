package aed;

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

    public class HandleABB {
        private Nodo nodoApuntado;

        private HandleABB(Nodo n) {
            this.nodoApuntado = n;
        }
        @Override
        public String toString(){
            return nodoApuntado.valor.toString();
        }
        public T valor(){
            return nodoApuntado.valor;
        }
        public void eliminar() {
            _cardinal -= 1;
            if(nodoApuntado.der == null && nodoApuntado.izq == null){
                if (nodoApuntado != _raiz) {
                    if(nodoApuntado.valor.compareTo(nodoApuntado.padre.valor) > 0){
                        nodoApuntado.padre.der = null;
                    } else {
                        nodoApuntado.padre.izq = null;
                    }
                } else {
                    _raiz = null;
                }
                
            } else if (nodoApuntado.der != null && nodoApuntado.izq == null) {
                if (nodoApuntado != _raiz) {
                    if(nodoApuntado.valor.compareTo(nodoApuntado.padre.valor) > 0){
                        nodoApuntado.padre.der = nodoApuntado.der;
                        nodoApuntado.der.padre = nodoApuntado.padre;
                    } else {
                        nodoApuntado.padre.izq = nodoApuntado.der;
                        nodoApuntado.der.padre = nodoApuntado.padre;
                    }
                } else {
                    _raiz = nodoApuntado.der;
                    nodoApuntado.der.padre = null;
                }
            } else if (nodoApuntado.der == null && nodoApuntado.izq != null) {
                if (nodoApuntado != _raiz) {
                    nodoApuntado.izq.padre = nodoApuntado.padre;
                    if(nodoApuntado.valor.compareTo(nodoApuntado.padre.valor) > 0){
                        nodoApuntado.padre.der = nodoApuntado.izq;
                    } else {
                        nodoApuntado.padre.izq = nodoApuntado.izq;
                    }
                } else {
                    _raiz = nodoApuntado.izq;
                    nodoApuntado.izq.padre = null;
                }
            } else if (nodoApuntado.der != null && nodoApuntado.izq != null) {
                Nodo sucesor_inmediato = nodoApuntado.izq;
                while (sucesor_inmediato.der != null) {
                    sucesor_inmediato = sucesor_inmediato.der;
                }
                
                if (sucesor_inmediato.padre != nodoApuntado) {
                    sucesor_inmediato.padre.der = sucesor_inmediato.izq;
                    if (sucesor_inmediato.izq != null) {
                        sucesor_inmediato.izq.padre = sucesor_inmediato.padre;
                    }
                    sucesor_inmediato.izq = nodoApuntado.izq;
                    nodoApuntado.izq.padre = sucesor_inmediato;
                }
                sucesor_inmediato.der = nodoApuntado.der;
                nodoApuntado.der.padre = sucesor_inmediato;
                sucesor_inmediato.padre = nodoApuntado.padre;
                
                if (nodoApuntado == _raiz) {
                    _raiz = sucesor_inmediato;
                } else {
                    if (nodoApuntado.valor.compareTo(nodoApuntado.padre.valor) > 0) {
                        nodoApuntado.padre.der = sucesor_inmediato;
                    } else {
                        nodoApuntado.padre.izq = sucesor_inmediato;
                    }
                }
            }
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
        while (actual != null && actual.izq != null) {
            actual = actual.izq;
        }
        return actual.valor;
    }

    public T maximo(){
        Nodo actual = _raiz;
        while (actual != null && actual.der != null) {
            actual = actual.der;
        }
        return actual.valor;
    }

    public HandleABB insertar(T elem){
        Nodo actual = _raiz;
        if (actual == null) {
            _raiz = new Nodo(elem);
            _cardinal += 1;
            return new HandleABB(_raiz);
            
        } else {
            while (true) {
                if (elem.compareTo(actual.valor) > 0) {
                    if (actual.der != null) {
                        actual = actual.der;
                    } else {
                        actual.der = new Nodo(elem);
                        actual.der.padre = actual;
                        _cardinal += 1;
                        return new HandleABB(actual.der);
                    }
                } else if (elem.compareTo(actual.valor) < 0) {
                    if (actual.izq != null) {
                        actual = actual.izq;
                    } else {
                        actual.izq = new Nodo(elem);
                        actual.izq.padre = actual;
                        _cardinal += 1;
                        return new HandleABB(actual.izq);
                    }
                } else {
                    return new HandleABB(actual);
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
            while (eliminar.valor.compareTo(elem) != 0) {
                if (elem.compareTo(eliminar.valor) > 0) {
                    eliminar = eliminar.der;
                } else {
                    eliminar = eliminar.izq;
                }
            }
            if(eliminar.der == null && eliminar.izq == null){
                if (eliminar != _raiz) {
                    if(eliminar.valor.compareTo(eliminar.padre.valor) > 0){
                        eliminar.padre.der = null;
                    } else {
                        eliminar.padre.izq = null;
                    }
                } else {
                    _raiz = null;
                }
                
            } else if (eliminar.der != null && eliminar.izq == null) {
                if (eliminar != _raiz) {
                    if(eliminar.valor.compareTo(eliminar.padre.valor) > 0){
                        eliminar.padre.der = eliminar.der;
                        eliminar.der.padre = eliminar.padre;
                    } else {
                        eliminar.padre.izq = eliminar.der;
                        eliminar.der.padre = eliminar.padre;
                    }
                } else {
                    _raiz = eliminar.der;
                    eliminar.der.padre = null;
                }
            } else if (eliminar.der == null && eliminar.izq != null) {
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
            } else if (eliminar.der != null && eliminar.izq != null) {
                Nodo sucesor_inmediato = eliminar.izq;
                while (sucesor_inmediato.der != null) {
                    sucesor_inmediato = sucesor_inmediato.der;
                }
                
                if (sucesor_inmediato.padre != eliminar) {
                    sucesor_inmediato.padre.der = sucesor_inmediato.izq;
                    if (sucesor_inmediato.izq != null) {
                        sucesor_inmediato.izq.padre = sucesor_inmediato.padre;
                    }
                    sucesor_inmediato.izq = eliminar.izq;
                    eliminar.izq.padre = sucesor_inmediato;
                }
                sucesor_inmediato.der = eliminar.der;
                eliminar.der.padre = sucesor_inmediato;
                sucesor_inmediato.padre = eliminar.padre;
                
                if (eliminar == _raiz) {
                    _raiz = sucesor_inmediato;
                } else {
                    if (eliminar.valor.compareTo(eliminar.padre.valor) > 0) {
                        eliminar.padre.der = sucesor_inmediato;
                    } else {
                        eliminar.padre.izq = sucesor_inmediato;
                    }
                }
            }
        }
    }

    @Override
    public String toString(){
        String res = "";
        ABB_Iterador dedito = this.iterador();
        if (dedito.haySiguiente()) {
            res += dedito.siguiente().toString();
            while (dedito.haySiguiente()) {
                res += ", ";
                res += dedito.siguiente().toString();
            }
        }
        
        return "{" + res + "}";
    }

    public class ABB_Iterador {
        private Nodo _actual;

        public boolean haySiguiente() {    
            return _actual != null;
        }
    
        public T siguiente() {
            if (haySiguiente()) {  
                T res = _actual.valor;
                if(_actual.der != null){
                    _actual = _actual.der;
                    while (_actual.izq != null) {
                        _actual = _actual.izq;
                    }
                } else {
                    Nodo padre = _actual.padre;
                    while (padre != null && _actual == padre.der) {
                        _actual = padre;
                        padre = padre.padre;
                    }
                    _actual = padre;
                }
                return res;
            } else {
                return null;
            }
            
        }
    }

    public ABB_Iterador iterador() {
        ABB_Iterador iterador = new ABB_Iterador();
        iterador._actual = _raiz;
        if (iterador._actual != null) {
            while (iterador._actual.izq != null) {
            iterador._actual = iterador._actual.izq;
            }
        }
        return iterador;
    }

}
