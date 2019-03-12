package nichol.springframework.springpetclinic.services.jpa;

import nichol.springframework.springpetclinic.model.Owner;
import nichol.springframework.springpetclinic.repositories.OwnerRepository;
import nichol.springframework.springpetclinic.repositories.PetRepository;
import nichol.springframework.springpetclinic.repositories.PetTypeRepository;
import nichol.springframework.springpetclinic.services.OwnerService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Profile("jpadata")
public class OwnerJpaService implements OwnerService {

	private final OwnerRepository ownerRepository;
	private final PetRepository petRepository;
	private final PetTypeRepository petTypeRepository;

	public OwnerJpaService(
			OwnerRepository ownerRepository,
			PetRepository petRepository,
			PetTypeRepository petTypeRepository
	) {
		this.ownerRepository = ownerRepository;
		this.petRepository = petRepository;
		this.petTypeRepository = petTypeRepository;
	}

	@Override
	public Owner findByLastName(String lastName) {

		return ownerRepository.findByLastName(lastName);
	}

	@Override
	public Set<Owner> findAll() {

		Set<Owner> owners = new HashSet<>();
		ownerRepository.findAll().forEach(owners::add);

		return owners;
	}

	@Override
	public Owner findById(Long id) {

		Optional<Owner> optionalOwner = ownerRepository.findById(id);
		return optionalOwner.orElse(null);
	}

	@Override
	public Owner save(Owner owner) {

		return ownerRepository.save(owner);
	}

	@Override
	public void delete(Owner owner) {

		ownerRepository.delete(owner);
	}

	@Override
	public void deleteById(Long id) {

		ownerRepository.deleteById(id);
	}
}
