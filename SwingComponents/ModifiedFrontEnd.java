import java.awt.*;
import javax.swing.*;

public class GridLayoutExample {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Movie Posters");
        Request request = new Request();
        request.get("http://localhost:8080/");
        String[][] resp = convertTo2DArray(request.reply_in_text());
        String[] posterNames = new String[resp.length];
        ImageIcon[] posters = new ImageIcon[resp.length];
        int count = 0;
        for (String[] i : resp) {
            posterNames[count] = i[0];
            posters[count++] = new ImageIcon("F:\\Movie_Database_Website\\Movie db\\"+i[4]+".jpg");
                    
        }
        
        // Create an array of posters (replace with your actual posters)
        

        // Create an array of poster names (replace with actual names)
        

        // Ensure arrays have the same length
        if (posters.length != posterNames.length) {
            throw new IllegalArgumentException("Poster and name arrays must have the same length");
        }

        // Resize posters (optional)
        for (int i = 0; i < posters.length; i++) {
            int newWidth = 200; // Adjust values as needed
            int newHeight = 300; // Adjust values as needed

            Image resizedImage = posters[i].getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
            posters[i] = new ImageIcon(resizedImage);
        }

        // Adjust panel size based on your needs
        int panelWidth = 250; // Adjust width as needed
        int panelHeight = 400; // Adjust height as needed

        // Create JPanel for each poster and its name
        JPanel[] panels = new JPanel[posters.length];
        for (int i = 0; i < posters.length; i++) {
            panels[i] = new JPanel();
            panels[i].setLayout(new BoxLayout(panels[i], BoxLayout.Y_AXIS)); // Vertical layout

            // no visible difference
            // panels[i].setPreferredSize(new Dimension(panelWidth, panelHeight)); // Set
            // fixed size
            // panels[i].setMinimumSize(new Dimension(panelWidth, panelHeight)); // Set
            // minimum size

            // Add border to JPanel (adjust color and thickness as needed)
            panels[i].setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));

            JLabel posterNameLabel = new JLabel(posterNames[i], SwingConstants.CENTER);
            posterNameLabel.setFont(new Font("SansSerif", Font.BOLD, 16)); // Adjust font size
            posterNameLabel.setVerticalAlignment(SwingConstants.TOP); // Align text to top

            // Optional: Set maximum width for the label to control potential wrapping
            int maxLabelWidth = panelWidth - 20; // Account for potential padding
            posterNameLabel.setPreferredSize(new Dimension(maxLabelWidth, Integer.MAX_VALUE));

            panels[i].add(new JLabel(posters[i])); // Add poster image
            panels[i].add(posterNameLabel); // Add poster name, centered

            // Tooltip for full title on hover (optional)
            MovieDetailsListener listener = new MovieDetailsListener(posterNames[i], "Disco");
            panels[i].addMouseListener(listener);
            panels[i].setToolTipText(posterNames[i]);
        }

        // Set grid layout for the frame
        frame.setLayout(new GridLayout(1, 4, 0, 0)); // Adjust rows/columns as needed

        // Add panels to the frame
        for (JPanel panel : panels) {
            frame.add(panel);
        }

        JScrollPane scrollPane = new JScrollPane(frame.getContentPane());
        frame.setContentPane(scrollPane);
        // frame.add(panels[0]);

        // Set frame to fullscreen by default
        // frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setSize(800, 600);

        // Exit on window closing
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);
    }

    public static String[][] convertTo2DArray(String data) {
        // Remove square brackets and split the string by "], [" to get individual movie
        // entries
        String[] movies = data.substring(2, data.length() - 2).split("\\], \\[");

        // Initialize a 2D array to store movie details
        String[][] movieArray = new String[movies.length][];

        // Loop through each movie entry and extract its details
        for (int i = 0; i < movies.length; i++) {
            // Split the movie entry by ", " to get movie details
            String[] details = movies[i].split(", ");

            // Store the movie details in the 2D array
            movieArray[i] = details;
        }

        return movieArray;
    }
}
