package com.iti.challenge.validationservice.controller;

import com.iti.challenge.validationservice.model.ValidateInput;
import com.iti.challenge.validationservice.service.PassValidationService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
public class ValidationController {

    private final PassValidationService passValidationService;

    @Autowired
    public ValidationController(PassValidationService passValidationService) {
        this.passValidationService = passValidationService;
    }

    @ApiOperation(value = "Valida se uma senha é válida.", response = Boolean.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Senha válida", response = Boolean.class),
            @ApiResponse(code = 403, message = "Senha inválida", response = Boolean.class)
    })
    @PostMapping(value = "/validate",
            produces = { "application/json" },
            consumes = { "application/json" })
    public ResponseEntity<Boolean> isAValidPass(@ApiParam(value = "Uma senha criptografada", required=true ) @Valid @NotNull @RequestBody ValidateInput body) {
        boolean isValid = passValidationService.isAValidPass(body.getPass());
        return new ResponseEntity<>(isValid, HttpStatus.OK);
    }
}
