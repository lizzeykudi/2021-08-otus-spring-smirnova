package ru.otus.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import ru.otus.spring.bookService.BookService;
import ru.otus.spring.domain.Book;

import javax.annotation.PostConstruct;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@EnableWebFlux
@EnableMongoRepositories
@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class);
    }

    private final BookService bookService;

    @Autowired
    public Main(BookService bookService) {
        this.bookService = bookService;
    }

    @PostConstruct
    public void init() {
        Flux.concat(
            bookService.insert("Thinking in Java", "Bruce Eckel", "Java"),
            bookService.insert("Spring in Action", "Craig Walls", "Spring")
        )
                .map(Book::toString)
                .subscribe(System.out::println);
    }

    @Bean
    public RouterFunction<ServerResponse> htmlRouter(
            @Value("classpath:/public/index.html") Resource html) {
        return route(GET("/"), request
                -> ok().contentType(MediaType.TEXT_HTML).bodyValue(html)
        );
    }

    @Bean
    public RouterFunction<ServerResponse> bundleMinJsRouter(
            @Value("classpath:/public/bundle.min.js") Resource bundleMinJs) {
        return route(GET("/bundle.min.js"), request
                -> ok().bodyValue(bundleMinJs)
        );
    }
}
