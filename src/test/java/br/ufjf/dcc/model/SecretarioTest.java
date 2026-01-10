package br.ufjf.dcc.model;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
class SecretarioTest {
    private static final String VALID_NAME = "Rafael";
    private static final String VALID_CPF = "678.492.841-22";
    private static final String VALID_PHONE = "(32)91459-5588";
    private static final String VALID_EMAIL = "rafael@admin.com";
    private static final String VALID_PASSWORD = "admin123";
    private static final String VALID_MATRICULA = "123456";

    private Secretario validSecretario;
         @BeforeEach
    void setUp() {
        validSecretario = new Secretario(VALID_NAME, VALID_CPF, VALID_PHONE, VALID_EMAIL, VALID_PASSWORD, VALID_MATRICULA);
    }
         @Test
    void should_create_secretario_with_valid_data() {
        assertEquals(VALID_NAME, validSecretario.getNome());
        assertEquals(VALID_CPF, validSecretario.getCpf());
        assertEquals(VALID_PHONE, validSecretario.getTelefone());
        assertEquals(VALID_EMAIL, validSecretario.getEmail());
        assertEquals(VALID_PASSWORD, validSecretario.getSenha());
        assertEquals(VALID_MATRICULA, validSecretario.getMatricula());
    }
         @Test
    void should_throw_exception_when_matricula_is_empty() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Secretario(VALID_NAME, VALID_CPF, VALID_PHONE, VALID_EMAIL, VALID_PASSWORD, "");
        });
}
}
// public Secretario(String nome, String cpf, String telefone, String email, String senha, String matricula) 