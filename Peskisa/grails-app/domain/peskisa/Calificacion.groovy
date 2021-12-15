package peskisa

class Calificacion {
    enum Valoracion {POSITIVA,NEGATIVA,NULA}
    Usuario usuario
    Calificalble calificado
    Valoracion valoracion

    static constraints = {
        usuario nullable: false
        calificado nullable: false
    }

    Calificacion(Usuario usuario, Calificable calificado, Valoracion valoracion) {
        this.usuario = usuario
        this.calificado = calificado
        this.valoracion = valoracion
    }

    boolean equals(object) {
        if (this.is(object)) return true
        if (getClass() != object.class) return false
        if (!super.equals(object)) return false

        Calificacion that = (Calificacion) object

        if (calificado != that.calificado) return false
        if (usuario != that.usuario) return false

        return true
    }

    void cambiarValoracion(Valoracion valoracion){
        this.valoracion = valoracion
    }

}
