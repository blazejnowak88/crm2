package pl.coderslab.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.app.models.Status;
import pl.coderslab.app.models.Task;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {

}
