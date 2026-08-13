package com.blackmarket.blackmarket.repository;

import com.blackmarket.blackmarket.entity.Brinquedo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface BrinquedosRepository extends JpaRepository<Brinquedo, Long> {
    Optional<Brinquedo> findById(Long id);
}
