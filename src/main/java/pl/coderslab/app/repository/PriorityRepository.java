package pl.coderslab.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.app.models.Priority;
import pl.coderslab.app.models.Task;

import java.util.List;
import java.util.Optional;

@Repository
public interface PriorityRepository  extends JpaRepository<Priority, Long> {
    Optional<Priority> findById(Long id);
    List<Priority> findAll();


}