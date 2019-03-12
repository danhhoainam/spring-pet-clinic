package nichol.springframework.springpetclinic.services.map;

import nichol.springframework.springpetclinic.model.Specialty;
import nichol.springframework.springpetclinic.services.SpecialtyService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile({"default", "mapdata"})
public class SpecialtyServiceMap extends AbstractMapService<Specialty, Long> implements SpecialtyService {
	@Override
	public Set<Specialty> findAll() {
		return super.findAll();
	}

	@Override
	public void deleteById(Long id) {
		super.deleteById(id);
	}

	@Override
	public void delete(Specialty object) {
		super.delete(object);
	}

	@Override
	public Specialty save(Specialty object) {
		return super.save(object);
	}

	@Override
	public Specialty findById(Long id) {
		return super.findById(id);
	}
}
