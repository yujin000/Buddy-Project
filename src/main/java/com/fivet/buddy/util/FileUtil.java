package com.fivet.buddy.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.Folder;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ComponentScan(basePackages = {"com.spring.qna.util.FileUtil"})
public class FileUtil {

    List<String> sysNameList = new ArrayList<>();
    List<String> oriNameList = new ArrayList<>();
    String oriName;
    String sysName;

    public void save(@RequestParam MultipartFile file, String realPath, String sysName)throws Exception {
        File fileSavePath = new File(realPath);
        file.transferTo(new File(fileSavePath + "/" + sysName));
    }
    public void saves(@RequestParam MultipartFile[] uploadfile, String realPath, String sysName) throws Exception {
        File fileSavePath = new File(realPath);
        for (MultipartFile file : uploadfile) {
            if (!file.isEmpty()) {
                this.oriNameList.add(file.getOriginalFilename());
                this.sysNameList.add(sysName);
                // 파일 복사
                File newFileName = new File(sysName);
                file.transferTo(new File(fileSavePath + "/" + newFileName));
            }
        }
    }
    public void delete(@RequestParam String realPath , String sysName) {
        String fileSavePath = realPath;
        File file = new File(fileSavePath + "/" + sysName);
        if(file.exists()){
            file.delete();
        }
    }
    public ResponseEntity<Resource> download(@ModelAttribute String realpath, String sysName , String oriName) throws Exception {
        Path path = Path.of(realpath + "/" + sysName);
        String contentType = Files.probeContentType(path);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDisposition(ContentDisposition.builder("attachment").filename(oriName, StandardCharsets.UTF_8).build());
        headers.add(HttpHeaders.CONTENT_TYPE, contentType);
        Resource resource = new InputStreamResource(Files.newInputStream(path));
        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }

    // 폴더 삭제
    public void deleteFolder(String path) {
        File folder = new File(path);
        if (folder.exists() && folder.isDirectory()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        deleteFolder(file.getAbsolutePath());
                    } else {
                        file.delete();
                    }
                }
            }
            folder.delete();
        }
    }

    // 파일 다운로드(경로)
    public ResponseEntity<Resource> downloadByPath(@ModelAttribute String realpath, String oriName) throws Exception {
        Path path = Path.of(realpath);
        String contentType = Files.probeContentType(path);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDisposition(ContentDisposition.builder("attachment").filename(oriName, StandardCharsets.UTF_8).build());
        headers.add(HttpHeaders.CONTENT_TYPE, contentType);
        Resource resource = new InputStreamResource(Files.newInputStream(path));
        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }

    // 폴더 재귀함수
    public void addFolderToZip(ZipOutputStream zos, File folder) throws IOException {
        for (File file : folder.listFiles()) {
            if (file.isDirectory()) {
                addFolderToZip(zos, file);
            } else {
                zos.putNextEntry(new ZipEntry(file.getName()));
                Files.copy(file.toPath(), zos);
                zos.closeEntry();
            }
        }
    }

}
