package com.nttdata.desafio.dto;

public class AddressResponseDto {

    private String zipcode;
    private String address;
    private String neighborhood;
    private String city;
    private String state;

    public AddressResponseDto() {
    }

    public AddressResponseDto(String zipcode, String address, String neighborhood, String city, String state) {
        this.zipcode = zipcode;
        this.address = address;
        this.neighborhood = neighborhood;
        this.city = city;
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
