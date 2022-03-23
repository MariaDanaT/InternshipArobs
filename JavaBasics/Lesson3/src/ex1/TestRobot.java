package ex1;

public class TestRobot {
    public static void main(String []args){
        Robot robot = new Robot();
        System.out.println(robot.toString().equals("1"));
        robot.change(3);
        System.out.println(robot.toString().equals("4"));
        robot.change(-1);
        System.out.println(robot.toString().equals("3"));
    }
}

