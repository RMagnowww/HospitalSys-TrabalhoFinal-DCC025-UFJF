package br.ufjf.dcc.model;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class EnderecoTest{
    private static final String VALID_RUA = "Padre Cafe";
    private static final String VALID_BAIRRO = "Paineiras";
    private static final String VALID_CEP = "78645-320";
    private static final String VALID_CIDADE = "Juiz de Fora";
    private static final String VALID_NUMERO = "210";

    private Endereco validEndereco;
    @BeforeEach
    void setUp() {
        validEndereco = new Endereco(VALID_RUA, VALID_BAIRRO, VALID_CEP, VALID_CIDADE, VALID_NUMERO);
    }
    @Test
    void should_create_endereco_with_valid_data() {
        assertEquals(VALID_RUA, validEndereco.getRua());
        assertEquals(VALID_BAIRRO, validEndereco.getBairro());
        assertEquals(VALID_CEP, validEndereco.getCep());
        assertEquals(VALID_CIDADE, validEndereco.getCidade());
        assertEquals(VALID_NUMERO, validEndereco.getNumero());
    }
    @Test
    void should_throw_exception_when_rua_is_null() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Endereco(null, "Paineiras", "78645-320", "Juiz de Fora", "210");
        });
    }

    @Test
    void should_throw_exception_when_bairro_is_empty() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Endereco("Padre Cafe", "", "78645-320", "Juiz de Fora", "210");
        });
    }

    @Test
    void should_throw_exception_when_cep_is_invalid() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Endereco("Padre Cafe", "Paineiras", "123", "Juiz de Fora", "210");
        });
    }

    @Test
    void should_throw_exception_when_cidade_is_null() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Endereco("Padre Cafe", "Paineiras", "78645-320", null, "210");
        });
    }

    @Test
    void should_throw_exception_when_numero_is_empty() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Endereco("Padre Cafe", "Paineiras", "78645-320", "Juiz de Fora", "");
        });
    }
}