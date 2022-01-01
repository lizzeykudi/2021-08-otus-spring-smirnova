package ru.otus.spring.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.spring.bookService.repository.PersonRepository;
import ru.otus.spring.domain.Person;

import java.util.List;

@Controller
public class PersonController {

    private final PersonRepository repository;

    @Autowired
    public PersonController(PersonRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/l")
    public String listPage(Model model) {
        List<Person> persons = repository.findAll();
        model.addAttribute("persons", persons);
        return "list";
    }

    @GetMapping("/edit/l")
    public String editPage(@RequestParam("id") int id, Model model) {
        Person person = repository.findById(id).orElseThrow(NotFoundException::new);
        model.addAttribute("person", person);
        return "edit";
    }
    
    @PostMapping("/edit/l")
    public String savePerson(
            Person person,
            Model model
                            ) {
        Person saved = repository.save(person);
        model.addAttribute(saved);
        //Ошибка! Нужен редирект!
        return "edit";
    }
}
