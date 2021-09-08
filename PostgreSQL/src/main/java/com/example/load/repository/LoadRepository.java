package com.example.load.repository;

import com.example.load.model.LoadDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LoadRepository extends JpaRepository<LoadDetails, Long> {
    List<LoadDetails> findByShipperId(String shipperId);

    Optional<LoadDetails> findById(Long loadId);
}
