package br.com.Iticket.project.tests;

import br.com.Iticket.project.entities.City;
import br.com.Iticket.project.entities.Event;

import java.time.Instant;

public class Factory {

    public static Event createEvent () {
        Event event = new Event(5L, "Rock in rio", "Copacabana", new City(2L, "Rio de janeiro", "RJ"), Instant.parse("2023-06-14T14:00:00Z"));
        return event;
    }

    public static City createCity() {
        City city = new City(5L, "Guarujá", "SP");
        return city;
    }

}
