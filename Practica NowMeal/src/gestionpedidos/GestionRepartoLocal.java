package gestionpedidos;

import gestionpedidos.pedido.Pedido;
import gestionpedidos.transportes.Furgoneta;
import gestionpedidos.transportes.Moto;
import gestionpedidos.transportes.Transporte;
import list.ArrayList;
import queues.NaiveQueue;

import anotacion.Programacion2;

@Programacion2(
		nombreAutor1 = "Anon",
		apellidoAutor1 = "Anon",
		emailUPMAutor1 = "Anon@alumnos.upm.es",
		nombreAutor2 = "",
		apellidoAutor2 = "",
		emailUPMAutor2 = ""
)

public class GestionRepartoLocal {
	// C�DIGO DE APOYO
	private ArrayList<Moto> motosDisponibles;
	private ArrayList<Furgoneta> furgonetasDisponibles;

	private NaiveQueue<Pedido> pedidosEsperandoMoto;
	private NaiveQueue<Pedido> pedidosEsperandoFurgoneta;

	// C�DIGO DE APOYO
	private static ArrayList<String> getCodList(ArrayList<?> disponibles) {
		ArrayList<String> salida = new ArrayList<>();
		for (int i = 0; i < disponibles.size(); i++)
			salida.add(salida.size(), ((Transporte) disponibles.get(i)).getCodigo());
		return salida;
	}

	// C�DIGO DE APOYO
	private static ArrayList<String[]> getClienteRestauranteList(NaiveQueue<Pedido> pendientes) {
		ArrayList<String[]> salida = new ArrayList<>();
		NaiveQueue<Pedido> aux = new NaiveQueue<>();
		while (!pendientes.isEmpty()) {
			Pedido pedido = pendientes.poll();

			salida.add(salida.size(), new String[]{pedido.getCliente().getCodigo(),
					pedido.getRestaurante().getCodigo()});
			aux.add(pedido);
		}
		while (!aux.isEmpty())
			pendientes.add(aux.poll());

		return salida;
	}

	// C�DIGO DE APOYO
	private static String myArrayListToString(ArrayList<?> list) {
		String salida = "";
		for (int i = 0; i < list.size(); i++) {
			salida += " ";
			if (list.get(i) instanceof String[]) {
				String[] item = (String[]) list.get(i);
				for (int j = 0; j < item.length; j++) {
					salida += item[j];
				}
			} else if (list.get(i) instanceof String) {
				salida += (String) list.get(i);
			}
		}

		return salida;
	}

	// C�DIGO DE APOYO
	public String getDisponibles() {
		return "Motos Disponibles:" + myArrayListToString(getCodList(motosDisponibles)) + System.lineSeparator() +
				"Furgonetas Disponibles:" + myArrayListToString(getCodList(furgonetasDisponibles)) + System.lineSeparator();

	}

	// C�DIGO DE APOYO
	public String getEsperando() {
		return "Pedidos esperando moto:" + myArrayListToString(getClienteRestauranteList(pedidosEsperandoMoto)) + System.lineSeparator() +
				"Pedidos esperando furgoneta:" + myArrayListToString(getClienteRestauranteList(pedidosEsperandoFurgoneta)) + System.lineSeparator();
	}

	// C�DIGO DE APOYO
	public ArrayList<String> getCodMotosDisponibles() {
		return getCodList(motosDisponibles);
	}

	// C�DIGO DE APOYO
	public ArrayList<String> getCodFurgoDisponibles() {
		return getCodList(furgonetasDisponibles);

	}

	// C�DIGO DE APOYO
	public ArrayList<String[]> getCodEsperandoMoto() {
		return getClienteRestauranteList(pedidosEsperandoMoto);
	}

	public ArrayList<String[]> getCodEsperandoFurgo() {
		return getClienteRestauranteList(pedidosEsperandoFurgoneta);
	}

	private static final double PESOMAXMOTO = 20;

	// C�DIGO DE APOYO
	public GestionRepartoLocal() {

		this.motosDisponibles = new ArrayList<>();
		this.furgonetasDisponibles = new ArrayList<>();

		this.pedidosEsperandoFurgoneta = new NaiveQueue<>();
		this.pedidosEsperandoMoto = new NaiveQueue<>();
	}

	/**
	 * Este metodo a�ade el transporte dado al final de la lista de motos o
	 * furgonetas disponibles dependiendo de si es una moto o una furgoneta
	 * @param transporte Transporte sin zona asignada
	 *
	 * Para saber si el transporte es una moto basta con realizar:
	 *      transporte instanceof Moto
	 * o
	 *      transporte.getCodigo().charAt(0) == 'M'
	 * Esto se debe a que los codigos son Strings del tipo "M1" "M2" "F1"...
	 */
	//PRE: el transporte no ha sido asignado a ninguna zona
	public void add(Transporte transporte) {
		if (transporte instanceof Moto) {
			motosDisponibles.add(motosDisponibles.size(), (Moto) transporte);
		} else {
			furgonetasDisponibles.add(furgonetasDisponibles.size(), (Furgoneta) transporte);
		}
	}

	/**
	 * Este metodo se encarga de asignar el pedido dado al transporte m�s barato disponible.
	 * En caso de no existir ningun transporte disponible, este es a�adido a la lista de espera
	 * correspondiente a su tipo de transporte
	 * @param pedido Pedido sin transporte asignado
	 */
	//PRE: el pedido no tiene asignado ning�n transporte
	public void asignarPedido(Pedido pedido){
		double[] preciosMoto = new double[motosDisponibles.size()];
		for (int i = motosDisponibles.size(); i > 0; i--) {
			preciosMoto[i - 1] = pedido.coste(motosDisponibles.get(i - 1));
		}
		double[] preciosFurgo = new double[furgonetasDisponibles.size()];
		for (int i = furgonetasDisponibles.size(); i > 0; i--) {
			preciosFurgo[i - 1] = pedido.coste(furgonetasDisponibles.get(i - 1));
		}

		int indicePrecioMinMoto = 0;
		double precioMinMoto = Integer.MAX_VALUE;
		for (int a = 0; a < motosDisponibles.size(); a++) {
			if (preciosMoto[a] < precioMinMoto) {
				precioMinMoto = preciosMoto[a];
				indicePrecioMinMoto = a;
			}
		}
		int indicePrecioMinFurgo = 0;
		double precioMinFurgo = Integer.MAX_VALUE;
		for (int a = 0; a < furgonetasDisponibles.size(); a++) {
			if (preciosFurgo[a] < precioMinFurgo) {
				precioMinFurgo = preciosFurgo[a];
				indicePrecioMinFurgo = a;
			}
		}

		if(pedido.getPeso() <= PESOMAXMOTO && motosDisponibles.size() > 0){
			pedido.setTransporte(motosDisponibles.get(indicePrecioMinMoto));
			motosDisponibles.removeElementAt(indicePrecioMinMoto);
		}
		else if (pedido.getPeso() <= PESOMAXMOTO && motosDisponibles.size() == 0) {
			pedidosEsperandoMoto.add(pedido);
		}
		else if (pedido.getPeso() > PESOMAXMOTO && furgonetasDisponibles.size() > 0){
			pedido.setTransporte(furgonetasDisponibles.get(indicePrecioMinFurgo));
			furgonetasDisponibles.removeElementAt(indicePrecioMinFurgo);
		}
		else {
			pedidosEsperandoFurgoneta.add(pedido);
		}
	}

	/**
	 * Este metodo se encarga de que el transporte del pedido pase a estar disponible.
	 * En caso de que exista otro pedido disponible para dicho transporte se le asigna directamente,
	 * en el caso de que no existan mas pedidos en espera, el transporte pasa a ser a�adido
	 * a la lista de transportes disponibles correspondiente a su tipo.
	 * @param pedido Pedido con transporte asignado
	 *
	 * Para saber si el transporte es una moto basta con realizar:
	 *      transporte instanceof Moto
	 * o
	 *      transporte.getCodigo().charAt(0) == 'M'
	 * Esto se debe a que los codigos son Strings del tipo "M1" "M2" "F1"...
	 */
	//PRE: el pedido tiene asignado un transporte
	public void notificarEntregaPedido(Pedido pedido) {
		if(pedido.getTransporte() instanceof Moto && pedidosEsperandoMoto.isEmpty()) {
			add(pedido.getTransporte());
		}
		else if (pedido.getTransporte() instanceof Moto && !pedidosEsperandoMoto.isEmpty()){
			pedidosEsperandoMoto.poll().setTransporte(pedido.getTransporte());
		}
		else if(pedido.getTransporte() instanceof Furgoneta && pedidosEsperandoFurgoneta.isEmpty()) {
			add(pedido.getTransporte());
		}
		else{
			pedidosEsperandoFurgoneta.poll().setTransporte(pedido.getTransporte());
		}
	}
}