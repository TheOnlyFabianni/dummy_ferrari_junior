package ferrari;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import ferrari.Specification.Type;
import ferrari.data.SpecificationRepository;

@SpringBootApplication
public class FerrariApplication {

  public static void main(String[] args) {
    SpringApplication.run(FerrariApplication.class, args);
  }

  @Bean
  public CommandLineRunner dataLoader(SpecificationRepository repo) {
    return new CommandLineRunner() {
      @Override
      public void run(String... args) throws Exception {
        repo.save(new Specification("CONV", "Convertible", Type.BTYPE));
        repo.save(new Specification("SUPR", "Supercar", Type.BTYPE));
        repo.save(new Specification("RED", "Red", Type.COLOR));
        repo.save(new Specification("YELL", "Yellow", Type.COLOR));
        repo.save(new Specification("PIRL", "Pirelli", Type.TYRES));
        repo.save(new Specification("MICH", "Michelin", Type.TYRES));
        repo.save(new Specification("F1E", "F136 E", Type.ENGINE));
        repo.save(new Specification("F1F", "F136 F", Type.ENGINE));
        repo.save(new Specification("CARB", "Carbon", Type.BODY));
        repo.save(new Specification("ALUM", "Aluminum", Type.BODY));
      }
    };
  }
  
}
