package org.springframework.samples.petclinic.web;

import java.util.Map;

import org.springframework.samples.petclinic.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class WelcomeController {
	
	
	  @GetMapping({"/","/welcome"})
	  public String welcome(Map<String, Object> model) {	    
		  
		  List<Person> persons=new ArrayList<Person>();
		  Person person = new Person();
		  person.setFirstName("Isabel ");
		  person.setLastName("de Vergara");
		  persons.add(person);
		  Person person2 = new Person();
		  person2.setFirstName("Fernando ");
		  person2.setLastName("González");
		  persons.add(person2);
		  Person person3 = new Person();
		  person3.setFirstName("Beatriz ");
		  person3.setLastName("Llamas");
		  persons.add(person3);
		  Person person4 = new Person();
		  person4.setFirstName("Iván ");
		  person4.setLastName("Romero");
		  persons.add(person4);
		  Person person5 = new Person();
		  person5.setFirstName("Jorge ");
		  person5.setLastName("Ruiz-Henestrosa");
		  persons.add(person5);
		  Person person6 = new Person();
		  person6.setFirstName("Alejandro ");
		  person6.setLastName("Sanabria");
		  persons.add(person6);
		  
		  model.put("persons", persons);
		  model.put("title", "Biblionet");
		  model.put("group", "G2-02");
		  
	    return "welcome";
	  }
}
