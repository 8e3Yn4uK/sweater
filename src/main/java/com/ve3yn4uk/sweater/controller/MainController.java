package com.ve3yn4uk.sweater.controller;

import com.ve3yn4uk.sweater.domain.Message;
import com.ve3yn4uk.sweater.repos.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MainController {

    private MessageRepo repo;

    @Autowired
    public MainController(MessageRepo repo) {
        this.repo = repo;
    }

    @GetMapping("/")
    public String greeting( Map<String, Object> model){


        return "greeting";
    }

    @GetMapping("/main")
    public String main(Map<String, Object> model){
        Iterable<Message> messages = repo.findAll();

        model.put("messages", messages);

        return "main";
    }

    @PostMapping("/main")
    public String add(@RequestParam String text, @RequestParam String tag, Map<String, Object> model){

        Message message = new Message(text, tag);

        repo.save(message);

        Iterable<Message> messages = repo.findAll();

        model.put("messages", messages);

        return "main";
    }

    @PostMapping("filter")
    public String filter(@RequestParam String filter, Map<String, Object> model){
        Iterable<Message> messages;
        if (filter != null && !filter.isEmpty()) {
            messages = repo.findByTag(filter);
        } else {
            messages = repo.findAll();
        }
        model.put("messages", messages);

        return "main";
    }
}
