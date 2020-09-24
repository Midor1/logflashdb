package com.fine.logflashdb.Controller;

import com.fine.logflashdb.Entity.Anomaly;
import com.fine.logflashdb.Repository.AnomalyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@RestController
@RequestMapping("/Anomaly")
public class AnomalyController {

    private final AnomalyRepository anomalyRepository;
    private final RestTemplate restTemplate;

    @Value("${logflash.url}")
    private String url;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public AnomalyController(RestTemplateBuilder restTemplateBuilder, AnomalyRepository anomalyRepository) {
        this.anomalyRepository = anomalyRepository;
        restTemplate = restTemplateBuilder.build();
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

    @PostMapping("/")
    public String postAnomalyIDs(@RequestBody List<Integer> IDs) {
        for (int id: IDs) {
            Anomaly posted = anomalyRepository.findAnomalyById(id);
            posted.setTagged(true);
            anomalyRepository.save(posted);
        }
        return restTemplate.postForObject(this.url + "/Anomaly", IDs, String.class);
    }

    @PostMapping("/{id}/")
    public String postAnomalyID(@PathVariable int id) {
        Anomaly posted = anomalyRepository.findAnomalyById(id);
        posted.setTagged(true);
        anomalyRepository.save(posted);
        return restTemplate.postForObject(this.url + "/Anomaly/" + id, null, String.class);
    }

}
