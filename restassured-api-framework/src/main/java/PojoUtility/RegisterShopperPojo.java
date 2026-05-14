package PojoUtility;

public class RegisterShopperPojo {

    private String city;
    private String country;
    private String email;
    private String firstName;
    private String gender;
    private String lastName;
    private String password;
    private String phone;
    private String state;

    public RegisterShopperPojo(String city,String country,String email,
            String firstName,String gender,String lastName,
            String password,String phone,String state) {

        this.city=city;
        this.country=country;
        this.email=email;
        this.firstName=firstName;
        this.gender=gender;
        this.lastName=lastName;
        this.password=password;
        this.phone=phone;
        this.state=state;
    }
    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getGender() {
        return gender;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public String getState() {
        return state;
    }
}