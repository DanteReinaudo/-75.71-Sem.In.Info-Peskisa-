package peskisa

class Denuncia {
    enum Estado {ACTIVA,ELIMINADA}
    Usuario denunciante
    Denunciable denunciado
    String motivo
    Estado estado

    static constraints = {
        denunciante nullable: false
        denunciado nullable: false
        motivo blank: false, nullable: false, maxSize: 255
    }

    boolean equals(object) {
        if (this.is(object)) return true
        if (getClass() != object.class) return false
        if (!super.equals(object)) return false

        Denuncia denuncia = (Denuncia) object

        if (denunciado != denuncia.denunciado) return false
        if (denunciante != denuncia.denunciante) return false

        return true
    }

    Denuncia(Usuario denunciante, Denunciable denunciado,String motivo){
        this.denunciante = denunciante
        this.denunciado = denunciado
        this.motivo = motivo
        this.estado = Estado.ACTIVA
    }

    void eliminar(){
        this.estado = Estado.ELIMINADA
    }

}
