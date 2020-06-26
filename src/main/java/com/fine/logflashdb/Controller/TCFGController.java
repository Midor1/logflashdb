package com.fine.logflashdb.Controller;

import com.fine.logflashdb.Repository.TCFGRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RestController
@RequestMapping("/TCFG")
public class TCFGController {
    private final TCFGRepository tcfgRepository;
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public TCFGController(TCFGRepository tcfgRepository) {
        this.tcfgRepository = tcfgRepository;
    }

    @GetMapping("/")
    public String getTCFG() {
        return tcfgRepository.findTCFGById(1).getTCFG_json();
    }

}
