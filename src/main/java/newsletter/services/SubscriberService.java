package newsletter.services;

import newsletter.models.Subscriber;

import java.util.List;
import java.util.Optional;

public interface SubscriberService {
    Optional<Subscriber> getInstanceById(Long id);
    void save (Subscriber subscriber);
    List<Subscriber> getInstancesList();
    void delete(Subscriber subscriber);
}
