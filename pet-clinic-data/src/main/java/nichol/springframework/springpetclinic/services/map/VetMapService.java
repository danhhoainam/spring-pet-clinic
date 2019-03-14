package nichol.springframework.springpetclinic.services.map;

import nichol.springframework.springpetclinic.model.Specialty;
import nichol.springframework.springpetclinic.model.Vet;
import nichol.springframework.springpetclinic.services.SpecialtyService;
import nichol.springframework.springpetclinic.services.VetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile({"default", "mapdata"})
public class VetMapService extends AbstractMapService<Vet, Long> implements VetService {

	private final SpecialtyService specialtyService;

	public VetMapService(SpecialtyService specialtyService) {
		this.specialtyService = specialtyService;
	}

	@Override
	public Set<Vet> findAll() {
		return super.findAll();
	}

	@Override
	public void deleteById(Long id) {
		super.deleteById(id);
	}

	@Override
	public void delete(Vet object) {
		super.delete(object);
	}

	@Override
	public Vet save(Vet object) {

		if (!object.getSpecialties().isEmpty()) {
			object.getSpecialties().forEach(specialty -> {
				if (specialty.getId() != null) {
					Specialty savedSpecialty = specialtyService.save(specialty);
					specialty.setId(savedSpecialty.getId());
				}
			});
		}
		return super.save(object);
	}

	@Override
	public Vet findById(Long id) {
		return super.findById(id);
	}
}
