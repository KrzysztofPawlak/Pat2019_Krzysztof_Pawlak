package com.krzysztof.studio;

import com.krzysztof.studio.boardroom.BoardroomService;
import com.krzysztof.studio.model.Boardroom;
import com.krzysztof.studio.model.Organization;
import com.krzysztof.studio.model.Reservation;
import com.krzysztof.studio.organization.OrganizationService;
import com.krzysztof.studio.reservation.ReservationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class MainApplication {

	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}

	@Bean
	public CommandLineRunner demoData(OrganizationService organizationService,
									  BoardroomService boardroomService,
									  ReservationService reservationService) {
		return args -> {
			organizationService.create(createSampleOrganization());
			boardroomService.create(createSampleBoardroom());
			reservationService.create(createSampleReservation());
		};
	}

	private Organization createSampleOrganization() {
		var organization = new Organization();
		organization.setName("foo");
		return organization;
	}

	private Boardroom createSampleBoardroom() {
		var boardroom = new Boardroom();
		boardroom.setName("salka blue");
		boardroom.setId("1.33");
		boardroom.setLevel(0);
		boardroom.setAvailable(true);
		boardroom.setSeats(10);
		boardroom.setSunbeds(10);
		boardroom.setHammocks(10);
		return boardroom;
	}

	private Reservation createSampleReservation() {
		var reservation = new Reservation();
		reservation.setId("foo");
		reservation.setReservationFrom(LocalDateTime.now());
		reservation.setReservationTo(LocalDateTime.now());
		return reservation;
	}
}

