package com.codeup.codeup_demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
class RollDiceController {

    @GetMapping("/roll-dice")
    public String showJoinForm() {
        return "roll-dice";
    }

    @PostMapping("/roll-dice")
    public String rolldice(@RequestParam(name="dice") String dice, Model model) {
        model.addAttribute("dice","Your dice number is "+dice+"!");
        return "roll-dice";
    }

}