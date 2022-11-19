package com.techytown.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techytown.model.OrderDTO;

@Repository
public interface OrderDTORepositoey extends JpaRepository<OrderDTO, Integer> {

}
