package Super;

public class ExtraSuper extends Super{

    public String speak() {
        return "Jestem Robocop!";
    }

    public static void main(String[] args) {
        Super human = new ExtraSuper();
        System.out.println(human.speak());
    }
}
