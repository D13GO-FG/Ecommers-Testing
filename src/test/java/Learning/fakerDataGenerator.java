package Learning;

import com.github.javafaker.Faker;
import org.testng.annotations.Test;

public class fakerDataGenerator {
    @Test
    public void testGenerateDummyData(){
        Faker faker = new Faker();
        String fullName = faker.name().fullName();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();

        String userName = faker.name().username();
        String password = faker.internet().password();

        String phoneNumber = faker.phoneNumber().cellPhone();
        String email = faker.internet().safeEmailAddress();

        System.out.println("Full name: " + fullName);
        System.out.println("First name: " + firstName);
        System.out.println("Last name: " + lastName);
        System.out.println("User name:" + userName);
        System.out.println("Password: " + password);
        System.out.println("Phone: " + phoneNumber);
        System.out.println("Email: " + email);
    }
}
