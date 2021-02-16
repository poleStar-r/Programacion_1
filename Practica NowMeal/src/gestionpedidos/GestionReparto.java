package gestionpedidos;

import gestionpedidos.mapa.Mapa;
import gestionpedidos.pedido.Pedido;
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

public class GestionReparto {
	// C�DIGO DE APOYO
	private GestionRepartoLocal[] gestoresLocales;
	private Mapa mapa;

	/**
	 * Inicializar los atributos del objeto. Para ello, se crean los 4 gestores de reparto locales
	 * @param mapa Mapa
	 */
	public GestionReparto(Mapa mapa){
		this.mapa = mapa;
		gestoresLocales = new GestionRepartoLocal[4];
		int i = 0;
		while(i<4){
			gestoresLocales[i] = new GestionRepartoLocal();
			i++;
		}
	}
	
	//C�DIGO DE APOYO
	public Mapa getMapa() {
		return mapa;
	}
	
	// C�DIGO DE APOYO
	public String getEstadoGestorLocal(int i){
		return this.gestoresLocales[i].getDisponibles() + this.gestoresLocales[i].getEsperando();
	}
	
	// C�DIGO DE APOYO
	public String getEstadoGestorLocalNum(int i){
		return this.gestoresLocales[i].getCodMotosDisponibles().size() + ";" +
				this.gestoresLocales[i].getCodFurgoDisponibles().size() + ";" +
				this.gestoresLocales[i].getCodEsperandoMoto().size() + ";" +
				this.gestoresLocales[i].getCodEsperandoFurgo().size() ;
	}

	/**
	 * A�ade el transporte dado al gestor de reparto local que le corresponde por su ubicaci�n en el mapa.
	 * @param transporte Transporte sin zona asignada
	 */
	//PRE: el transporte no ha sido asignado a ninguna zona
	public void addTransporteLocalidad(Transporte transporte) {
		double coordX = transporte.getMapa().getPosicion(transporte.getCodigo()).getX();
		double coordY = transporte.getMapa().getPosicion(transporte.getCodigo()).getY();

		boolean zona0 = (0 <= coordX && coordX <= mapa.getMaxCoordX()/2) && (0 <= coordY && coordY <= mapa.getMaxCoordY()/2);
		boolean zona1 = (0 <= coordX && coordX <= mapa.getMaxCoordX()/2) && (mapa.getMaxCoordY()/2+1 <= coordY) && coordY <= mapa.getMaxCoordY();
		boolean zona2 = (mapa.getMaxCoordX()/2+1 <= coordX && coordX <= mapa.getMaxCoordX()) && (0 <= coordY && coordY <= mapa.getMaxCoordY()/2);

		if(zona0){
			gestoresLocales[0].add(transporte);
		}
		else if(zona1){
			gestoresLocales[1].add(transporte);
		}
		else if(zona2){
			gestoresLocales[2].add(transporte);
		}
		else{
			gestoresLocales[3].add(transporte);
		}
	}

	/**
	 * Asigna el pedido dado al gestor de reparto local que le corresponde por su ubicaci�n en el mapa
	 * @param pedido Pedido sin transporte asignado
	 */
	//PRE: el pedido no tiene asignado ning�n transporte
	public void asignarPedido(Pedido pedido){
		double coordX = mapa.getPosicion(pedido.getCliente().getCodigo()).getX();
		double coordY = mapa.getPosicion(pedido.getCliente().getCodigo()).getY();
		double coordXMapa = mapa.getMaxCoordX();
		double coordYMapa = mapa.getMaxCoordY();

		boolean zona0 = (0 <= coordX && coordX <= coordXMapa/2) && (0 <= coordY && coordY <= coordYMapa/2);
		boolean zona1 = (0 <= coordX && coordX <= coordXMapa/2) && (coordYMapa/2+1 <= coordY) && coordY <= coordYMapa;
		boolean zona2 = (coordXMapa/2+1 <= coordX && coordX <= coordXMapa) && (0 <= coordY && coordY <= coordYMapa/2);

		if(zona0){
			gestoresLocales[0].asignarPedido(pedido);
		}
		else if(zona1){
			gestoresLocales[1].asignarPedido(pedido);
		}
		else if(zona2){
			gestoresLocales[2].asignarPedido(pedido);
		}
		else{
			gestoresLocales[3].asignarPedido(pedido);
		}
	}


	/**
	 * Notifica la entrega del pedido al gestor de reparto local que le corresponde por su ubicaci�n en el mapa
	 * @param pedido Pedido con transporte asignado
	 */
	//PRE: el pedido tiene asignado un transporte
	public void notificarEntregaPedido(Pedido pedido){
		double coordX = mapa.getPosicion(pedido.getCliente().getCodigo()).getX();
		double coordY = mapa.getPosicion(pedido.getCliente().getCodigo()).getY();
		double coordXMapa = mapa.getMaxCoordX();
		double coordYMapa = mapa.getMaxCoordY();

		boolean zona0 = (0 <= coordX && coordX <= coordXMapa/2) && (0 <= coordY && coordY <= coordYMapa/2);
		boolean zona1 = (0 <= coordX && coordX <= coordXMapa/2) && (coordYMapa/2+1 <= coordY) && coordY <= coordYMapa;
		boolean zona2 = (coordXMapa/2+1 <= coordX && coordX <= coordXMapa) && (0 <= coordY && coordY <= coordYMapa/2);

		if(zona0){
			gestoresLocales[0].notificarEntregaPedido(pedido);
		}
		else if(zona1){
			gestoresLocales[1].notificarEntregaPedido(pedido);
		}
		else if(zona2){
			gestoresLocales[2].notificarEntregaPedido(pedido);
		}
		else{
			gestoresLocales[3].notificarEntregaPedido(pedido);
		}
	}
}