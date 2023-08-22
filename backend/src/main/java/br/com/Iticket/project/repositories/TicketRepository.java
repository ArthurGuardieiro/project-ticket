package br.com.Iticket.project.repositories;


import br.com.Iticket.project.entities.Ticket;
import br.com.Iticket.project.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    Page<Ticket> findByUser (User user, Pageable pageable);

}
