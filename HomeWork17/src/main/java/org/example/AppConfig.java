package org.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("org.example")
public class AppConfig {

    @Bean
    public Main mainObject() {
        return new Main(terminal());
    }

    @Bean
    public TerminalImpl terminal() {
        return new TerminalImpl("1234", messageDisplay());
    }

    @Bean
    public TerminalServer terminalServer() {
        return new TerminalServer(messageDisplay());
    }

    @Bean
    public PinValidator pinValidator() {
        return new PinValidator("1234", messageDisplay());
    }

    @Bean
    public MessageDisplay messageDisplay() {
        return new ConsoleDisplay();
    }
}
