package br.com.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.api.entity.PhoneEntity;

@Repository
public interface PhoneRepository extends JpaRepository<PhoneEntity, Long> {

	Optional<PhoneEntity> findByDddAndNumber(Integer ddd, String number);
}
