package peskisa
import java.time.*

class Publicacion {
    Usuario usuario
    Producto producto
    Comercio comercio
    LocalDate fecha
    int megustas
    int nomegustas
    BidDecimal precio
    static constraints = {
    }
}
