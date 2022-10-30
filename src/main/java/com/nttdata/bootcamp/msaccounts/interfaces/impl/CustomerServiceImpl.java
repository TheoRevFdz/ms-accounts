package com.nttdata.bootcamp.msaccounts.interfaces.impl;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nttdata.bootcamp.msaccounts.config.RestConfig;
import com.nttdata.bootcamp.msaccounts.dto.CustomerDTO;
import com.nttdata.bootcamp.msaccounts.interfaces.ICustomerService;

@Service
public class CustomerServiceImpl implements ICustomerService {
    @Autowired
    private RestConfig customerRest;

    @Override
    public Optional<CustomerDTO> findCustomerByNroDoc(String nroDoc) throws ParseException {
        Map<String, String> pathVar = new HashMap<String, String>();
        pathVar.put("nroDoc", nroDoc);
        String uri = "http://localhost:8090/api/customers/byNroDoc/{nroDoc}";
        CustomerDTO dto = customerRest.getForObject(
                uri,
                CustomerDTO.class,
                pathVar);
        return Optional.ofNullable(dto);
    }

}
