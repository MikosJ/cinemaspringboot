package cinema;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CinemaController {


    @GetMapping("/seats")
    public CinemaHall getHall() {
        return new CinemaHall(9,9);
    }

}
