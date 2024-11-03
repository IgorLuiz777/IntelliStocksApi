package br.com.intellistocks.api.controller;

import br.com.intellistocks.api.Ai.AiService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/ai")
@Tag(name = "AI", description = "Endpoints relacionados a IA")
public class AiController {
    @Autowired
    private AiService aiService;
    @Qualifier("messageSource")
    @Autowired
    private MessageSource messageSource;

    @PostMapping("/predict")
    public ResponseEntity<String> sentCsv(@RequestParam("file") MultipartFile file, Locale locale) {
        try {
            File tempFile = convertMultipartFileToFile(file);

            aiService.sendCsvToQueue(tempFile);

            tempFile.delete();

            String successMessage = messageSource.getMessage("predict.Ok", null, locale);
            return ResponseEntity.ok(successMessage);
        } catch (IOException e) {
            e.printStackTrace();
            String errorMessage = messageSource.getMessage("predict.Error", null, locale);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
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
