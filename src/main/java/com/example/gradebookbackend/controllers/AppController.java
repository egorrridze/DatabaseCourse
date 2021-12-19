package com.example.gradebookbackend.controllers;

import com.example.gradebookbackend.models.Customer;
import com.example.gradebookbackend.models.Manager;
import com.example.gradebookbackend.models.Project;
import com.example.gradebookbackend.repositories.CustomerRepository;
import com.example.gradebookbackend.repositories.ManagerRepository;
import com.example.gradebookbackend.repositories.ProjectRepository;
import com.example.gradebookbackend.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@Transactional
public class AppController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private ProjectRepository projectRepo;
    @Autowired
    private CustomerRepository customerRepo;
    @Autowired
    private ManagerRepository managerRepo;

    @RequestMapping("/")
    public String viewStudentHomePage(Map<String, Object> model) {

        Iterable<Project> projects = projectRepo.findAll(); //markRepo.findAll();
        model.put("projects", projects);
        Iterable<Customer> customers = customerRepo.findAll();
        model.put("customers", customers);
        Iterable<Manager> managers = managerRepo.findAll();
        model.put("managers", managers);

        return "index";
    }

//    @PostMapping("/addcustomer")
//    public String addCustomer(@Validated Customer customer, BindingResult result, Model model) {
//        if (result.hasErrors()) {
//            return "add-customer";
//        }
//
//        customerRepo.save(customer);
//        return "redirect:/index";
//    }

    @GetMapping("/showNewCustomerForm")
    public String showNewCustomerForm(Model model) {
        // create model attribute to bind form data
        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        return "new_customer";
    }

    @RequestMapping(value = "/saveCustomer", method = RequestMethod.POST)
    public String saveCustomer(@ModelAttribute("customer") Customer customer) {
        // save customers to database
        customerRepo.save(customer);
        return "redirect:/";
    }

    @GetMapping("/deleteCustomer/{customer_id}")
    public String deleteCustomer(@PathVariable(value = "customer_id") Integer customer_id) {

        // call delete employee method
        this.customerRepo.deleteCustomerByCustomerID(customer_id);
        return "redirect:/";
    }

    @GetMapping(value = "/showFormForUpdate/{customer_id}")
    public String showFormForUpdate(@PathVariable(value = "customer_id") Integer id, Model model) {

        // get employee from the service
        Customer customer = customerRepo.getCustomerByCustomerID(id);

        // set employee as a model attribute to pre-populate the form
        model.addAttribute("customer", customer);
        return "update_customer";
    }
}
