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


    ArrayList<Calificacion> megustas
    ArrayList<Calificacion> nomegustas

    Set<Reporte> reportes = []


    static constraints = {
        usuario blank: false, nullable: false
        producto blank: false, nullable: false
        comercio blank: false, nullable: false
        fecha blank: false, nullable: false
    }

    void validar(){
        if (this.estado == Estado.ELIMINADO) throw new IllegalStateException("La publicacion ha sido eliminada")
    }

    Publicacion(Usuario usuario, Producto producto, Comercio comercio, String precio){
        this.usuario = usuario
        this.producto = producto
        this.comercio = comercio
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")
        this.fecha = dtf.format(LocalDateTime.now())
        this.precio = new BigDecimal(precio)
        this.estado = Estado.ACTIVO
    }

    void reportar(Reporte reporte){
        this.validar()
        this.reportes  << reportes
        if (reportes.length > 3){
            this.estado = Estado.ELIMINADO
        }
    }

    void valorar(Calificacion calificacion,boolean valoracion){
        if (valoracion == true){
            megustas.push(calificacion)
        } else {
            nomegustas.push(calificacion)
        }
    }
}
