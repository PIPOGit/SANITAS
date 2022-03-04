package com.atsistemas.sanitas.calculadora.controllers;

import io.swagger.annotations.Api;

import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import javax.validation.constraints.*;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;

@Validated
@Api(tags = "Operaciones")
public interface Operaciones {


	/**
	 * Suma dos operandos.
	 *
	 * @param first: El primer operando como String.
	 * @param second: El segundo operando como String.
	 * @return ResponseEntity<String> El resultado como String
	 */
    @Operation(
		summary = "Suma",
		description = "Suma dos operandos dados."
	)
    @ApiResponses(
		value = {
			@ApiResponse(responseCode = "204", description = "OK"),
			@ApiResponse(responseCode = "400", description = "Petición incorrecta",        content = @Content(mediaType = "application/json") ),
			@ApiResponse(responseCode = "405", description = "Operación no permitida",     content = @Content(mediaType = "application/json") ),
			@ApiResponse(responseCode = "406", description = "Operación no aceptada",      content = @Content(mediaType = "application/json") ),
			@ApiResponse(responseCode = "415", description = "Contenido no soportado",     content = @Content(mediaType = "application/json") ),
			@ApiResponse(responseCode = "429", description = "Demasiadas peticiones",      content = @Content(mediaType = "application/json") ),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(mediaType = "application/json") ),
			@ApiResponse(responseCode = "200", description = "Unexpected error",           content = @Content(mediaType = "application/json") )
		}
	)
    @GetMapping(
		value = "/suma/{first}/{second}",
        produces = { "application/json" })
    ResponseEntity<Double> suma(
		@NotNull @Parameter(in = ParameterIn.PATH, description = "El primer operando",  required=true) @PathVariable("first")  String first,
		@NotNull @Parameter(in = ParameterIn.PATH, description = "El segundo operando", required=true) @PathVariable("second") String second
	);


	/**
	 * Resta dos operandos.
	 *
	 * @param first: El primer operando como String.
	 * @param second: El segundo operando como String.
	 * @return ResponseEntity<String> El resultado como String
	 */
    @Operation(
		summary = "Resta",
		description = "Resta dos operandos dados."
	)
    @ApiResponses(
		value = {
			@ApiResponse(responseCode = "204", description = "OK"),
			@ApiResponse(responseCode = "400", description = "Petición incorrecta",        content = @Content(mediaType = "application/json") ),
			@ApiResponse(responseCode = "405", description = "Operación no permitida",     content = @Content(mediaType = "application/json") ),
			@ApiResponse(responseCode = "406", description = "Operación no aceptada",      content = @Content(mediaType = "application/json") ),
			@ApiResponse(responseCode = "415", description = "Contenido no soportado",     content = @Content(mediaType = "application/json") ),
			@ApiResponse(responseCode = "429", description = "Demasiadas peticiones",      content = @Content(mediaType = "application/json") ),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(mediaType = "application/json") ),
			@ApiResponse(responseCode = "200", description = "Unexpected error",           content = @Content(mediaType = "application/json") )
		}
	)
    @GetMapping(
		value = "/resta/{first}/{second}",
        produces = { "application/json" })
    ResponseEntity<Double> resta(
		@NotNull @Parameter(in = ParameterIn.PATH, description = "El primer operando",  required=true) @PathVariable("first") String first,
		@NotNull @Parameter(in = ParameterIn.PATH, description = "El segundo operando", required=true) @PathVariable("second") String second
	);

}
