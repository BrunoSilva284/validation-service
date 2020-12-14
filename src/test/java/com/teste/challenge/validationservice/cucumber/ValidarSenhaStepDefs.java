package com.teste.challenge.validationservice.cucumber;

import com.teste.challenge.validationservice.model.ValidateInput;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.*;

public class ValidarSenhaStepDefs extends CucumberAppConfig {

    private static String senha;

    @Dado("Que sera recebida uma senha criptografada")
    public void queSeraRecebidaUmaSenhaCriptografada() {
        //App up Test
        String retorno = getStatusUp();
        assertNotNull(retorno);
        assertTrue(retorno.contains("UP"));
    }

    @Quando("Receber a senha: {string}")
    public void receberASenhaSenha(String senha) {
        ValidarSenhaStepDefs.senha = senha;
    }

    @Entao("A senha sera validada conforme as regras e o resultado sera apresentado: {string}")
    public void aSenhaSeraValidadaConformeAsRegrasEOResultadoSeraApresentadoResultadoEsperado(String resultadoEsperado) {
        ValidateInput input = new ValidateInput();
        input.setPass(senha);

        ResponseEntity<Boolean> retorno = postValidar(input);

        assertEquals(Boolean.valueOf(resultadoEsperado), retorno.getBody());
    }
}
