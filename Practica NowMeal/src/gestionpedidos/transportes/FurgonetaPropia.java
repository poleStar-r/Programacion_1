package gestionpedidos.transportes;

import gestionpedidos.mapa.Mapa;

import anotacion.Programacion2;

@Programacion2(
        nombreAutor1 = "Anon",
        apellidoAutor1 = "Anon",
        emailUPMAutor1 = "Anon@alumnos.upm.es",
        nombreAutor2 = "",
        apellidoAutor2 = "",
        emailUPMAutor2 = ""
)

public class FurgonetaPropia extends Furgoneta {
    private double velocidadMedia = 30;
    private static final double EUROS_P_HORA = 40;

    /**
     * Inicializa los atributos del objeto con los argumentos recibidos como entrada
     * @param codigo Codigo de la furgoneta
     * @param mapa Mapa
     * @param tara Peso maximo de la furgoneta
     */
    public FurgonetaPropia(String codigo, Mapa mapa, double tara){
        super(codigo, mapa, tara);
    }

    public double coste (String codPosOrigen, String codPosDestino){
        if (getTara()<500){
            return getMapa().distancia(codPosOrigen, codPosDestino) * EUROS_P_HORA / velocidadMedia;
        }
        else {
            return getMapa().distancia(codPosOrigen, codPosDestino) * EUROS_P_HORA / velocidadMedia * 1.10;
        }
    }

    /**
     * Se encarga de establecer un nuevo valor de velocidad media
     * @param newVelocidadMedia Nuevo valor de velocidad media
     */
    public void setVelocidadMedia(double newVelocidadMedia) {
        this.velocidadMedia = newVelocidadMedia;
    }
}