package pl.coderslab.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.app.models.Project;
import pl.coderslab.app.models.Status;
import pl.coderslab.app.repository.StatusRepository;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class StatusController {
    @Autowired
    StatusRepository statusRepository;

    @GetMapping("statuses")
    public String getStatuses(HttpSession session,Model model){
        if(session.getAttribute("user") == null){
            return "login";
        }
        List<Status> statuses = statusRepository.findAll();
        model.addAttribute("statuses",statuses);
        return "statuses";
    }

    @GetMapping("status_edition")
    public String getEdition(HttpSession session,Model model,@RequestParam("status_id") Long status_id){
        if(session.getAttribute("user") == null){
            return "login";
        }
        Status status = statusRepository.findById(status_id).get();
        model.addAttribute("status",status);
        return "status_edition";
    }

    @PostMapping("status_update")
    public String updateStatus(HttpSession session, Model model, @ModelAttribute("status") Status status) {
        if(session.getAttribute("user") == null){
            return "login";
        }
        Status update = statusRepository.findById(status.getId()).get();
        update.setName(status.getName());
        update.setActivity(status.getActivity());
        update.setSorted(status.getSorted());
        statusRepository.save(update);
        model.addAttribute("statuses",statusRepository.findAll());
        return "statuses";
    }
}
