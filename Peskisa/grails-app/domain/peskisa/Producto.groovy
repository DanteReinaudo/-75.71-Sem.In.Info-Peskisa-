package peskisa

class Producto {
    String marca
    String modelo
    String nombre
    String categoria
    int precioMercado
    static constraints = {
        marca blank: false, nullable: false
        modelo blank: false, nullable: false
        nombre blank: false, nullable: false
        categoria blank: false, nullable: false
    }

    Producto(String marca, String modelo, String nombre, String categoria){
        this.marca = marca
        this.modelo = modelo
        this.nombre = nombre
        this.categoria = categoria
    }

}
