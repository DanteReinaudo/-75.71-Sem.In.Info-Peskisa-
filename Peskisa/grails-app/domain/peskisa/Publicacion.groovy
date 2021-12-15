package peskisa
import java.time.LocalDateTime
import java.util.*


class Publicacion implements Denunciable,Calificable {

    static int MAX_DENUNCIAS = 3
    static int MAX_COMENTARIOS_USUARIO = 5
    enum Estado {ACTIVO ,ELIMINADO,DENUNCIADO}
    Usuario usuario
    Producto producto
    Comercio comercio
    LocalDateTime fecha
    static Suplier<LocalDateTime> ahora = {LocalDateTime.now()}
    BigDecimal precio
    Estado estado

    List<Calificacion> calificaciones = []
    int megustas
    int nomegustas

    List<Denuncias> denunciasRecibidas = []
    List<Comentario> comentarios = []


    static constraints = {
        usuario nullable: false
        producto nullable: false
        comercio nullable: false
        fecha nullable: false
        precio nullable: false
    }

    void validar(){
        if (this.estado == Estado.ELIMINADO) throw new IllegalStateException("La publicacion ha sido eliminada")
    }

    boolean equals(object) {
        if (this.is(object)) return true
        if (getClass() != object.class) return false
        if (!super.equals(object)) return false

        Publicacion that = (Publicacion) object

        if (comercio != that.comercio) return false
        if (precio != that.precio) return false
        if (producto != that.producto) return false
        if (usuario != that.usuario) return false

        return true
    }


    Publicacion(Usuario usuario, Producto producto, Comercio comercio, String precio){
        this.usuario = usuario
        this.producto = producto
        this.comercio = comercio
        this.fecha = ahora.get()
        this.precio = new BigDecimal(precio)
        this.estado = Estado.ACTIVO
        this.megustas = 0
        this.nomegustas = 0
    }


    void calificar(Calificacion calificacion,Valoracion valoracion){
        this.validar()

        Calificacion calificacionPrevia = this.calificaciones.find{ Calificacion calif ->
            calif == calificacion
        }

        if (calificacionPrevia != null){
            if (calificacionPrevia.valoracion == Valoracion.POSITIVA && valoracion != Valoracion.POSITIVA ){
                this.megustas --
            } else if (calificacionPrevia.valoracion == Valoracion.NEGATIVA && valoracion != Valoracion.NEGATIVA ){
                this.nomegustas --
            } else if (calificacionPrevia.valoracion == Valoracion.NULA ) {
                if (valoracion == Valoracion.POSITIVA){
                    this.megustas ++
                } else if (valoracion == Valoracion.NEGATIVA){
                    this.nomegustas ++
                }
            }
            calificacionPrevia.cambiarValoracion(valoracion)

        } else {
            this.calificaciones.add(calificacion)
            if (calificacion.valoracion == Valoracion.POSITIVA){
                this.megustas++
            } else if (calificacion.valoracion == Valoracion.NEGATIVA) {
                this.nomegustas++
            }
        }
    }

    void recibirDenuncia(Denuncia denuncia){
        this.validar()
        this.denunciasRecibidas.add(denuncia)
        if(this.denunciasRecibidas.length >= MAX_DENUNCIAS){
            this.estado = Estado.DENUNCIADO
        }
    }

    void eliminarDenunciaRecibida(Denuncia denuncia){
        this.validar()
        if (denuncia.denunciado != this) throw new IllegalStateException("La denuncia =${denuncia} no corresponde a la publicacion =${this}")
        this.denunciasRecibidas.remove(denuncia)
        if(this.denunciasRecibidas.length < MAX_DENUNCIAS) {
            this.estado = Estado.ACTIVO
        }
    }

    void comentar(Comentario comentario){
        this.validar()
        List<Comentario> comentariosPrevios = this.comentarios.findAll{ Comentario c ->
            c.usuario == comentario.usuario
        }
        if (comentariosPrevios.length > MAX_COMENTARIOS_USUARIO) throw new IllegalStateException("El usuario=${comentario.usuario} ya realizo demasiados comentarios en =${this}")
        this.comentarios.add(comentario)
    }

}
