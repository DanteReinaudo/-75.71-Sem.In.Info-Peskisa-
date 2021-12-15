package peskisa
import java.time.*


class Usuario implements Denunciable {

    static int MAX_DENUNCIAS = 3;
    enum Estado {ACTIVO ,ELIMINADO, DENUNCIADO}
    enum Valoracion {POSITIVA,NEGATIVA,NULA}
    String nombre
    String email
    String contrasenia
    LocalDate fechaNacimiento
    Ubicacion ubicacion
    Estado estado

    boolean equals(object) {
        if (this.is(object)) return true
        if (getClass() != object.class) return false
        if (!super.equals(object)) return false

        Usuario usuario = (Usuario) object
        if (email != usuario.email) return false
        
        return true
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Usuario{" +
                "nombre='" + nombre + '\'' +
                '}';
    }

    List<Publicacion> publicaciones = []
    Set<Publicacion> favoritos = []
    List<Calificacion> calificaciones = []
    List<Denuncia> denunciasRealizadas = []
    List<Denuncia> denunciasRecibidas = []

    static hasMany = [
            publicaciones: Publicacion,
            favoritos: Publicacion,
            calificaciones: Calificacion,
            denunciasRelizadas: Denuncia,
            denunciasRecibidas: Denuncia
    ]

    static constraints = {
        nombre blank: false, nullable: false
        email blank: false, nullable: false
        contrasenia blank: false, nullable: false
        fechaNacimiento nullable: false
        ubicacion nullable: false
    }

    Usuario (String nombre, String email, String contrasenia, LocalDate fechaNacimiento,Ubicacion ubicacion) {
        this.nombre = nombre
        this. email = email
        this.contrasenia = contrasenia
        this.fechaNacimiento = fechaNacimiento
        this.ubicacion = ubicacion
        this.estado = Estado.ACTIVO
    }

    void agregarAFavoritos(Publicacion publicacion){
        this.validar()
        favoritos.add(publicacion)
    }

    void eliminarDeFavoritos(Publicacion publicacion){
        this.validar()
        favoritos.remove(publicacion)
    }

    Calificacion realizarCalificacion(Calificable calificado, Valoracion valoracion){
        this.validar()
        Calificacion calificacionPrevia = this.calificaciones.find{ Calificacion calificacion ->
            calificacion.calificado == calificado
        }
        if (calificacionPrevia != null){
            calificado.calificar(calificacionPrevia,valoracion)
            return calificacionPrevia
        } else {
            Calificacion calificacion = new Calificacion(this,calificado,valoracion)
            this.calificaciones.add(calificaciones)
            calificado.calificar(calificacion,valoracion)
            return calificacion
        }
    }

    Denuncia realizarDenuncia(Denunciable denunciado,String motivo){

        this.validar()
        this.validarDenunciable(denunciado)

        boolean yaDenuncie = this.denunciasRealizadas.any{ Denuncia denuncia ->
            denuncia.denunciado == denunciado
        }
        if (yaDenuncie) throw new IllegalStateException("El usuario=${this} ya denuncio a =${denunciado}")

        Denuncia denuncia = new Denuncia(this,denunciado,motivo)
        this.denunciasRealizadas.add(denuncia)
        denunciado.recibirDenuncia(denuncia);
        denuncia
    }

    void eliminarDenunciaRealizada(Denuncia denuncia){
        this.validar()
        if (denuncia.denunciante != this)  throw new IllegalStateException("El usuario=${this} no puede eliminar la denuncia =${denuncia} porque no le pertenece")
        this.denunciasRealizadas.remove(denuncia)
        denuncia.denunciado.eliminarDenunciaRecibida(denuncia)
        denuncia.eliminar()
    }

    void recibirDenuncia(Denuncia denuncia){
        this.validar()
        this.denunciasRecibidas.add(denuncia)
        if(this.denunciasRecibidas.length >= MAX_DENUNCIAS){
            this.estado = Estado.DENUNCIADO
        }
    }

    void eliminarDenunciaRecibida(Denuncia denuncia){
        if (denuncia.denunciado != this) throw new IllegalStateException("La denuncia =${denuncia} no corresponde al usuario=${this}")
        this.denunciasRecibidas.remove(denuncia)
        if(this.denunciasRecibidas.length < MAX_DENUNCIAS) {
            this.estado = Estado.ACTIVO
        }
    }

    Publicacion crearPublicacion(Producto producto, Comercio comercio, String precio){
        this.validar()
        Publicacion publicacion = new Publicacion (this,producto,comercio,precio)
        this.publicaciones.add(publicacion)
        publicacion
    }

    void eliminarPublicacion(Publicacion publicacion){
        this.validar()
        if (publicacion.usuario != this) throw new IllegalStateException("El usuario=${this} no puede eliminar la publicacion =${publicacion} porque no le pertenece")
        this.publicaciones.remove(publicacion)
        publicacion.eliminar()
    }

    Comentario comentarPublicacion(Publicacion publicacion, String mensaje){
        this.validar()
        Comentario comentario = new Comentar(this,publicacion,mensaje)
        publicacion.comentar(comentario)
    }


    void validar(){
        if (this.estado == Estado.ELIMINADO) throw new IllegalStateException("El usuario ha sido eliminado")
        if (this.estado == Estado.DENUNCIADO) throw new IllegalStateException("El usuario ha sido bloqueado por recibir demasiadas denuncias")
    }

    void validarDenunciable(Denunciable denunciado){
        if (this == denunciado) throw new IllegalStateException("El usuario=${this} no puede denunciarse a si mismo")
        if (denunciado instanceof Publicacion){
            if (this == denunciado.usuario) throw new IllegalStateException("El usuario=${this} no puede denunciar su propia publicacion")
        }
        if (denunciado instanceof Comentario){
            if (this == denunciado.usuario) throw new IllegalStateException("El usuario=${this} no puede denunciar su propio comentario")
        }
    }

    void eliminar(){
        this.estado = Estado.ELIMINADO
    }




}
