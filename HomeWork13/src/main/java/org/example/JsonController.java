package org.example;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import java.time.Duration;

@RestController
@RequestMapping("/api")
public class JsonController {

    @GetMapping("/json-stream")
    public Flux<String> getJsonStream() {
        // Пример JSON чанков с интервалом в 5 секунд
        return Flux.just(
                "{\"field\":\"value1\"}","{\"field\":\"value2\"}","{\"field\":\"value3\"}"
        )
        .delayElements(Duration.ofSeconds(5))
        .concatWithValues("");
    }
}
