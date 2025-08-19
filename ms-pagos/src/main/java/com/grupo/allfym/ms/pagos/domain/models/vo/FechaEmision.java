package com.grupo.allfym.ms.pagos.domain.models.vo;

import java.time.LocalDateTime;

public class FechaEmision {
    private int dia;
    private int mes;
    private int anio;

    public FechaEmision(int dia, int mes, int anio) {
        this.dia = dia;
        this.mes = mes;
        this.anio = anio;
    }

    public FechaEmision() {
        this.dia = LocalDateTime.now().getDayOfMonth();
        this.mes = LocalDateTime.now().getMonthValue();
        this.anio = LocalDateTime.now().getYear();
    }

    public FechaEmision(LocalDateTime fecha) {
        this.dia = fecha.getDayOfMonth();
        this.mes = fecha.getMonthValue();
        this.anio = fecha.getYear();
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }
}
