import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class RockPaperScissors {

    private static JFrame frame;
    private static JLabel imageLabel;
    private static String lastChoice = "";
    private static int repeatCount = 0;

    public static void main(String[] args) {
        frame = new JFrame("Pick an Image");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        imageLabel = new JLabel();
        panel.add(imageLabel);

        updateImage();

        imageLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                randomizeImageWithAnimation();
            }
        });

        frame.add(panel);
        frame.setVisible(true);
    }

    private static void updateImage() {

        String[] choices = {"rock", "paper", "scissors"};
        Random rand = new Random();
        String randomChoice = choices[rand.nextInt(3)];

        ImageIcon newIcon = new ImageIcon(RockPaperScissors.class.getResource("/images/" + randomChoice + ".png"));
        Image img = newIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        newIcon = new ImageIcon(img);

        imageLabel.setIcon(newIcon);
        imageLabel.setText("");
    }

    private static void randomizeImageWithAnimation() {
        String[] choices = {"rock", "paper", "scissors"};
        Random rand = new Random();

        Timer timer = new Timer(100, new ActionListener() {
            int cycleCount = 0;
            @Override
            public void actionPerformed(ActionEvent e) {

                String randomChoice = choices[rand.nextInt(3)];

                ImageIcon newIcon = new ImageIcon(RockPaperScissors.class.getResource("/images/" + randomChoice + ".png"));
                Image img = newIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
                newIcon = new ImageIcon(img);

                imageLabel.setIcon(newIcon);
                imageLabel.setText("");

                cycleCount++;
                if (cycleCount >= 10) {
                    ((Timer) e.getSource()).stop();
                    updateFinalImage(rand);
                }
            }
        });

        timer.start();
    }

    private static void updateFinalImage(Random rand) {
        String[] choices = {"rock", "paper", "scissors"};
        String randomChoice = choices[rand.nextInt(3)];
        if(randomChoice.equals(lastChoice)){
            repeatCount++;
        }else{
            repeatCount = 1;
        }
        lastChoice = randomChoice;

        ImageIcon newIcon = new ImageIcon(RockPaperScissors.class.getResource("/images/" + randomChoice + ".png"));
        Image img = newIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        newIcon = new ImageIcon(img);

        imageLabel.setIcon(newIcon);
        String displayText = randomChoice.substring(0,1).toUpperCase() + randomChoice.substring(1);
        if(repeatCount > 1){
            displayText += " x" + repeatCount;
        }
        imageLabel.setText(displayText);
        imageLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        imageLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
    }
}
