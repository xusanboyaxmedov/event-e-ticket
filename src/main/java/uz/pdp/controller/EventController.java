package uz.pdp.controller;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import uz.pdp.DTO.EventDTO;
import uz.pdp.entity.EventEntity;
import uz.pdp.entity.TicketEntity;
import uz.pdp.entity.UserEntity;
import uz.pdp.service.EventService;
import uz.pdp.service.TicketService;
import uz.pdp.service.UserService;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/events")
@AllArgsConstructor
public class EventController {
    EventService eventService;
    UserService userService;
    TicketService ticketService;

    @RequestMapping(value = "/add-event", method = RequestMethod.GET)
    public String addEvents(Model model, HttpSession session) {
        model.addAttribute("events", eventService.getEvents((UUID) session.getAttribute("userId")));
        model.addAttribute("balance", userService.findById((UUID) session.getAttribute("userId")).getBalance());
        model.addAttribute("session", userService.findById((UUID) session.getAttribute("userId")));
        return "add-event";
    }

    @RequestMapping(value = "/show-user-events", method = RequestMethod.GET)
    public String userGetEvents(Model model, HttpSession session) {
        model.addAttribute("events", eventService.getEvents());
        model.addAttribute("balance", userService.findById((UUID) session.getAttribute("userId")).getBalance());
        return "show-user-events";
    }

    @RequestMapping(value = "/buy-event", method = RequestMethod.POST)
    public String userBuyEvent(@RequestParam("eventId") UUID eventId, Model model, HttpSession session) {
        UserEntity user = userService.findById( (UUID) session.getAttribute("userId"));
        EventEntity event = eventService.findById(eventId);

        TicketEntity eventTicket = ticketService.findByEventId(event.getId());

        if (eventTicket == null) {
            model.addAttribute("errorMessage", "event not found");
            model.addAttribute("balance", user.getBalance());
            return "show-user-events";
        }

        if (user.getBalance() < event.getTicketPrice()) {

        }
        userService.updateMinusBalance(user.getId(), eventTicket.getPrice());
        userService.updatePlusBalance(event.getOwnerId().getId(), eventTicket.getPrice());

        eventTicket.setBuyer(user);
        ticketService.update(eventTicket);

        model.addAttribute("events", eventService.getEvents());
        model.addAttribute("balance", userService.findById(user.getId()).getBalance());
        return "show-user-events";
    }

    @RequestMapping(value = "/add-event", method = RequestMethod.POST)
    public String addEvent(@ModelAttribute EventDTO eventDTO, Model model, HttpSession session) {
        UserEntity userId = userService.findById ((UUID) session.getAttribute("userId"));
        eventDTO.setUserId(userId);
        try {
            eventService.addEvent(eventDTO);
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("session", userId);
            return "add-event";
        }
        model.addAttribute("events", eventService.getEvents(userId.getId()));
        model.addAttribute("balance", userService.findById (userId.getId()).getBalance());
        return "show-events";
    }

    @RequestMapping(value = "/show-events")
    public String showEvent(Model model, HttpSession session) {
        List<EventEntity> ownerEvents = eventService.getEvents((UUID) session.getAttribute("userId"));
        model.addAttribute("events", ownerEvents);
        model.addAttribute("balance", userService.findById((UUID) session.getAttribute("userId")).getBalance());
        return "show-events";
    }

    @RequestMapping(value = "/delete-event", method = RequestMethod.POST)
    public String deleteEvent(@RequestParam("eventId") UUID eventId, Model model, HttpSession session) {
        eventService.deleteEvent(eventId);
        model.addAttribute("events", eventService.getEvents((UUID) session.getAttribute("userId")));
        model.addAttribute("balance", userService.findById((UUID) session.getAttribute("userId")).getBalance());
        model.addAttribute("session", userService.findById((UUID) session.getAttribute("userId")));
        return "show-events";
    }


}
