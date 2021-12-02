package peskisa

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class ComercioSpec extends Specification implements DomainUnitTest<Comercio> {

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

}
