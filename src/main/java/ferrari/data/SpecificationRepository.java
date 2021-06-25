package ferrari.data;

import org.springframework.data.repository.CrudRepository;

import ferrari.Specification;

public interface SpecificationRepository 
         extends CrudRepository<Specification, String> {

}
