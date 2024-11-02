package br.com.intellistocks.api.Ai;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/ai")
public class AiController {
    @Autowired
    private AiService aiService;

    @PostMapping("/predict")
    public ResponseEntity<String> sentCsv(@RequestParam("file") MultipartFile file) {
        try {
            File tempFile = convertMultipartFileToFile(file);

            aiService.sendCsvToQueue(tempFile);

            tempFile.delete();

            return ResponseEntity.ok("Csv sent to queue RabbitMQ.");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error to process the file.");
        }
    }

    @GetMapping("/responses")
    public ResponseEntity<List<String>> getResponses() {
        List<String> respostas = aiService.getResponses();
        return ResponseEntity.ok(respostas);
    }

    private File convertMultipartFileToFile(MultipartFile file) throws IOException {
        File convFile = new File(System.getProperty("java.io.tmpdir") + "/" + file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }
}
