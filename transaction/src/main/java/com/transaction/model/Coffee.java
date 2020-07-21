package com.transaction.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table
@Data
public class Coffee {
    public Coffee() {
        String time = DateTimeFormatter.ofPattern("HHmmss").format(LocalDateTime.now());
        this.name = "COFFEE- " + time;
        this.loan_no = "LOAN_NO-" + DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S").format(LocalDateTime.now());
    }

    @Id
    @GeneratedValue
    private Integer id;

    private String loan_no;

    private String name;

    private Double price;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoan_no() {
        return loan_no;
    }

    public void setLoan_no(String loan_no) {
        this.loan_no = loan_no;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Coffee{" + "id=" + id + ", loan_no='" + loan_no + '\'' + ", name='" + name + '\'' + ", price=" + price
            + '}';
    }
}
