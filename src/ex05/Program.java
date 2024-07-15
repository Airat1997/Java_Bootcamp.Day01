import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        boolean profileUser = false;
        String profile = "";
        if (args.length > 0) {
            profile = args[0];
            String target = "--profile=dev";
            profileUser = profile.contains(target);
        }
        TransactionsService transactionsService = new TransactionsService();
        Scanner console = new Scanner(System.in);
        Menu menu = new Menu(transactionsService, console);
        menu.initMenu(profileUser);
    }
}
