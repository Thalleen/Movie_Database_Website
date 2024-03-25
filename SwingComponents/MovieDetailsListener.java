import java.awt.event.*;

public class MovieDetailsListener implements MouseListener {
    private String movieTitle;
    private String movieDescription; // Optional: Store movie description

    public MovieDetailsListener(String title, String description) {
        this.movieTitle = title;
        this.movieDescription = description;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // Create a new MovieDetailsFrame with movie information
        MovieDetailsFrame detailsFrame = new MovieDetailsFrame(movieTitle, movieDescription);
        detailsFrame.setVisible(true);
    }

    // Implement other MouseListener methods if needed (optional)
    @Override
    public void mousePressed(MouseEvent e) { }

    @Override
    public void mouseReleased(MouseEvent e) { }

    @Override
    public void mouseEntered(MouseEvent e) { }

    @Override
    public void mouseExited(MouseEvent e) { }
}
