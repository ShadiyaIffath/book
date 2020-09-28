package com.bookstore.book.services;


import com.bookstore.book.dto.AccountDto;
import com.bookstore.book.dto.CreateInquiryDto;
import com.bookstore.book.dto.InquiryDto;
import com.bookstore.book.entities.Inquiry;
import com.bookstore.book.repositories.InquiryRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InquiryService {
    private static final Logger logger = LoggerFactory.getLogger(InquiryService.class);

    @Autowired
    private InquiryRepository inquiryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MailService mailService;

    public void saveInquiry(CreateInquiryDto createInquiryDto) {
        Inquiry inquiry = modelMapper.map(createInquiryDto, Inquiry.class);
        inquiryRepository.save(inquiry);
    }

    public List<InquiryDto> getAllInquiries() {
        List<InquiryDto> inquiries = inquiryRepository.findAll()
                .stream().map(x -> modelMapper.map(x, InquiryDto.class))
                .collect(Collectors.toList());
        return inquiries;
    }

    public void deleteInquiryById(int id){
        inquiryRepository.deleteById(id);
    }

    public void sendResponseEmail(int id, String response){
        Inquiry inquiry = inquiryRepository.findInquiriesById(id);
        if(inquiry != null) {
            String inquiryResponse = "Your Question: " + inquiry.getQuestion() + "\n" + "Our response: " + response;
            mailService.sendMail("Response to your inquiry", inquiry.getEmail(), inquiryResponse);
            inquiry.setResponded(true);
            inquiryRepository.save(inquiry);
        }else{
            logger.info("Inquiry with id "+ id+" didn't exist. No response mail was sent.");
        }
    }
}
