package com.teste.challenge.validationservice.service.impl;

import com.teste.challenge.validationservice.service.PassValidationService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class PassValidationServiceImpl implements PassValidationService {

    /**
     * @param pass
     * @return boolean indica se a senha atende os requisitos
     * A pressima é de receber uma senha criptografada e após a decriptação realizar as validações
     * */
    @Override
    public boolean isAValidPass(String pass) {
        //Tendo como premissa receber uma senha criptografada,
        //Após a descriptografia da senha seguir as validações:

        boolean valid = true;

        if(StringUtils.isEmpty(pass) || pass.length() < 9) {
            return false;
        }

        Pattern p = Pattern.compile("(?=.*[A-Z])(?=.*[0-9])(?=.*[a-z])(?=.*[!@#$%^&*()\\-+])");
        Matcher matcher = p.matcher(pass);

        if(!matcher.find()){
            return false;
        }

        if(hasDuplications(pass)){
            return false;
        }

        return valid;
    }

    /** Regra da duplicidade:
     * @param pass
     * @return possui duplicidade
     * Regra:
     * 1. Itera a string passando por cada caracter;
     * 2. Para cada caracter, re itera a string procurando ocorrências;
     * 3. No fim da segunda iteração completa, se houver mais de uma ocorrência, indica que o caracter foi repetido;
    **/
    private boolean hasDuplications(String pass){
        int ocor = 0;
        for(char ini : pass.toCharArray()) {
            for(char rep : pass.toCharArray()) {
                if(ini == rep){
                    ocor++;
                }
            }

            if(ocor > 1){
                return true;
            }

            ocor = 0;
        }
        return false;
    }
}