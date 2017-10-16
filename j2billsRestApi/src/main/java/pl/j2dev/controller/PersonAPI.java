package pl.j2dev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.j2dev.pojo.Person;
import pl.j2dev.service.PersonService;

@Controller
@RequestMapping("/person")
public class PersonAPI {

	@Autowired
	PersonService personService;

	@RequestMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody Person getObjectById(@PathVariable("id") long id) {
		return personService.getObjectById(id);
	}
	
	@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody List<Person> getListOfAllObjects() {
		return personService.getListOfAllObjects();
	}

	@RequestMapping(method = RequestMethod.POST, consumes =  MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody Person createNewPerson(@RequestBody Person person) {
		return personService.createNewPersonInDB(person);
	}
}
