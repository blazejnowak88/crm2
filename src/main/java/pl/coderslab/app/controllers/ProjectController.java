package pl.coderslab.app.controllers;

import pl.coderslab.app.models.Priority;
import pl.coderslab.app.models.Project;
import pl.coderslab.app.models.Task;
import pl.coderslab.app.models.User;
import pl.coderslab.app.repository.PriorityRepository;
import pl.coderslab.app.repository.ProjectRepository;
import pl.coderslab.app.repository.TaskRepository;
import pl.coderslab.app.repository.UserRepository;
import pl.coderslab.app.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("")
public class ProjectController {
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private PriorityRepository priorityRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("project_description")
    public String getDescription(HttpSession session,Model model,@RequestParam("project_id") Long project_id){
        if(session.getAttribute("user") == null){
            return "login";
        }
        Project project = projectRepository.findById(project_id).get();
        model.addAttribute("project",project);
        model.addAttribute("user",project.getUser());
        return "project_description";
    }

    @GetMapping("project_task")
    public String getTasks(HttpSession session, Model model, @RequestParam("project_id") Long project_id){
        if(session.getAttribute("user") == null){
            return "login";
        }
        Project project = projectRepository.findById(project_id).get();
        model.addAttribute("project",project);
        model.addAttribute("user",project.getUser());
        return "project_task";
    }

    @GetMapping("project_edition")
    public String getEdition(HttpSession session,Model model,@RequestParam("project_id") Long project_id){
        if(session.getAttribute("user") == null){
            return "login";
        }
        Project project = projectRepository.findById(project_id).get();
        model.addAttribute("project",project);
        model.addAttribute("user",project.getUser());
        return "project_edition";
    }

    @PostMapping("projects_update")
    public String updateProject(HttpSession session,Model model, @ModelAttribute("project") Project project) {
        if(session.getAttribute("user") == null){
            return "login";
        }
        Project update = projectRepository.findById(project.getId()).get();
        update.setActivity(project.isActivity());
        update.setName(project.getName());
        update.setWww(project.getWww());
        update.setDescribes(project.getDescribes());
        projectRepository.save(update);
        model.addAttribute("project",update);
        model.addAttribute("user",update.getUser());
        return "project_edition";
    }

    @GetMapping("projects_add_new")
    public String addProject(HttpSession session,Model model){
        if(session.getAttribute("user") == null){
            return "login";
        }
        model.addAttribute("project",new Project());
        return "projects_add_new";
    }

    @PostMapping("projects_add")
    public String addProject(HttpSession session,Model model, @ModelAttribute("project") Project project) {
        if(session.getAttribute("user") == null){
            return "login";
        }
        project.setCreated(LocalDateTime.now());
        User user = userRepository.findByLogin(((User)session.getAttribute("user")).getLogin()).get();
        project.setUser(user);
        user.getProject().add(project);
        projectRepository.save(project);
        userRepository.save(user);
        model.addAttribute("projects",projectRepository.findAll());
        return "projects";
    }

    @GetMapping("task_add_new")
    public String addTaskToProject(HttpSession session,Model model , @RequestParam("project_id") Long project_id){
        if(session.getAttribute("user") == null){
            return "login";
        }
        model.addAttribute("project",projectRepository.findById(project_id).get());
        return "task_add";
    }

    @PostMapping("task_add")
    public String addTask(HttpSession session,Model model,
                          @RequestParam("project_id") Long project_id,
                          @RequestParam("topic") String topic,
                          @RequestParam("describes") String description,
                          @RequestParam("priority_name") String priorityName,
                          @RequestParam("priority_activity") String priorityActivity
                          ) {
        if(session.getAttribute("user") == null){
            return "login";
        }
        Project project = projectRepository.findById(project_id).get();
        Task t =new Task();
        t.setTopic(topic);
        t.setDescribes(description);
        t.setProject(project);
        project.getTasks().add(t);
        Priority priority = new Priority();
        priority.setName(priorityName);
        priority.setActivity(priorityActivity);
        t.setPriority(priority);
        priorityRepository.save(priority);
        projectRepository.save(project);
        taskRepository.save(t);
        model.addAttribute("project",project);
        model.addAttribute("user",project.getUser());
        return "project_task";
    }

    @GetMapping("task_task_description")
    public String getTaskDescription(HttpSession session,Model model,@RequestParam("task_id") Long task_id){
        if(session.getAttribute("user") == null){
            return "login";
        }
        Task task = taskRepository.findById(task_id).get();
        model.addAttribute("task",task);
        return "task_description";
    }

    @GetMapping("task_task_edition")
    public String getTaskEdition(HttpSession session,Model model,@RequestParam("task_id") Long task_id){
        if(session.getAttribute("user") == null){
            return "login";
        }
        Task task = taskRepository.findById(task_id).get();
        model.addAttribute("task",task);
        return "task_edition";
    }

    @PostMapping("task_update")
    public String updateTask(HttpSession session,Model model, @ModelAttribute("task") Task task) {
        if(session.getAttribute("user") == null){
            return "login";
        }
        Task update = taskRepository.findById(task.getId()).get();
        update.setTopic(task.getTopic());
        update.setDescribes(task.getDescribes());
        taskRepository.save(update);

        return "task_description";
    }


}


