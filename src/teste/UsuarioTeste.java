package br.ufjf.dcc.model;

import br.ufjf.dcc.model.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("User Tests")
class UsuarioTest {
     private static final String VALID_NAME = String.getInstance("Joao");
     private static final String VALID_CPF = String.getInstance("086.756.245-90");
     private static final String VALID_PASSWORD = String.getInstance("paciente123");
     private static final String VALID_PHONE = String.getInstance("(32)96655-3322");
     private static final String VALID_EMAIL = String.getInstance("joao@paciente.com");
     private static final PerfilUsuario VALID_PROFILE = PerfilUsuario.CLIENTE;

     private Usuario validUser;
     @BeforeEach
    void setUp() {
        validUser = new Usuario(VALID_NAME, VALID_CPF, VALID_PHONE, VALID_EMAIL, VALID_PASSWORD, VALID_PROFILE);
    }
    @Nested
    @DisplayName("Constructor Tests")
    class ConstructorTests {
         @Test
         void should_throw_exception_when_email_is_null() {
            assertThrows(NullPointerException.class, () ->
                new Usuario(VALID_NAME, VALID_CPF, VALID_PHONE, null, VALID_PASSWORD, VALID_PROFILE));
        }

        @Test
        void should_throw_exception_when_password_is_null() {
            assertThrows(NullPointerException.class, () ->
                    new Usuario(VALID_NAME, VALID_CPF, VALID_PHONE, VALID_EMAIL, null, VALID_PROFILE));
        }
         @Test
        void should_create_user_with_valid_data() {
            Usuario user = new Usuario(VALID_NAME, VALID_CPF, VALID_PHONE, VALID_EMAIL, VALID_PASSWORD, VALID_PROFILE); ;
            assertNotNull(user);
            assertEquals(VALID_NAME, user.getNome());
            assertEquals(VALID_CPF, user.getCpf());
            assertEquals(VALID_PHONE, user.getTelefone());
            assertEquals(VALID_EMAIL, user.getEmail());
            assertEquals(VALID_PROFILE, user.getPerfil());
        }
    }
     @Nested
    @DisplayName("Password Tests")
    class PasswordTests {
        @Test
        void should_validate_password_when_check_password_of_user1() {
            assertTrue(validUser.checkPassword("paciente123"));
        }

        @Test
        void should_not_validate_password_when_check_password_of_user1() {
            assertFalse(validUser.checkPassword("paciente123@"));
        }

        @Test
        void should_not_validate_null_password() {
            assertFalse(validUser.checkPassword(null));
        }

        @Test
        void should_not_validate_empty_password() {
            assertFalse(validUser.checkPassword(""));
        }
    }
      @Nested
    @DisplayName("Equals Tests")
    class EqualsTests {
        @Test
        void should_be_not_equal_when_compare_user1_to_user2() {
            Usuario user2 = new Usuario(Nome.getInstance("Roberto"), VALID_CPF, VALID_PHONE, VALID_EMAIL, VALID_PASSWORD, VALID_PROFILE); ;
            assertNotEquals(validUser, user2);
        }

        @Test
        void should_be_equal_when_compare_user1_to_user2() {
            Usuario user2 = new Usuario(VALID_NAME, VALID_CPF, VALID_PHONE, VALID_EMAIL, VALID_PASSWORD, VALID_PROFILE);
            assertEquals(validUser, user2);
        }

        @Test
        void should_be_not_equal_when_compare_to_null() {
            Usuario actual = null;
            assertNotEquals(validUser, actual);
        }

        @Test
        void should_have_same_hashcode_when_users_are_equal() {
            Usuario user2 = new Usuario(VALID_NAME, VALID_CPF, VALID_PHONE, VALID_EMAIL, VALID_PASSWORD, VALID_PROFILE);
            assertEquals(validUser.hashCode(), user2.hashCode());
        }
    }
}