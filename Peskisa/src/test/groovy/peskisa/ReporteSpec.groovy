package peskisa

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class ReporteSpec extends Specification implements DomainUnitTest<Reporte> {

    def setup() {
    }

    def cleanup() {
    }

    void 'test usuario no puede ser null'() {
        when:
        domain.usuario = null

        then:
        !domain.validate(['usuario'])
        domain.errors['usuario'].code == 'nullable'
    }

    void 'test publicacion no puede ser null'() {
        when:
        domain.publicacion = null

        then:
        !domain.validate(['publicacion'])
        domain.errors['publicacion'].code == 'nullable'
    }

    void 'test motivo no puede ser blank'() {
        when:
        domain.motivo = ''

        then:
        !domain.validate(['motivo'])
    }

    void 'test motivo puede tener un maximo de 255 caracteres'() {
        when: 'se esribe un string de 256 caracteres'
        String str = 'a' * 256
        domain.motivo = str

        then: 'falla la validacion del motivo'
        !domain.validate(['motivo'])
        domain.errors['motivo'].code == 'maxSize.exceeded'

        when: 'se escribe un caracter de 255 caracteres'
        str = 'a' * 255
        domain.motivo = str

        then: 'la validacion del motivo pasa'
        domain.validate(['motivo'])
    }



}
