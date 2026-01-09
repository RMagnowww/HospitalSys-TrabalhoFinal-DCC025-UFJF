package br.ufjf.dcc.model;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
class MedicoTest {
    private static final String VALID_NAME = "Dr. Andrade";
    private static final String VALID_CPF = "556.854.145-90";
    private static final String VALID_PHONE = "(32)95872-0813";
    private static final String VALID_EMAIL = "andrade@medico.com";
    private static final String VALID_PASSWORD = "medico123";
    private static final String VALID_CRM = "12345-MG";
    private static final String VALID_ESPECIALIDADE = "Ortopedista";

    private Medico validMedico;
    @BeforeEach
        void setUp() {
        validMedico = new Medico(VALID_NAME, VALID_CPF, VALID_PHONE, VALID_EMAIL, VALID_PASSWORD, VALID_CRM, VALID_ESPECIALIDADE);
        }
         @Test
        void should_create_medico_with_valid_data() {
        assertEquals(VALID_NAME, validMedico.getNome());
        assertEquals(VALID_CPF, validMedico.getCpf());
        assertEquals(VALID_PHONE, validMedico.getTelefone());
        assertEquals(VALID_EMAIL, validMedico.getEmail());
        assertEquals(VALID_PASSWORD, validMedico.getSenha());
        assertEquals(VALID_CRM, validMedico.getCrm());
        assertEquals(VALID_ESPECIALIDADE, validMedico.getEspecialidade());
    }
    /*    @Test
    void should_throw_exception_when_CRM_is_invalid() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Medico(VALID_NAME, VALID_CPF, VALID_PHONE, VALID_EMAIL, VALID_PASSWORD, "12345-mg@", VALID_ESPECIALIDADE);
        });
    }*/
         @Test
    void should_throw_exception_when_especialidade_is_empty() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Medico(VALID_NAME, VALID_CPF, VALID_PHONE, VALID_EMAIL, VALID_PASSWORD, VALID_CRM, "");
        });

}
}
    
//public Medico(String nome, String cpf, String telefone, String email, String senha, String crm, String especialidade)