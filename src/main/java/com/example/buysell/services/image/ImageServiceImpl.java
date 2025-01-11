package com.example.buysell.services.image;

import com.example.buysell.models.Image;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {


    @Override
    @SneakyThrows
    public List<Image> getListImagesFromFiles(List<MultipartFile> files) {
        var images = new ArrayList<Image>();
        for(MultipartFile file : files) {
            images.add(toImageEntity(file));
        }
        images.get(0).setPreviewImage(true);
        return images;
    }

    private Image toImageEntity(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setBytes(file.getBytes());
        return image;
    }
}
