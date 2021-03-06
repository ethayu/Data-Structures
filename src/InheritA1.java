import acm.graphics.GOval;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;

import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

public class InheritA1 extends GraphicsProgram {
    static final int arenaSize = 120;

    public static void main(String[] args) {
        new InheritA1().start(args);
    }

    public void run() {
        GRect rectangleRange = new GRect(arenaSize, arenaSize);
        add(rectangleRange);
        System.out.print("How many bawls?: ");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        ArrayList<Ball> balls = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            balls.add(new Ball((i + 1) * arenaSize / (n + 1), (i + 1) * arenaSize / (n + 1), 10, 10, Color.BLUE));
            add(balls.get(i));
        }
        while (true) {
            for (int i = 0; i < balls.size(); i++) {
                Ball ball = balls.get(i);
                if (ball.x - Ball.radius <= -12 || ball.x + Ball.radius >= arenaSize - 12) ball.xB();
                if (ball.y - Ball.radius <= -12 || ball.y + Ball.radius >= arenaSize - 12) ball.yB();
                for (int j = i + 1; j < balls.size(); j++) {
                    Ball otherball = balls.get(j);
                    if (Ball.isColliding(ball, otherball)) {
                        ball.r = 2;
                        otherball.r = 2;
                        double tangent1 = Math.atan((ball.getY() - otherball.getY())/(ball.getX() - otherball.getX())) * 180 / Math.PI + 90;
                        double tangent2 = tangent1 + 180;
                        System.out.println(tangent1);
                        if (ball.theta < tangent1 + 90) {
                            ball.theta = Math.toIntExact(Math.round(tangent2 - ball.theta + tangent1));
                            while (Ball.isColliding(ball, otherball)) ball.move(0.1);
                        } else {
                            ball.theta = Math.toIntExact(Math.round(tangent1 + tangent2 - ball.theta));
                            while (Ball.isColliding(ball, otherball)) ball.move(0.1);
                        }
                        if (otherball.theta < tangent1 + 90) otherball.theta = Math.toIntExact(Math.round(tangent2 - otherball.theta + tangent1));
                        else otherball.theta = Math.toIntExact(Math.round(tangent1 + tangent2 - otherball.theta));
                    }
                }
                ball.move(1);
                pause(1);
            }
        }
    }

    static class Ball extends GOval {
        public static final int radius = 12;
        int theta = (int) (Math.random() * 360), x = Math.toIntExact(Math.round(getX())), y = Math.toIntExact(Math.round(getY())), r = 1;


        public void xB() {
                theta = 180 - theta;
                theta %= 360;
                if (theta == 0 || theta == 180) theta = (int) (Math.random() * 360);
                if (x < arenaSize / 2) x = 1;
                else x = arenaSize - 1;
        }

        public void yB() {
                theta *= -1;
                theta %= 360;
                if (theta == 90 || theta == 270) theta = (int) (Math.random() * 360);
                if (y < arenaSize / 2) y = 1;
                else y = arenaSize - 1;
        }

        public Ball(double a, double b, double v, double v1, Color c) {
            super(a, b, v, v1);
            setFilled(true);
            setColor(c);
        }

        public void move(double a) {
            movePolar(a, theta);
            x = Math.toIntExact(Math.round(getX()));
            y = Math.toIntExact(Math.round(getY()));
        }

        static boolean isColliding(Ball a, Ball b) {
            return (a.getX() - b.getX()) * (a.getX() - b.getX()) + (a.getY() - b.getY()) * (a.getY() - b.getY()) <= 4 * radius * radius;
        }
    }
}
