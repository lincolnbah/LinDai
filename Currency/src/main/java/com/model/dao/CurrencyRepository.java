package com.model.dao;

import com.model.po.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {
    List<Currency> findAll();

    List<Currency> findById(int id);

    @Query("select c from Currency c where c.id=(select max(c2.id) from Currency c2)")
    List<Currency> findLatestData();

    @Query(value = "select * from Currency c where  c.update_time = ?1", nativeQuery = true)
    List<Currency> getByDate(String dateTime);

    @Modifying
    @Transactional
    @Query(value="delete from Currency where id = ?1", nativeQuery = true)
    int deleteById(int id);

}
