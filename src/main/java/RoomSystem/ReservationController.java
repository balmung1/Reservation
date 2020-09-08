package RoomSystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class ReservationController {
    @Autowired
    ReservationRepository reservationRepository;

    @RequestMapping(method = RequestMethod.PATCH, path = "/reservation")
    public void reservation(@RequestBody Reservation reservation) {
        Reservation reser1 = new Reservation();

        reser1.setReservationId(reservation.getReservationId());
        reser1.setUserId(reservation.getUserId());
        reser1.setRoomId(reservation.getRoomId());
        reser1.setRoomStatus("False");
        reser1.setmType("reservation");
        reservationRepository.save(reser1);
    }

    @RequestMapping(method = RequestMethod.PATCH, path = "/cancel")
    public void reservationCancel(@RequestBody ReservationCanceled reservationCanceled) {
        Reservation reser2 = new Reservation();

        reser2.setReservationId(reservationCanceled.getReservationId());
        reser2.setUserId(reservationCanceled.getUserId());
        reser2.setRoomId(reservationCanceled.getRoomId());
        reser2.setRoomStatus("True");
        reser2.setmType("reservationCanceled");
        reservationRepository.save(reser2);
    }

}
