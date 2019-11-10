package ar.gob.afip.training.tdd.wallet.web;

import ar.gob.afip.training.tdd.wallet.core.business.model.Greeting;
import ar.gob.afip.training.tdd.wallet.core.persistence.GreetingRepository;
import com.google.inject.internal.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.stream.Collectors;

import static org.apache.commons.lang.StringUtils.join;

@RestController
public class WalletController {

    @Autowired
    private GreetingRepository greetingRepository;

    @RequestMapping("/hello")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @RequestMapping("/saveGreeting")
    public String saveGreeting() {
        Greeting greeting = new Greeting();
        greeting.setText("Howdy");
        greetingRepository.save(greeting);
        return "Greeting saved";
    }

    @RequestMapping("/showAllGreetings")
    public String showAllGreetings() {
        Iterable<Greeting> greetings = greetingRepository.findAll();

        return join(Lists.newArrayList(greetings).stream().map(Greeting::getText).collect(Collectors.toList()), ", ");
    }

}