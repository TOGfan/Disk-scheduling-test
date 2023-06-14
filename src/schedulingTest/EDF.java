package schedulingTest;
//add random element during simulation
public class EDF {
	public static int[][] arrangeArrival(int[][] items) {
		int[][] arranged = items.clone();
		int[] temp = new int[2];
		int minDL;
		for (int i = 0; i < arranged.length - 1; i++) {
			minDL=i;
			for (int j = i+1; j < arranged.length; j++) {
				if (arranged[j][2]<arranged[minDL][2]) {
					minDL=j;
				}
			}
			temp=arranged[i];
			arranged[i]=arranged[minDL];
			arranged[minDL]=temp;
		}
		return arranged;
	}
}
