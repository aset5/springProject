package com.example.buysell.services.image;

import com.example.buysell.models.Image;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Component
public interface ImageService {

    List<Image> getListImagesFromFiles(List<MultipartFile> files);
}
