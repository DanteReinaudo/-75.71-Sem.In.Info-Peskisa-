package peskisa

interface Denunciable {
    List<Denuncia> denunciasRecibidas
    void recibirDenuncia(Denuncia denuncia)
    void eliminarDenunciaRecibida(Denuncia denuncia)
}
