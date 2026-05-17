package za.ac.cput;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import za.ac.cput.repositories.*;
import za.ac.cput.repositories.inmemory.*;


@Configuration
public class RepositoryConfig {

    @Bean
    public BookRepository bookRepository() {
        return new InMemoryBookRepository();
    }

    @Bean
    public MemberRepository memberRepository() {
        return new InMemoryMemberRepository();
    }

    @Bean
    public LoanRepository loanRepository() {
        return new InMemoryLoanRepository();
    }

    @Bean
    public ReservationRepository reservationRepository() {
        return new InMemoryReservationRepository();
    }

    @Bean
    public FineRepository fineRepository() {
        return new InMemoryFineRepository();
    }
}