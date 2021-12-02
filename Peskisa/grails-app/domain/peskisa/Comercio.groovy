package peskisa

class Comercio {
    String nombre;
    //ubicacion Ubicacion;
    static constraints = {
        nombre blank: false, nullable: false
        //ubicacion blank: false, nullable: false
    }

    Comercio(String nombre){
        this.nombre = nombre
    }
}
