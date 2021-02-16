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

public abstract class Furgoneta extends Transporte{
    private double tara;

    /**
     * Inicializa los atributos del objeto con los argumentos recibidos como entrada
     * @param codigo Codigo de la furgoneta
     * @param mapa Mapa
     * @param tara Peso maximo de la furgoneta
     */
    public Furgoneta(String codigo, Mapa mapa, double tara){
        super(codigo, mapa);
        this.tara = tara;
    }

    public abstract double coste(String codPosOrigen, String codPosDestino);

    /**
     * Se encarga de devolver la tara
     * @return tara en kilogramos
     */
    public double getTara() {
        return tara;
    }
}