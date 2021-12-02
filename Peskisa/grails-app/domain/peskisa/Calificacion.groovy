package peskisa

//enum Valoracion {POSITIVA,NEGATIVA,NULA}

class Calificacion {
    Usuario usuario
    Publicacion publicacion
    Valoracion valoracion

    static constraints = {
        usuario blank: false, nullable: false
        publicacion blank: false, nullable: false
    }

    Calificacion(Usuario usuario, Publicacion publicacion, Valoracion valoracion) {
        this.usuario = usuario
        this.publicacion = publicacion
        this.valoracion = valoracion
    }

    void CambiarValoracion(Valoracion valoracion){
        this.valoracion = Valoracion.valoracion
    }

    void quitarValoracion(){
        this.valoracion = Valoracion.NULA;
    }
}
