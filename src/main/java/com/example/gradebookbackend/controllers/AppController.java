package com.example.gradebookbackend.controllers;

import com.example.gradebookbackend.models.Customer;
import com.example.gradebookbackend.models.Developer;
import com.example.gradebookbackend.models.Manager;
import com.example.gradebookbackend.models.Project;
import com.example.gradebookbackend.repositories.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
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
    @Autowired
    private DeveloperRepository developerRepo;
    @Autowired
    private IDERepository IDERepo;


    @GetMapping("/")
    public String viewStudentHomePage(Map<String, Object> model, String keyword) {

        if (keyword != null) {
            model.put("projects", projectRepo.findByKeyword(keyword));
            model.put("customers", customerRepo.findByKeyword(keyword));
            model.put("managers", managerRepo.findByKeyword(keyword));
            model.put("developers", developerRepo.findByKeyword(keyword));
        } else {
            model.put("projects", projectRepo.findByOrderByProjectIDAsc());
            Iterable<Customer> customers = customerRepo.findByOrderByCustomerIDAsc();
            model.put("customers", customers);
            Iterable<Manager> managers = managerRepo.findByOrderByManagerIDAsc();
            model.put("managers", managers);
            Iterable<Developer> developers = developerRepo.findByOrderByDeveloperIDAsc();
            model.put("developers", developers);
        }


//        model.put("projects", projectRepo.findByOrderByProjectIDAsc());


//        Iterable<Project> projects = projectRepo.findByOrderByProjectIDAsc(); //markRepo.findAll();
//        model.put("projects", projects);


        return "index";
    }

//    @RequestMapping(value = "/{keyword}", method = RequestMethod.GET)
//    public String viewHomePage(@RequestParam String keyword, Map<String, Object> model) {
//
//        if (keyword != null) {
//            model.put("projects", projectRepo.findByKeyword(keyword));
//        } else {
//            model.put("projects", projectRepo.findByOrderByProjectIDAsc());
//        }
//
//
////        model.put("projects", projectRepo.findByOrderByProjectIDAsc());
//
//
////        Iterable<Project> projects = projectRepo.findByOrderByProjectIDAsc(); //markRepo.findAll();
////        model.put("projects", projects);
//        Iterable<Customer> customers = customerRepo.findByOrderByCustomerIDAsc();
//        model.put("customers", customers);
//        Iterable<Manager> managers = managerRepo.findByOrderByManagerIDAsc();
//        model.put("managers", managers);
//        Iterable<Developer> developers = developerRepo.findByOrderByDeveloperIDAsc();
//        model.put("developers", developers);
//
//        return "index";
//    }


    /*--------------------------show forms for creating row-------------------------------------*/

    @GetMapping("/showNewCustomerForm")
    public String showNewCustomerForm(Model model) {
        // create model attribute to bind form data
        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        return "new_customer";
    }

    @GetMapping("/showNewProjectForm")
    public String showNewProjectForm(Model model) {

        model.addAttribute("project", new Project());

        model.addAttribute("customers", customerRepo.findByOrderByCustomerIDAsc());
        model.addAttribute("managers", managerRepo.findByOrderByManagerIDAsc());
        return "new_project";
    }

    @GetMapping("/addManager")
    public String addManager(Model model) {

        model.addAttribute("manager", new Manager());

        return "new_manager";
    }

    @GetMapping("/addDeveloper")
    public String addDeveloper(Model model) {

        model.addAttribute("developer", new Developer());

        model.addAttribute("ides", IDERepo.findByOrderByIDEidAsc());
        return "new_developer";
    }


    /*-----------------------------------save---------------------------------------*/

    @RequestMapping(value = "/saveCustomer", method = RequestMethod.POST)
    public String saveCustomer(@ModelAttribute("customer") Customer customer) {
        // save customers to database
        customerRepo.save(customer);
        return "redirect:/";
    }

    @RequestMapping(value = "/saveProject", method = RequestMethod.POST)
    public String saveProject(@ModelAttribute("project") Project project) {
        // save projects to database
        projectRepo.save(project);
        return "redirect:/";
    }

    @RequestMapping(value = "/saveManager", method = RequestMethod.POST)
    public String saveManager(@ModelAttribute("manager") Manager manager) {
        // save projects to database
        managerRepo.save(manager);
        return "redirect:/";
    }

    @RequestMapping(value = "/saveDeveloper", method = RequestMethod.POST)
    public String saveDeveloper(@ModelAttribute("developer") Developer developer) {
        // save projects to database
        developerRepo.save(developer);
        return "redirect:/";
    }


    /*-----------------------------------delete-------------------------------------*/

    @GetMapping("/deleteCustomer/{customer_id}")
    public String deleteCustomer(@PathVariable(value = "customer_id") Integer customer_id) {

        // call delete employee method
        this.customerRepo.deleteCustomerByCustomerID(customer_id);
        return "redirect:/";
    }

    @GetMapping("/deleteProject/{project_id}")
    public String deleteProject(@PathVariable(value = "project_id") Integer project_id) {

        projectRepo.deleteProjectByProjectID(project_id);
        return "redirect:/";
    }

    @GetMapping("/deleteManager/{id}")
    public String deleteManager(@PathVariable(value = "id") Integer id) {

        managerRepo.deleteManagerByManagerID(id);
        return "redirect:/";
    }

    @GetMapping("/deleteDeveloper/{id}")
    public String deleteDeveloper(@PathVariable(value = "id") Integer id) {

        developerRepo.deleteDeveloperByDeveloperID(id);
        return "redirect:/";
    }


    /*--------------------------------------updating-------------------------------------*/

    @GetMapping(value = "/showFormForUpdateCustomer/{customer_id}")
    public String showFormForUpdateCustomer(@PathVariable(value = "customer_id") Integer id, Model model) {

        // get customer from the service
        Customer customer = customerRepo.getCustomerByCustomerID(id);

        // set customer as a model attribute to pre-populate the form
        model.addAttribute("customer", customer);
        return "update_customer";
    }

    @GetMapping(value = "/showFormForUpdateProject/{id}")
    public String showFormForUpdateProject(@PathVariable(value = "id") Integer id, Model model) {

        Project project = projectRepo.getProjectByProjectID(id);
        Iterable<Customer> customers = customerRepo.findByOrderByCustomerIDAsc();

        model.addAttribute("project", project);
        model.addAttribute("customers", customers);
        return "update_project";
    }

    @GetMapping(value = "/updateManager/{id}")
    public String updateManager(@PathVariable(value = "id") Integer id, Model model) {
        model.addAttribute("manager", managerRepo.getManagerByManagerID(id));
        return "update_manager";
    }

    @GetMapping(value = "/updateDeveloper/{id}")
    public String updateDeveloper(@PathVariable(value = "id") Integer id, Model model) {

        model.addAttribute("developer", developerRepo.getDeveloperByDeveloperID(id));

        model.addAttribute("ides", IDERepo.findByOrderByIDEidAsc());
        return "update_developer";
    }

}
