package com.userapp.repository;

import com.userapp.model.AddressDetails;
import com.userapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressDetailsRepository extends JpaRepository<AddressDetails, Long> {

    Optional<AddressDetails> findByUser(User user);

}
