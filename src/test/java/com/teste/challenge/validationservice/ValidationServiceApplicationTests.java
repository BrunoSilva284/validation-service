package com.teste.challenge.validationservice;

import com.teste.challenge.validationservice.controller.ValidationController;
import com.teste.challenge.validationservice.model.ValidateInput;
import com.teste.challenge.validationservice.service.PassValidationService;
import com.teste.challenge.validationservice.service.impl.PassValidationServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.*;

@SpringBootTest
public class ValidationServiceApplicationTests {

    private static final String ENDPOINT = "http://localhost:7777";

    private ValidationController validationController;
    private PassValidationService passValidationService;

    @Before
    public void setUp() {
        passValidationService = new PassValidationServiceImpl();
        validationController = new ValidationController(passValidationService);
    }

    @Test
    public void applicationStatusUpTest() {
        ValidationServiceApplication.main(new String[] {"--server.port=7777"});

        RestTemplate restTemplate = new RestTemplate();
        String retorno = restTemplate.getForObject(ENDPOINT, String.class);
        assertNotNull(retorno);
        assertTrue(retorno.contains("UP"));
    }

    @Test
    public void validarSenhasInvalidas(){
        //Valida senha vazia
        boolean retorno = passValidationService.isAValidPass("");
        assertFalse(retorno);

        //Valida senha sem caractere maiúsculo
        retorno = passValidationService.isAValidPass("aaaa123@!");
        assertFalse(retorno);

        //Valida senha sem dígito ou caractere especial
        retorno = passValidationService.isAValidPass("AAAbbbCc");
        assertFalse(retorno);

        //Valida senha com espaço em branco
        retorno = passValidationService.isAValidPass("AbTp9 fok");
        assertFalse(retorno);

        //Valida senha com duplicações
        retorno = passValidationService.isAValidPass("AbTp9!fokA");
        assertFalse(retorno);

        //Valida senha com caractere inválido
        retorno = passValidationService.isAValidPass("AbTp9ªfok");
        assertFalse(retorno);
    }

    @Test
    public void validarUmaSenhaValida(){
        boolean retorno = passValidationService.isAValidPass("AbTp9!fok");
        assertTrue(retorno);
    }

    @Test
    public void validarUmaSenhaInvalidaViaController(){
        ValidateInput input = new ValidateInput();
        input.setPass("AbTp9 fok");

        ResponseEntity<Boolean> retorno = validationController.isAValidPass(input);

        assertNotNull(retorno.getBody());
        assertFalse(retorno.getBody());
    }

    @Test
    public void validarUmaSenhaValidaViaController(){
        ValidateInput input = new ValidateInput();
        input.setPass("AbTp9@fok");

        ResponseEntity<Boolean> retorno = validationController.isAValidPass(input);

        assertNotNull(retorno.getBody());
        assertTrue(retorno.getBody());
    }

}
