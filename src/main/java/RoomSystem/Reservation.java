package RoomSystem;

import javax.persistence.*;

import RoomSystem.external.Demerit;
import RoomSystem.external.DemeritService;
import org.springframework.beans.BeanUtils;

import java.util.List;

@Entity
@Table(name = "Reservation_table")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long reservationId;
    private Long roomId;
    private String userId;
    private String roomStatus;
    private String mType;


    @PostPersist
    public void onPostUpdate() {
        if(mType.equals("reservation")){
            Reserved reserved = new Reserved();
            BeanUtils.copyProperties(this,reserved);
            reserved.publish();

        }else{
            ReservationCanceled reservationCanceled = new ReservationCanceled();
            BeanUtils.copyProperties(this,reservationCanceled);
            reservationCanceled.publish();

            Demerit demerit = new Demerit();

            demerit.setDemeritId(reservationCanceled.getId());
            demerit.setUserId(reservationCanceled.getUserId());

            DemeritService demeritService = Application.applicationContext.getBean(DemeritService.class);
            demeritService.demerit(demerit);
        }

    }


    public String getmType() {
        return mType;
    }

    public void setmType(String mType) {
        this.mType = mType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(String roomStatus) {
        this.roomStatus = roomStatus;
    }


}
