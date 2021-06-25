package ferrari.data;

import org.springframework.data.repository.CrudRepository;

import ferrari.Ferrari;

public interface FerrariRepository 
         extends CrudRepository<Ferrari, Long> {

}
