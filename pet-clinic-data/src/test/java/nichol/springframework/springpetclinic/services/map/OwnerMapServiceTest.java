package nichol.springframework.springpetclinic.services.map;

import nichol.springframework.springpetclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OwnerMapServiceTest {

	private OwnerMapService ownerMapService;
	private static final Long ownerId = 1L;
	private static final String lastName = "Smith";

	@BeforeEach
	void setUp() {
		ownerMapService = new OwnerMapService(new PetTypeMapService(), new PetMapService());

		ownerMapService.save(Owner.builder().id(ownerId).lastName(lastName).build());
	}

	@Test
	void findAll() {

		Set<Owner> ownerSet = ownerMapService.findAll();

		assertEquals(1, ownerSet.size());
	}

	@Test
	void deleteById() {


	}

	@Test
	void saveExistingId() {

		Long id = 2L;
		Owner owner2 = Owner.builder().id(id).build();
		Owner savedOwner = ownerMapService.save(owner2);

		assertEquals(id, savedOwner.getId());
	}

	@Test
	void saveNoId() {

		Owner savedOwner = ownerMapService.save(Owner.builder().build());

		assertNotNull(savedOwner);
		assertNotNull(savedOwner.getId());
	}

	@Test
	void delete() {

		ownerMapService.delete(ownerMapService.findById(ownerId));
		assertEquals(0, ownerMapService.findAll().size());
	}

	@Test
	void findById() {

		Owner owner = ownerMapService.findById(ownerId);

		assertEquals(ownerId, owner.getId());
	}

	@Test
	void findByLastName() {

		Owner smith = ownerMapService.findByLastName(lastName);

		assertNotNull(smith);
		assertEquals(ownerId, smith.getId());
	}

	@Test
	void findByLastNameNotNull() {

		Owner smith = ownerMapService.findByLastName("test");

		assertNull(smith);
	}
}