package peskisa

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class UsuarioSpec extends Specification implements DomainUnitTest<Usuario> {

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

    void 'test email no puede ser blank'() {
        when:
        domain.email = ''

        then:
        !domain.validate(['email'])
    }

    void 'test contrasenia no puede ser blank'() {
        when:
        domain.contrasenia = ''

        then:
        !domain.validate(['contrasenia'])
    }

    /*
    void "test something"() {
        expect:"fix me"
            true == false
    }
     */
}
