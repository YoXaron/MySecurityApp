package com.yoxaron.MySecurityApp.services;

import com.yoxaron.MySecurityApp.models.Person;
import com.yoxaron.MySecurityApp.repositories.PersonRepository;
import com.yoxaron.MySecurityApp.security.PersonDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonDetailsService implements UserDetailsService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonDetailsService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Person> optionalPerson = personRepository.findByUsername(username);

        if(optionalPerson.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        return new PersonDetails(optionalPerson.get());
    }
}
