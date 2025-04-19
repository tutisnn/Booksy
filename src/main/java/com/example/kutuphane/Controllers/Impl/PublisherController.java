package com.example.kutuphane.Controllers.Impl;

import com.example.kutuphane.Controllers.IPublisherController;
import com.example.kutuphane.Entities.Publisher;
import com.example.kutuphane.ResponseMessage.GenericResponse;
import com.example.kutuphane.Services.IPublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("rest/api/Publisher")
public class PublisherController implements IPublisherController {

    @Autowired
    private IPublisherService publisherService;

    @PostMapping("/save")
    @Override
    public GenericResponse<?> savePublisher(@RequestBody Publisher publisher) {
        return publisherService.savePublisher(publisher);
    }

    @GetMapping("/listAllPublishers")
    @Override
    public GenericResponse<?> getAllPublishers() {
        return publisherService.getAllPublishers();
    }

    @GetMapping("/list/{id}")
    @Override
    public GenericResponse<?> getPublisherbyId(@PathVariable(name = "id") Integer id) {
        return publisherService.getPublisherbyId(id);
    }

    @PutMapping("/update/{id}")
    @Override
    public GenericResponse<?> updatePublisher(@PathVariable(name = "id") Integer id,
                                              @RequestBody Publisher updatedPublisher) {
        return publisherService.updatePublisher(id, updatedPublisher);
    }

    @DeleteMapping("/delete/{id}")
    @Override
    public GenericResponse<?> deletePublisher(@PathVariable(name = "id") Integer id) {
        return publisherService.deletePublisher(id);
    }

    @GetMapping("/{publisherId}/books")
    @Override
    public GenericResponse<?> getBooksByPublisher(@PathVariable(name = "publisherId") Integer publisherId) {
        return publisherService.getBooksByPublisher(publisherId);
    }
}
