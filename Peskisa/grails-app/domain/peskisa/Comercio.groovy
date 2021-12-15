package peskisa

class Comercio {
    String nombre;
    Ubicacion ubicacion;
    static constraints = {
        nombre blank: false, nullable: false
        ubicacion nullable: false
    }

    boolean equals(object) {
        if (this.is(object)) return true
        if (getClass() != object.class) return false
        if (!super.equals(object)) return false

        Comercio comercio = (Comercio) object

        if (nombre != comercio.nombre) return false
        return true
    }

    Comercio(String nombre,Ubicacion ubicacion){
        this.nombre = nombre
        this.ubicacion = ubicacion
    }
}
