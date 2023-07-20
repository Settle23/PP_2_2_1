package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        Car car1 = new Car("Porsche", 911);
        Car car2 = new Car("BMW", 5);
        Car car3 = new Car("UAZ", 2108);
        Car car4 = new Car("Jaguar", 666);

        User user1 = new User("Ivan", "Petrov", "user1@mail.ru");
        User user2 = new User("Petr", "Ivanov", "user2@mail.ru");
        User user3 = new User("Ilya", "Snegurochkin", "user3@mail.ru");
        User user4 = new User("Jane", "Air", "user4@mail.ru");


        userService.add(user1, car1);
        userService.add(user2, car2);
        userService.add(user3, car3);
        userService.add(user4, car3);

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Model = " + user.getCar().getModel());
            System.out.println("Series = " + user.getCar().getSeries());
            System.out.println();

            context.close();
        }
    }
}
