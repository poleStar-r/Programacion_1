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

public class FurgonetaSubcontratada extends Furgoneta {
    private double eurosPKm = 1;

    /**
     * Inicializa los atributos del objeto con los argumentos recibidos como entrada
     * @param codigo Codigo de la furgoneta
     * @param mapa Mapa
     * @param tara Peso maximo de la furgoneta
     */
    public FurgonetaSubcontratada(String codigo, Mapa mapa, double tara) {
        super(codigo, mapa, tara);
    }

    public double coste(String codPosOrigen, String codPosDestino) {
        if (getTara() < 1000) {
            return getMapa().distancia(codPosOrigen, codPosDestino) * eurosPKm;
        }
        else {
            return getMapa().distancia(codPosOrigen, codPosDestino) * eurosPKm * 1.10;
        }
    }

    /**
     * Se encarga de establecer un nuevo valor de euros por kilometro
     * @param newEurosPKm Nuevo valor de euros por kilometro
     */
    public void setEurosPKm(double newEurosPKm) {
        this.eurosPKm = newEurosPKm;
    }
}