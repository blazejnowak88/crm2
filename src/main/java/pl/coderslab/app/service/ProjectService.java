package pl.coderslab.app.service;

import pl.coderslab.app.models.Project;
import pl.coderslab.app.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class ProjectService {

    @Autowired
    private ProjectRepository
    projectRepository;

    public void create(Project project) {
        projectRepository.save(project);
    }

    public Project read(Long id) {
        return projectRepository.findById(id).orElse(null);
    }


    public void update(Project project) {
        projectRepository.save(project);
    }

    public void delete(Long id) {
        projectRepository.deleteById(id);
    }

    public List<Project> findAll() {
        return projectRepository.findAll();
    }

}


