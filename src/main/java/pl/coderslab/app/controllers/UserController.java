package pl.coderslab.app.controllers;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.coderslab.app.models.User;
import pl.coderslab.app.repository.UserRepository;
import pl.coderslab.app.service.UserService;
import pl.coderslab.app.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpSession;
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

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @GetMapping()
    public String getUsersList(HttpSession session, Model model) {
        if(session.getAttribute("user") == null){
            return "login";
        }
        model.addAttribute("users", userRepository.findAll());
        return "users";
    }

    @GetMapping("add")
    public String getUserForm(HttpSession session,Model model) {
        if(session.getAttribute("user") == null){
            return "login";
        }
        model.addAttribute("user", new User());
        return "user";
    }


    @PostMapping("add")
    public String addUser(HttpSession session,Model model, @ModelAttribute User user) {
        if(session.getAttribute("user") == null){
            return "login";
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("user_edition")
    public String getUserEdition(HttpSession session,Model model,@RequestParam("user_id") Long user_id){
        if(session.getAttribute("user") == null){
            return "login";
        }
        User user = userRepository.findById(user_id).get();
        model.addAttribute("user",user);
        return "user_edition";
    }

    @PostMapping("update")
    public String updateUser(HttpSession session,Model model, @ModelAttribute("user") User user) {
        if(session.getAttribute("user") == null){
            return "login";
        }
        User update = userRepository.findById(user.getId()).get();
        update.setLogin(user.getLogin());
        update.setName(user.getName());
        update.setLastName(user.getLastName());
        update.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return "users";
    }


}





