package org.example.homework20.task2;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/terminal")
public class TerminalController {

    private final TerminalImpl terminal;

    public TerminalController() {
        this.terminal = new TerminalImpl("1234");
    }

    @GetMapping("/balance")
    public ResponseEntity<Float> checkBalance() {
        return ResponseEntity.ok(terminal.getBalance());
    }

    @PostMapping("/deposit")
    public ResponseEntity<String> deposit(@RequestParam int amount) {
        terminal.put(amount);
        return ResponseEntity.ok("На счет зачислено: " + amount);
    }

    @PostMapping("/withdraw")
    public ResponseEntity<String> withdraw(@RequestParam int amount) {
        terminal.withdraw(amount);
        return ResponseEntity.ok("Со счета снято: " + amount);
    }
}