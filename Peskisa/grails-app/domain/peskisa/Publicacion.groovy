package peskisa
//import java.time.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

//enum Estado {ACTIVO ,ELIMINADO}

class Publicacion {
    Usuario usuario
    Producto producto
    Comercio comercio
    LocalDateTime fecha
    BigDecimal precio
    Estado estado

    ArrayList<Calificacion> calificaciones
    int megustas
    int nomegustas

    Set<Reporte> reportes = []


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
        if (estado != that.estado) return false
        if (fecha != that.fecha) return false
        if (megustas != that.megustas) return false
        if (nomegustas != that.nomegustas) return false
        if (precio != that.precio) return false
        if (producto != that.producto) return false
        if (reportes != that.reportes) return false
        if (usuario != that.usuario) return false

        return true
    }


    Publicacion(Usuario usuario, Producto producto, Comercio comercio, String precio){
        this.usuario = usuario
        this.producto = producto
        this.comercio = comercio
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")
        this.fecha = dtf.format(LocalDateTime.now())
        this.precio = new BigDecimal(precio)
        this.estado = Estado.ACTIVO
        this.megustas = 0
        this.nomegustas = 0
    }

    void reportar(Reporte reporte){
        this.validar()
        this.reportes  << reportes
        if (reportes.length > 3){
            this.estado = Estado.ELIMINADO
        }
    }

    void calificar(Calificacion calificacion,Valoracion valoracion){
        this.validar()

        Calificacion calificacionPrevia = this.calificaciones.find{ Calificacion calif ->
            calif = calificacion
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
            this.calificaciones  << calificacion
            if (calificacion.valoracion == Valoracion.POSITIVA){
                this.megustas++
            } else if (calificacion.valoracion == Valoracion.NEGATIVA) {
                this.nomegustas++
            }
        }
    }

}
