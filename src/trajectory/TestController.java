package trajectory;
import trajectory.*;
import java.io.*;
public class TestController{

	public static void main(String[] args) throws IOException{	
		Segment[] leftTraj;
		Segment[] rightTraj;
	
		TrajectoryGenerator leftGenerator = new TrajectoryGenerator();
		TrajectoryGenerator rightGenerator = new TrajectoryGenerator();	
	
		TrajectoryFollower leftFollower = new TrajectoryFollower();
		TrajectoryFollower rightFollower = new TrajectoryFollower();

		leftGenerator.setConfig(12,15,0.005);
		rightGenerator.setConfig(12,15,0.005);
		leftTraj = leftGenerator.generateTraj(0,0,20);
		rightTraj = rightGenerator.generateTraj(0,0,20);
		leftFollower.setTrajectory(leftTraj);
		rightFollower.setTrajectory(rightTraj);		
		double max_v = (1.0)*1/12;
		double dt = 1.0/100.0;
		rightFollower.setGains(max_v,0,1,0,0);
		leftFollower.setLoopTime(dt);
		rightFollower.setLoopTime(dt);
			
		leftFollower.init();
		rightFollower.init();
				
		double curr_pos =0;
		rightFollower.calcMotorOutput(curr_pos);
		
	}
		
}
