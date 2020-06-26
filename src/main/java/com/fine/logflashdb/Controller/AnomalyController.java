package com.fine.logflashdb.Controller;

import com.fine.logflashdb.Entity.Anomaly;
import com.fine.logflashdb.Repository.AnomalyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

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
    public Page<Anomaly> getAnomalies(@RequestParam(name = "page", defaultValue = "0") int page,
                                      @RequestParam(name = "size", defaultValue = "20") int size,
                                      @RequestParam(name = "sortProperty", defaultValue = "id") String sortProperty) {
        return anomalyRepository.findAllBy(PageRequest.of(page, size, Sort.by(Sort.Order.asc(sortProperty))));
    }

    @GetMapping("/{id}/")
    public Anomaly getAnomalyByID(@PathVariable int id) {
        return anomalyRepository.findAnomalyById(id);
    }
}
