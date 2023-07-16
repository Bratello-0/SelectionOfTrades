package org.example.selectionOfTrades.repositories.contractCSGO;


import org.example.selectionOfTrades.models.entities.contractCSGO.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ContractRepository  extends JpaRepository<Contract, Long> {
    List<Contract> findByCoefficient(Double coefficient);
    @Query("SELECT u FROM Contract u WHERE u.coefficient > ?1 ORDER BY coefficient DESC")
    List<Contract> findAllContractsGreaterNumberWithCoefficient(Double number);
}
