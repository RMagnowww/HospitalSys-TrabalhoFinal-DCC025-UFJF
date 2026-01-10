package br.ufjf.dcc.model;

import br.ufjf.dcc.model.enums.TipoDocumento;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class AtestadoTest{
    private static final int VALID_DIAS = 5;
    private static final String VALID_DESCRICAO = "Dor de barriga";
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
    private Atestado validAtestado;
    @BeforeEach
        void setUp() {
        validMedico = new Medico(VALID_MEDICO_NAME, VALID_MEDICO_CPF, VALID_MEDICO_PHONE, VALID_MEDICO_EMAIL, VALID_MEDICO_PASSWORD, VALID_MEDICO_CRM, VALID_MEDICO_ESPECIALIDADE);
        validPaciente = new Paciente(VALID_PACIENTE_NAME, VALID_PACIENTE_CPF, VALID_PACIENTE_PHONE, VALID_PACIENTE_EMAIL, VALID_PACIENTE_PASSWORD, VALID_ENDERECO, VALID_DATA_NASCIMENTO, VALID_TIPO_SANGUINEO);
        validAtestado = new Atestado(validMedico, validPaciente, VALID_DIAS, VALID_DESCRICAO);
    }
    @Test
    void should_create_atestado_with_valid_data() {
        assertNotNull(validAtestado);
        assertEquals(validMedico, validAtestado.getMedico());
        assertEquals(validPaciente, validAtestado.getPaciente());
        assertEquals(TipoDocumento.ATESTADO, validAtestado.getTipo());
        assertEquals(VALID_DIAS, validAtestado.getDiasAfastamento());
        assertEquals(VALID_DESCRICAO, validAtestado.getDescricao());
    }
        @Test
    void should_throw_exception_when_dias_afastamento_is_negative() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Atestado(validMedico, validPaciente, -1, VALID_DESCRICAO);
        });
    }

    @Test
    void should_change_dias_afastamento_when_valid() {
        validAtestado.setDiasAfastamento(10);
        assertEquals(10, validAtestado.getDiasAfastamento());
    }

}