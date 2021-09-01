package com.codegym.controller;

import com.codegym.model.ClassRoom;
import com.codegym.model.Student;
import com.codegym.service.IClassService;
import com.codegym.service.IStudentService;
import com.codegym.validate.StudentValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Locale;

@Controller
public class StudentController {

    @Autowired
    private IStudentService iStudentService;

    @Autowired
    private IClassService iClassService;

    @Autowired
    private StudentValidate studentValidate;

    @Autowired
    private MessageSource messageSource;

    @ModelAttribute("class")
    private Iterable<ClassRoom> classRooms() {
        return iClassService.findAll();
    }


    @GetMapping("/admin")
    public ModelAndView homeAdmin(@PageableDefault(value = 5) Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("homeAdmin");
        Page<Student> students = iStudentService.fillAll(pageable);
        modelAndView.addObject("list", students);
        String name = messageSource.getMessage("Name" , null ,Locale.ENGLISH);
        modelAndView.addObject("Name" , name);
        return modelAndView;
    }

    @GetMapping("/user")
    public ModelAndView homeUser(@PageableDefault(value = 5) Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("homeUser");
        Page<Student> students = iStudentService.fillAll(pageable);
        modelAndView.addObject("list", students);
        String name = messageSource.getMessage("Name" , null ,Locale.ENGLISH);
        modelAndView.addObject("Name" , name);
        return modelAndView;
    }

    @GetMapping("/error")
    public ModelAndView error() {
        ModelAndView modelAndView = new ModelAndView("404");
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreate() {
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("student", new Student());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView create(@Valid @ModelAttribute Student student, BindingResult bindingResult) {
        studentValidate.validate(student, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("create");
            return modelAndView;
        }
        iStudentService.save(student);
        ModelAndView modelAndView = new ModelAndView("homeAdmin");
        modelAndView.addObject("student", new Student());
        return  new ModelAndView("redirect:/admin");
    }

    @GetMapping("/findByName")
    public ModelAndView findByName(@RequestParam String findName) {
        ModelAndView modelAndView = new ModelAndView("homeAdmin");
        modelAndView.addObject("list", iStudentService.findAllByName(findName));
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEdit(@PathVariable int id) {
        Student student = iStudentService.findById(id).get();
        ModelAndView modelAndView = new ModelAndView("edit");
        modelAndView.addObject("student", student);
        return modelAndView;
    }

    @PostMapping("/edit")
    public ModelAndView edit(@Valid @ModelAttribute Student student, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("edit");
            modelAndView.addObject("student", student);
            return modelAndView;
        }
        iStudentService.save(student);
         return  new ModelAndView("redirect:/admin");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView showDelete(@PathVariable int id) {
        Student student = iStudentService.findById(id).get();
        ModelAndView modelAndView = new ModelAndView("delete");
        modelAndView.addObject("student", student);
        return modelAndView;
    }

    @PostMapping("/delete")
    public ModelAndView delete(@ModelAttribute Student student) {
        iStudentService.delete(student.getId());
        return  new ModelAndView("redirect:/admin");    }

    @GetMapping("/view/{id}")
    public ModelAndView view(@PathVariable int id) {
        Student student = iStudentService.findById(id).get();
        ModelAndView modelAndView = new ModelAndView("details");
        modelAndView.addObject("student", student);
        return modelAndView;
    }

}
