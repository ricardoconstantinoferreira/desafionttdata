package com.nttdata.desafio.service;

import com.nttdata.desafio.entity.AddressLog;

public interface AddressService {
    AddressLog getAddressByZipcode(String zipcode) throws Exception;
    AddressLog save(String description);
}
