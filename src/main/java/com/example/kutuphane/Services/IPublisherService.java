package com.example.kutuphane.Services;

import com.example.kutuphane.Dto.DtoPublisher;
import com.example.kutuphane.Dto.DtoPublisherIU;
import com.example.kutuphane.ResponseMessage.GenericResponse;

public interface IPublisherService {
        GenericResponse<?> savePublisher(DtoPublisherIU dto);
        GenericResponse<?> getAllPublishers();
        GenericResponse<?> getPublisherbyId(Integer id);
        GenericResponse<?> updatePublisher(Integer id, DtoPublisherIU dto);
        GenericResponse<?> deletePublisher(Integer id);
        GenericResponse<?> getBooksByPublisher(Integer publisherId);
}
