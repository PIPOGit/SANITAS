package com.atsistemas.sanitas.calculadora.service.impl;

import com.atsistemas.sanitas.calculadora.service.OperacionesService;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;

import io.corp.calculator.TracerImpl;

@Service
@Slf4j
public class OperacionesServiceImpl implements OperacionesService {

	@Autowired
	private TracerImpl tracer;

	@Override
	public Double suma( String first, String second ) {
		Double doubleFirst  = Double.valueOf( first );
		Double doubleSecond = Double.valueOf( second );
		Double result = doubleFirst + doubleSecond;
		this.tracer.trace( "Resultado: ["+first+"] - ["+second+"] = ["+(result)+"]" );
		log.trace( "Resultado: ["+first+"] - ["+second+"] = ["+(result)+"]" );
		return result;
	}

	@Override
	public Double resta( String first, String second ) {
		Double doubleFirst  = Double.valueOf( first );
		Double doubleSecond = Double.valueOf( second );
		Double result = doubleFirst - doubleSecond;
		this.tracer.trace( "Resultado: ["+first+"] - ["+second+"] = ["+(result)+"]" );
		log.trace( "Resultado: ["+first+"] - ["+second+"] = ["+(result)+"]" );
		return result;
	}

}
