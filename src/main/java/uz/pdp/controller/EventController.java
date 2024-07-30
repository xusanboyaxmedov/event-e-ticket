package uz.pdp.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import uz.pdp.DTO.EventDTO;
import uz.pdp.entity.EventEntity;
import uz.pdp.service.EventService;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/events")
@AllArgsConstructor
public class EventController {
    EventService eventService;

    @RequestMapping(value = "/add-event", method = RequestMethod.GET)
    public String addEvents(Model model) {
        model.addAttribute("events", eventService.getEvents());
        return "add-event";
    }

    @RequestMapping(value = "/show-events", method = RequestMethod.GET)
    public String showEvents(Model model) {
        model.addAttribute("events", eventService.getEvents());
        return "show-events";
    }


    @RequestMapping(value = "/add-event", method = RequestMethod.POST)
    public String addEvent(@ModelAttribute EventDTO eventDTO, Model model) {
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
