package com.currencyconverter.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.currencyconverter.services.ConversionService;

import java.util.List;

@RestController
@RequestMapping("api/v1/conversion")
public class ConversionController {
    private ConversionService service;

    @Autowired
    public ConversionController(ConversionService service){
        this.service = service;
    }



    @GetMapping("/rates")
    public responseDTO getRates(){
        try{
            List<ConversionService.Rates> rates = this.service.getRates();
            return new responseDTO("request success", rates);
        }catch(Exception e){
            return new responseDTO(e.getMessage(), null);
        }
    }




    record responseDTO(
        String message,
        Object data
    ){}

}