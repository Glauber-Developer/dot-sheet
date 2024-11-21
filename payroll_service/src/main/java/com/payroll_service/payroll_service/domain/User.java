package com.payroll_service.payroll_service.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String cargo;
    private String login;
    private String senha;
    private long workedHours;
    private double hourlyRate;
    private double totalPay;

    public User() {
    }

    public User(String nome, String cargo, String login, String senha, double hourlyRate) {
        this.nome = nome;
        this.cargo = cargo;
        this.login = login;
        this.senha = senha;
        this.hourlyRate = hourlyRate;
        this.totalPay = 0.0;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public long getWorkedHours() {
        return workedHours;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setTotalPay(double totalPay) {
        this.totalPay = totalPay;
    }

    public double getTotalPay() {
        return totalPay;
    }

    public void setWorkedHours(long workedHours) {
        this.workedHours = workedHours;
    }
}