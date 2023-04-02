package com.example.demo.controller;

import com.example.demo.Helper.FileUploader;
import com.example.demo.entity.StudentEnity;
import com.example.demo.repositry.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Controller
public class StudentController {
    int maxSize=5;

    @Autowired
    FileUploader uploader;
    @Autowired
    StudentRepo studentRepo;
    @GetMapping("/")
    public String welcome(){

        return "welcome";
    }



    @GetMapping("/reg/")
    public String stdlist(Model model,StudentEnity studentEnity){
        int curPage=1;

        Pageable pageable = PageRequest.of(curPage-1, maxSize, Sort.by("id").descending());
        Page<StudentEnity> page = studentRepo.findAll(pageable);
        int totalPages = page.getTotalPages();
        List<StudentEnity> listEmp = page.toList();

        if(totalPages<1)
        {
            totalPages=1;
        }

        model.addAttribute("listEmp", listEmp);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("curPage", curPage);
        return "reg";
    }


    @PostMapping("/reg/save/")
    public String save(Model model, StudentEnity studentEnity, MultipartFile file){
        int curPage=1;
        String fileNameOld = file.getOriginalFilename();

        String extension = fileNameOld.substring(fileNameOld.indexOf(".") + 1);

        studentEnity.setStuext(extension);

        StudentEnity saveStudent=studentRepo.save(studentEnity);
        String fileNameNew = saveStudent.getId() + "." + extension;

        uploader.uploadFile(file,fileNameNew);




        Pageable pageable = PageRequest.of(curPage-1, maxSize, Sort.by("id").descending());
        Page<StudentEnity> page = studentRepo.findAll(pageable);
        int totalPages = page.getTotalPages();
        List<StudentEnity> listEmp = page.toList();

        if(totalPages<1)
        {
            totalPages=1;
        }

        model.addAttribute("listEmp", listEmp);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("curPage", curPage);
        return "reg";
    }
    @GetMapping("/stu/delete/{stuid}/")
    public String delete(Model model, @PathVariable long stuid) {

        int curPage=1;
        studentRepo.deleteById(stuid);
        Pageable pageable = PageRequest.of(curPage-1, maxSize, Sort.by("id").descending());
        Page<StudentEnity> page = studentRepo.findAll(pageable);
        int totalPages = page.getTotalPages();
        List<StudentEnity> listEmp = page.toList();

        if(totalPages<1)
        {
            totalPages=1;
        }

        model.addAttribute("listEmp", listEmp);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("curPage", curPage);
        return "reg";
    }
    @GetMapping("/reg/{curPage}/")
    public String sudentdata(@PathVariable int curPage, Model model){


        Pageable pageable = PageRequest.of(curPage-1, maxSize, Sort.by("id").descending());
        Page<StudentEnity> page = studentRepo.findAll(pageable);
        int totalPages = page.getTotalPages();
        List<StudentEnity> listEmp = page.toList();

        if(totalPages<1)
        {
            totalPages=1;
        }

        model.addAttribute("listEmp", listEmp);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("curPage", curPage);
        return "reg";
    }






}
