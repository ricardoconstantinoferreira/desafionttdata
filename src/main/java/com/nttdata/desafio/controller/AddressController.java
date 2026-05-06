package com.nttdata.desafio.controller;

import com.nttdata.desafio.entity.AddressLog;
import com.nttdata.desafio.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/addresses")
public class AddressController {

    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/{zipcode}")
    public ResponseEntity<AddressLog> getByZipcode(@PathVariable String zipcode) throws Exception {
        AddressLog adress = addressService.getAddressByZipcode(zipcode);
        return ResponseEntity.ok(adress);
    }
}
