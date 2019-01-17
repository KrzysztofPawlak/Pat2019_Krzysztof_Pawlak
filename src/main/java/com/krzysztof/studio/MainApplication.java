package com.krzysztof.studio;

import com.krzysztof.studio.boardroom.BoardroomService;
import com.krzysztof.studio.model.db.*;
import com.krzysztof.studio.model.rest.Equipment;
import com.krzysztof.studio.model.rest.Organization;
import com.krzysztof.studio.model.rest.Phone;
import com.krzysztof.studio.model.PhoneInterface;
import com.krzysztof.studio.model.rest.Reservation;
import com.krzysztof.studio.organization.OrganizationService;
import com.krzysztof.studio.reservation.ReservationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.UUID;

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

	private DbOrganization createSampleOrganization() {
		var organization = new DbOrganization();
		organization.setName("foo");
		return organization;
	}

	private DbBoardroom createSampleBoardroom() {
		var boardroom = new DbBoardroom();
		boardroom.setName("salka blue");
		boardroom.setId("1.33");
		boardroom.setOrganizationName("foo");
		boardroom.setLevel(0);
		boardroom.setAvailable(true);
		boardroom.setSeats(10);
		boardroom.setStandingPlaces(10);
		boardroom.setSunbeds(10);
		boardroom.setHammocks(10);
		boardroom.setEquipment(createSampleEquipment());
		return boardroom;
	}

	private DbEquipment createSampleEquipment() {
		var equipment = new DbEquipment();
		equipment.setProjectorName("foo");
		equipment.setId(UUID.randomUUID());
		equipment.setPhoneAvailable(true);
		equipment.setPhone(createSamplePhone());
		return equipment;
	}

	private DbPhone createSamplePhone() {
		var phone = new DbPhone();
		phone.setId(UUID.randomUUID());
		phone.setExternalNumber("+12 123456789");
		phone.setInternalNumber(99);
		phone.setPhoneInterface(PhoneInterface.USB.toString());
		return phone;
	}

	private DbReservation createSampleReservation() {
		var reservation = new DbReservation();
		reservation.setId("foo");
		reservation.setBoardroomName("salka blue");
		reservation.setReservationFrom(LocalDateTime.now());
		reservation.setReservationTo(LocalDateTime.now());
		return reservation;
	}
}

