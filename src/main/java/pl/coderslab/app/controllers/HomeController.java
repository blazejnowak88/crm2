package pl.coderslab.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.app.repository.ProjectRepository;
import pl.coderslab.app.repository.UserRepository;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    private ProjectRepository projectRepository;

    @GetMapping()
    public String getIndex(Model model) {
        model.addAttribute("projects",projectRepository.findFirstFive(PageRequest.of(0,5)));
        return "index";
    }

    @GetMapping("index")
    public String getIndexPrim(Model model) {
        model.addAttribute("projects",projectRepository.findFirstFive(PageRequest.of(0,5)));
        return "index";
    }

    @GetMapping("projects")
    public String getProjects(Model model) {
        model.addAttribute("projects",projectRepository.findAll());
        return "projects";
    }
}
