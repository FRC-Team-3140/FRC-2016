package lib;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.opencv.core.*;
import org.opencv.core.Point;
import org.opencv.imgproc.Imgproc;
import java.util.ArrayList;
import java.util.Iterator;
import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;
import edu.wpi.first.wpilibj.vision.USBCamera;

public class Vision {
	/*Setup
	 * Line 7 of build.properties
	 * added JRE under Preferences/Java/BuildPath/ClassPathVariables
	 * added JRE to Project Build path
	 */
    
	// used for the controlling the robot and the control flow of the vision program
	public static boolean trackgoalseq = false;
	private static boolean init = false;
	private static USBCamera Cam;
	private static Image frame;
    
	// used for the high goal tracking
    public static ArrayList<MatOfPoint> contours = new ArrayList<MatOfPoint>();
    public static final double HIGH_TARGET_HEIGHT = 2463.8; // mm from the floor to the middle of the top goal
    public static final double HIGH_TARGET_LENGTH = 508; // mm
    public static final double CAMERA_FOCAL_LENGTH = 2.8; // in millimeters
    public static final Scalar TOP_TARGET_LOWER = new Scalar(80, 0, 90);
    public static final Scalar TOP_TARGET_UPPER = new Scalar(95, 255, 190);
    public static Point targetcenter = null, imagecenter = null;
    public static int yoffcenter, xoffcenter;
    public static int recwidth;
    public static Vision instance;
    
    public static Vision getInstance() {
		if (instance == null) {
			instance = new Vision();
		}
		return instance;
	}
    
    public void CameraController() {
		int session = NIVision.IMAQdxOpenCamera("cam0", NIVision.IMAQdxCameraControlMode.CameraControlModeController);
		NIVision.IMAQdxConfigureGrab(session);
		NIVision.IMAQdxStartAcquisition(session);
		frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
		stream();
	}

	public static void initCameras() {
		if (init)
			return;
		
		Cam = new USBCamera("cam0");
		init = true;
	}

	public void stream() {
		if (!init) {
			initCameras();
			Cam.startCapture();
		}
		Cam.getImage(frame);

	}
	
    public static void toGoal() throws InterruptedException {
        System.out.println("Starting Vision...");
        
        
		while (true) {

			// step 1: Turn the turret directly towards the goal
			if (targetcenter.y > imagecenter.y) {
				SmartDashboard.putBoolean("Angle Up", false);
				SmartDashboard.putBoolean("Angle Down", true);

			} else if (targetcenter.y < imagecenter.y) {
				SmartDashboard.putBoolean("Angle Up", true);
				SmartDashboard.putBoolean("Angle Down", false);

			} else if (targetcenter.y == imagecenter.y)
				SmartDashboard.putBoolean("Angle Up", false);
				SmartDashboard.putBoolean("Angle Down", false);

			// step 2: Turn the robot directly towards the goal

			if (targetcenter.x > imagecenter.x) {
				SmartDashboard.putBoolean("Turn Left", true);
				SmartDashboard.putBoolean("Turn Right", false);

			} else if (targetcenter.x < imagecenter.x) {
				SmartDashboard.putBoolean("Turn Left", false);
				SmartDashboard.putBoolean("Turn Right", true);

			} else if (targetcenter.x == imagecenter.x) {
				SmartDashboard.putBoolean("Turn Left", false);
				SmartDashboard.putBoolean("Turn Right", false);

			}
			
			// step 3: Get the right distance away from the goal

			if (findDistanceToGoal(recwidth) < 4500) {
				SmartDashboard.putString("Drive", "Forward");
			}

			else if (findDistanceToGoal(recwidth) >= 4500 && findDistanceToGoal(recwidth) <= 5500) {
				SmartDashboard.putString("Drive", "In position");
			}

			else if (findDistanceToGoal(recwidth) > 5500) {
				SmartDashboard.putString("Drive", "Backward");
			} else {}
			Thread.sleep(100);
		}

	}

    public static void findHighGoal(Mat frame) {
        Mat hsl = new Mat();
        Mat hierarchy = new Mat();
        Imgproc.cvtColor(frame, hsl, Imgproc.COLOR_RGB2HLS);
		Core.inRange(hsl, new Scalar(87, 140, 117), new Scalar(180, 255, 255), hsl);
		Imgproc.findContours(hsl, contours, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);
        for (Iterator<MatOfPoint> iterator = contours.iterator(); iterator.hasNext();) {
            MatOfPoint matOfPoint = (MatOfPoint) iterator.next();
            Rect rec = Imgproc.boundingRect(matOfPoint);
            if (rec.height < 35 || rec.width < 35) {
                iterator.remove();
                continue;
            }
            float aspect = (float)rec.width / (float)rec.height;
            if (aspect < 1.0)
                iterator.remove();
        }

        if (contours.size() == 1) {
            Rect rec = Imgproc.boundingRect(contours.get(0));
            Core.rectangle(frame, rec.br(), rec.tl(), new Scalar(0, 255, 255));
            targetcenter = new Point(rec.x + rec.width / 2, rec.y + rec.height / 2);
            imagecenter = new Point(frame.width()/2, frame.height()/2);
            recwidth = rec.width;
           
        }
    }


    public static double findDistanceToGoal(int pw) {
        return (HIGH_TARGET_LENGTH * CAMERA_FOCAL_LENGTH) / pw;
    }

    public static void displayImage (){
    	CameraServer.getInstance().setQuality(50);
    	CameraServer.getInstance().setImage(frame);
                
    }
    
}



	


		    

		    
		  