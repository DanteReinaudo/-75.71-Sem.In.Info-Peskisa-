package peskisa

//enum Estado {ACTIVO,ELIMINADO}
class Reporte {
    Usuario usuario
    Publicacion publicacion
    String motivo
    Estado estado
    static constraints = {
        usuario blank: false, nullable: false
        publicacion blank: false, nullable: false
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
