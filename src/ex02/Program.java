public class Program {

    public static void main(String[] args) {
        UsersArrayList repository = new UsersArrayList();
        User john = new User("John", 22);
        User mike = new User("Mike", 34);
        repository.add(john);
        repository.add(mike);
        System.out.println(repository.getById(0).getName());
        System.out.println(repository.getByIndex(1).getName());
        System.out.println("size repo: " + repository.getNumbersOfUsers(repository));
        System.out.println(repository.getById(2).getName());
    }
}
