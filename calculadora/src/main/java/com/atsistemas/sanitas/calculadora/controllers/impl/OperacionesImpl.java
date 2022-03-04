package com.atsistemas.sanitas.calculadora.controllers.impl;

import io.swagger.annotations.Api;

import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import javax.validation.constraints.*;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;

import org.springframework.validation.annotation.Validated;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.atsistemas.sanitas.calculadora.controllers.Operaciones;

import com.atsistemas.sanitas.calculadora.service.OperacionesService;

import com.atsistemas.sanitas.calculadora.exceptions.OperacionesException;

import lombok.extern.slf4j.Slf4j;

@RestController
@Api(tags = "Operaciones")
@Slf4j
public class OperacionesImpl implements Operaciones {

	@Autowired
	private OperacionesService service;

	@Override
    public ResponseEntity<Double> suma( String first, String second ) {
		return new ResponseEntity< Double >( this.service.suma( first, second ), HttpStatus.OK );
	}

	@Override
    public ResponseEntity<Double> resta( String first, String second ) {
		return new ResponseEntity< Double >( this.service.resta( first, second ), HttpStatus.OK );
	}

}
