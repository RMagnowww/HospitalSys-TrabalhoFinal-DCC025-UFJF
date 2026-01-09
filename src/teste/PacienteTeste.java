package br.ufjf.dcc.model;

import br.ufjf.dcc.model.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
class PacienteTest {
     private static final String VALID_NAME = String.getInstance("Joao");
     private static final String VALID_CPF = String.getInstance("086.756.245-90");
     private static final String VALID_PASSWORD = String.getInstance("paciente123");
     private static final String VALID_PHONE = String.getInstance("(32)96655-3322");
     private static final String VALID_EMAIL = String.getInstance("joao@paciente.com");
     private static final PerfilUsuario VALID_PROFILE = PerfilUsuario.PACIENTE;
     private static final String VALID_DATADENACIMENTO = String.getInstance("07/04/1986");
     private static final String VALID_TIPOSANGUINEO = String.getInstance("A+");
     private static final Endereco VALID_ENDERECO = Endereco.getInstance("Padre Cafe, 210 - Paineiras, Juiz de Fora - CEP: 78645-320");

     @BeforeEach
    void setUp() {
        validPaciente = new Paciente(VALID_NAME, VALID_CPF, VALID_PHONE, VALID_EMAIL, VALID_PASSWORD, VALID_ENDERECO, VALID_DATADENACIMENTO, VALID_TIPOSANGUINEO);
    }
      @Nested
    @DisplayName("Datadenacimento Tests")
     class DatadenacimentoTests{
           @Test
        void should_validate_datadenacimento_when_check_datadenacimento_of_paciente1() {
            assertTrue(validUser.checkDatadenacimento("07/04/1986"));
        }

        @Test
        void should_not_validate_datadenacimento_when_check_datadenacimento_of_paciente1() {
            assertFalse(validUser.checkDatadenacimento("7/04/1986"));
        }

        @Test
        void should_not_validate_null_datadenacimento() {
            assertFalse(validUser.checkDatadenacimento(null));
        }

        @Test
        void should_not_validate_empty_datadenacimento() {
            assertFalse(validUser.checkDatadenacimento(""));
        }
     }
           @Nested
    @DisplayName("Tiposanguineo Tests")
     class TiposanguineoTests{
           @Test
        void should_validate_Tiposanguineo_when_check_Tiposanguineo_of_paciente1() {
            assertTrue(validUser.checkTiposanguineo("A+"));
        }

        @Test
        void should_not_validate_Tiposanguineo_when_check_Tiposanguineo_of_paciente1() {
            assertFalse(validUser.checkTiposanguineo("A+@"));
        }

        @Test
        void should_not_validate_null_() {
            assertFalse(validUser.checkTiposanguineo(null));
        }

        @Test
        void should_not_validate_empty_() {
            assertFalse(validUser.checkTiposanguineo(""));
        }
     }
}