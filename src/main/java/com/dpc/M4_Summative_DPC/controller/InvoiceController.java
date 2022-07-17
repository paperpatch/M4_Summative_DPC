package com.dpc.M4_Summative_DPC.controller;

import com.dpc.M4_Summative_DPC.models.Invoice;
import com.dpc.M4_Summative_DPC.service.ServiceLayer;
import com.dpc.M4_Summative_DPC.viewmodel.InvoiceViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class InvoiceController {
    @Autowired
    ServiceLayer service;

    // Get all invoice
    @RequestMapping(value = "/invoice", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Invoice> getAllInvoices() {
        return service.getAllInvoices();
    }

    @GetMapping("/invoices")
    public List<InvoiceViewModel> getAllInvoiceModels() { return service.findAllInvoices(); }

    // Get invoice by id
    @RequestMapping(value = "/invoices/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Optional<Invoice> getInvoiceById(@PathVariable int id) {
        if (service.getInvoiceById(id) == null) {
            throw new IllegalArgumentException("Does not exist.");
        }
        return service.getInvoiceById(id);
    }
    // Create Invoice
    @RequestMapping(value = "/invoices", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Invoice createInvoice(@RequestBody Invoice invoice) {
        return service.addInvoice(invoice);
    }

    // Update Invoice
    @PutMapping("/invoices")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateInvoice2(@RequestBody Invoice invoice) {
        service.updateInvoice(invoice);
    }

    //    Delete invoice
    @RequestMapping(value = "/invoices/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void destroyInvoice(@PathVariable int id) {
        service.deleteInvoice(id);
    }
}
