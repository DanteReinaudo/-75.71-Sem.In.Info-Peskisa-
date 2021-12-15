package peskisa
import java.util.Math.*

class Ubicacion {

    int latitud
    int longitud

    static constraints = {
    }

    Ubicacion(int latitud, int longitud) {
        this.latitud = latitud
        this.longitud = longitud
    }

    boolean equals(object) {
        if (this.is(object)) return true
        if (getClass() != object.class) return false
        if (!super.equals(object)) return false

        Ubicacion ubicacion = (Ubicacion) object

        if (latitud != ubicacion.latitud) return false
        if (longitud != ubicacion.longitud) return false

        return true
    }


    //Como metrica para la calcular la distancia se usa la distancia Euclidea
    float calcularDistancia(Ubicacion ubicacion){
        return sqrt( pow(this.latitud - ubicacion.latitud) + pow(this.longitud- ubicacion.longitud) )
    }
}
