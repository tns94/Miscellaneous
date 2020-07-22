package com.filetest.multipartupload;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/*
Sending Multipart File to RestPoint without creating file on disk;
 */
public class Sender {
    public void upload()throws Exception
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        String contentToWrite="Example File Content";


        FileNameAwareByteArrayResource fr=new FileNameAwareByteArrayResource("testBA.txt",contentToWrite.getBytes(),"This is description");

        MultiValueMap<String, Object> body
                = new LinkedMultiValueMap<>();
        body.add("file", fr);
        body.add("id","ID1");

        HttpEntity<MultiValueMap<String, Object>> requestEntity
                = new HttpEntity<>(body, headers);

        String serverUrl = "http://localhost:8080/uploadFile";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate
                .postForEntity(serverUrl, requestEntity, String.class);

        System.out.println(response.getBody());

    }
    class FileNameAwareByteArrayResource extends ByteArrayResource {

        private String fileName;

        public FileNameAwareByteArrayResource(String fileName, byte[] byteArray, String description) {
            super(byteArray, description);
            this.fileName = fileName;
        }

        @Override
        public String getFilename() {
            return fileName;
        }
    }
    public static void main(String[] args) throws Exception{
        new Sender().upload();
    }
}

