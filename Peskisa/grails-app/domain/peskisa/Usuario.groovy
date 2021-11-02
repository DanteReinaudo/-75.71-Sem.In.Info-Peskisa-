package peskisa

class Usuario {

    String nombre
    String email
    String contraseña
    int id
    ArrayList<Publicacion> publicaciones = []
    ArrayList<Publicacion> historial = []
    ArrayList<Publicacion> favoritos = []
    static hasMany = [
            publicaciones: Publicacion,
            historial: Publicacion,
            favoritos: Publicacion
    ]
    static constraints = {
        nombre blank: false, nullable: false
        email blank: false, nullable: false
        password blank: false, nullable: false
        id blank: false, nullable: false
    }
}
