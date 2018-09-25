/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.spacefight.spacefight.restcontrollers;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ARSW - Projeyecto
 */
@Service
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/spacefight")
public class SpaceFightAPIController {
    @RequestMapping(method = RequestMethod.GET)        
    public ResponseEntity<?> manejadorGetRecursoOrdenes(){
 	try {
            //obtener datos que se enviarán a través del API
            return new ResponseEntity<>("spacefight",HttpStatus.ACCEPTED);
        }catch (Exception ex) {
            return new ResponseEntity<>("Error ",HttpStatus.NOT_FOUND);
 	}  
    } 
}
