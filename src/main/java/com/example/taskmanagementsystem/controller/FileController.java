package com.example.taskmanagementsystem.controller;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.taskmanagementsystem.dto.ApiResponse;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/files")
public class FileController {

    @PostMapping(
            value = "/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
public ApiResponse<String> uploadFile(
        @RequestParam("file")
        MultipartFile file
) throws IOException {

  if(file.isEmpty())
{
    throw new RuntimeException(
        "File is empty"
    );
}
if(file.getSize() >
        5 * 1024 * 1024)
{
    throw new RuntimeException(
            "File too large"
    );
}
    String fileName =
            file.getOriginalFilename();

    Path path =
            Paths.get(
                    "uploads", // before this also check if this path exists or not . for now i skip beacuse i have this folder.
                    fileName
            );

    // Files.write(
    //         path,                    //instead this use .copy
    //         file.getBytes()
    // );
    Files.copy(
        file.getInputStream(),
        path
);

    return new ApiResponse<>(
         true,
                "File uploaded successfully",
                fileName
    );
}
}