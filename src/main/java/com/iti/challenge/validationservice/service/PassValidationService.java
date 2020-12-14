package com.iti.challenge.validationservice.service;

public interface PassValidationService {

    /**
     * @param pass
     * @return boolean indica se a senha atende os requisitos
     * A pressima é de receber uma senha criptografada e após a decriptação realizar as validações
     * */
    boolean isAValidPass(String pass);
}
