package com.university.application.repository;

import com.university.application.entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface BranchRepository extends JpaRepository<Branch,Long>
{
    Optional<Branch> findByBranchCode(String branchCode);
    boolean existsByBranchCode(String branchCode);
    void deleteByBranchCode(String branchCode);

}
