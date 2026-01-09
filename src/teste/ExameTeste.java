package br.ufjf.dcc.model;

import br.ufjf.dcc.model.enums.TipoDocumento;
import br.ufjf.dcc.model.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class ExameTest{
    private static final String VALID_NOMEEXAME = "Resonancia";
    private static final String VALID_RESULTADO = "Nenhum";
    private static final String VALID_PACIENTE_NAME = "Joao";
    private static final String VALID_PACIENTE_CPF = "086.756.245-90";
    private static final String VALID_PACIENTE_PHONE = "(32)96655-3322";
    private static final String VALID_PACIENTE_EMAIL = "joao@paciente.com";
    private static final String VALID_PACIENTE_PASSWORD = "paciente123";
    private static final String VALID_DATA_NASC = "07/04/1986";
    private static final String VALID_TIPO_SANGUE = "A+";
    private static final Endereco VALID_ENDERECO = Endereco.getInstance("Padre Cafe, 210 - Paineiras, Juiz de Fora - CEP: 78645-320");
    private static final String VALID_MEDICO_NAME = "Dr. Andrade";
    private static final String VALID_MEDICO_CPF = "556.854.145-90";
    private static final String VALID_MEDICO_PHONE = "(32)95872-0813";
    private static final String VALID_MEDICO_EMAIL = "andrade@medico.com";
    private static final String VALID_MEDICO_PASSWORD = "medico123";
    private static final String VALID_MEDICO_CRM = "12345-MG";

    private Paciente validPaciente;
    private Medico validMedico;

    private Exame validExame;
        @BeforeEach
        void setUp() {
        validMedico = new Medico(VALID_MEDICO_NAME, VALID_MEDICO_CPF, VALID_MEDICO_PHONE, VALID_MEDICO_EMAIL, VALID_MEDICO_PASSWORD, VALID_MEDICO_CRM);
        validPaciente = new Paciente(VALID_PACIENTE_NAME, VALID_PACIENTE_CPF, VALID_PACIENTE_PHONE, VALID_PACIENTE_EMAIL, VALID_PACIENTE_PASSWORD, VALID_ENDERECO, VALID_DATA_NASC, VALID_TIPO_SANGUE);
        validExame = new Exame(validMedico, validPaciente, VALID_NOMEEXAME, VALID_RESULTADO);
    }
     @Test
    void should_create_receita_with_valid_data() {
        assertNotNull(validExame);
        assertEquals(validMedico, validExame.getMedico());
        assertEquals(validPaciente, validExame.getPaciente());
        assertEquals(TipoDocumento.EXAME, validExame.getTipo());
        assertEquals(VALID_NOMEEXAME, validExame.getNomeExame());
        assertEquals(VALID_RESULTADO, validExame.getResultado());
    }
        @Test
    void should_throw_exception_when_nomeexame_is_null() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Exame(validMedico, validPaciente, null, VALID_RESULTADO);
        });
    }

        @Test
    void should_throw_exception_when_nomeexame_is_empty() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Exame(validMedico, validPaciente, "", VALID_RESULTADO);
        });
    }
            @Test
    void should_throw_exception_when_resultado_is_null() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Exame(validMedico, validPaciente, VALID_NOMEEXAME, null);
        });
    }

        @Test
    void should_throw_exception_when_resultado_is_empty() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Exame(validMedico, validPaciente, VALID_NOMEEXAME, "");
        });
    }
}