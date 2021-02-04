package org.springframework.samples.petclinic.web;

import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Authorities;
import org.springframework.samples.petclinic.model.DatosDiarios;
import org.springframework.samples.petclinic.model.Miembro;
import org.springframework.samples.petclinic.model.Person;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.service.DatosDiariosService;
import org.springframework.samples.petclinic.service.EncargoService;
import org.springframework.samples.petclinic.service.MiembroService;
import org.springframework.samples.petclinic.service.PrestamoService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class WelcomeController {
	
	@Autowired
    UserService userService;
    
    @Autowired
    MiembroService miembroService;
    
    @Autowired
    PrestamoService prestamoService;
    
    @Autowired
    EncargoService encargoService;

    @Autowired
    DatosDiariosService datosService;
    
    @GetMapping(path="/inicio")
    public String welcomeLogin(ModelMap model, Principal principal) {
        String vista = welcome(model);
        User user = userService.findByUsername(principal.getName());

        Set<Authorities> permisos = user.getAuthorities();
        if(permisos.stream().map(x->x.getAuthority()).anyMatch(x->x.equals("miembro"))) {
        	//Comprueba si existe un préstamo pendiente de devolución en los próximos 3 días, y lo notifica si es el caso.
            Miembro miembro = miembroService.findByUser(user);
            
            if(!prestamoService.prestamosMiembrosUrgentes(miembro).isEmpty()) {
                model.addAttribute("prestamoUrgente", true);
            }
        } 
        else if(permisos.stream().map(x->x.getAuthority()).anyMatch(x->x.equals("bibliotecario") || x.equals("admin"))) {
            //Comprueba si hay un pedido que llega en los próximos 2 días, y lo notifica si es el caso.
        	if(!encargoService.pedidosUrgentes().isEmpty()) {
        		model.addAttribute("pedidoUrgente", true);
        	}
        	
        }
        model.addAttribute("message", "Bienvenido, " + user.getUsername());
        return vista;
    }
	
	
	  @GetMapping({"/","/welcome"})
	  public String welcome(ModelMap model) {	   
		  
		  DatosDiarios datos = datosService.findAllOrderByFecha().get(0);
		  model.addAttribute("datos", datos);
		  
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
		  
		  model.addAttribute("persons", persons);
		  model.addAttribute("title", "Biblionet");
		  model.addAttribute("group", "G2-02");
		  
	    return "welcome";
	  }
}
