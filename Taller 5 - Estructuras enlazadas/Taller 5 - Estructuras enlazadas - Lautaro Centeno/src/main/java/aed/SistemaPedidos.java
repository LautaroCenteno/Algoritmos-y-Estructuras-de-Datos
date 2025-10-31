package aed;

public class SistemaPedidos {
    ABB<Pedido> arbolPedidos;
    ListaEnlazada<ABB<Pedido>.HandleABB> listaPedidos;

    public SistemaPedidos(){
        arbolPedidos = new ABB<Pedido>();
        listaPedidos = new ListaEnlazada<ABB<Pedido>.HandleABB>();
    }

    public void agregarPedido(Pedido pedido){
        ABB<Pedido>.HandleABB handle = arbolPedidos.insertar(pedido);
        listaPedidos.agregarAtras(handle);
    }

    public Pedido proximoPedido(){
        Pedido proximo = listaPedidos.obtener(0).valor();
        listaPedidos.obtener(0).eliminar();
        listaPedidos.eliminar(0);
        return proximo;
    }

    public Pedido pedidoMenorId(){
        return arbolPedidos.minimo();
    }

    public String obtenerPedidosEnOrdenDeLlegada(){
        return listaPedidos.toString();
    }

    public String obtenerPedidosOrdenadosPorId(){
        return arbolPedidos.toString();
    }
}