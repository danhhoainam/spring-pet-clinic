package nichol.springframework.springpetclinic.services.jpa;

import nichol.springframework.springpetclinic.model.PetType;
import nichol.springframework.springpetclinic.repositories.PetTypeRepository;
import nichol.springframework.springpetclinic.services.PetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Profile("jpa")
public class PetTypeJpaService implements PetTypeService {

	private final PetTypeRepository petTypeRepository;

	public PetTypeJpaService(PetTypeRepository petTypeRepository) {
		this.petTypeRepository = petTypeRepository;
	}


	@Override
	public Set<PetType> findAll() {
		Set<PetType> petTypes = new HashSet<>();
		petTypeRepository.findAll().forEach(petTypes::add);
		return petTypes;
	}

	@Override
	public PetType findById(Long id) {
		Optional<PetType> optionalPetType = petTypeRepository.findById(id);
		return optionalPetType.orElse(null);
	}

	@Override
	public PetType save(PetType petType) {
		return petTypeRepository.save(petType);
	}

	@Override
	public void delete(PetType petType) {
		petTypeRepository.save(petType);
	}

	@Override
	public void deleteById(Long id) {
		petTypeRepository.deleteById(id);
	}
}
