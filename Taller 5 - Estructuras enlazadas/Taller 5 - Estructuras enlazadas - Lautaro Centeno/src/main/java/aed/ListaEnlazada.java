package aed;

public class ListaEnlazada<T> {
    private Nodo primero;
    private Nodo ultimo;

    private class Nodo {
        T valor;
        Nodo sig;
        Nodo ant;
        Nodo(T v) { valor = v; }
    }

    public ListaEnlazada() {
        primero = null;
        ultimo = null;
    }

    public int longitud() {
        Nodo actual = primero;
        int res = 0;
        while (actual != null) {
            res += 1;
            actual = actual.sig;
        }
        return res;
    }

    public void agregarAdelante(T elem) {
        Nodo n = new Nodo(elem);
        if (primero != null) {
            n.sig = primero;
            primero.ant = n;
            primero = n;
        } else{
            primero = n;
            ultimo = n;
        }
    }

    public void agregarAtras(T elem) {
        Nodo n = new Nodo(elem);
        if (primero != null) {
        n.ant = ultimo;
        ultimo.sig = n;
        ultimo = n;
        } else{
            primero = n;
            ultimo = n;
        }
    }

    public T obtener(int i) {
        if (longitud() > i && i >= 0) {
            int j = 0;
        Nodo res = primero;
        while (j != i) {
            j += 1;
            res = res.sig;
        }
        return res.valor;
        } else {
            return null;
        }
    }

    public void eliminar(int i) {
        int j = 0;
        Nodo elim = primero;
        if (elim == null) {
            return;
        }
        while (j != i) {
            j += 1;
            elim = elim.sig;
        }
        if (elim.ant == null && elim.sig == null) {
            primero = null;
            ultimo = null;
        } else if (elim.ant == null) {
            primero = elim.sig;
            primero.ant = null;
        } else if (elim.sig == null) {
            ultimo = elim.ant;
            ultimo.sig = null;
        } else {
            elim.ant.sig = elim.sig;
            elim.sig.ant = elim.ant;
        }
    }

    public void modificarPosicion(int indice, T elem) {
        int j = 0;
        Nodo mod = primero;
        if (mod == null) {
            return;
        }
        while (j != indice) {
            j += 1;
            mod = mod.sig;
        }
        mod.valor = elem;
    }

    public ListaEnlazada(ListaEnlazada<T> lista) {
        Nodo actual = lista.primero;
        while (actual != null) {
            agregarAtras(actual.valor);
            actual = actual.sig;
        }
    }
    
    @Override
    public String toString() {
        Nodo actual = primero;
        String res = "";
        while (actual != null) {
            res += actual.valor;
            if (actual != ultimo) {
                res += ", ";
            }
            actual = actual.sig;
        }
        return "[" + res + "]";
    }

    public class ListaIterador{
    	int dedito;

        public boolean haySiguiente() {
	        return (obtener(dedito) != null);
        }
        
        public boolean hayAnterior() {
	        return (obtener(dedito -1) != null);
        }

        public T siguiente() {
	        if (haySiguiente()) {
                dedito += 1;
                return obtener(dedito -1);
            } else {
                return null;
            }
        }
        

        public T anterior() {
	        if (hayAnterior()) {
                dedito = dedito - 1;
                return obtener(dedito);
            } else {
                return null;
            }
        }
    }

    public ListaIterador iterador() {
	    ListaIterador iterador = new ListaIterador();
        iterador.dedito = 0;
        return iterador;
    }

}
