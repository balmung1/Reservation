package RoomSystem;

import RoomSystem.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PolicyHandler{

    @Autowired
    ReservationRepository reservationRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverRoomRegistered_RoomList(@Payload RoomRegistered roomRegistered){

        if(roomRegistered.isMe()){
            Reservation reservation = new Reservation();

            reservation.setRoomId(roomRegistered.getRoomId());
            reservation.setRoomStatus("True");
            reservation.setReservationId(roomRegistered.getId());

            reservationRepository.save(reservation);
            System.out.println("##### Room Registered RoomList : " + roomRegistered.toJson());
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverRoomDelete_RoomList(@Payload RoomDelete roomDelete){

        if(roomDelete.isMe()){
            Reservation reservation = new Reservation();

            reservation.setId(roomDelete.getId());

            reservationRepository.delete(reservation);

            System.out.println("##### Room Delete RoomList : " + roomDelete.toJson());
        }
    }

}
