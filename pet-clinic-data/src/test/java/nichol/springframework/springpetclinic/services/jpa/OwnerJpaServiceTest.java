package nichol.springframework.springpetclinic.services.jpa;

import nichol.springframework.springpetclinic.model.Owner;
import nichol.springframework.springpetclinic.repositories.OwnerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerJpaServiceTest {

	private final String LAST_NAME = "Smith";

	@Mock
	OwnerRepository ownerRepository;

//	@Mock
//	PetRepository petRepository;
//
//	@Mock
//	PetTypeRepository petTypeRepository;

	@InjectMocks
	OwnerJpaService ownerService;

	Owner expectedOwner;

	@BeforeEach
	void setUp() {
		expectedOwner = Owner.builder().id(1L).lastName(LAST_NAME).build();
	}

	@Test
	void findByLastName() {

		// given
		when(ownerRepository.findByLastName(any())).thenReturn(expectedOwner);

		// when
		Owner smith = ownerService.findByLastName(LAST_NAME);

		// then
		assertEquals(LAST_NAME, smith.getLastName());
		Mockito.verify(ownerRepository).findByLastName(any());
	}

	@Test
	void findAll() {

		// given
		Set<Owner> ownerSet = new HashSet<>();
		ownerSet.add(Owner.builder().id(1L).build());
		ownerSet.add(Owner.builder().id(2L).build());

		when(ownerRepository.findAll()).thenReturn(ownerSet);

		// when
		Set<Owner> ownerResults = ownerService.findAll();

		// then
		assertNotNull(ownerResults);
		assertEquals(2, ownerResults.size());
	}

	@Test
	void findById() {
		// given
		when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(expectedOwner));

		// when
		Owner result = ownerService.findById(1L);

		// then
		assertNotNull(result);
		verify(ownerRepository).findById(anyLong());
	}

	@Test
	void findByIdNotFound() {
		// given
		when(ownerRepository.findById(anyLong())).thenReturn(Optional.empty());

		// when
		Owner result = ownerService.findById(1L);

		// then
		assertNull(result);
		verify(ownerRepository).findById(anyLong());
	}

	@Test
	void save() {

		// given
		Owner owner = Owner.builder().id(1L).build();
		when(ownerRepository.save(any())).thenReturn(owner);

		// when
		Owner savedOwner = ownerService.save(owner);

		// then
		assertNotNull(savedOwner);
		verify(ownerRepository).save(any());

	}

	@Test
	void delete() {

		// when
		ownerService.delete(expectedOwner);

		// then
		verify(ownerRepository, times(1)).delete(any());
	}

	@Test
	void deleteById() {

		// when
		ownerService.deleteById(1L);

		// then
		verify(ownerRepository, times(1)).deleteById(anyLong());
	}
}