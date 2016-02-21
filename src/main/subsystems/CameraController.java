package main.subsystems;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.vision.USBCamera;
import main.commands.StreamCamera;

/**
 *
 */
public class CameraController extends Subsystem {
	//private static CameraController instance;
    private static CameraServer server = CameraServer.getInstance();
    private static USBCamera bowCam, sternCam, targetCam;
	private static boolean init = false;
    public enum Camera {FRONT, BACK, TARGET};
	
	private Camera cur;
	private Image frame;
	private int quality;
	
	public CameraController(final int quality) {
		setQuality(quality);
		frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
		//bowCam = new USBCamera("cam0");
		//sternCam = new USBCamera("cam1");
		stream();
	}
	
	public void setQuality(int quality) {
	    if (quality < 0) 
	    	quality = 0;
	    else if (quality > 100) 
	    	quality = 100;

	    this.quality = quality;
	    server.setQuality(quality);
	  }
	
	 public int getQuality() {
		    return quality;
		  }
	 
	  public static void initCameras() {
		    if (init) 
		    	return;

		    bowCam = new USBCamera("cam0");
		    sternCam = new USBCamera("cam1");
		    //targetCam = new USBCamera("cam2");
		    
		    init = true;
		  }
	  
	  public void stream() {
		    if (!init) {
		      initCameras();
		      startStream();
		    }

		    System.out.println("stream");

		    switch (cur) {
		      case FRONT:
		        bowCam.getImage(frame);
		        break;
		      case BACK:
		        sternCam.getImage(frame);
		        break;
		      case TARGET:
		        throw new IllegalStateException("Pi camera not implemented");
		    }
		    server.setImage(frame);

		    //System.out.println("done stream");
		  }
	  
	  public void startStream() {
		    startStream(Camera.FRONT);
		  }
	  
	  public void startStream(Camera camera) {
		    cur = camera;
		    System.out.println("Start stream");
		    switch (camera) {
		      case FRONT:
			    sternCam.stopCapture();
		        bowCam.startCapture();
//		        target.stopCapture();
		        break;
		      case BACK:
		        bowCam.stopCapture();
		        sternCam.startCapture();
//		        target.stopCapture();
		        break;
		      case TARGET:
		        throw new IllegalStateException("Shooter camera not implemented");
		    }
		  }
	  
	  public Camera getCurCamera() {
		  return cur;
	  }
	

    public void initDefaultCommand() {
    	setDefaultCommand(new StreamCamera());
    }
}

