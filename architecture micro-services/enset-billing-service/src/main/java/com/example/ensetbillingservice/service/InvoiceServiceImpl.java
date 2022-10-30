package com.example.ensetbillingservice.service;

import com.example.ensetbillingservice.dto.InvoiceRequestDTO;
import com.example.ensetbillingservice.dto.InvoiceResponseDTO;
import com.example.ensetbillingservice.entities.Customer;
import com.example.ensetbillingservice.entities.Invoice;
import com.example.ensetbillingservice.mappers.InvoiceMapper;
import com.example.ensetbillingservice.openfeign.CustomerRestClient;
import com.example.ensetbillingservice.repositories.InvoiceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class InvoiceServiceImpl implements InvoiceService {
    private InvoiceRepository invoiceRepository;
    private InvoiceMapper invoiceMapper;
    private CustomerRestClient customerRestClient;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, InvoiceMapper invoiceMapper, CustomerRestClient customerRestClient) {
        this.invoiceRepository = invoiceRepository;
        this.invoiceMapper = invoiceMapper;
        this.customerRestClient = customerRestClient;
    }

    @Override
    public InvoiceResponseDTO save(InvoiceRequestDTO invoiceRequestDTO) {
        Customer customer=null;
        try {
            customer=customerRestClient.getCustomer(invoiceRequestDTO.getCustomerId());
        }catch (Exception e){
           throw new CustomerNotFoundException("Customer Not Found");
        }
        Invoice invoice=invoiceMapper.fromInvoiceRequestDTO(invoiceRequestDTO);
        invoice.setId(UUID.randomUUID().toString());
        invoice.setDate(new Date());
        Invoice saveInvoice=invoiceRepository.save(invoice);
        saveInvoice.setCustomer(customer);
        return invoiceMapper.fromInvoice(saveInvoice);
    }

    @Override
    public InvoiceResponseDTO getInvoice(String invoiceId) {
        Invoice invoice=invoiceRepository.findById(invoiceId).get();
        if(invoice.getCustomerId()!=null){
            Customer customer=customerRestClient.getCustomer(invoice.getCustomerId());
            invoice.setCustomer(customer);
        }
        return invoiceMapper.fromInvoice(invoice);
    }

    @Override
    public List<InvoiceResponseDTO> invoivesByCustomer(String customerId) {
        List<Invoice> invoices=invoiceRepository.
                findByCustomerId(customerId);
        for (Invoice invoice: invoices){
            if(invoice.getCustomerId()!=null){
                Customer customer=customerRestClient.getCustomer(invoice.getCustomerId());
                invoice.setCustomer(customer);
            }

        }
        return invoices.stream().
                map(invoice -> invoiceMapper.
                        fromInvoice(invoice)).
                collect(Collectors.toList());
    }

    @Override
    public List<InvoiceResponseDTO> allInvoives() {
        List<Invoice> invoices=invoiceRepository.findAll();
        for (Invoice invoice: invoices){
            if(invoice.getCustomerId()!=null){
                Customer customer=customerRestClient.getCustomer(invoice.getCustomerId());
                invoice.setCustomer(customer);
            }

        }
        return invoices.stream().map(
                inv->invoiceMapper.fromInvoice(inv))
                .collect(Collectors.toList());
    }
}
