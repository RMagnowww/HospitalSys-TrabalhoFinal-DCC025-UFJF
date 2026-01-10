package br.ufjf.dcc.model;

import br.ufjf.dcc.model.enums.TipoDocumento;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class ReceitaTest{
    private static final String VALID_MEDICAMENTOS = "Dipirona";
    private static final String VALID_PACIENTE_NAME = "Joao";
    private static final String VALID_PACIENTE_CPF = "086.756.245-90";
    private static final String VALID_PACIENTE_PHONE = "(32)96655-3322";
    private static final String VALID_PACIENTE_EMAIL = "joao@paciente.com";
    private static final String VALID_PACIENTE_PASSWORD = "paciente123";
    private static final String VALID_DATA_NASCIMENTO = "07/04/1986";
    private static final String VALID_TIPO_SANGUINEO = "A+";
    private static final Endereco VALID_ENDERECO = new Endereco("Padre Cafe", "Paineiras", "78645-320", "Juiz de Fora", "210");
    private static final String VALID_MEDICO_NAME = "Dr. Andrade";
    private static final String VALID_MEDICO_CPF = "556.854.145-90";
    private static final String VALID_MEDICO_PHONE = "(32)95872-0813";
    private static final String VALID_MEDICO_EMAIL = "andrade@medico.com";
    private static final String VALID_MEDICO_PASSWORD = "medico123";
    private static final String VALID_MEDICO_CRM = "123456";
    private static final String VALID_MEDICO_ESPECIALIDADE = "Ortopedista";

    private Paciente validPaciente;
    private Medico validMedico;
    private Receita validReceita;

    @BeforeEach
        void setUp() {
        validMedico = new Medico(VALID_MEDICO_NAME, VALID_MEDICO_CPF, VALID_MEDICO_PHONE, VALID_MEDICO_EMAIL, VALID_MEDICO_PASSWORD, VALID_MEDICO_CRM, VALID_MEDICO_ESPECIALIDADE);
        validPaciente = new Paciente(VALID_PACIENTE_NAME, VALID_PACIENTE_CPF, VALID_PACIENTE_PHONE, VALID_PACIENTE_EMAIL, VALID_PACIENTE_PASSWORD, VALID_ENDERECO, VALID_DATA_NASCIMENTO, VALID_TIPO_SANGUINEO);
        validReceita = new Receita(validMedico, validPaciente, VALID_MEDICAMENTOS);
    }
     @Test
    void should_create_receita_with_valid_data() {
        assertNotNull(validReceita);
        assertEquals(validMedico, validReceita.getMedico());
        assertEquals(validPaciente, validReceita.getPaciente());
        assertEquals(TipoDocumento.RECEITA, validReceita.getTipo());
        assertEquals(VALID_MEDICAMENTOS, validReceita.getMedicamentos());
    }
    @Test
    void should_throw_exception_when_medicamentos_is_null() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Receita(validMedico, validPaciente, null);
        });
    }

    @Test
    void should_throw_exception_when_medicamentos_is_empty() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Receita(validMedico, validPaciente, "");
        });
    }
}