package demo;

import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Scalar;
import org.bytedeco.opencv.opencv_imgcodecs.Imgcodecs;
import org.bytedeco.opencv.opencv_imgproc.Imgproc;
import static org.bytedeco.opencv.global.opencv_core.*;
import static org.bytedeco.opencv.global.opencv_imgproc.*;

public class RedCircleDetector {
    static {
        System.loadLibrary(NATIVE_LIBRARY_NAME);
    }

    public static boolean isRedCircle(String imagePath) {
        Mat image = Imgcodecs.imread(imagePath);
        if (image.empty()) {
            System.err.println("Image not found: " + imagePath);
            return false;
        }

        // Convert image to HSV color space
        Mat hsvImage = new Mat();
        Imgproc.cvtColor(image, hsvImage, COLOR_BGR2HSV);

        // Define the range for red color in HSV
        Scalar lowerRed1 = new Scalar(0, 50, 50, 0);
        Scalar upperRed1 = new Scalar(10, 255, 255, 0);
        Scalar lowerRed2 = new Scalar(160, 50, 50, 0);
        Scalar upperRed2 = new Scalar(180, 255, 255, 0);

        Mat mask1 = new Mat();
        Mat mask2 = new Mat();
        Mat redMask = new Mat();

        // Threshold the image to get only red colors
        Imgproc.inRange(hsvImage, lowerRed1, upperRed1, mask1);
        Imgproc.inRange(hsvImage, lowerRed2, upperRed2, mask2);
        add(mask1, mask2, redMask);

        // Detect circles in the image
        Mat circles = new Mat();
        Imgproc.HoughCircles(redMask, circles, CV_HOUGH_GRADIENT, 1, redMask.rows() / 8, 100, 30, 0, 0);

        // Check if any circles were detected
        return circles.cols() > 0;
    }
}
