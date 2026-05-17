package za.ac.cput.repositories;

import za.ac.cput.domain.Fine;
import java.util.List;

public interface FineRepository extends Repository<Fine, String> {
    List<Fine> findByStatus(String status);
    List<Fine> findByLoanId(String loanId);
}