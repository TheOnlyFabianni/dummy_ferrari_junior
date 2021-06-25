package ferrari.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ferrari.Specification;
import ferrari.data.SpecificationRepository;

@Component
public class SpecificationByIdConverter implements Converter<String, Specification> {

  private SpecificationRepository specificationRepo;

  @Autowired
  public SpecificationByIdConverter(SpecificationRepository specificationRepo) {
    this.specificationRepo = specificationRepo;
  }
  
  @Override
  public Specification convert(String id) {
    Optional<Specification> optionalSpecification = specificationRepo.findById(id);
	return optionalSpecification.isPresent() ?
		   optionalSpecification.get() : null;
  }

}
