package com.health.service;

import java.io.IOException;

/**
 * @author YueLiMin
 * @version 1.0.0
 * @since 11
 */
public interface IFileService {
    String imageUpload(String fileName, byte[] image) throws IOException;
}
