package com.fine.logflashdb.Controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/heartbeat")
public class HeartbeatController {
    @GetMapping("/")
    public String heartbeat() {
        return "Hello!";
    }

}
