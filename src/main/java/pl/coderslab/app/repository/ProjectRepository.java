package pl.coderslab.app.repository;


import org.springframework.data.domain.Pageable;
import pl.coderslab.app.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository  extends JpaRepository<Project, Long> {
    Optional<Project> findById(Long id);
    List<Project> findAll();

    /*@Query("select t from Project t " +
            "where t.describe = 'Programo%' " +
            "order by t.created ASC")
    List<Project> findByTitleStartsWithOrderByCreatedCreatedAsc();*/

    @Query("select p from Project p " +
            "order by p.created DESC ")
    List<Project> findFirstFive(Pageable pageable);

}