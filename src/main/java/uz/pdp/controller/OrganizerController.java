package uz.pdp.controller;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.pdp.service.EventService;
import uz.pdp.service.UserService;

import java.util.UUID;

@Controller
@RequestMapping("/organizer")
@AllArgsConstructor
public class OrganizerController {
    EventService eventService;
    UserService userService;


    @RequestMapping
    public String organizerPage(HttpSession session, Model model) {
        model.addAttribute("session", userService.findById((UUID) session.getAttribute("userId")));
        return "organizer";
    }
}
