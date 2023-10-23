    package com.example.cinema_booking.entity;


    import com.example.cinema_booking.domain.Chair;
    import jakarta.persistence.*;
    import lombok.Getter;
    import lombok.Setter;
    import org.springframework.context.annotation.EnableMBeanExport;

    @Getter
    @Setter
    @Entity
    @Table(name = "Chairs")
    public class ChairEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long idChair;

        private boolean isOccupied;

        private String nameChair;

        @ManyToOne
        @JoinColumn(name = "idShowtime")
        private ShowtimeEntity showtime;

        @ManyToOne
        @JoinColumn(name = "cinema_room_id")
        private CinemaRoomEntity cinemaRoom;

        @ManyToOne
        @JoinColumn(name = "id_cinema")
        private CinemaEntity cinemaEntity;



        public CinemaEntity getCinemaEntity() {
            return cinemaEntity;
        }

        public void setCinemaEntity(CinemaEntity cinemaEntity) {
            this.cinemaEntity = cinemaEntity;
        }

        public CinemaRoomEntity getCinemaRoom() {
            return cinemaRoom;
        }

        public void setCinemaRoom(CinemaRoomEntity cinemaRoom) {
            this.cinemaRoom = cinemaRoom;
        }

        public Long getIdChair() {
            return idChair;
        }

        public void setIdChair(Long idChair) {
            this.idChair = idChair;
        }

        public boolean isOccupied() {
            return isOccupied;
        }

        public void setOccupied(boolean occupied) {
            isOccupied = occupied;
        }

        public Chair toChair() {
            Chair chair = new Chair();
            chair.setIdChair(this.idChair);
            chair.setOccupied(this.isOccupied);
            // Các dòng code khác để sao chép dữ liệu từ ChairEntity sang Chair

            return chair;
        }
    }
