package peskisa

class Comercio {
    String nombre;
    //ubicacion Ubicacion;
    static constraints = {
        nombre blank: false, nullable: false
        //ubicacion blank: false, nullable: false
    }

    boolean equals(object) {
        if (this.is(object)) return true
        if (getClass() != object.class) return false
        if (!super.equals(object)) return false

        Comercio comercio = (Comercio) object

        if (nombre != comercio.nombre) return false
        return true
    }

    Comercio(String nombre){
        this.nombre = nombre
    }
}
