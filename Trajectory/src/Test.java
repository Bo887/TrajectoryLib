import java.io.*;
public class Test{
	public static void main(String[] args) throws IOException{
		//start_vel, end_pos, end_vel, max_vel, accel, dt
		TrajectoryGenerator test = new TrajectoryGenerator();
		test.setConfig(12, 15, 0.005);
		//PrintWriter out = new PrintWriter("C:\\Users\\Eric\\Desktop\\generated_ff_values");	
		double setpoint = 10; //5 feet
		Segment[] traj = test.generateTraj(0,0,setpoint);
		for(int i=0;i<traj.length;i++){
			System.out.printf("Pos:%f   Vel:%f   Accel:%f   Time:%f\r\n",traj[i].pos,traj[i].vel,traj[i].accel,traj[i].time);
			//out.println();
		}
		//out.close();
	}	
}	
