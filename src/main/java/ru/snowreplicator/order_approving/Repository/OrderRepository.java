package ru.snowreplicator.order_approving.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.snowreplicator.order_approving.Entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
