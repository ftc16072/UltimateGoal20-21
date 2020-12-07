package org.firstinspires.ftc.teamcode.ftc16072.utils;

import com.acmerobotics.dashboard.config.Config;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

import java.nio.channels.ScatteringByteChannel;
import java.util.ArrayList;
import java.util.List;

@Config
public class StackPipeline extends OpenCvPipeline {
        public static int testRec_x = 140;
        public static int testRec_y = 90;
        public static int testRec_width = 125;
        public static int testRec_height = 90;

        /*
         * NOTE: if you wish to use additional Mat objects in your processing pipeline, it is
         * highly recommended to declare them here as instance variables and re-use them for
         * each invocation of processFrame(), rather than declaring them as new local variables
         * each time through processFrame(). This removes the danger of causing a memory leak
         * by forgetting to call mat.release(), and it also reduces memory pressure by not
         * constantly allocating and freeing large chunks of memory.
         */

        @Override
        public Mat processFrame(Mat input)
        {
            Mat gray = new Mat(input.rows(), input.cols(),input.type());
            Imgproc.cvtColor(input, gray, Imgproc.COLOR_BGR2GRAY);
            Mat binary = new Mat(input.rows(), input.cols(),input.type(), new Scalar(0));
            Imgproc.threshold(gray, binary, 0, 255, Imgproc.THRESH_BINARY_INV);



            List<MatOfPoint> contours = new ArrayList<>();

            Mat hierarchey = new Mat();

            Imgproc.findContours(binary, contours, hierarchey, Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE);

            Scalar color = new Scalar(0,255,0);

            //for testing
            Rect rect = new Rect(testRec_x, testRec_y, testRec_width, testRec_height);

            Imgproc.rectangle(input, rect, new Scalar(0, 0, 255), 2);

            Mat ring = input.submat(rect);
            Mat ringHSV = new Mat(ring.cols(), ring.rows(), ring.type());
            Imgproc.cvtColor(ring, ringHSV, Imgproc.COLOR_BGR2HSV);

            Scalar colors = Core.mean(ring);

            Imgproc.putText(input, "1:" + (int)colors.val[0], new Point(testRec_x, testRec_y - 40), Imgproc.FONT_HERSHEY_COMPLEX_SMALL,0.8, new Scalar(0, 255, 0));
            Imgproc.putText(input, "2:" + (int)colors.val[1], new Point(testRec_x, testRec_y - 22.5), Imgproc.FONT_HERSHEY_COMPLEX_SMALL,0.8, new Scalar(0, 255, 0));
            Imgproc.putText(input, "3:" + (int)colors.val[2], new Point(testRec_x, testRec_y - 10), Imgproc.FONT_HERSHEY_COMPLEX_SMALL,0.8, new Scalar(0, 255, 0));



            //Imgproc.drawContours(gray, contours, -1, color, 2, Imgproc.LINE_8, hierarchey, 2);


            return input;
        }
}
