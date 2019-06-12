package newsletter.controllers;

import newsletter.models.Subscriber;
import newsletter.services.SubscriberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class SubscriberController {
    private SubscriberService subscriberService;

    @Autowired
    public SubscriberController(SubscriberService subscriberService){
        this.subscriberService = subscriberService;
    }

    @RequestMapping( value = { "", "/", "/index"}, method = RequestMethod.GET )
    public String index(Model model){
        List<Subscriber> subscribers = subscriberService.getInstancesList();
        model.addAttribute("subscribers", subscribers);
        return "index";
    }

    @RequestMapping( value = "/edit/{id}", method = RequestMethod.GET )
    public String edit(Model model, @PathVariable("id") Long id){
        Subscriber subscriber = subscriberService.getInstanceById(id).orElseGet(null);
        if (subscriber != null){
            model.addAttribute("subscriber", subscriber);
            return "edit";
        }
        return "redirect:/index";
    }

    @RequestMapping( value = "/edit/{id}", method = RequestMethod.POST )
    public String editPost(Subscriber subscriberNew, @PathVariable("id") Long id){
        Subscriber subscriber = subscriberService.getInstanceById(id).orElseGet(null);
        if (subscriber != null){
            subscriber.setName(subscriberNew.getName());
            subscriber.setEmail(subscriberNew.getEmail());
            subscriberService.save(subscriber);
        }
        return "redirect:/index";
    }

    @RequestMapping( value = "/delete/{id}", method = RequestMethod.GET )
    public String delete(@PathVariable("id") Long id){
        Subscriber subscriber = subscriberService.getInstanceById(id).orElseGet(null);
        if (subscriber != null){
            subscriberService.delete(subscriber);
        }
        return "redirect:/index";
    }

    @RequestMapping( value = "/add", method = RequestMethod.GET )
    public String add(Model model){
        model.addAttribute("subscriber", new Subscriber());
        return "add";
    }

    @RequestMapping( value = "/add", method = RequestMethod.POST )
    public String addPost(HttpServletRequest request, @ModelAttribute("subscriber") Subscriber subscriberNew, BindingResult result){
        Subscriber subscriber = new Subscriber();
        subscriber.setId(subscriberNew.getId());
        subscriber.setName(subscriberNew.getName());
        subscriber.setEmail(subscriberNew.getEmail());
        subscriberService.save(subscriber);
        return "redirect:/index";
    }
}
