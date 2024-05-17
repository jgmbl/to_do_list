package pl.jgmbl.to_do_list_backend.tasks;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TasksRepository extends JpaRepository<Tasks, Integer> {
    Iterable<Tasks> findByNamesId(Integer nameId);
}
