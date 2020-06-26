package com.fine.logflashdb.Controller;

import com.fine.logflashdb.Entity.Anomaly;
import com.fine.logflashdb.Repository.AnomalyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RestController
@RequestMapping("/Anomaly")
public class AnomalyController {
    private final AnomalyRepository anomalyRepository;
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public AnomalyController(AnomalyRepository anomalyRepository) {
        this.anomalyRepository = anomalyRepository;
    }

    @GetMapping("/")
    public Page<Anomaly> getAnomalies(@RequestParam int page, @RequestParam int size) {
        return anomalyRepository.findAllBy(PageRequest.of(page, size));
    }
}
