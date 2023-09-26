package br.com.Iticket.project.tests;

import br.com.Iticket.project.model.City;
import br.com.Iticket.project.model.Event;
import br.com.Iticket.project.model.Role;
import br.com.Iticket.project.model.User;

import java.time.Instant;

public class Factory {

    public static Event createEvent () {
        Event event = new Event(5L, "Rock in rio", "Copacabana", new City(1L, "Rio de janeiro", "RJ"), Instant.parse("2023-06-14T14:00:00Z"));
        return event;
    }

    public static City createCity() {
        City city = new City(5L, "Guarujá", "SP");
        return city;
    }

    public static User createUser() {
        User user = new User(1L, "jonas@gmail.com", "$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG", "arthur");
        user.setCity( Factory.createCity() );
        user.setRole( new Role(1L, "ROLE_ADMIN") );
        return user;
    }


}
