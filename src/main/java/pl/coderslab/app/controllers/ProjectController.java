package pl.coderslab.app.controllers;

import pl.coderslab.app.models.Priority;
import pl.coderslab.app.models.Project;
import pl.coderslab.app.models.Task;
import pl.coderslab.app.repository.PriorityRepository;
import pl.coderslab.app.repository.ProjectRepository;
import pl.coderslab.app.repository.TaskRepository;
import pl.coderslab.app.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


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

    @GetMapping("project_description")
    public String getDescription(Model model,@RequestParam("project_id") Long project_id){
        Project project = projectRepository.findById(project_id).get();
        model.addAttribute("project",project);
        model.addAttribute("user",project.getUser());
        return "project_description";
    }

    @GetMapping("project_task")
    public String getTasks(Model model,@RequestParam("project_id") Long project_id){
        Project project = projectRepository.findById(project_id).get();
        model.addAttribute("project",project);
        model.addAttribute("user",project.getUser());
        return "project_task";
    }

    @GetMapping("project_edition")
    public String getEdition(Model model,@RequestParam("project_id") Long project_id){
        Project project = projectRepository.findById(project_id).get();
        model.addAttribute("project",project);
        model.addAttribute("user",project.getUser());
        return "project_edition";
    }

    @PostMapping("/projects/update")
    public String updateProject(Model model, @ModelAttribute("project") Project project) {
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
    public String addProject(Model model){
        model.addAttribute("project",new Project());
        return "projects_add_new";
    }

    @PostMapping("/projects/add")
    public String addProject(Model model, @ModelAttribute("project") Project project) {
        project.setCreated(LocalDateTime.now());
        projectRepository.save(project);
        model.addAttribute("projects",projectRepository.findAll());
        return "projects";
    }

    @GetMapping("/task/add_new")
    public String addTaskToProject(Model model , @RequestParam("project_id") Long project_id){
        model.addAttribute("project",projectRepository.findById(project_id).get());
        return "task_add";
    }

    @PostMapping("/task/add")
    public String addTask(Model model,
                          @RequestParam("project_id") Long project_id,
                          @RequestParam("topic") String topic,
                          @RequestParam("describes") String description,
                          @RequestParam("priority_name") String priorityName,
                          @RequestParam("priority_activity") String priorityActivity
                          ) {
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

    /*@Autowired
    ProjectRepository projectRepository;

    @Autowired
    ProjectService projectService;

    @GetMapping("/add")
    public String getTweetForm(Model model) {
        model.addAttribute("project", new Project());
        return "project";
    }


    @PostMapping("/add")
    public String addTweet(Model model, @ModelAttribute Project project) {
        Project result = projectRepository.save(project);
        //model.addAttribute("users", Arrays.asList(result));
        List<Project> projects = projectRepository.findAll();
        model.addAttribute("tweets", projects);
        return "tweetsList";
    }

    @GetMapping("/list")
    public String getList(Model model) {
        List<Project> projects = projectRepository.findAll();
        model.addAttribute("projects", projects);
        return "projectList";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        projectService.delete(id);
        return "redirect:../list";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id, Model model) {
        Project project = projectService.read(id);
        model.addAttribute("project", project);
        return "project";
    }

    @PostMapping("/update/{id}")
    public String update(@ModelAttribute Project project, BindingResult result) {
        if (result.hasErrors()) {
            return "project";
        }
        projectService.update(project);
        return "redirect:../list";
    }

    @GetMapping("/user/search-project")
    public String lista(Model model) {
        List<Project> projects = projectService.findAll();
        model.addAttribute("projects", projects);
        return "projectsList";
    }*/
}


