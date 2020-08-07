package com.danilketov.testapp.entity;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Response {
    @SerializedName("response")
    private ArrayList<Worker> response = null;

    public ArrayList<Worker> getResponse() {
        return response;
    }

    public void setResponse(ArrayList<Worker> response) {
        this.response = response;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Response response1 = (Response) o;

        return response.equals(response1.response);
    }

    @Override
    public int hashCode() {
        return response.hashCode();
    }

    @Override
    public String toString() {
        return "Response{" +
                "response=" + response +
                '}';
    }
}
