package com.example.kutuphane.Services.Impl;

import com.example.kutuphane.Entities.Book;
import com.example.kutuphane.Entities.Publisher;
import com.example.kutuphane.Repositories.BookRepository;
import com.example.kutuphane.Repositories.PublisherRepository;
import com.example.kutuphane.ResponseMessage.Constants;
import com.example.kutuphane.ResponseMessage.GenericResponse;
import com.example.kutuphane.Services.IPublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class PublisherServiceImpl implements IPublisherService {
    @Autowired
   private PublisherRepository publisherRepository;
    @Autowired
   private  BookRepository bookRepository;

    @Override
    public GenericResponse<?> savePublisher(Publisher publisher) {
       if(publisher.getId() != null&& publisherRepository.existsById(publisher.getId())) {
           return GenericResponse.error(Constants.FOUND_ID);
       }
       Publisher saved=publisherRepository.save(publisher);
       return GenericResponse.success(saved);
    }

    @Override
    public GenericResponse<?> getAllPublishers() {
        List<Publisher> publishers=publisherRepository.findAll();
        if(publishers.isEmpty()){
           return GenericResponse.error(Constants.EMPTY_LIST);
        }else{
            return GenericResponse.success(publishers);
        }


    }

    @Override
    public GenericResponse<?> getPublisherbyId(Integer id) {
        Optional<Publisher> optional=publisherRepository.findById(id);
        if(optional.isPresent()){
            return GenericResponse.success(optional.get());
        }else{
            return GenericResponse.error(Constants.EMPTY_ID);
        }

    }

    @Override
    public GenericResponse<?> updatePublisher(Integer id, Publisher updatedPublisher) {
        Optional<Publisher> optional=publisherRepository.findById(id);
        if(optional.isPresent()){
            Publisher dbPublisher=optional.get();
            dbPublisher.setAd(updatedPublisher.getAd());

            Publisher updated=publisherRepository.save(dbPublisher);
            return GenericResponse.success(updated);
        }else{
            return GenericResponse.error(Constants.EMPTY_ID);
        }

    }

    @Override
    public GenericResponse<?> deletePublisher(Integer id) {
        Optional<Publisher> optional=publisherRepository.findById(id);
        if(optional.isPresent()){
            publisherRepository.delete(optional.get());
            return GenericResponse.success("YayınEvi başarıyla Silindi");
        }else {
            return GenericResponse.error(Constants.EMPTY_ID);
        }
    }

    @Override
    public GenericResponse<?> getBooksByPublisher(Integer publisherId) {
        if (!publisherRepository.existsById(publisherId)) {
            return GenericResponse.error(Constants.EMPTY_ID);
        }
        List<Book> books = bookRepository.findByPublisherId(publisherId);
        if (books.isEmpty()) {
        return GenericResponse.error("Bu yayınevine ait kitap Bulunamadı");
        } else {
            return GenericResponse.success(books);
        }
    }
}
