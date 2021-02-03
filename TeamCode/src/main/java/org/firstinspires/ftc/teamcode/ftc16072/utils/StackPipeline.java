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
        private final int TEST_RECT_X = 240;
        private final int TEST_RECT_Y = 146;
        private final int TEST_RECT_WIDTH = 20;
        private final int TEST_RECT_HEIGHT = 60;
        private final double FOUR_STACK_HUE_THRESHOLD = 70;
        private final double ONESTACK_HUE_THRESHOLD = 58;

        public ringNumber analysis;

        public enum ringNumber{
            ZERO,
            ONE,
            FOUR
        }


    @Override
        public Mat processFrame(Mat input)
        {
            Rect rect = new Rect(TEST_RECT_X, TEST_RECT_Y, TEST_RECT_WIDTH, TEST_RECT_HEIGHT);

            Imgproc.rectangle(input, rect, new Scalar(0, 0, 255), 2);

            Mat ring = input.submat(rect);
            Mat ringHSV = new Mat(ring.cols(), ring.rows(), ring.type());
            Imgproc.cvtColor(ring, ringHSV, Imgproc.COLOR_BGR2HSV);

            double avgHueValue = Core.mean(ringHSV).val[0];

            ring.release();
            ringHSV.release();

            Imgproc.putText(input, "H:" + (int)avgHueValue, new Point(TEST_RECT_X, TEST_RECT_Y - 40), Imgproc.FONT_HERSHEY_COMPLEX_SMALL,0.8, new Scalar(255, 255, 255));

            if(avgHueValue > FOUR_STACK_HUE_THRESHOLD ){
                analysis = ringNumber.FOUR;
            } else if (avgHueValue > ONESTACK_HUE_THRESHOLD){
                analysis = ringNumber.ONE;
            } else {
                analysis = ringNumber.ZERO;
            }

            return input;
        }

}
