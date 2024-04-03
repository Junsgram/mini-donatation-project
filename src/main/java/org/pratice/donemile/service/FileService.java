package org.pratice.donemile.service;

import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

@Service
public class FileService {

    // 파일 등록하기
    public String uploadFile(String uploadPath, String originFile, byte[] fileData) throws Exception {
        UUID uuid = UUID.randomUUID();
        String extentsion = originFile.substring(originFile.lastIndexOf("."));
        System.out.println("extentsion 의 값 : " + extentsion);
        String saveFileName = uuid.toString() + extentsion;
        String fileUploadUrl = uploadPath + "/" + saveFileName;
        System.out.println("fileUploadUrl :" + fileUploadUrl);
        FileOutputStream fos = new FileOutputStream(fileUploadUrl);
        fos.write(fileData);
        fos.close();
        return saveFileName;
    }
    // 파일 삭제하기
}
