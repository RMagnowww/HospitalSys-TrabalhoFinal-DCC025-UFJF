package br.ufjf.dcc.model;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
class PacienteTest {
     private static final String VALID_NAME = "Joao";
     private static final String VALID_CPF = "086.756.245-90";
     private static final String VALID_PASSWORD = "paciente123";
     private static final String VALID_PHONE = "(32)96655-3322";
     private static final String VALID_EMAIL = "joao@paciente.com";
     private static final String VALID_DATADENASCIMENTO = "07/04/1986";
     private static final String VALID_TIPOSANGUINEO = "A+";
     private static final Endereco VALID_ENDERECO = new Endereco("Padre Cafe", "Paineiras", "78645-320", "Juiz de Fora", "210");

    private Paciente validPaciente;
     @BeforeEach
    void setUp() {
        validPaciente = new Paciente(VALID_NAME, VALID_CPF, VALID_PHONE, VALID_EMAIL, VALID_PASSWORD, VALID_ENDERECO, VALID_DATADENASCIMENTO, VALID_TIPOSANGUINEO);
    }
     @Test
    void should_create_paciente_with_valid_data() {
        assertEquals(VALID_NAME, validPaciente.getNome());
        assertEquals(VALID_CPF, validPaciente.getCpf());
        assertEquals(VALID_PHONE, validPaciente.getTelefone());
        assertEquals(VALID_EMAIL, validPaciente.getEmail());
        assertEquals(VALID_PASSWORD, validPaciente.getSenha());
        assertEquals(VALID_DATADENASCIMENTO, validPaciente.getDataNascimento());
        assertEquals(VALID_TIPOSANGUINEO, validPaciente.getTipoSanguineo());
        assertEquals(VALID_ENDERECO, validPaciente.getEndereco());
    }
    /*@Test
    void should_throw_exception_when_dataNascimento_is_invalid() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Paciente(
                VALID_NAME, VALID_CPF, VALID_PHONE, VALID_EMAIL, VALID_PASSWORD,
                VALID_ENDERECO, "00/00/0000", VALID_TIPOSANGUINEO);
        });
    }

    @Test
    void should_throw_exception_when_tipoSanguineo_is_invalid() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Paciente(
                VALID_NAME, VALID_CPF, VALID_PHONE, VALID_EMAIL, VALID_PASSWORD,
                VALID_ENDERECO, VALID_DATADENASCIMENTO, "A+@");
        });
    }*/
    @Nested
@DisplayName("Usuario Tests")
class UsuarioTests {
        @Test
    void should_not_change_cpf_when_invalid() {
        validPaciente.setCpf("086.756.45-90");
        assertEquals("086.756.245-90", validPaciente.getCpf());
    }
        @Test
    void should_not_change_email_when_invalid() {
        validPaciente.setEmail("renapaciente.com");
        assertEquals(VALID_EMAIL, validPaciente.getEmail());
    }
        @Test
    void should_not_change_phone_when_invalid() {
        validPaciente.setTelefone("96655-3322");
        assertEquals("(32)96655-3322", validPaciente.getTelefone());
    }
        @Test
    void should_not_change_name_when_invalid() {
        validPaciente.setNome("");
        assertEquals(VALID_NAME, validPaciente.getNome());
    }
}
}