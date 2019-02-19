package nichol.springframework.springpetclinic.bootstrap;

import nichol.springframework.springpetclinic.model.Owner;
import nichol.springframework.springpetclinic.model.Vet;
import nichol.springframework.springpetclinic.services.OwnerService;
import nichol.springframework.springpetclinic.services.VetService;
import nichol.springframework.springpetclinic.services.map.OwnerServiceMap;
import nichol.springframework.springpetclinic.services.map.VetServiceMap;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

	private final OwnerService ownerService;
	private final VetService vetService;

	public DataLoader() {
		ownerService = new OwnerServiceMap();
		vetService = new VetServiceMap();
	}

	@Override
	public void run(String... args) throws Exception {

		Owner owner1 = new Owner();
		owner1.setId(1L);
		owner1.setFirstName("Micheal");
		owner1.setLastName("Owen");
		ownerService.save(owner1);

		Owner owner2 = new Owner();
		owner2.setId(2L);
		owner2.setFirstName("Micheal");
		owner2.setLastName("Bubble");
		ownerService.save(owner2);

		System.out.println("Loaded Owners...");

		Vet vet1 = new Vet();
		vet1.setId(1L);
		vet1.setFirstName("Sam");
		vet1.setLastName("Smith");
		vetService.save(vet1);

		Vet vet2 = new Vet();
		vet2.setId(2L);
		vet2.setFirstName("Sam");
		vet2.setLastName("Wise");
		vetService.save(vet2);

		System.out.println("Loaded Vets...");
	}
}
