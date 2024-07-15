import java.util.ArrayList;

public class UsersArrayList implements UsersList {

    public ArrayList<User> repository;

    public UsersArrayList() {
        this.repository = new ArrayList<>();
    }

    @Override
    public void add(User user) {
        repository.add(user);
    }

    @Override
    public User getById(int id) {
        for (User c : repository) {
            if (c.getId() == id) {
                return c;
            }
        }
        throw new UserNotFoundException("UserNotFoundException!");
    }

    @Override
    public User getByIndex(int index) {
        return repository.get(index);
    }

    @Override
    public int getNumbersOfUsers(UsersArrayList usersArrayList) {
        return repository.size();
    }
    @Override
    public int length(){
        return repository.size();
    }
}
