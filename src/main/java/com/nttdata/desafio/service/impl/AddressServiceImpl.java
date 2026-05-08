package com.nttdata.desafio.service.impl;

import com.nttdata.desafio.dto.EnderecoDTO;
import com.nttdata.desafio.entity.AddressLog;
import com.nttdata.desafio.repository.AddressLogRepository;
import com.nttdata.desafio.service.AddressService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;
import tools.jackson.databind.ObjectMapper;

@Service
public class AddressServiceImpl implements AddressService {

    private final RestTemplate restTemplate;
    private final AddressLogRepository addressLogRepository;
    private final ObjectMapper objectMapper;

    @Value("${viacep.base.url}")
    private String baseUrl;

    public AddressServiceImpl(
            AddressLogRepository addressLogRepository,
            ObjectMapper objectMapper
    ) {
        this.addressLogRepository = addressLogRepository;
        this.objectMapper = objectMapper;
        this.restTemplate = new RestTemplate();
    }

    @Override
    public AddressLog getAddressByZipcode(String zipcode) throws Exception {
        try {
            String uri = baseUrl + zipcode + "/json/";
            EnderecoDTO dto = restTemplate.getForObject(uri, EnderecoDTO.class);
            String description = objectMapper.writeValueAsString(dto);
            return this.save(description);
        } catch (HttpClientErrorException.NotFound e) {
            this.save(e.getMessage());
            throw new Exception(e.getMessage());
        } catch (Exception e) {
            this.save(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public AddressLog save(String description) {
        AddressLog addressLog = new AddressLog();
        addressLog.setDescription(description);

        return addressLogRepository.save(addressLog);
    }
}
