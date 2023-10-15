package com.yoxaron.MySecurityApp.util;

import com.yoxaron.MySecurityApp.models.Person;
import com.yoxaron.MySecurityApp.services.PersonDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {

    private final PersonDetailsService personDetailsService;

    @Autowired
    public PersonValidator(PersonDetailsService personDetailsService) {
        this.personDetailsService = personDetailsService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;

        /*
        не очень хорошая практика полагаться на выброс исключения,
        в данном случае переиспользован метод loadUserByUsername,
        который выбрасывает исключение если пользователь не найден,
        а если найден, то создается ошибка в валидоторе.

        лучше всего было бы создать отдельный PeopleService
        с методом поиска по username, который возвращает Optional
        */

        try {
            personDetailsService.loadUserByUsername(person.getUsername());
        } catch (UsernameNotFoundException ignored) {
            return;
        }

        errors.rejectValue("username", "", "Пользователь с таким именем уже существует");
    }
}
