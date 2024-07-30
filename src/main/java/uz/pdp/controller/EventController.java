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
    public String addEvents(Model model) {
        model.addAttribute("events", eventService.getEvents());
        return "add-event";
    }

    @RequestMapping(value = "/show-user-events", method = RequestMethod.GET)
    public String userGetEvents(Model model) {
        model.addAttribute("events", eventService.getEvents());
        return "show-user-events";
    }

    @RequestMapping(value = "/buy-event", method = RequestMethod.POST)
    public String userBuyEvent(Model model, HttpSession session) {
        UserEntity user = (UserEntity) session.getAttribute("userId");
        UUID eventId = (UUID) model.getAttribute("eventId");
        EventEntity event = eventService.findById(eventId);

        TicketEntity byEventId = ticketService.findByEventId(event.getId());

        if(user.getBalance() < event.getTicketPrice()){
            // xusanboyga berldi
        }
        userService.updateMinusBalance(user.getId(), byEventId.getPrice());
        userService.updatePlusBalance(event.getOwnerId().getId(), byEventId.getPrice());

        byEventId.setBuyer(user);
        ticketService.update(byEventId);

        model.addAttribute("events", eventService.getEvents());
        return "show-user-events";
    }

    @RequestMapping(value = "/show-events", method = RequestMethod.GET)
    public String showEvents(Model model) {
        model.addAttribute("events", eventService.getEvents());
        return "show-events";
    }


    @RequestMapping(value = "/add-event", method = RequestMethod.POST)
    public String addEvent(@ModelAttribute EventDTO eventDTO, Model model, HttpSession session) {
        UserEntity userId = (UserEntity) session.getAttribute("userId");
        eventDTO.setUserId(userId);
        try {
            eventService.addEvent(eventDTO);
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "add-event";
        }
        model.addAttribute("events", eventService.getEvents());
        return "show-events";
    }

    @RequestMapping(value = "/show-events")
    public String showEvent(Model model) {
        List<EventEntity> events = eventService.getEvents();
        model.addAttribute("events", events);
        return "show-events";
    }

    @RequestMapping(value = "/delete-event", method = RequestMethod.POST)
    public String deleteEvent(@RequestParam("eventId") UUID eventId, Model model) {
        eventService.deleteEvent(eventId);
        model.addAttribute("events", eventService.getEvents());
        return "show-events";
    }


}
