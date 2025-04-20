package com.example.kutuphane.Controllers;

import com.example.kutuphane.Dto.DtoPublisherIU;
import com.example.kutuphane.Entities.Publisher;
import com.example.kutuphane.ResponseMessage.GenericResponse;

public interface IPublisherController {
    GenericResponse<?> savePublisher(DtoPublisherIU dto);
    GenericResponse<?> getAllPublishers();
    GenericResponse<?> getPublisherbyId(Integer id);
    GenericResponse<?> updatePublisher(Integer id, DtoPublisherIU dto);
    GenericResponse<?> deletePublisher(Integer id);
    GenericResponse<?> getBooksByPublisher(Integer publisherId);
}
