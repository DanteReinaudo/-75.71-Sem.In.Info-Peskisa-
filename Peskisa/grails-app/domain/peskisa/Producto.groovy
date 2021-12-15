package peskisa

class Producto {
    String marca
    String modelo
    String nombre
    String categoria

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

    boolean equals(object) {
        if (this.is(object)) return true
        if (getClass() != object.class) return false
        if (!super.equals(object)) return false

        Producto producto = (Producto) object

        if (categoria != producto.categoria) return false
        if (marca != producto.marca) return false
        if (modelo != producto.modelo) return false
        if (nombre != producto.nombre) return false

        return true
    }

}
