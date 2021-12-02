package peskisa

//enum Valoracion {POSITIVA,NEGATIVA,NULA}

class Calificacion {
    Usuario usuario
    Publicacion publicacion
    Valoracion valoracion

    static constraints = {
        usuario nullable: false
        publicacion nullable: false
    }

    Calificacion(Usuario usuario, Publicacion publicacion, Valoracion valoracion) {
        this.usuario = usuario
        this.publicacion = publicacion
        this.valoracion = valoracion
    }

    boolean equals(object) {
        if (this.is(object)) return true
        if (getClass() != object.class) return false
        if (!super.equals(object)) return false

        Calificacion that = (Calificacion) object

        if (publicacion != that.publicacion) return false
        if (usuario != that.usuario) return false
        //if (valoracion != that.valoracion) return false

        return true
    }

    void CambiarValoracion(Valoracion valoracion){
        this.valoracion = Valoracion.valoracion
    }

}
