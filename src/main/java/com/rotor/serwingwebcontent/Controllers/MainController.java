package com.rotor.serwingwebcontent.Controllers;

import com.rotor.serwingwebcontent.entity.Item;
import com.rotor.serwingwebcontent.repositories.ItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Controller
public class MainController {
    @Autowired
    private ItemRepo itemRepo;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/")
    public String greeting() {
        return "greeting";
    }
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @PostMapping("/hello")
    public String helloPage(
            @RequestParam("item_img") MultipartFile file,
            Map<String, Object> model
    ) throws IOException {
        Item itemWImg = new Item();
        if(file != null){
            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();
            file.transferTo(new File(uploadPath + "/" + resultFilename));
            File resultFile = new File(uploadPath + "/" + resultFilename);
            itemWImg.setItemName(resultFilename);
            itemRepo.save(itemWImg);
            System.out.println(resultFilename);
            model.put("itemName", resultFile.getName());
        }
        return "hello";
    }
}
