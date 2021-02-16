package gestionpedidos.pedido;

import gestionpedidos.transportes.Furgoneta;
import gestionpedidos.transportes.Moto;
import gestionpedidos.transportes.Transporte;

import anotacion.Programacion2;

@Programacion2(
        nombreAutor1 = "Anon",
        apellidoAutor1 = "Anon",
        emailUPMAutor1 = "Anon@alumnos.upm.es",
        nombreAutor2 = "",
        apellidoAutor2 = "",
        emailUPMAutor2 = ""
)

public class Pedido {
	// C�DIGO DE APOYO
	private Cliente cliente;
	private PlatoComida[] comidas;
	private Restaurante restaurante;
	private double importe;
	private Transporte transporte;
	private double peso;

	/**
	 * Este metodo se encarga de inicializar los atributos del objeto con los argumentos recibidos como entrada
	 * adem�s calcula el importe y el peso y se lo asigna a sus atributos
	 * @param cliente Cliente que realiza el pedido
	 * @param comidas Comidas encargadas
	 * @param restaurante Restaurante solicitado
	 */
	public Pedido(Cliente cliente, PlatoComida[] comidas, Restaurante restaurante) {
		this.cliente = cliente;
		this.comidas = comidas;
		this.restaurante = restaurante;
		for (int i = 0; i < comidas.length; i++) {
			importe = importe + comidas[i].getPrecio();
			peso = peso + comidas[i].getPeso();
		}
	}

	public double getPeso() {
		return peso;
	}

	/**
	 * Este metodo se encarga de calcular y devolver el coste del pedido para un cierto transporte
	 * @param transporte Transporte asignado al pedido
	 * @return Coste del pedido
	 * Para saber si el transporte es una moto basta con realizar:
	 *      transporte instanceof Moto
	 * o
	 *      transporte.getCodigo().charAt(0) == 'M'
	 * Esto se debe a que los codigos son Strings del tipo "M1" "M2" "F1"...
	 */
	public double coste(Transporte transporte) {
		if (transporte instanceof Moto) {
			return importe + transporte.coste(restaurante.getCodigo()) + ((Moto)transporte).coste(restaurante.getCodigo(), cliente.getCodigo());
		}
		else {
			return importe + transporte.coste(restaurante.getCodigo()) + ((Furgoneta)transporte).coste(restaurante.getCodigo(), cliente.getCodigo());
		}
	}

	//C�DIGO DE APOYO
	public double getImporte(){
		return importe;
	}	

	// C�DIGO DE APOYO
	public Transporte getTransporte() {
		return transporte;
	}

	// C�DIGO DE APOYO
	public void setTransporte(Transporte transporte) {
		this.transporte = transporte;
	}
	
	// C�DIGO DE APOYO
	public Cliente getCliente(){
		return cliente;
	}
	
	// C�DIGO DE APOYO
	public Restaurante getRestaurante(){
		return restaurante;
	}
}
