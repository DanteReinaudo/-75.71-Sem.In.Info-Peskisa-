package peskisa

enum Estado {ACTIVO ,ELIMINADO}
enum Valoracion {POSITIVA,NEGATIVA,NULA}

class Usuario {

    String nombre
    String email
    String contrasenia
    //date fecha_nacimiento
    Estado estado

    boolean equals(object) {
        if (this.is(object)) return true
        if (getClass() != object.class) return false
        if (!super.equals(object)) return false

        Usuario usuario = (Usuario) object
        if (contrasenia != usuario.contrasenia) return false
        if (email != usuario.email) return false
        if (estado != usuario.estado) return false
        if (nombre != usuario.nombre) return false
        if (publicaciones != usuario.publicaciones) return false

        return true
    }

    ArrayList<Publicacion> publicaciones = []
    ArrayList<Publicacion> historial = []
    ArrayList<Publicacion> favoritos = []

    Set<Calificacion> calificaciones = []
    Set<Reporte> reportes = []

    static hasMany = [
            publicaciones: Publicacion,
            historial: Publicacion,
            favoritos: Publicacion
    ]

    static constraints = {
        nombre blank: false, nullable: false
        email blank: false, nullable: false
        contrasenia blank: false, nullable: false
        //fecha_nacimiento blank: false, nullable: false
    }

    Usuario (String nombre, String email, String contrasenia) {
        this.nombre = nombre
        this. email = email
        this.contrasenia = contrasenia
        //this.fecha_nacimiento = fecha_nacimiento
        this.estado = Estado.ACTIVO
    }

    void guardarPublicacion(Publicacion publicacion){
        favoritos.push(publicacion)
    }


    Calificacion calificarPublicacion(Publicacion publicacion, Valoracion valoracion){
        this.validar()
        Calificacion calificacionPrevia = this.calificaciones.find{ Calificacion calificacion ->
            calificacion.publicacion = publicacion
        }

        if (calificacionPrevia != null){
            publicacion.calificar(calificacionPrevia,valoracion)
            return calificacionPrevia
        } else {
            Calificacion calificacion = new Calificacion(this,publicacion,valoracion)
            this.calificaciones  << calificaciones
            publicacion.calificar(calificacion,valoracion);
            calificacion
        }


    }


    Reporte reportarPublicacion(Publicacion publicacion,String motivo){
        this.validar()
        boolean reportado = this.reportes.any{ Reporte reporte ->
            reporte.publicacion = publicacion
        }
        if (reportado) throw new IllegalStateException("El usuario=${this} ya reporto la publicacion =${publicacion}")

        Reporte reporte = new Reporte(this,publicacion,motivo)
        this.reportes  << reporte
        publicacion.reportar(reporte);
        reporte
    }

    void validar(){
        if (this.estado == Estado.ELIMINADO) throw new IllegalStateException("El usuario ha sido eliminado")
    }

    void eliminar(){
        this.estado = Estado.ELIMINADO
    }

}
