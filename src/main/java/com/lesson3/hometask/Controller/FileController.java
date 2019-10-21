package com.lesson3.hometask.Controller;

import com.google.gson.Gson;
import com.lesson3.hometask.Model.File;
import com.lesson3.hometask.Service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

@Controller
@Component
public class FileController{
    private Service service;

    @Autowired
    public FileController(Service service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/test")
    public @ResponseBody
    String test(HttpServletRequest req) {
        return "ok";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/fileSave")
    public @ResponseBody
    String save(HttpServletRequest req) throws IOException {
        return service.save(readValuesPostman(req), Integer.parseInt(req.getParameter("id"))).toString();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/fileFind")
    public @ResponseBody
    String findById(HttpServletRequest req) throws IOException {
        return service.findById(File.class, Integer.parseInt(req.getParameter("id"))).toString();
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/fileDelete")
    public @ResponseBody
    String delete(HttpServletRequest req) throws IOException {
        service.delete(File.class, Integer.parseInt(req.getParameter("id")));
        return service.findById(File.class, Integer.parseInt(req.getParameter("id"))).toString();
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/fileUpdate")
    public @ResponseBody
    String update(HttpServletRequest req) throws IOException {
        service.update(readValuesPostman(req));
        return readValuesPostman(req).toString();
    }

    private File readValuesPostman(HttpServletRequest req) throws IOException {
        File file;
        try(BufferedReader reader = req.getReader()) {
            Gson gson = new Gson();
            file = gson.fromJson(reader, File.class);
        }
        return file;
    }
}
