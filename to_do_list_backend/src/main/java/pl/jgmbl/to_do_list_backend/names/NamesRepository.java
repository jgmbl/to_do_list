package pl.jgmbl.to_do_list_backend.names;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NamesRepository extends JpaRepository<Names, Integer> {
    Optional<Names> findByName(String name);
}
