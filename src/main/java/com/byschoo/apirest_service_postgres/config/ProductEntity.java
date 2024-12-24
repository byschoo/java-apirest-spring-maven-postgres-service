package com.byschoo.apirest_service_postgres.config;

import java.time.LocalDate;
import java.time.Period;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;


@Entity
@Table(name="Products")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //unique: Indica si los valores en la columna deben ser únicos en la tabla.
    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "price")
    private double price;

    @Column(name = "date")
    private LocalDate date;

    //@Transient Marca un atributo de la clase como no persistente, lo que significa que no se mapeará a una columna.
    //Este campo se ignorará durante las operaciones de guardar y recuperar una entidad de la base de datos.
    @Transient
    private int antiquity;

    public ProductEntity() {
    }

    public ProductEntity(Long id, String name, double price, LocalDate date) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.date = date;
    }
    
    public ProductEntity(String name, double price, LocalDate date) {
        this.name = name;
        this.price = price;
        this.date = date;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public int getAntiquity() {
        return Period.between(this.date,LocalDate.now()).getYears();
    }
    public void setAntiquity(int antiquity) {
        this.antiquity = antiquity;
    }

    
}
