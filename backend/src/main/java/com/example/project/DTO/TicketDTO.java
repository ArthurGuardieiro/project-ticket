package com.example.project.DTO;

import com.example.project.entities.Ticket;
import com.example.project.entities.User;

public class TicketDTO {

    private Long id;
    private Double price;
    private Long user_id;
    private Long event_id;

    public TicketDTO() {

    }

    public TicketDTO(Long id, Double price, Long user_id, Long event_id) {
        this.id = id;
        this.price = price;
        this.user_id = user_id;
        this.event_id = event_id;
    }

    public TicketDTO(Ticket entity) {
        this.id = entity.getId();
        this.price = entity.getPrice();
        this.user_id = entity.getUser().getId();
        this.event_id = entity.getEvent().getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getEvent_id() {
        return event_id;
    }

    public void setEvent_id(Long event_id) {
        this.event_id = event_id;
    }
}
