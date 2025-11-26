package br.edu.ifpb.veritas.controllers.mvc;

import br.edu.ifpb.veritas.models.Role;
import br.edu.ifpb.veritas.models.User;
import br.edu.ifpb.veritas.repository.RoleRepository;
import br.edu.ifpb.veritas.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.Set;

@Controller
@RequestMapping("/users")
public class UserMvcController {

    private final UserService userService;
    private final RoleRepository roleRepository;

    public UserMvcController(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @GetMapping("/list")
    public ModelAndView list() {
        ModelAndView mv = new ModelAndView("users-list");
        mv.addObject("users", userService.findAll());
        return mv;
    }

    @GetMapping("/new")
    public ModelAndView createForm() {
        ModelAndView mv = new ModelAndView("users-form");
        mv.addObject("user", new User());
        mv.addObject("roles", roleRepository.findAll());
        return mv;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editForm(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView("users-form");
        userService.findById(id).ifPresent(u -> mv.addObject("user", u));
        mv.addObject("roles", roleRepository.findAll());
        return mv;
    }

    @PostMapping("/save")
    public ModelAndView save(
            @ModelAttribute User user,
            @RequestParam(required = false) java.util.List<Long> rolesIds
    ) {

        if (rolesIds != null) {
            java.util.List<Role> roles = roleRepository.findAllById(rolesIds);
            user.setRoles(Set.copyOf(roles));
        }

        userService.save(user);

        return new ModelAndView("redirect:/users/list");
    }

    @PostMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable Long id) {
        userService.delete(id);
        return new ModelAndView("redirect:/users/list");
    }
}
