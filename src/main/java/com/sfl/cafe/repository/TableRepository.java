package com.sfl.cafe.repository;

import com.sfl.cafe.model.Table;
import com.sfl.cafe.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface TableRepository extends JpaRepository<Table, Integer> {


    @Modifying
    @Transactional
    @Query(value = "update Table t set t.waiter=:waiter where t.id=:tableId")
    void setWaiterToTale(@Param("tableId") int tableId,
                         @Param("waiter") User waiter);


    List<Table> findAllByOpen(boolean open);

    List<Table> findAllByWaiter(User user);


    @Modifying
    @Transactional
    @Query(value = "update Table t set t.open=:status where t.id=:id")
    void updateStatus(
            @Param("status") boolean status,
            @Param("id") int id);
}
