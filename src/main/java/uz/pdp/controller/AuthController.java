package uz.pdp.controller;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import uz.pdp.university.DTO.SignInDTO;
import uz.pdp.university.DTO.SignUpDTO;
import uz.pdp.university.entity.UserEntity;
import uz.pdp.university.service.UserService;

import static uz.pdp.university.entity.UserType.ORGANIZER;
import static uz.pdp.university.entity.UserType.USER;

@Controller
@RequestMapping(value = {"/auth", "/"})
@AllArgsConstructor
public class AuthController {

    UserService userService;

    @RequestMapping()
    public String index() {
        return "index";
    }

    @RequestMapping("/sign-in")
    public String sigInPage() {
        return "sign-in";
    }

    @RequestMapping("/sign-up")
    public String sigUpPage() {
        return "sign-up";
    }

    @RequestMapping(value = "/sign-up", method = RequestMethod.POST)
    public String signUp(@ModelAttribute("user") SignUpDTO signUpDTO, HttpSession session, Model model) {
        UserEntity userEntity = userService.signUp(signUpDTO);
        model.addAttribute("session", userEntity);

        session.setAttribute("userId", userEntity.getId());
        if (userEntity.getRole().equals(USER)) {
            return "users";
        } else if (userEntity.getRole().equals(ORGANIZER)) {
            return "organizer";
        }
        return "sign-up";
    }

    @RequestMapping(value = "/sign-in", method = RequestMethod.POST)
    public String signIn(@ModelAttribute SignInDTO signInDto, HttpSession session, Model model) {
        UserEntity userEntity = userService.signIn(signInDto);
        model.addAttribute("session", userEntity);
        if (userEntity != null) {
            session.setAttribute("userId", userEntity.getId());
            if (userEntity.getRole().equals(USER)) {
                return "users";
            } else if (userEntity.getRole().equals(ORGANIZER)) {
                return "organizer";
            }
            return "sign-in";
        }

        return "sign-in";
    }
}
