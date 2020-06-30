package com.fine.logflashdb.Util.storage;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Service
public class FileSystemStorageService implements StorageService {

    private final Path rootLocation;

    public FileSystemStorageService() {
        this.rootLocation = Paths.get("upload-url");

        try {
            if (Files.notExists(this.rootLocation))
                Files.createDirectory(this.rootLocation);
        } catch (IOException e) {
            throw new StorageFileNotFoundException("Create dir failed:" + e.toString(), e);
        }
    }

    @Override
    public String store(MultipartFile file, String name) {
        String originalFilename = file.getOriginalFilename();
        String[] names = originalFilename.split("\\.");
        String extensionName = names[names.length - 1];
        try {
            String fileName = name + "." + extensionName;
            Path path = this.rootLocation.resolve(fileName);
            if (file.isEmpty())
                throw new StorageException("Failed to store empty file " + fileName);
            if (!Files.notExists(path))
                Files.delete(path);
            Files.copy(file.getInputStream(), path);
        } catch (IOException e) {
            throw new StorageException("Failed to store file " + originalFilename, e);
        }
        return name + "." + extensionName;
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.rootLocation, 1)
                    .filter(path -> !path.equals(this.rootLocation))
                    .map(this.rootLocation::relativize);
        } catch (IOException e) {
            throw new StorageException("Failed to read stored files", e);
        }

    }

    @Override
    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new StorageFileNotFoundException("Could not read file: " + filename);

            }
        } catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    @Override
    public void init() {
        try {
            Files.createDirectory(rootLocation);
        } catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }
}
