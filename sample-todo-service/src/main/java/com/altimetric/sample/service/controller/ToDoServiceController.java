package com.altimetric.sample.service.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.altimetric.sample.service.domain.ToDoTask;
import com.altimetric.sample.service.domain.ToDoTaskResult;

@RestController
@RequestMapping("/to-do-app")
public class ToDoServiceController {

    @Value("${reply.message}")
    private String message;
    
    private List<ToDoTask> taskList=new ArrayList<ToDoTask>();
    
    @RequestMapping(value = "tasks", method = RequestMethod.POST)
    public ResponseEntity<ToDoTaskResult> addTask(@RequestBody ToDoTask task) {
    	taskList.add(task);
     	
    	ToDoTaskResult result = new ToDoTaskResult("Task added successfully.");
        return new ResponseEntity<ToDoTaskResult>(result, HttpStatus.OK);
    }

    
    @RequestMapping(value = "tasks", method = RequestMethod.GET)
    public ResponseEntity<List<ToDoTask>> listTasks() {
     	
        return new ResponseEntity<List<ToDoTask>>(taskList, HttpStatus.OK);
    }
    
    
    @RequestMapping(value = "config", method = RequestMethod.GET)
    public ResponseEntity<String> getConfig() {
     	
        return new ResponseEntity<String>(message, HttpStatus.OK);
    }
 
}
