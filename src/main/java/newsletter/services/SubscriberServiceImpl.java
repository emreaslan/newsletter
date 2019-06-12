package newsletter.services;

import newsletter.repositories.SubscriberRepository;
import newsletter.models.Subscriber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SubscriberServiceImpl implements SubscriberService {
    private SubscriberRepository subscriberRepository;

    @Autowired
    public SubscriberServiceImpl(SubscriberRepository subscriberRepository) {
        this.subscriberRepository = subscriberRepository;
    }

    @Override
    public Optional<Subscriber> getInstanceById(Long id) {
        return subscriberRepository.findById(id);
    }

    @Override
    public void save(Subscriber subscriber) {
        subscriberRepository.save(subscriber);
    }

    @Override
    public List<Subscriber> getInstancesList() {
        List<Subscriber> list = new ArrayList<>();
        subscriberRepository.findAll().forEach(list::add);
        return list;
    }

    @Override
    public void delete(Subscriber subscriber) {
        subscriberRepository.delete(subscriber);
    }
}
