package pl.coderslab.app.controllers;

import pl.coderslab.app.models.User;
import pl.coderslab.app.repository.UserRepository;
import pl.coderslab.app.service.UserService;
import pl.coderslab.app.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;
    @Autowired
    ProjectRepository projectRepository;

    @GetMapping()
    public String getUsersList(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "users";
    }

    @GetMapping("/add")
    public String getUserForm(Model model) {
        model.addAttribute("user", new User());
        return "user";
    }


    @PostMapping("/add")
    public String addUser(Model model, @ModelAttribute User user) {
        userRepository.save(user);
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/user_edition")
    public String getUserEdition(Model model,@RequestParam("user_id") Long user_id){
        User user = userRepository.findById(user_id).get();
        model.addAttribute("user",user);
        return "user_edition";
    }

    @PostMapping("/update")
    public String updateUser(Model model, @ModelAttribute("user") User user) {
        User update = userRepository.findById(user.getId()).get();
        update.setLogin(user.getLogin());
        update.setName(user.getName());
        update.setLastName(user.getLastName());
        update.setPassword(user.getPassword());
        return "users";
    }


/*
    @GetMapping("/list")
    public String getList(Model model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "usersList";
    }



    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id, Model model) {
        User user = userService.read(id);
        model.addAttribute("user", user);
        return "user";
    }

    @PostMapping("/update/{id}")
    public String update(@ModelAttribute User user, BindingResult result) {
        if (result.hasErrors()) {
            return "user";
        }
        userService.update(user);
        return "redirect:../list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        userService.delete(id);
        return "redirect:../list";

    }


*/
}





