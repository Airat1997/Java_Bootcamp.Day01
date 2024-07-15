public class Program {

    public static void main(String[] args) {
        User user = new User("John", 100);
        User user1 = new User("Mike", 100);
        User user2 = new User("Dick", 100);
        System.out.format("id:%s, name:%s\n",user.getId(), user.getName());
        System.out.format("id:%s, name:%s\n",user1.getId(), user1.getName());
        System.out.format("id:%s, name:%s\n",user2.getId(), user2.getName());
    }
}
