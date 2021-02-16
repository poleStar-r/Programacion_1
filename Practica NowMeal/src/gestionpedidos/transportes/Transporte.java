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

public abstract class Transporte {
    private String codigo;
    private Mapa mapa;

    /**
     * Inicializa los atributos del objeto con los argumentos recibidos como entrada
     * @param codigo Codigo del transporte
     * @param mapa Mapa
     */
    public Transporte(String codigo, Mapa mapa){
        this.codigo = codigo;
        this.mapa = mapa;
    }

    /**
     * Se encarga de devolver el coste que supone para el transporte ir desde su ubicaci�n
     * hasta la ubicaci�n del objeto con c�digo codPosDestino
     * @param codPosDestino Ubicacion de destino
     * @return Coste en euros
     */
    public double coste(String codPosDestino){
        return this.coste(codigo, codPosDestino);
    }

    /**
     * Se encarga de devolver el coste que supone para el transporte ir desde la ubicaci�n
     * del objeto con c�digo codPosOrigen hasta la ubicaci�n del objeto con c�digo codPosDestino
     * Se declara como abstracto ya que es sobreescrito por las clases Moto, FurgonetaPropia y FurgonetaSubcontratada
     * @param codPosOrigen Ubicacion de origen
     * @param codPosDestino Ubicacion de destino
     * @return Coste en euros
     */
    abstract double coste(String codPosOrigen, String codPosDestino);

    /**
     * Devuelve el codigo del transporte
     * @return Codigo del transporte
     */
    public String getCodigo(){
        return codigo;
    }

    /**
     * Se encarga de asignar un codigo al transporte
     * @param codigo Nuevo codigo del transporte
     */
    public void setCodigo(String codigo){
        this.codigo = codigo;
    }

    /**
     * Se encarga de devolver el atributo mapa del transporte
     * @return mapa
     */
    public Mapa getMapa() {
        return mapa;
    }
}