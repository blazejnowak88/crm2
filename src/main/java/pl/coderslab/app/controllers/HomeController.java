package pl.coderslab.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.app.models.User;
import pl.coderslab.app.repository.ProjectRepository;
import pl.coderslab.app.repository.UserRepository;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @GetMapping()
    public String getIndex(HttpSession session, Model model) {
        if(session.getAttribute("user") == null){
            return "login";
        }
        model.addAttribute("projects",projectRepository.findFirstFive(PageRequest.of(0,5)));
        return "redirect:/index";
    }

    @GetMapping("index")
    public String getIndexPrim(HttpSession session, Model model) {
        if(session.getAttribute("user") == null){
            return "login";
        }
        model.addAttribute("projects",projectRepository.findFirstFive(PageRequest.of(0,5)));
        return "index";
    }

    @GetMapping("projects")
    public String getProjects(HttpSession session,Model model) {
        if(session.getAttribute("user") == null){
            return "login";
        }
        model.addAttribute("projects",projectRepository.findAll());
        return "projects";
    }

    @PostMapping("login")
    public String logIn(HttpSession session,Model model,@RequestParam String username, @RequestParam String password){
        Optional<User> userOptional = userRepository.findByLogin(username);
        if(userOptional.isPresent()){
            User user = userOptional.get();
            if(bCryptPasswordEncoder.matches(password,user.getPassword())){
                session.setAttribute("user",user);
                return "redirect:/index";
            }
            model.addAttribute("error","true");
            return "login";
        }else{
            model.addAttribute("error","true");
            return "login";
        }
    }

    @GetMapping("logout")
    public String logOut(HttpSession session){
        session.setAttribute("user", null);
        return "redirect:/index";
    }
}
