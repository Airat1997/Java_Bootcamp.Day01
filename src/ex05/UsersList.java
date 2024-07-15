public interface UsersList {
    public void add(User user);
    public User getById(int id) throws UserNotFoundException;
    public User getByIndex(int index);
    public int getNumbersOfUsers(UsersArrayList usersArrayList);
    public int length();
}
