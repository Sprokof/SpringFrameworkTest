package com.twister.app.controller;

import com.twister.app.entity.Message;
import com.twister.app.entity.User;
import com.twister.app.repositories.MessageRepository;
import com.twister.app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class GreetingController {
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String greeting(){
        return "greeting";
    }

    @PostMapping("/registration")
    public String registration(@RequestParam(required = false, defaultValue = "username") String username,
                               @RequestParam(required = false, defaultValue = "password")
            String password, Map<String, Object> model) {
        boolean rightPassword = password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])" +
                "(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");
        boolean rightName = (username.matches("[aA-zZ]") &&
                (username.length()>=3 && username.length()<=15));
        if(rightName && rightPassword){
           userRepository.save(new User(username, password));
           Iterable<User> users = userRepository.findAll();
           model.put("users", users);
           return "main";}

        return "registration";
    }


    @GetMapping("/main")
    public String main(Map<String, Object> model) {
        Iterable<Message> all = messageRepository.findAll();
        model.put("messages", all);
        return "main";
    }

    @PostMapping("/main/add")
    public String add(@RequestParam String text, @RequestParam String tag, Map<String, Object> model) {
        messageRepository.save(new Message(text, tag));
        Iterable<Message> all = messageRepository.findAll();
        model.put("messages", all);
        return "main";
    }

    @PostMapping("/main/filter")
    public String filter(@RequestParam String tag, Map<String, Object> model) {
        Iterable<Message> messages;
        if (tag != null && !tag.isEmpty()) {
            messages = messageRepository.findByTag(tag);
        } else {
            messages = messageRepository.findAll();
        }
        model.put("messages", messages);
        return "main";
    }

    @PostMapping("/login")
    public String login(@RequestParam(required = false, defaultValue = "username") String username,
                        @RequestParam(required = false, defaultValue = "password") String password,
                        Map<String, Object> model){
    User user = new User(username, password);
        if(userRepository.findUserByUsername(username).contains(user)){
            model.put("user", user);
            return "main";}
        else {
            return "login";
        }}
        }






