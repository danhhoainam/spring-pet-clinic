package nichol.springframework.springpetclinic.bootstrap;

import nichol.springframework.springpetclinic.model.Owner;
import nichol.springframework.springpetclinic.model.Pet;
import nichol.springframework.springpetclinic.model.PetType;
import nichol.springframework.springpetclinic.model.Vet;
import nichol.springframework.springpetclinic.services.OwnerService;
import nichol.springframework.springpetclinic.services.PetTypeService;
import nichol.springframework.springpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

	private final OwnerService ownerService;
	private final VetService vetService;
	private final PetTypeService petTypeService;

	public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
		this.ownerService = ownerService;
		this.vetService = vetService;
		this.petTypeService = petTypeService;
	}

	@Override
	public void run(String... args) throws Exception {

		PetType dogType = new PetType();
		dogType.setName("Dog");
		PetType savedDogType = petTypeService.save(dogType);

		PetType catType = new PetType();
		catType.setName("Cat");
		PetType savedCatType = petTypeService.save(catType);

		Owner owner1 = new Owner();
		owner1.setFirstName("Micheal");
		owner1.setLastName("Owen");
		owner1.setAddress("123 Lang ha");
		owner1.setCity("Hanoi");
		owner1.setTelephone("222222222");

		Pet pet1 = new Pet();
		pet1.setPetType(savedDogType);
		pet1.setOwner(owner1);
		pet1.setBirthDate(LocalDate.now());
		pet1.setName("Rusty");
		owner1.getPets().add(pet1);

		ownerService.save(owner1);

		Owner owner2 = new Owner();
		owner2.setFirstName("Micheal");
		owner2.setLastName("Bubble");
		owner2.setAddress("555 Lang ha");
		owner2.setCity("Hanoi");
		owner2.setTelephone("444444");

		Pet pet2 = new Pet();
		pet2.setPetType(savedCatType);
		pet2.setOwner(owner2);
		pet2.setBirthDate(LocalDate.now());
		pet2.setName("Mickey");
		owner2.getPets().add(pet2);

		ownerService.save(owner2);

		System.out.println("Loaded Owners...");

		Vet vet1 = new Vet();
		vet1.setFirstName("Sam");
		vet1.setLastName("Smith");
		vetService.save(vet1);

		Vet vet2 = new Vet();
		vet2.setFirstName("Sam");
		vet2.setLastName("Wise");
		vetService.save(vet2);

		System.out.println("Loaded Vets...");
	}
}
