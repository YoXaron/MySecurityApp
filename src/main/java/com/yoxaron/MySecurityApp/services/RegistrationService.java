package com.yoxaron.MySecurityApp.services;

import com.yoxaron.MySecurityApp.models.Person;
import com.yoxaron.MySecurityApp.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistrationService {

    private final PersonRepository personRepository;

    @Autowired
    public RegistrationService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Transactional
    public void register(Person person) {
        personRepository.save(person);
    }
}
