package com.fine.logflashdb.Repository;

import com.fine.logflashdb.Entity.TCFG;
import org.springframework.data.repository.CrudRepository;

public interface TCFGRepository extends CrudRepository<TCFG, Long> {
    TCFG findTCFGById(int id);
}
