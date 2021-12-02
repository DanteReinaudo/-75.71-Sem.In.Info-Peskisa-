package peskisa

//enum Estado {ACTIVO,ELIMINADO}
class Reporte {
    Usuario usuario
    Publicacion publicacion
    String motivo
    Estado estado
    static constraints = {
        usuario nullable: false
        publicacion nullable: false
        motivo blank: false, nullable: false, maxSize: 255
    }

    boolean equals(object) {
        if (this.is(object)) return true
        if (getClass() != object.class) return false
        if (!super.equals(object)) return false

        Reporte reporte = (Reporte) object

        if (estado != reporte.estado) return false
        if (motivo != reporte.motivo) return false
        if (publicacion != reporte.publicacion) return false
        if (usuario != reporte.usuario) return false

        return true
    }


    Reporte(Usuario usuario, Publicacion publicacion, String motivo){
        this.usuario = usuario
        this.publicacion = publicacion
        this.motivo = motivo
        this.estado = Estado.ACTIVO
    }

    void eliminar(){
        this.estado = Estado.ELIMINADO
    }

}
