package PojoUtility;

public class UpdateShopperPojo 
{

	    private String city;
	    private String country;
	    private String firstName;
	    private String lastName;
	    private String phone;
	    private String state;

	    public UpdateShopperPojo(String city, String country, String firstName,
	                             String lastName, String phone, String state)
	    {
	        this.city = city;
	        this.country = country;
	        this.firstName = firstName;
	        this.lastName = lastName;
	        this.phone = phone;
	        this.state = state;
	    }

	    public String getCity() { return city; }
	    public String getCountry() { return country; }
	    public String getFirstName() { return firstName; }
	    public String getLastName() { return lastName; }
	    public String getPhone() { return phone; }
	    public String getState() { return state; }
	}

