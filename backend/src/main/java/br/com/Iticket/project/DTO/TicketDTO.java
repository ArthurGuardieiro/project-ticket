package br.com.Iticket.project.DTO;


import br.com.Iticket.project.entities.Ticket;
import jakarta.validation.constraints.PositiveOrZero;

public class TicketDTO {

    private Long id;
    @PositiveOrZero(message = "valor deve ser positivo ou igual a zero")
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
