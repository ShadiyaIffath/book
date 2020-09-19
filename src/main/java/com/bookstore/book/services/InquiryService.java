package com.bookstore.book.services;


import com.bookstore.book.dto.CreateInquiryDto;
import com.bookstore.book.entities.Inquiry;
import com.bookstore.book.repositories.InquiryRepository;
import com.bookstore.book.utils.Role;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InquiryService {

    @Autowired
    private InquiryRepository inquiryRepository;

    @Autowired
    private ModelMapper modelMapper;

    public void saveInquiry(CreateInquiryDto createInquiryDto) {
        Inquiry inquiry = modelMapper.map(createInquiryDto, Inquiry.class);
        inquiryRepository.save(inquiry);
    }
}
