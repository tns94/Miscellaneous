package com.filetest.multipartupload;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
/*
Multipart Reciever Rest Endpoint
 */
@RestController
public class Controller {

    @PostMapping("/uploadFile")
    public String uploadFile(@RequestParam("file") MultipartFile file,@RequestParam("id") String id)throws Exception{

        File fil=new File("output.txt");
        file.transferTo(fil);

        return file.getOriginalFilename()+" Recieved";
    }
}
