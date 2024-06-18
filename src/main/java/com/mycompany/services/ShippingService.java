package com.mycompany.services;

import com.mycompany.entities.Order;
import com.mycompany.entities.Payment;
import com.mycompany.entities.Shipping;
import com.mycompany.exceptions.PaymentNotFoundException;
import com.mycompany.exceptions.ShippingNotFoundException;
import com.mycompany.repositories.PaymentRepository;
import com.mycompany.repositories.ShippingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShippingService {
    @Autowired
    private ShippingRepository repo;

    public List<Shipping> listAll(){
        return (List<Shipping>) repo.findAll();
    }

    public void save(Shipping shipping) {
        repo.save(shipping);
    }

    public Shipping get(Integer id) throws ShippingNotFoundException {
        Optional<Shipping> shipping = repo.findById(id);
        if(shipping.isPresent()){
            return shipping.get();
        }
        throw new ShippingNotFoundException("Could not find any shippings with ID " + id);
    }

    public void delete(Integer id) throws ShippingNotFoundException {
        if(id == null || id == 0) {
            throw new ShippingNotFoundException("Invalid ID: " + id);
        }

        Optional<Shipping> shipping = repo.findById(id);
        if(shipping.isPresent()) {
            repo.deleteById(id);
        } else {
            throw new ShippingNotFoundException("Shipping not found with ID: " + id);
        }
    }

    public List<Shipping> pastShippings(){
        return repo.finalizedShippings();
    }
}