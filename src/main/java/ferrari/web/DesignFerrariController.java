package ferrari.web;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import ferrari.Specification;
import ferrari.Specification.Type;
import ferrari.Order;
import ferrari.Ferrari;
import ferrari.User;
import ferrari.data.SpecificationRepository;
import ferrari.data.FerrariRepository;
import ferrari.data.UserRepository;


@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignFerrariController {

  private final SpecificationRepository specificationRepo;

  private FerrariRepository ferrariRepo;

  private UserRepository userRepo;

  @Autowired
  public DesignFerrariController(
        SpecificationRepository specificationRepo, 
        FerrariRepository ferrariRepo,
        UserRepository userRepo) {
    this.specificationRepo = specificationRepo;
    this.ferrariRepo = ferrariRepo;
    this.userRepo = userRepo;
  }

  @ModelAttribute(name = "order")
  public Order order() {
    return new Order();
  }
  
  @ModelAttribute(name = "design")
  public Ferrari design() {
    return new Ferrari();
  }
  
  @GetMapping
  public String showDesignForm(Model model, Principal principal) {
    System.out.println("   --- Designing ferrari");
    List<Specification> specifications = new ArrayList<>();
    specificationRepo.findAll().forEach(i -> specifications.add(i));
    
    Type[] types = Specification.Type.values();
    for (Type type : types) {
      model.addAttribute(type.toString().toLowerCase(), 
          filterByType(specifications, type));      
    }
    System.out.println("   --- Designing ferrari-2");
    String username = principal.getName();
    User user = userRepo.findByUsername(username);
    model.addAttribute("user", user);
    System.out.println("   --- Designing ferrari-3");
    return "design";
  }

  @PostMapping
  public String processDesign(
      @Valid Ferrari ferrari, Errors errors, 
      @ModelAttribute Order order) {

    System.out.println("   --- Saving ferrari");

    if (errors.hasErrors()) {
      System.out.println("Dawg there was some error saving your design");
      System.out.println(ferrari);
      System.out.println(errors);
      System.out.println(order);
      return "design";
    }
    System.out.println("   --- Saving ferrari-2");
    Ferrari saved = ferrariRepo.save(ferrari);
    order.addDesign(saved);
    System.out.println("   --- Saving ferrari-3");
    return "redirect:/orders/current";
  }

  private List<Specification> filterByType(
      List<Specification> specifications, Type type) {
    return specifications
              .stream()
              .filter(x -> x.getType().equals(type))
              .collect(Collectors.toList());
  }


}
