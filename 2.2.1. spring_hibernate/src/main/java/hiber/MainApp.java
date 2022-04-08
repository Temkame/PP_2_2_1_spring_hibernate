package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.NoResultException;
import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user1 = new User("Artem", "Fokin", "Tema@yandex.com");
      User user2 = new User("Sergey", "Lomov", "Serg@yandex.com");
      User user3 = new User("Ilya", "Kamolov", "Ilya@yandex.com");
      User user4 = new User("Evgeniy", "Karpov", "Evgen@yandex.com");

      Car car1 = new Car("Priora", 2170);
      Car car2 = new Car("Vesta", 2180);
      Car car3 = new Car("Kalina", 1117);
      Car car4 = new Car("Granta", 2190 );

      userService.add(user1.setCar(car1).setUser(user1));
      userService.add(user2.setCar(car2).setUser(user2));
      userService.add(user3.setCar(car3).setUser(user3));
      userService.add(user4.setCar(car4).setUser(user4));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println(user + " " + user.getCar());
         System.out.println("1. _____________________________________________");
      }

      System.out.println(userService.getUserCar("Kalina", 1117));
      System.out.println("2. _____________________________________________");

      try {
         User notFoundUser = userService.getUserCar("Berry", 1190);
      } catch (NoResultException e) {
         System.out.println("User not found");
         System.out.println("3. _____________________________________________");
      }

      context.close();
   }
}
