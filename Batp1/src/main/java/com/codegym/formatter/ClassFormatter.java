package com.codegym.formatter;


import com.codegym.model.ClassRoom;
import com.codegym.service.IClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;
import java.util.Optional;

public class ClassFormatter implements Formatter<ClassRoom> {
    private IClassService iClassService;

    @Autowired
    public ClassFormatter(IClassService classService) {
        this.iClassService = classService;
    }

    @Override
    public ClassRoom parse(String text, Locale locale) throws ParseException {
        Optional<ClassRoom> branchOptional = iClassService.findById(Integer.parseInt(text));
        return branchOptional.orElse(null);
    }

    @Override
    public String print(ClassRoom object, Locale locale) {
        return "[" + object.getId() + "," + object.getName() + "]";
    }
}
