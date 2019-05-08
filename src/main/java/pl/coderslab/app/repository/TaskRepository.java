package pl.coderslab.app.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.app.models.Project;
import pl.coderslab.app.models.Task;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository  extends JpaRepository<Task, Long> {
    Optional<Task> findById(Long id);
    List<Task> findAll();


}
