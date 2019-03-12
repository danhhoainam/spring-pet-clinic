package nichol.springframework.springpetclinic.repositories;

import nichol.springframework.springpetclinic.model.Specialty;
import org.springframework.data.repository.CrudRepository;

public interface SpecialtyRepository extends CrudRepository<Specialty, Long> {
}
