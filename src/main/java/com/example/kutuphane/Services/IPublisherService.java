package com.example.kutuphane.Services;

import com.example.kutuphane.Entities.Publisher;
import com.example.kutuphane.ResponseMessage.GenericResponse;

public interface IPublisherService {
        GenericResponse<?> savePublisher(Publisher publisher);
        GenericResponse<?>getAllPublishers();
        GenericResponse<?> getPublisherbyId(Integer id);
        GenericResponse<?> updatePublisher(Integer id,Publisher updatedPublisher);
        GenericResponse<?> deletePublisher(Integer id);
        GenericResponse<?> getBooksByPublisher(Integer publisherId);
}
