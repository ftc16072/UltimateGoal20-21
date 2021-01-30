package org.firstinspires.ftc.teamcode.ftc16072.utils;

import com.acmerobotics.dashboard.config.Config;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

@Config
public class StackPipeline extends OpenCvPipeline {
        public static int testRec_x = 240;
        public static int testRec_y = 146;
        private static int testRec_width = 20;
        private static int testRec_height = 60;
        public ringNumber analysis;

        public enum ringNumber{
            ZERO,
            ONE,
            FOUR
        }

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
            Rect rect = new Rect(testRec_x, testRec_y, testRec_width, testRec_height);

            Imgproc.rectangle(input, rect, new Scalar(0, 0, 255), 2);

            Mat ring = input.submat(rect);
            Mat ringHSV = new Mat(ring.cols(), ring.rows(), ring.type());
            Imgproc.cvtColor(ring, ringHSV, Imgproc.COLOR_BGR2HSV);

            Scalar colors = Core.mean(ringHSV);

            Imgproc.putText(input, "1:" + (int)colors.val[0], new Point(testRec_x, testRec_y - 40), Imgproc.FONT_HERSHEY_COMPLEX_SMALL,0.8, new Scalar(255, 255, 255));
            Imgproc.putText(input, "2:" + (int)colors.val[1], new Point(testRec_x, testRec_y - 22.5), Imgproc.FONT_HERSHEY_COMPLEX_SMALL,0.8, new Scalar(255, 255, 255));
            Imgproc.putText(input, "3:" + (int)colors.val[2], new Point(testRec_x, testRec_y - 10), Imgproc.FONT_HERSHEY_COMPLEX_SMALL,0.8, new Scalar(255, 255, 255));



            //Imgproc.drawContours(gray, contours, -

            if(colors.val[0] > 70 ){
                analysis = ringNumber.FOUR;
            } else if (colors.val[0] > 58){
                analysis = ringNumber.ONE;
            } else {
                analysis = ringNumber.ZERO;
            }

            return input;
        }

}
