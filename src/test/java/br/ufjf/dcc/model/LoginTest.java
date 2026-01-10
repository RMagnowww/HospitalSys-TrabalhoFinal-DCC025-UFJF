package br.ufjf.dcc.model;

import org.junit.jupiter.api.*;
import br.ufjf.dcc.controller.LoginController;
import static org.junit.jupiter.api.Assertions.*;
import br.ufjf.dcc.model.exceptions.LoginException;


class LoginTest {
     private static final String VALID_NAME = "testePaciente";
     private static final String INVALID_EMAIL = "invalid@paciente.com";
     private static final String VALID_CPF = "123.456.789-00";
     private static final String INVALID_SENHA = "wrongpassword";       
     private static final String VALID_PASSWORD = "teste123";
     private static final String VALID_PHONE = "(32)99999-9999";
     private static final String VALID_EMAIL = "teste@paciente.com";
     private static final String VALID_DATADENASCIMENTO = "01/01/2000";
     private static final String VALID_TIPOSANGUINEO = "A+";
     private static final Endereco VALID_ENDERECO = new Endereco("Padre Cafe", "Paineiras", "78645-320", "Juiz de Fora", "210");
     private static Paciente paciente;
     
     @BeforeEach
     void setUp() {
          //instancia usuario valido no sistema para teste
          paciente = new Paciente(VALID_NAME, VALID_CPF, VALID_PHONE, VALID_EMAIL, VALID_PASSWORD, VALID_ENDERECO, VALID_DATADENASCIMENTO, VALID_TIPOSANGUINEO);
          try{
               Persistencia.salvarUsuario(paciente);
          } catch (Exception e){
               //ignora
          }
     }
      


     @Test
     void should_login_with_valid_credentials() {
         Usuario usuario = LoginController.autenticar(VALID_EMAIL, VALID_PASSWORD);
         assertNotNull(usuario);
         assertEquals(VALID_EMAIL, usuario.getEmail()); 
     }

     @Test
     void should_throw_exception_when_login_fails() {
         assertThrows(LoginException.class, () -> {
             LoginController.autenticar(INVALID_EMAIL, INVALID_SENHA);
         });
     }

    @AfterAll
    static void tearDown() {
         try{
              Persistencia.deletarUsuario(paciente);
         } catch (Exception e){
              //ignora
         }
    }
  }