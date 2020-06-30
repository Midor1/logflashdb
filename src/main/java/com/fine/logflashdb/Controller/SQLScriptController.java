package com.fine.logflashdb.Controller;

import com.fine.logflashdb.Util.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@RestController
@RequestMapping("/SQL")
public class SQLScriptController {

    private final StorageService storageService;
    private final DataSource dataSource;

    @Autowired
    public SQLScriptController(StorageService storageService, DataSource dataSource) {
        this.storageService = storageService;
        this.dataSource = dataSource;
    }

    // Temporary feature.
    @PostMapping(value = "/", consumes = "multipart/form-data")
    public String executeUploadedSQL(@RequestParam("file") MultipartFile file) throws SQLException {
        String fileName = storageService.store(file, "anomalies");
        Connection conn = dataSource.getConnection();
        Resource script = new FileSystemResource(storageService.load(fileName));
        EncodedResource encodedScript = new EncodedResource(script);
        ScriptUtils.executeSqlScript(conn, encodedScript);
        DataSourceUtils.releaseConnection(conn, dataSource);
        return "OK";
    }


}
