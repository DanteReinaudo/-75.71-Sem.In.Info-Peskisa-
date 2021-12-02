package peskisa

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class ProductoSpec extends Specification implements DomainUnitTest<Producto> {

    def setup() {
    }

    def cleanup() {
    }


    void 'test nombre no puede ser blank'() {
        when:
        domain.nombre = ''

        then:
        !domain.validate(['nombre'])
    }

    void 'test marca no puede ser blank'() {
        when:
        domain.marca = ''

        then:
        !domain.validate(['marca'])
    }

    void 'test modelo no puede ser blank'() {
        when:
        domain.modelo = ''

        then:
        !domain.validate(['modelo'])
    }

    void 'test categoria no puede ser blank'() {
        when:
        domain.categoria = ''

        then:
        !domain.validate(['categoria'])
    }
}
