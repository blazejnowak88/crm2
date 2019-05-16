package pl.coderslab.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.app.models.Priority;
import pl.coderslab.app.models.Project;
import pl.coderslab.app.models.Status;
import pl.coderslab.app.repository.PriorityRepository;
import pl.coderslab.app.repository.StatusRepository;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class PriorityController {
    @Autowired
    PriorityRepository priorityRepository;

    @GetMapping("priorities")
    public String getPriorities(HttpSession session,Model model){
        if(session.getAttribute("user") == null){
            return "login";
        }
        List<Priority> priorities = priorityRepository.findAll();
        model.addAttribute("priorities",priorities);
        return "priorities";
    }

    @GetMapping("priority_edition")
    public String getEdition(HttpSession session,Model model,@RequestParam("priority_id") Long priority_id){
        if(session.getAttribute("user") == null){
            return "login";
        }
        Priority priority = priorityRepository.findById(priority_id).get();
        model.addAttribute("priority",priority);
        return "priority_edition";
    }

    @PostMapping("priority_update")
    public String updatePriority(HttpSession session, Model model, @ModelAttribute("priority") Priority priority) {
        if(session.getAttribute("user") == null){
            return "login";
        }
        Priority update = priorityRepository.findById(priority.getId()).get();
        update.setName(priority.getName());
        update.setActivity(priority.getActivity());
        priorityRepository.save(update);
        model.addAttribute("priorities",priorityRepository.findAll());
        return "priorities";
    }
}
