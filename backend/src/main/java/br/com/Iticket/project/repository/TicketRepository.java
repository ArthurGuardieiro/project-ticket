package br.com.Iticket.project.repository;


import br.com.Iticket.project.model.Ticket;
import br.com.Iticket.project.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    Page<Ticket> findByUser (User user, Pageable pageable);

}
