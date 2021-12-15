package peskisa

class Comentario implements Denunciable, Calificable{

    static int MAX_DENUNCIAS = 3

    @java.lang.Override
    public java.lang.String toString() {
        return "Comentario{" +
                "usuario=" + usuario +
                "mensaje=" + mensaje +
                '}';
    }

    enum Estado {ACTIVO ,ELIMINADO, DENUNCIADO}
    Usuario usuario
    Publicacion publicacion
    String mensaje
    int megustas
    int nomegustas
    List<Calificacion> calificaciones = []
    List<Denuncias> denunciasRecibidas = []



    static constraints = {
        usuario nullable: false
        publicacion nullable: false
        mensaje nullable: false,blank : false, maxsize: 255
    }

    Comentario(Usuario usuario,Publicacion publicacion, String mensaje) {
        this.usuario = usuario
        this.publicacion = publicacion
        this.mensaje = mensaje
        this.megustas = 0
        this.nomegustas = 0
    }

    void calificar(Calificacion calificacion,Valoracion valoracion){
        this.validar()

        Calificacion calificacionPrevia = this.calificaciones.find{ Calificacion c ->
            c == calificacion
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
            this.calificaciones.add(calificacion)
            if (calificacion.valoracion == Valoracion.POSITIVA){
                this.megustas++
            } else if (calificacion.valoracion == Valoracion.NEGATIVA) {
                this.nomegustas++
            }
        }
    }

    void recibirDenuncia(Denuncia denuncia){
        this.validar()
        this.denunciasRecibidas.add(denuncia)
        if(this.denunciasRecibidas.length >= MAX_DENUNCIAS){
            this.estado = Estado.DENUNCIADO
        }
    }

    void eliminarDenunciaRecibida(Denuncia denuncia){
        if (denuncia.denunciado != this) throw new IllegalStateException("La denuncia =${denuncia} no corresponde al comentario =${this}")
        this.denunciasRecibidas.remove(denuncia)
        if(this.denunciasRecibidas.length < MAX_DENUNCIAS) {
            this.estado = Estado.ACTIVO
        }
    }

    void validar(){
        if (this.estado == Estado.ELIMINADO) throw new IllegalStateException("El comentario ha sido eliminado")
    }
}
