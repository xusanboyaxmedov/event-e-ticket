package uz.pdp.controller;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import uz.pdp.DTO.EventDTO;
import uz.pdp.entity.EventEntity;
import uz.pdp.entity.UserEntity;
import uz.pdp.service.EventService;
import uz.pdp.service.TicketService;
import uz.pdp.service.UserService;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    EventService eventService;

    UserService userService;

    TicketService ticketService;

    @RequestMapping
    public String userPage(HttpSession session, Model model) {
        model.addAttribute("session", userService.findById((UUID) session.getAttribute("userId")));
        return "user";
    }

    @RequestMapping(value = "/history", method = RequestMethod.GET)
    public String showEvent(Model model, HttpSession session) {
        model.addAttribute("balance", userService.findById((UUID) session.getAttribute("userId")).getBalance());
        model.addAttribute("tickets", ticketService.getTickets((UUID) session.getAttribute("userId")));
        return "user-attendance";
    }



}
