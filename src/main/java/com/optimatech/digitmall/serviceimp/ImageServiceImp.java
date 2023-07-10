package com.optimatech.digitmall.serviceimp;


import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.optimatech.digitmall.services.ImageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Service
public class ImageServiceImp implements ImageService {

    private final Cloudinary cloudinary;

    public ImageServiceImp(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    public String createPathUrlForImage(MultipartFile file) {
        String f = "";
        try {
            Map x = this.cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap("resource_type", "auto"));
            f = (x.get("url").toString());
        } catch (Exception e) {
            f = e.getMessage();
        }
        return f;
    }

}
