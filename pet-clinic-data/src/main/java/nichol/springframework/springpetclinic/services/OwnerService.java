package nichol.springframework.springpetclinic.services;

import nichol.springframework.springpetclinic.model.Owner;

public interface OwnerService extends CrudService<Owner, Long> {

	Owner findByLastName(String lastName);
}
