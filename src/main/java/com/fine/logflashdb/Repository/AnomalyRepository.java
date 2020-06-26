package com.fine.logflashdb.Repository;

import com.fine.logflashdb.Entity.Anomaly;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface AnomalyRepository extends PagingAndSortingRepository<Anomaly, Long> {
    Page<Anomaly> findAllBy(Pageable pageable);

    Anomaly findAnomalyById(int id);
}
