package ar.gob.afip.training.tdd.wallet.core.persistence;

import ar.gob.afip.training.tdd.wallet.core.business.model.Greeting;
import org.springframework.data.repository.CrudRepository;

public interface GreetingRepository extends CrudRepository<Greeting, Long> {
    Greeting findById(long id);
}


