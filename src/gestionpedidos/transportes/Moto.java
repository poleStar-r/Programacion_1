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

public class Moto extends Transporte{
    private double eurosPKm = 2;

    /**
     * Inicializa los atributos del objeto con los argumentos recibidos como entrada
     * @param codigo Codigo de la moto
     * @param mapa Mapa
     */
    public Moto(String codigo, Mapa mapa){
        super(codigo, mapa);
    }

    public double coste(String codPosOrigen, String codPosDestino){
        return getMapa().distancia(codPosOrigen, codPosDestino)*eurosPKm;
    }

    /**
     * Se encarga de establecer un nuevo valor de euros por kilometro
     * @param newEurosPKm Nuevo valor de euros por kilometro
     */
    public void setEurosPKm(double newEurosPKm){
        this.eurosPKm = newEurosPKm;
    }

    /**
     * Se encarga de devolver el valor de euros por kilometro
     * @return euros por kilometro
     */
    public double getEurosPKm(){
        return eurosPKm;
    }
}