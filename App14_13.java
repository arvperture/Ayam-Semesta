import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class App14_13 extends Application {
    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();

        double[] degrees = {72, 36, 108, 144};
        Color[] colors = {Color.RED, Color.BLUE, Color.GREEN, Color.ORANGE};
        String[] labels = {"Project -- 20%", "Quiz -- 10%", "Midterm -- 30%", "Final -- 40%"};

        double currentAngle = 0;
        double centerX = 150;
        double centerY = 150;
        double radius = 100;

        for (int i = 0; i < degrees.length; i++) {
            Arc arc = new Arc(centerX, centerY, radius, radius, currentAngle, degrees[i]);
            arc.setFill(colors[i]);
            arc.setType(ArcType.ROUND);
            double x = centerX + radius * Math.cos(Math.toRadians(currentAngle + degrees[i] / 2));
            double y = centerY - radius * Math.sin(Math.toRadians(currentAngle + degrees[i] / 2));
            Text text = new Text(x, y, labels[i]);
            pane.getChildren().addAll(arc, text);
            currentAngle += degrees[i];
        }

        Scene scene = new Scene(pane, 350, 300);
        primaryStage.setTitle("Exercise14_13 : Diagram Lingkaran");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
