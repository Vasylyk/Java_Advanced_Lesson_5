import service.UserService;
import service.impl.UserServiceImpl;

public class testMain {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
//        userService.create(new User("someEmail", "Vadim", "Vasylyk", "creator"));
        System.out.println(userService.read(1));
//        userService.update(1, new User("anotherEmail", "Vasyl", "Holova", "x"));
//        userService.create(new User("asdsa", "afa", "dfsv", "fsdgew"));
        System.out.println(userService.readAll());
//        userService.delete(2);

    }
}
