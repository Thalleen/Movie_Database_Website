import java.awt.*;
import javax.swing.*;

public class MovieDetailsFrame extends JFrame {

    public MovieDetailsFrame(String title, String description) {
        super(title + " Details"); // Set frame title with movie title

        // Layout and components for displaying details
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BorderLayout()); // Adjust layout as needed

        JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 18)); // Adjust font size
        detailsPanel.add(titleLabel, BorderLayout.NORTH);

        JTextArea descriptionArea = new JTextArea(description);
        descriptionArea.setEditable(false); // Prevent user editing
        descriptionArea.setLineWrap(true); // Wrap text to fit
        descriptionArea.setWrapStyleWord(true); // Wrap on word breaks
        JScrollPane scrollPane = new JScrollPane(descriptionArea);
        detailsPanel.add(scrollPane, BorderLayout.CENTER);

        // Optional: Add image, cast, director, etc. components

        // Add details panel to the frame
        this.getContentPane().add(detailsPanel);
        
        // Set frame properties (adjust size as needed)
        //this.pack();
        this.setSize(800, 600);
        this.setLocationRelativeTo(null); // Center the frame on screen
    }
}
