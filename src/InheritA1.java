import acm.graphics.GOval;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;

import java.awt.*;
import java.util.Scanner;

public class InheritA1 extends GraphicsProgram {
    static final int arenaSize = 500;
    public static void main(String[] args) {
        new InheritA1().start(args);
    }

    public void run(){
        GRect rectangelRange = new GRect(500, 500);
        System.out.print("How many bawls?: ");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Ball ball = new Ball(100, 100, Color.BLUE);
        while (true) {
            if (ball.getX() <= 0 || ball.getX() + Ball.radius >= arenaSize) ball.xB();
            if (ball.getY() <= 0 || ball.getY() + Ball.radius >= arenaSize) ball.yB();
            ball.move();
            pause(20);
        }
    }
    class Ball extends GOval {
        public static final int radius = 20;
        private int x = 1, y = 1;


        public void xB() {
            x *= -1;
        }

        public void yB() {
            y *= -1;
        }

        public Ball(double v, double v1, Color c) {
            super(v, v1);
            setColor(c);
        }
        public void move() {
            move(x, y);
        }
    }
}
