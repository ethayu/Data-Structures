import acm.graphics.GOval;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Scanner;

public class InheritA1v2 extends GraphicsProgram {
    static final int arenaSize = 500;

    public static void main(String[] args) {
        new InheritA1v2().start(args);
    }

    /**
     * runs field
     */
    public void run() {
        GRect rectangleRange = new GRect(arenaSize, arenaSize);
        add(rectangleRange);
        System.out.print("How many bawls?: ");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.print("Detect Collisions (Y/n): ");
        boolean detect = sc.next().charAt(0) == 'Y';
        ArrayList<Ball> balls = new ArrayList<>(n);
        Color[] colors = {Color.BLACK, Color.BLUE, Color.CYAN, Color.GRAY, Color.GREEN, Color.MAGENTA, Color.ORANGE, Color.PINK, Color.RED, Color.WHITE, Color.YELLOW};
        for (int i = 0; i < n; i++) {
            balls.add(new Ball((i + 1) * arenaSize / (n + 1), (i + 1) * arenaSize / (n + 1), 10, 10, colors[i % 11]));
            add(balls.get(i));
        }
        while (true) {
            for (int i = 0; i < balls.size(); i++) {
                Ball ball = balls.get(i);
                if (!ball.isVisible()) continue;
                if (ball.x - Ball.radius <= -5 || ball.x + Ball.radius >= arenaSize - 5) ball.xB();
                if (ball.y - Ball.radius <= -5 || ball.y + Ball.radius >= arenaSize - 5) ball.yB();
                for (int j = i + 1; j < balls.size(); j++) {
                    Ball otherball = balls.get(j);
                    if (!otherball.isVisible()) continue;
                    if (Ball.isColliding(ball, otherball) && detect) {
                        otherball.setVisible(false);
                        ball.theta = (int) (Math.random() * 360);
                    }
                }
                ball.move(1);
                pause(1);
            }
        }
    }

    /**
     * Ball class. Keeps track of current location in cartesian coordinates as well as angle (theta)
     */
    static class Ball extends GOval {
        public static final int radius = 5;
        int theta = (int) (Math.random() * 360), x = Math.toIntExact(Math.round(getX())), y = Math.toIntExact(Math.round(getY()));

        /**
         * action done when ball hits one of the vertical sides.
         */
        public void xB() {
            theta = 180 - theta;
            theta %= 360;
            if (theta == 0 || theta == 180) theta = (int) (Math.random() * 360);
            if (x < arenaSize / 2) x = 1;
            else x = arenaSize - 1;
        }

        /**
         * action done when ball hits one of the horizontal sides.
         */
        public void yB() {
            theta *= -1;
            theta %= 360;
            if (theta == 90 || theta == 270) theta = (int) (Math.random() * 360);
            if (y < arenaSize / 2) y = 1;
            else y = arenaSize - 1;
        }

        /**
         * Ball constructor that creates GOval and sets location, size, color based on input and sets Fill to true.
         * @param a
         * @param b
         * @param v
         * @param v1
         * @param c
         */
        public Ball(double a, double b, double v, double v1, Color c) {
            super(a, b, v, v1);
            setFilled(true);
            setColor(c);
        }

        /**
         * Moves ball by inputted unit in current direction (theta). Also updates current position.
         * @param a
         */
        public void move(double a) {
            movePolar(a, theta);
            x = Math.toIntExact(Math.round(getX()));
            y = Math.toIntExact(Math.round(getY()));
        }

        /**
         * returns true if Balls a and b collide.
         * @param a
         * @param b
         * @return
         */
        static boolean isColliding(Ball a, Ball b) {
            return (a.getX() - b.getX()) * (a.getX() - b.getX()) + (a.getY() - b.getY()) * (a.getY() - b.getY()) <= 4 * radius * radius;
        }
    }
}
