package peskisa

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class PublicacionSpec extends Specification implements DomainUnitTest<Publicacion> {

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


    void 'test producto no puede ser NULL'() {
        when:
        domain.producto = null

        then:
        !domain.validate(['producto'])
        domain.errors['producto'].code == 'nullable'
    }

    void 'test comercio no puede ser NULL'() {
        when:
        domain.comercio = null

        then:
        !domain.validate(['comercio'])
        domain.errors['comercio'].code == 'nullable'
    }
}
