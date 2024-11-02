package br.com.intellistocks.api.Ai;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

@Service
public class AiService {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private RestTemplate restTemplate;

    private static final String QUEUE_NAME = "ai_prediction_queue";
    private List<String> responses = new ArrayList<>();

    public void sendCsvToQueue(File csvFile) {
        try {
            byte[] fileContent = Files.readAllBytes(csvFile.toPath());
            rabbitTemplate.convertAndSend(QUEUE_NAME, fileContent);
            System.out.println("File sent to queue - RabbitMQ.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RabbitListener(queues = QUEUE_NAME)
    public void receiveAndProcessCsv(byte[] fileContent) {
        try {
            File tempFile = File.createTempFile("uploaded", ".csv");
            Files.write(tempFile.toPath(), fileContent);

            String url = "http://127.0.0.1:5000/prever";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);

            FileSystemResource fileResource = new FileSystemResource(tempFile);
            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            body.add("file", fileResource);
            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

            ResponseEntity<String> response = restTemplate.postForEntity(url, requestEntity, String.class);
            System.out.println("Ai response: " + response.getBody());

            responses.add(response.getBody());

            tempFile.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<String> getResponses() {
        return responses;
    }
}