package peskisa

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class CalificacionSpec extends Specification implements DomainUnitTest<Calificacion> {

    def setup() {
    }

    def cleanup() {
    }

    void 'test usuario no puede ser NULL'() {
        when:
        domain.usuario = null

        then:
        !domain.validate(['usuario'])
        domain.errors['usuario'].code == 'nullable'
    }


    void 'test publicacion no puede ser NULL'() {
        when:
        domain.publicacion = null

        then:
        !domain.validate(['publicacion'])
        domain.errors['publicacion'].code == 'nullable'
    }


}
