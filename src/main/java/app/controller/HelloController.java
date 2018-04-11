package app.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import app.model.Guest;
import app.repository.GuestRepository;

@Controller
public class HelloController {

	@Autowired
	GuestRepository repository;
	
	@RequestMapping("/")
	public String index() {
		return "index";
	}

	@PostMapping("/hello.do")
	public String sayHello(@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName,
			Model model) {
		Guest guest = new Guest(firstName, lastName);
		model.addAttribute("guest", guest);
		return "hello";
	}
	
	@PostMapping("/signIn.do")
	public String addGuest(@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName,
			Model model) {
		Guest guest = new Guest(firstName, lastName);
		guest.setVisitDate(new Date());
		repository.insert(guest);
		List<Guest> guests = new ArrayList<>();
		guests = repository.findAll();
		model.addAttribute("guests", guests);
		return "guests";
	}
	
	@GetMapping("/guests.do")
	public String listGuests(@RequestParam(value="filter", required=false) String filter, Model model) {
		List<Guest> guests = new ArrayList<>();
		if (filter == null) {
			guests = repository.findAll();
		} else {
			Guest g = new Guest("LISTO PAN", "FERNANDEZ");
			guests.add(g);
		}
		model.addAttribute("guests", guests);
		return "guests";
	}

	@GetMapping("/hardcodedDBTest")
	public String listGuestsPoorly(@RequestParam("firstName") String firstName, Model model) {
		repository.deleteAll();
		Guest a = new Guest("Alice", "Smith");
		Guest b = new Guest("Bob", "Smith");
		a.setVisitDate(new Date());
		b.setVisitDate(new Date());
		List<Guest> guests = new ArrayList<>();
		guests.add(a);
		guests.add(b);
		// save a couple of customers
		repository.save(a);
		repository.save(b);

		// fetch all customers
		System.out.println("Customers found with findAll():");
		System.out.println("-------------------------------");
		for (Guest customer : repository.findAll()) {
			System.out.println(customer.toString());
		}
		System.out.println();

		// fetch an individual customer
		System.out.println("Customer found with findByFirstName('Alice'):");
		System.out.println("--------------------------------");
		System.out.println(repository.findByFirstName("Alice"));

		System.out.println("Customers found with findByLastName('Smith'):");
		System.out.println("--------------------------------");
		for (Guest customer : repository.findByLastName("Smith")) {
			System.out.println(customer);
		}
		
		model.addAttribute("guests", guests);
		return "guests";
	}

}
