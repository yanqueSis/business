package com.example.veraj.aplicacionandroid;
import java.util.List;

/**
 * Created by luis on 5/02/2016.
 */
public class Busqueda {
    boolean success;
    String año;
    String mes;
    List<Dato> data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getAño() {
        return año;
    }

    public void setAño(String año) {
        this.año = año;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public List<Dato> getData() {
        return data;
    }

    public void setData(List<Dato> data) {
        data.add(new Dato());
        this.data = data;
    }


}
